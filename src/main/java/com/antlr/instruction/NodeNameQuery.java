package com.antlr.instruction;

import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.RuleNode;
import org.antlr.v4.runtime.tree.TerminalNode;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 按照节点名查找指令
 *
 * @author prs
 * @date 2019/08/21
 */
public class NodeNameQuery extends TestInstruction {

    private String ruleName;

    public NodeNameQuery(String name) {
        this.ruleName = name;
    }

    Pattern pattern = Pattern.compile("\\$(.+)Context$");

    @Override
    boolean test(ParseTree node) {
        try {
            List<String> myRuleNames = new ArrayList<>();
            //非叶子节点
            if (!(node instanceof TerminalNode)) {
                Matcher matcher = pattern.matcher(node.getClass().getName());
                if (matcher.find()) {
                    myRuleNames.add(matcher.group(1));
                }
                Class cl = Class.forName(node.getClass().getName().split("\\$")[0]);
                Field field = cl.getField("ruleNames");
                field.setAccessible(true);
                String[] ruleNames = (String[]) field.get(null);
                myRuleNames.add(ruleNames[((RuleNode) node).getRuleContext().getRuleIndex()]);
            } else {
                myRuleNames.add(((TerminalNode) node).getSymbol().getText());
            }
            for (String myRuleName : myRuleNames) {
                if (ruleName.equalsIgnoreCase(myRuleName)) {
                    return true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public String toString() {
        return ruleName;
    }
}
