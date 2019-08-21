package com.antlr.instruction;

import org.antlr.v4.runtime.tree.ParseTree;

import java.util.HashSet;
import java.util.Set;

/**
 * 相邻后继节点
 *
 * @author prs
 * @date 2019/08/21
 */
public class NextNodeQuery extends LevelQueryInstruction {
    @Override
    public Set<ParseTree> query(ParseTree tree) {
        ParseTree parent = tree.getParent();
        Set<ParseTree> set = new HashSet<>();
        boolean flag = false;
        //TODO parent 有可能为null
        if (parent != null) {
            for (int i = 0; i < parent.getChildCount() - 1; i++) {
                if (tree.equals(parent.getChild(i))) {
                    flag = true;
                }
                //遍历到当前节点
                if (flag) {
                    ParseTree next = parent.getChild(i + 1);
                    if (testNode(next)) {
                        set.add(next);
                    }
                    break;
                }
            }
        }
        return set;
    }

    @Override
    public String toString() {
        return "+";
    }
}
