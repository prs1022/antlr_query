package com.antlr.instruction;

import org.antlr.v4.runtime.tree.ParseTree;

/**
 * 属性值过滤查询
 *
 * @author prs
 * @date 2019/08/21
 */
public class FilterAttrQuery extends TestInstruction {

    public FilterAttrQuery(String name, String value) {

    }

    @Override
    boolean test(ParseTree node) {
        return false;
    }
}
