package com.antlr.instruction;

import org.antlr.v4.runtime.tree.ParseTree;

import java.util.HashSet;
import java.util.Set;

/**
 * 所有后继兄弟节点
 *
 * @author prs
 * @date 2019/08/21
 */
public class NextAllNodeQuery extends LevelQueryInstruction {
    @Override
    public Set<ParseTree> query(ParseTree tree) {
        Set<ParseTree> set = new HashSet<>();
        ParseTree parent = tree.getParent();
        for (int i = 0; i < tree.getChildCount(); i++) {
            ParseTree curr = parent.getChild(i);
            if (!tree.equals(curr)) {
                continue;
            }
            if (!testNode(curr)) {
                break;
            }
            set.add(curr);
        }
        return set;
    }

    @Override
    public String toString() {
        return "~";
    }
}
