grammar hello;

HELLO:'hello';
WORLD:'world';
HW:'hello world';

root:HELLO WORLD	#h1
     |HW		#h2
     ;