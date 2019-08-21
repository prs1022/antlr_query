package com.antlr.instruction;

import org.antlr.v4.runtime.tree.ParseTree;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Stream;

/**
 * 指令查找执行器
 *
 * @author prs
 * @date 2019/08/21
 */
public abstract class QueryInstruction implements Instruction {
    /**
     * 在目标语法树上执行本查找指令
     *
     * @param tree
     * @return
     */
    abstract Set<ParseTree> query(ParseTree tree);

    /**
     * 依附于流式处理
     *
     * @param stream
     * @return
     */
    @Override
    public Stream<ParseTree> attach(Stream<ParseTree> stream) {
        Set<ParseTree> pass = new HashSet<>();
        return stream.flatMap((node) -> {
            if (pass.add(node)) {
                return query(node).stream();
            } else {
                return Stream.empty();
            }
        });
    }
}
