package com.antlr.instruction;

import org.antlr.v4.runtime.tree.ParseTree;

import java.util.HashSet;
import java.util.Set;

/**
 * 所有子孙后继节点查找 (先序遍历) (包含自己)
 *
 * @author prs
 * @date 2019/08/21
 */
public class SubNodeQuery extends LevelQueryInstruction {

    @Override
    Set<ParseTree> query(ParseTree tree) {
        Set<ParseTree> set = new HashSet<>();
        set.add(tree);
        for (int i = 0; i < tree.getChildCount(); i++) {
            addChild(tree.getChild(i),set);
        }
        return set;
    }

    public void addChild(ParseTree root,Set<ParseTree> set) {
        if (root == null||!testNode(root)) {
            return;
        }
        set.add(root);
        for (int i = 0; i < root.getChildCount(); i++) {
            set.add(root.getChild(i));
            addChild(root.getChild(i),set);
        }
    }

    @Override
    public String toString() {
        return " ";
    }
}
