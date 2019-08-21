grammar hello2;
STRING : [a-zA-Z ]+;
HW:'hello world';

root:STRING		#h1
     |HW		#h2
     ;