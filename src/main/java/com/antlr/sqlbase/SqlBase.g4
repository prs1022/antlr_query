/*
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

grammar SqlBase;

import CommonLexer;

tokens {
    DELIMITER
}

singleStatement
    : statement EOF
    ;

singleExpression
    : expression EOF
    ;

statement
    : query                                                            #statementDefault
    | USE schema=identifier                                            #use
    | USE catalog=identifier '.' schema=identifier                     #use
    | CREATE TABLE qualifiedName
        (WITH tableProperties)? AS query
        (WITH (NO)? DATA)?                                             #createTableAsSelect
    | CREATE TABLE (IF NOT EXISTS)? qualifiedName
        '(' tableElement (',' tableElement)* ')'
        (WITH tableProperties)?                                        #createTable
    | DROP TABLE (IF EXISTS)? qualifiedName                            #dropTable
    | INSERT (INTO|OVERWRITE) TABLE? qualifiedName columnAliases? query                   #insertInto
    | DELETE FROM qualifiedName (WHERE booleanExpression)?             #delete
    | ALTER TABLE from=qualifiedName RENAME TO to=qualifiedName        #renameTable
    | ALTER TABLE tableName=qualifiedName
        RENAME COLUMN from=identifier TO to=identifier                 #renameColumn
    | ALTER TABLE tableName=qualifiedName
        ADD COLUMN column=tableElement                                 #addColumn
    | CREATE (OR REPLACE)? VIEW qualifiedName AS query                 #createView
    | DROP VIEW (IF EXISTS)? qualifiedName                             #dropView
    | EXPLAIN ('(' explainOption (',' explainOption)* ')')? statement  #explain
    | SHOW TABLES ((FROM | IN) qualifiedName)? (LIKE pattern=STRING)?  #showTables
    | SHOW SCHEMAS ((FROM | IN) identifier)?                           #showSchemas
    | SHOW CATALOGS                                                    #showCatalogs
    | SHOW COLUMNS (FROM | IN) qualifiedName                           #showColumns
    | DESCRIBE qualifiedName                                           #showColumns
    | DESC qualifiedName                                               #showColumns
    | SHOW FUNCTIONS                                                   #showFunctions
    | SHOW SESSION                                                     #showSession
    | SET SESSION qualifiedName EQ expression                          #setSession
    | RESET SESSION qualifiedName                                      #resetSession
    | SHOW PARTITIONS (FROM | IN) qualifiedName
        (WHERE booleanExpression)?
        (ORDER BY sortItem (',' sortItem)*)?
        (LIMIT limit=(INTEGER_VALUE | ALL))?                           #showPartitions
    ;

query
    :  with? queryNoWith
    ;

with
    : WITH RECURSIVE? namedQuery (',' namedQuery)*
    ;

tableElement
    : identifier type
    ;

tableProperties
    : '(' tableProperty (',' tableProperty)* ')'
    ;

tableProperty
    : identifier EQ expression
    ;

queryNoWith:
      queryTerm
      (ORDER BY sortItem (',' sortItem)*)?
      (LIMIT limit=(INTEGER_VALUE | ALL))?
      (APPROXIMATE AT confidence=number CONFIDENCE)?
    ;

queryTerm
    : queryPrimary                                                             #queryTermDefault
    | left=queryTerm operator=INTERSECT setQuantifier? right=queryTerm         #setOperation
    | left=queryTerm operator=(UNION | EXCEPT) setQuantifier? right=queryTerm  #setOperation
    ;

queryPrimary
    : querySpecification                   #queryPrimaryDefault
    | TABLE qualifiedName                  #table
    | VALUES expression (',' expression)*  #inlineTable
    | '(' queryNoWith  ')'                 #subquery
    ;

sortItem
    : expression ordering=(ASC | DESC)? (NULLS nullOrdering=(FIRST | LAST))?
    ;

querySpecification
    : SELECT setQuantifier? selectItem (',' selectItem)*
      (FROM relation (',' relation)*)?
      (WHERE where=booleanExpression)?
      (GROUP BY groupingElement (',' groupingElement)*)?
      (HAVING having=booleanExpression)?
    ;

groupingElement
    : groupingExpressions                                               #singleGroupingSet
    | ROLLUP '(' (qualifiedName (',' qualifiedName)*)? ')'              #rollup
    | CUBE '(' (qualifiedName (',' qualifiedName)*)? ')'                #cube
    | GROUPING SETS '(' groupingSet (',' groupingSet)* ')'              #multipleGroupingSets
    ;

groupingExpressions
    : '(' (expression (',' expression)*)? ')'
    | expression
    ;

groupingSet
    : '(' (qualifiedName (',' qualifiedName)*)? ')'
    | qualifiedName
    ;

namedQuery
    : name=identifier (columnAliases)? AS '(' query ')'
    ;

setQuantifier
    : DISTINCT
    | ALL
    ;

selectItem
    : expression (AS? identifier)?  #selectSingle
    | qualifiedName '.' ASTERISK    #selectAll
    | ASTERISK                      #selectAll
    ;

relation
    : left=relation
      ( CROSS JOIN right=sampledRelation
      | joinType JOIN rightRelation=relation joinCriteria
      | NATURAL joinType JOIN right=sampledRelation
      )                                           #joinRelation
    | sampledRelation                             #relationDefault
    ;

joinType
    : INNER?
    | LEFT OUTER?
    | RIGHT OUTER?
    | FULL OUTER?
    ;

joinCriteria
    : ON booleanExpression
    | USING '(' identifier (',' identifier)* ')'
    ;

sampledRelation
    : aliasedRelation (
        TABLESAMPLE sampleType '(' percentage=expression ')'
        RESCALED?
        (STRATIFY ON '(' stratify+=expression (',' stratify+=expression)* ')')?
      )?
    ;

sampleType
    : BERNOULLI
    | SYSTEM
    | POISSONIZED
    ;

aliasedRelation
    : relationPrimary (AS? identifier columnAliases?)?
    ;

columnAliases
    : '(' identifier (',' identifier)* ')'
    ;

relationPrimary
    : qualifiedName                                                   #tableName
    | '(' query ')'                                                   #subqueryRelation
    | UNNEST '(' expression (',' expression)* ')' (WITH ORDINALITY)?  #unnest
    | '(' relation ')'                                                #parenthesizedRelation
    ;

expression
    : booleanExpression
    ;

booleanExpression
    : predicated                                                   #booleanDefault
    | NOT booleanExpression                                        #logicalNot
    | left=booleanExpression operator=AND right=booleanExpression  #logicalBinary
    | left=booleanExpression operator=OR right=booleanExpression   #logicalBinary
    | EXISTS '(' query ')'                                         #exists
    ;

// workaround for:
//  https://github.com/antlr/antlr4/issues/780
//  https://github.com/antlr/antlr4/issues/781
predicated
    : valueExpression predicate[$valueExpression.ctx]?
    ;

predicate[ParserRuleContext value]
    : comparisonOperator right=valueExpression                            #comparison
    | NOT? BETWEEN lower=valueExpression AND upper=valueExpression        #between
    | NOT? IN '(' expression (',' expression)* ')'                        #inList
    | NOT? IN '(' query ')'                                               #inSubquery
    | NOT? LIKE pattern=valueExpression (ESCAPE escape=valueExpression)?  #like
    | IS NOT? NULL                                                        #nullPredicate
    | IS NOT? DISTINCT FROM right=valueExpression                         #distinctFrom
    ;

valueExpression
    : primaryExpression                                                                 #valueExpressionDefault
    | valueExpression AT timeZoneSpecifier                                              #atTimeZone
    | operator=(MINUS | PLUS) valueExpression                                           #arithmeticUnary
    | left=valueExpression operator=(ASTERISK | SLASH | PERCENT) right=valueExpression  #arithmeticBinary
    | left=valueExpression operator=(PLUS | MINUS) right=valueExpression                #arithmeticBinary
    | left=valueExpression CONCAT right=valueExpression                                 #concatenation
    ;

primaryExpression
    : NULL                                                                           #nullLiteral
    | interval                                                                       #intervalLiteral
    | identifier STRING                                                              #typeConstructor
    | number                                                                         #numericLiteral
    | booleanValue                                                                   #booleanLiteral
    | STRING                                                                         #stringLiteral
    | POSITION '(' valueExpression IN valueExpression ')'                            #position
    | '(' expression (',' expression)+ ')'                                           #rowConstructor
    | ROW '(' expression (',' expression)* ')'                                       #rowConstructor
    | qualifiedName '(' ASTERISK ')' over?                                           #functionCall
    | qualifiedName '(' (setQuantifier? expression (',' expression)*)? ')' over?     #functionCall
    | identifier '->' expression                                                     #lambda
    | '(' identifier (',' identifier)* ')' '->' expression                           #lambda
    | '(' query ')'                                                                  #subqueryExpression
    | CASE valueExpression whenClause+ (ELSE elseExpression=expression)? END         #simpleCase
    | CASE whenClause+ (ELSE elseExpression=expression)? END                         #searchedCase
    | CAST '(' expression AS type ')'                                                #cast
    | TRY_CAST '(' expression AS type ')'                                            #cast
    | ARRAY '[' (expression (',' expression)*)? ']'                                  #arrayConstructor
    | value=primaryExpression '[' index=valueExpression ']'                          #subscript
    | identifier                                                                     #columnReference
    | base=primaryExpression '.' fieldName=identifier                                #dereference
    | name=CURRENT_DATE                                                              #specialDateTimeFunction
    | name=CURRENT_TIME ('(' precision=INTEGER_VALUE ')')?                           #specialDateTimeFunction
    | name=CURRENT_TIMESTAMP ('(' precision=INTEGER_VALUE ')')?                      #specialDateTimeFunction
    | name=LOCALTIME ('(' precision=INTEGER_VALUE ')')?                              #specialDateTimeFunction
    | name=LOCALTIMESTAMP ('(' precision=INTEGER_VALUE ')')?                         #specialDateTimeFunction
    | SUBSTRING '(' valueExpression FROM valueExpression (FOR valueExpression)? ')'  #substring
    | NORMALIZE '(' valueExpression (',' normalForm)? ')'                            #normalize
    | EXTRACT '(' identifier FROM valueExpression ')'                                #extract
    | '(' expression ')'                                                             #parenthesizedExpression
    ;

timeZoneSpecifier
    : TIME ZONE interval  #timeZoneInterval
    | TIME ZONE STRING    #timeZoneString
    ;

comparisonOperator
    : EQ | NEQ | LT | LTE | GT | GTE
    ;

booleanValue
    : TRUE | FALSE
    ;

interval
    : INTERVAL sign=(PLUS | MINUS)? STRING from=intervalField (TO to=intervalField)?
    ;

intervalField
    : YEAR | MONTH | DAY | HOUR | MINUTE | SECOND
    ;

type
    : type ARRAY
    | ARRAY '<' type '>'
    | MAP '<' type ',' type '>'
    | simpleType
    ;

simpleType
    : TIME_WITH_TIME_ZONE
    | TIMESTAMP_WITH_TIME_ZONE
    | identifier
    ;

whenClause
    : WHEN condition=expression THEN result=expression
    ;

over
    : OVER '('
        (PARTITION BY partition+=expression (',' partition+=expression)*)?
        (ORDER BY sortItem (',' sortItem)*)?
        windowFrame?
      ')'
    ;

windowFrame
    : frameType=RANGE start=frameBound
    | frameType=ROWS start=frameBound
    | frameType=RANGE BETWEEN start=frameBound AND end=frameBound
    | frameType=ROWS BETWEEN start=frameBound AND end=frameBound
    ;

frameBound
    : UNBOUNDED boundType=PRECEDING                 #unboundedFrame
    | UNBOUNDED boundType=FOLLOWING                 #unboundedFrame
    | CURRENT ROW                                   #currentRowBound
    | expression boundType=(PRECEDING | FOLLOWING)  #boundedFrame // expression should be unsignedLiteral
    ;


explainOption
    : FORMAT value=(TEXT | GRAPHVIZ)         #explainFormat
    | TYPE value=(LOGICAL | DISTRIBUTED)     #explainType
    ;

qualifiedName
    : identifier ('.' identifier)*
    ;

identifier
    : IDENTIFIER             #unquotedIdentifier
    | quotedIdentifier       #quotedIdentifierAlternative
    | nonReserved            #unquotedIdentifier
    | BACKQUOTED_IDENTIFIER  #backQuotedIdentifier
    | DIGIT_IDENTIFIER       #digitIdentifier
    ;

quotedIdentifier
    : QUOTED_IDENTIFIER
    ;

number
    : DECIMAL_VALUE  #decimalLiteral
    | INTEGER_VALUE  #integerLiteral
    ;

nonReserved
    : SHOW | TABLES | COLUMNS | COLUMN | PARTITIONS | FUNCTIONS | SCHEMAS | CATALOGS | SESSION
    | ADD
    | OVER | PARTITION | RANGE | ROWS | PRECEDING | FOLLOWING | CURRENT | ROW | MAP
    | DATE | TIME | TIMESTAMP | INTERVAL | ZONE
    | YEAR | MONTH | DAY | HOUR | MINUTE | SECOND
    | EXPLAIN | FORMAT | TYPE | TEXT | GRAPHVIZ | LOGICAL | DISTRIBUTED
    | TABLESAMPLE | SYSTEM | BERNOULLI | POISSONIZED | USE | TO
    | RESCALED | APPROXIMATE | AT | CONFIDENCE
    | SET | RESET
    | VIEW | REPLACE
    | IF | NULLIF | COALESCE
    | normalForm
    | POSITION
    | NO | DATA
    ;

normalForm
    : NFD | NFC | NFKD | NFKC
    ;

SELECT: S E L E C T;
FROM: F R O M;
ADD: A D D;
AS: A S;
ALL: A L L;
SOME: S O M E;
ANY: A N Y;
DISTINCT: D I S T I N C T;
WHERE: W H E R E;
GROUP: G R O U P;
BY: B Y;
GROUPING: G R O U P I N G;
SETS: S E T S;
CUBE: C U B E;
ROLLUP: R O L L U P;
ORDER: O R D E R;
HAVING: H A V I N G;
LIMIT: L I M I T;
APPROXIMATE: A P P R O X I M A T E;
AT: A T;
CONFIDENCE: C O N F I D E N C E;
OR: O R;
AND:A N D;
IN: I N;
NOT: N O T;
NO: N O;
EXISTS: E X I S T S;
BETWEEN: B E T W E E N;
LIKE: L I K E;
IS: I S;
NULL: N U L L;
TRUE: T R U E;
FALSE: F A L S E;
NULLS: N U L L S;
FIRST: F I R S T;
LAST: L A S T;
ESCAPE: E S C A P E;
ASC: A S C;
DESC: D E S C;
SUBSTRING: S U B S T R I N G;
POSITION: P O S I T I O N;
FOR: F O R;
DATE: D A T E;
TIME: T I M E;
TIMESTAMP: T I M E S T A M P;
INTERVAL: I N T E R V A L;
YEAR: Y E A R;
MONTH: M O N T H;
DAY: D A Y;
HOUR: H O U R;
MINUTE: M I N U T E;
SECOND: S E C O N D;
ZONE: Z O N E;
CURRENT_DATE: C U R R E N T '_' D A T E;
CURRENT_TIME: C U R R E N T '_' T I M E;
CURRENT_TIMESTAMP: C U R R E N T '_' T I M E S T A M P;
LOCALTIME: L O C A L T I M E;
LOCALTIMESTAMP: L O C A L T I M E S T A M P;
EXTRACT: E X T R A C T;
CASE: C A S E;
WHEN: W H E N;
THEN: T H E N;
ELSE: E L S E;
END: E N D;
JOIN: J O I N;
CROSS: C R O S S;
OUTER: O U T E R;
INNER: I N N E R;
LEFT: L E F T;
RIGHT: R I G H T;
FULL: F U L L;
NATURAL: N AT U R A L;
USING: U S I N G;
ON: O N;
OVER:  O V E  R ;
PARTITION:  P A R T I T I O N ;
RANGE: R A N G E;
ROWS: R O W S ;
UNBOUNDED: U N B O U N D E D;
PRECEDING: P R E C E D I N G;
FOLLOWING: F O L L O W I N G;
CURRENT: C U R R E N T;
ROW: R O W ;
WITH: W I T H;
RECURSIVE: R E C U R S I V E;
VALUES: V A L U E S;
CREATE: C R E A T E;
TABLE: T A B L E;
VIEW: V I E W;
REPLACE: R E P L A C E;
INSERT: I N S E R T;
DELETE: D E L E T E;
INTO: I N T O;
OVERWRITE: O V E R W R I T E;
CONSTRAINT: C O N S T R A I N T;
DESCRIBE: D E S C R I B E;
EXPLAIN: E X P L A I N;
FORMAT: F O R M A T;
TYPE: T Y P E;
TEXT: T E X T;
GRAPHVIZ: G R A P H V I Z;
LOGICAL: L O G I C A L;
DISTRIBUTED: D I S T R I B U T E D;
CAST: C A S T;
TRY_CAST: T R Y '_' C A S T;
SHOW: S H O W;
TABLES: T A B L E S;
SCHEMAS: S C H E M A S;
CATALOGS: C A T A L O G S;
COLUMNS: C O L U M N S;
COLUMN: C O L U M N;
USE: U S E;
PARTITIONS: P A R T I T I O N S;
FUNCTIONS: F U N C T I O N S;
DROP: D R O P;
UNION: U N I O N;
EXCEPT: E X C E P T;
INTERSECT: I N T E R S E C T;
TO: T O;
SYSTEM: S Y S T E M;
BERNOULLI: B E R N O U L L I;
POISSONIZED: P O I S S O N I Z E D;
TABLESAMPLE: T A B L E S A M P L E;
RESCALED: R E S C A L E D;
STRATIFY: S T R A T I F Y;
ALTER: A L T E R;
RENAME: R E N A M E;
UNNEST: U N N E S T;
ORDINALITY: O R D I N A L I T Y;
ARRAY: A R R A Y;
MAP: M A P;
SET: S E T;
RESET: R E S E T;
SESSION: S E S S I O N;
DATA: D A T A;

NORMALIZE: N O R M A L I Z E;
NFD : N F D;
NFC : N F C;
NFKD : N F K D;
NFKC : N F K C;

IF: I F;
NULLIF: N U L L I F;
COALESCE: C O A L E S C E;

EQ  : '=';
NEQ : '<>' | '!=';
LT  : '<';
LTE : '<=';
GT  : '>';
GTE : '>=';

PLUS: '+';
MINUS: '-';
ASTERISK: '*';
SLASH: '/';
PERCENT: '%';
CONCAT: '||';

STRING
    : '\'' ( ~'\'' | '\'\'' )* '\''
    ;

INTEGER_VALUE
    : DIGIT+
    ;

DECIMAL_VALUE
    : DIGIT+ '.' DIGIT*
    | '.' DIGIT+
    | DIGIT+ ('.' DIGIT*)? EXPONENT
    | '.' DIGIT+ EXPONENT
    ;

IDENTIFIER
    : (LETTER | '_') (LETTER | DIGIT | '_' | '@' | ':')*
    ;

DIGIT_IDENTIFIER
    : DIGIT (LETTER | DIGIT | '_' | '@' | ':')+
    ;

QUOTED_IDENTIFIER
    : '"' ( ~'"' | '""' )* '"'
    ;

BACKQUOTED_IDENTIFIER
    : '`' ( ~'`' | '``' )* '`'
    ;

TIME_WITH_TIME_ZONE
    : T I M E WS W I T H WS T I M E WS Z O N E
    ;

TIMESTAMP_WITH_TIME_ZONE
    : T I M E S T A M P WS W I T H WS T I M E WS Z O N E
    ;

fragment EXPONENT
    : E [+-]? DIGIT+
    ;

SIMPLE_COMMENT
    : '--' ~[\r\n]* '\r'? '\n'? -> channel(HIDDEN)
    ;

BRACKETED_COMMENT
    : '/*' .*? '*/' -> channel(HIDDEN)
    ;

WS
    : [ \r\n\t]+ -> channel(HIDDEN)
    ;

// Catch-all for anything we can't recognize.
// We use this to be able to ignore and recover all the text
// when splitting statements with DelimiterLexer
UNRECOGNIZED
    : .
    ;
