package com.antlr.instruction;

import org.antlr.v4.runtime.tree.ParseTree;

/**
 * levelQuery + filterQuery
 * levelQuery 非并发顺序查找并每个元素都经过filterQuery.test的验证，
 * 当遇到第一个filterQuery.test为false时则终止查询
 *
 * @author prs
 * @date 2019/08/21
 */
public abstract class LevelQueryInstruction extends QueryInstruction {

    private TestInstruction testInstruction;
    public final void attachTest(TestInstruction testInstruction){
        this.testInstruction=testInstruction;
    }
    protected boolean testNode(ParseTree node){
        if(testInstruction==null){
            return true;
        }else{
            return testInstruction.test(node);
        }
    }


}
