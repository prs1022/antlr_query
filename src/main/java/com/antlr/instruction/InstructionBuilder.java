package com.antlr.instruction;

import com.antlr.aquery.AqueryBaseVisitor;
import com.antlr.aquery.AqueryLexer;
import com.antlr.aquery.AqueryParser;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 将DSL语言转成指令集
 * DSL -> Instruction
 *
 * @author prs
 * @date 2019/08/21
 */
public class InstructionBuilder {


    public static ComplexQuery build(String dsl) {
        dsl = dsl.trim();
        AqueryBaseVisitorImpl visitor = new AqueryBaseVisitorImpl();
        ComplexQuery query = new ComplexQuery(visitor.visit(getParser(dsl).query()));
        if (query.beginWithTestInstruction()) {
            query = new ComplexQuery(new SubNodeQuery(), query);
        }
        return query;
    }

    /**
     * string -> parser
     *
     * @param txt
     * @return
     */
    private static AqueryParser getParser(String txt) {
        ANTLRInputStream input = new ANTLRInputStream(txt);
        return new AqueryParser(new CommonTokenStream(new AqueryLexer(input)));
    }

    private static class AqueryBaseVisitorImpl extends AqueryBaseVisitor<List<Instruction>> {

        @Override
        public List<Instruction> visitQ_node(AqueryParser.Q_nodeContext ctx) {
            List<Instruction> ins = new ArrayList<>();
            for (int i = 0; i < ctx.getChildCount(); i++) {
                List<Instruction> theIn = visit(ctx.getChild(i));
                if (theIn != null) ins.addAll(theIn);
            }
            return ins;
        }

        @Override
        public List<Instruction> visitBase_query(AqueryParser.Base_queryContext ctx) {
            List<Instruction> ins = new ArrayList<>();
            for (int i = 0; i < ctx.getChildCount(); i++) {
                List<Instruction> theIn = visit(ctx.getChild(i));
                if (theIn != null) ins.addAll(theIn);
            }
            return ins;
        }

        @Override
        public List<Instruction> visitRule_name(AqueryParser.Rule_nameContext ctx) {
            List<Instruction> ins = new ArrayList<>();
            ins.add(new NodeNameQuery(ctx.WORD().getText()));
            return ins;
        }

        @Override
        public List<Instruction> visitQ_level_op(AqueryParser.Q_level_opContext ctx) {
            List<Instruction> ins = new ArrayList();
            if (ctx.GT() != null) {//子级选择器
                ins.add(new ChildrenNodeQuery());
            } else if (ctx.WS() != null) {//后代选择器
                ins.add(new SubNodeQuery());
            } else if (ctx.TILDE() != null) {//~ 后继兄弟节点
                ins.add(new NextAllNodeQuery());
            } else if (ctx.PLUS() != null) {//+相邻后继兄弟节点
                ins.add(new NextNodeQuery());
            } else if (ctx.filter != null) {//层级Filter复合筛选器
                LevelQueryInstruction l1 = (LevelQueryInstruction) new AqueryBaseVisitorImpl().visit(ctx.level).get(0);
                TestInstruction l2 = (TestInstruction) new AqueryBaseVisitorImpl().visit(ctx.filter).get(0);
                l1.attachTest(l2);
                ins.add(l1);
            }
            return ins;
        }

        @Override
        public List<Instruction> visitFilter_eq(AqueryParser.Filter_eqContext ctx) {
            List<Instruction> ins = new ArrayList<Instruction>();
            ins.add(new FilterEqQuery(Integer.parseInt(ctx.WORD().getText())));
            return ins;
        }

        @Override
        public List<Instruction> visitFilter_attr(AqueryParser.Filter_attrContext ctx) {
            List<Instruction> ins = new ArrayList<>();
            ins.add(new FilterAttrQuery(ctx.WORD(0).getText(), ctx.WORD(1).getText()));
            return ins;
        }

        @Override
        public List<Instruction> visitFilter_not(AqueryParser.Filter_notContext ctx) {
            List<Instruction> ins = new ArrayList<>();
            ins.add(new FilterNotQuery(new ComplexQuery(visit(ctx.query()))));
            return ins;
        }

        @Override
        public List<Instruction> visitFilter_is(AqueryParser.Filter_isContext ctx) {
            List<Instruction> ins = new ArrayList<>();
            ins.add(new FilterIsQuery(new ComplexQuery(visit(ctx.query()))));
            return ins;
        }

        @Override
        public List<Instruction> visitFilter_text(AqueryParser.Filter_textContext ctx) {
            List<Instruction> ins = new ArrayList<>();
            ins.add(new FilterTextQuery(ctx.reg().getText()));
            return ins;
        }

        @Override
        public List<Instruction> visitMuti_query(AqueryParser.Muti_queryContext ctx) {
            List<ComplexQuery> complexQueries = ctx.q_single().stream().map(
                    q -> {
                        ComplexQuery cq = new ComplexQuery(visit(q));
                        return cq;
                    }
            ).collect(Collectors.toList());
            List<Instruction> ins = new ArrayList<>();
            ins.add(new MultiQuery(complexQueries));
            return ins;
        }

        @Override
        public List<Instruction> visitUnit_query(AqueryParser.Unit_queryContext ctx) {
            List<Instruction> ins = new ArrayList<>();
            ComplexQuery complex = new ComplexQuery(visit(ctx.query()));
            ins.add(complex);
            return ins;
        }
    }
}
