package com.antlr.instruction;

import org.antlr.v4.runtime.tree.ParseTree;

import java.util.stream.Stream;

/**
 * 节点查找指令接口定义
 *
 * @author prs
 * @date 2019/08/21
 */
public interface Instruction {

    /**
     * 依附于流式处理
     *
     * @param stream
     * @return
     */
    Stream<ParseTree> attach(Stream<ParseTree> stream);


}
