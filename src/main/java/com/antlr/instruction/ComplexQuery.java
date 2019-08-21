package com.antlr.instruction;

import org.antlr.v4.runtime.tree.ParseTree;
import org.apache.commons.lang3.StringUtils;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 复合型查找指令
 *
 * @author prs
 * @date 2019/08/21
 */
public class ComplexQuery extends QueryInstruction {

    private List<Instruction> ins = new ArrayList<>();

    public ComplexQuery(List<Instruction> instructions) {
        if (instructions == null || instructions.size() == 0) {
            throw new RuntimeException("invalid arguments");
        }
        ins.addAll(instructions);
    }

    public ComplexQuery(Instruction... instructions) {
        if (instructions == null || instructions.length == 0) {
            throw new RuntimeException("invalid arguments");
        }
        for (Instruction i : instructions) {
            ins.add(i);
        }
    }

    public boolean beginWithTestInstruction() {
        return ins.size() > 0 && ins.get(0) instanceof TestInstruction;
    }

    @Override
    public Set<ParseTree> query(ParseTree tree) {
        Set<ParseTree> allNode = new HashSet<>();
        allNode.add(tree);
        Stream<ParseTree> stream = allNode.parallelStream();
        for (Instruction in : ins) {
            stream = in.attach(stream);
        }
        return stream.collect(Collectors.toSet());
    }

    public ParseTree first(ParseTree tree) {
        Set<ParseTree> parseTrees = query(tree);
        Iterator<ParseTree> its = parseTrees.iterator();
        if (its.hasNext()) {
            return its.next();
        }
        return null;
    }

    @Override
    public String toString() {
        if (ins.size() == 1) {
            return ins.get(0).toString();
        } else {
            List<String> strArr = ins.stream().map(
                    instruction -> instruction instanceof ComplexQuery ? ((ComplexQuery) instruction).embedString() : instruction.toString()
            ).collect(Collectors.toList());
            return StringUtils.join(strArr, "");
        }
    }

    public String embedString() {
        if (ins.size() == 1) {
            return ins.get(0).toString();
        } else {
            return "(" + StringUtils.join(ins, "") + ")";
        }
    }
}
