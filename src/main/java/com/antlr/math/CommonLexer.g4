lexer grammar CommonLexer;

fragment A : 'A'|'a';
fragment B : 'B'|'b';
fragment C : 'C'|'c';
fragment D : 'D'|'d';
fragment E : 'E'|'e';
fragment F : 'F'|'f';
fragment G : 'G'|'g';
fragment H : 'H'|'h';
fragment I : 'I'|'i';
fragment J : 'J'|'j';
fragment K : 'K'|'k';
fragment L : 'L'|'l';
fragment M : 'M'|'m';
fragment N : 'N'|'n';
fragment O : 'O'|'o';
fragment P : 'P'|'p';
fragment Q : 'Q'|'q';
fragment R : 'R'|'r';
fragment S : 'S'|'s';
fragment T : 'T'|'t';
fragment U : 'U'|'u';
fragment V : 'V'|'v';
fragment W : 'W'|'w';
fragment X : 'x'|'x';
fragment Y : 'Y'|'y';
fragment Z : 'Z'|'z';

fragment HANZI:
    [\u4e00-\u9fa5]
;

fragment DIGIT
    : [0-9]
    ;

fragment LETTER
    : [A-Za-z]
    ;