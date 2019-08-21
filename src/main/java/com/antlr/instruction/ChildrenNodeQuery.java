package com.antlr.instruction;

import org.antlr.v4.runtime.tree.ParseTree;

import java.util.LinkedHashSet;
import java.util.Set;

/**
 * 子节点查找指令
 *
 * @author prs
 * @date 2019/08/21
 */
public class ChildrenNodeQuery extends LevelQueryInstruction {

    @Override
    public Set<ParseTree> query(ParseTree node) {
        Set<ParseTree> children = new LinkedHashSet<>();
        for (int i = 0, childCount = node.getChildCount(); i < childCount; i++) {
            ParseTree curr = node.getChild(i);
            if (!testNode(curr)) {
                break;
            }
            children.add(curr);
        }
        return children;
    }

    @Override
    public String toString() {
        return ">";
    }
}
