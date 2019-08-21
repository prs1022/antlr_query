package com.antlr.instruction;

import org.antlr.v4.runtime.tree.ParseTree;

import java.util.stream.Stream;

/**
 *  索引过滤
 * @author prs
 * @date 2019/08/21
 */
public class FilterEqQuery extends TestInstruction {

    private Integer index;

    public FilterEqQuery(Integer index) {
        this.index = index;
    }

    @Override
    boolean test(ParseTree node) {
        return false;
    }

    @Override
    public Stream<ParseTree> attach(Stream<ParseTree> stream) {
//        return stream.sorted((node1,node2)->{
//            int dp1 = node1.getRuleContext().depth();
//            int dp2=node2.getRuleContext().depth();
//            if(dp1!=dp2){
//                int index1=node1.getParent().getChild();
//            }else{
//                return dp1-dp2;
//            }
//        }).skip(index).limit(1);
        return stream;
    }
}
