grammar Aquery;

import CommonLexer;

GT:'>';
WS : [ \t\r\n]+;
TILDE:'~';
PLUS:'+';
ASTERISK:'*';
WORD:(DIGIT|LETTER|'_'|'.'|'$'|'"')+;
COMMA:',';      //COMMA 和 REG 在逗号上有冲突 要解决下
REG:('\\'|'['|']'|'?'|'^'|'{'|'}'|COMMA|'|')+;
//查询
query:
      q_single                                               #single_query  //顺序查询
     |q_single(COMMA q_single)+                              #muti_query   //并发式查询
     ;
q_single:
        q_breed                                               #breed_query//生发型查询 : breed_query优先级需要比unit_query高
        |q_test                                               #test_query //测试型查询
        |'('query')'                                          #unit_query
        |q_single q_single                                    #base_query //两个生发型的查询不能相邻 否则会有暴增情况
       ;

//生发 选择器
q_breed:
    q_level_op
    ;
//测试 选择器
q_test:
    q_node
    |q_filter
    ;

//节点选择器
q_node:rule_name
       |ASTERISK
       ;
//层级选择器
q_level_op:WS
        |TILDE
        |PLUS
        |GT
        |'('level=q_level_op filter=q_filter')'
        ;

reg:(REG|WORD|PLUS|ASTERISK)+;

//附加过滤器选择器
q_filter:
   ':eq(' WORD ')'                         #filter_eq
   |'['WORD('!'|'$'|'*')?'=\''WORD'\']'    #filter_attr
   |':is('query')'                        #filter_is
   |':not('query')'                       #filter_not
   |':text('reg')'                      #filter_text
   ;


//节点名
rule_name:WORD;