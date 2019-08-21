package com.antlr.instruction;

import org.antlr.v4.runtime.tree.ParseTree;

/**
 *  "非"门
 * @author prs
 * @date 2019/08/21
 */
public class FilterNotQuery extends TestInstruction {
    private ComplexQuery ins;

    public FilterNotQuery(ComplexQuery ins) {
        this.ins = ins;
    }

    @Override
    boolean test(ParseTree node) {
        return ins.query(node).isEmpty();
    }

    @Override
    public String toString() {
        return ":not("+ins+")";
    }
}
