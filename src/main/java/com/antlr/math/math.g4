grammar math;

import CommonLexer;

NUMBER:DIGIT+;

WS  : [ \r\n\t]+ -> channel(HIDDEN)
    ;

ADD : '+' ;
SUB : '-' ;
MUL : '*' ;
DIV : '/' ;
L:'(';
R:')';

root:exp EOF			#n1
;

exp:factor (op1 factor)*	#n2
;
factor:term (op2 term)*		#n3
;
term:	n			#n4
	|L exp R		#n5
;
op1:ADD				#n6
    |SUB			#n7
;
op2:MUL				#n8
   |DIV				#n9
;
n:NUMBER			#n10
;

