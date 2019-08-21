package com.antlr.instruction;

import org.antlr.v4.runtime.tree.ParseTree;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 流入的节点取文本内容为text的返回
 *
 * @author prs
 * @date 2019/08/21
 */
public class FilterTextQuery extends TestInstruction {

    private String text;

    public FilterTextQuery(String text) {
        this.text = text;
    }

    @Override
    boolean test(ParseTree node) {
        Pattern pattern = Pattern.compile(text);
        Matcher matcher = pattern.matcher(node.getText().replaceAll("\"", ""));
        boolean flag = false;
        if (matcher.matches()) {
            flag = true;
        }
        return flag;
    }

    @Override
    public String toString() {
        return ":text(" + text + ")";
    }
}
