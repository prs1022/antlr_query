package com.antlr.instruction;

import org.antlr.v4.runtime.tree.ParseTree;

import java.util.stream.Stream;

/**
 * 布尔查找器
 *
 * @author prs
 * @date 2019/08/21
 */
public abstract class TestInstruction implements Instruction {
    /**
     * 节点进行是否符合判断
     *
     * @param node
     * @return
     */
    abstract boolean test(ParseTree node);

    @Override
    public Stream<ParseTree> attach(Stream<ParseTree> stream) {
        return stream.filter((node) -> {
            try {
                return test(node);
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        });
    }
}
