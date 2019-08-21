package com.antlr.util;


import com.antlr.instruction.ComplexQuery;
import com.antlr.instruction.InstructionBuilder;
import com.antlr.sqlbase.SqlBaseBaseListener;
import com.antlr.sqlbase.SqlBaseParser;
import com.antlr.sqlbase.SqlBaseParser.AliasedRelationContext;
import com.antlr.sqlbase.SqlBaseParser.BooleanExpressionContext;
import com.antlr.sqlbase.SqlBaseParser.QueryContext;
import com.antlr.sqlbase.SqlBaseParser.QuerySpecificationContext;
import com.antlr.sqlbase.SqlBaseParser.SelectItemContext;
import com.antlr.sqlbase.SqlBaseParser.SubqueryRelationContext;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.TokenStreamRewriter;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * SQL抽象语法树 辅助工具
 * Created by jian.sun on 2016/6/17.
 */
public class SqlAstHelper {
    private static final Logger logger = LoggerFactory.getLogger(SqlAstHelper.class);

    /**
     * 通过别名获取表名
     *
     * @param query 查询的抽象语法树
     * @param alias 表别名或本名
     * @return 表本名
     */
    public static String getTableName(QuerySpecificationContext query, String alias) {
        //非子查询的涉及的所有表
        List<AliasedRelationContext> tables = AntlrHelper.downs(query, AliasedRelationContext.class, SubqueryRelationContext.class);
        if (alias == null && tables.size() == 1) {
            return tables.get(0).relationPrimary().getText();
        } else if (alias != null) {
            for (AliasedRelationContext curr : tables) {
                if (alias.equalsIgnoreCase(curr.identifier() != null ? curr.identifier().getText() : curr.relationPrimary().getText())) {
                    return curr.relationPrimary().getText();
                }
            }
        }
        return alias;
    }

    /**
     * 通过表名获取表别名
     *
     * @param query
     * @param tableName
     * @return 找不到表返回NULL 找到了表但是没有别名返回""
     */
    public static String getTableAlias(QuerySpecificationContext query, String tableName) {
        //非子查询的涉及的所有表
        List<AliasedRelationContext> tables = AntlrHelper.downs(query, AliasedRelationContext.class, SubqueryRelationContext.class);
        if (tableName == null && tables.size() == 1) {
            return tables.get(0).identifier().getText();
        } else if (tableName != null) {
            for (AliasedRelationContext curr : tables) {
                if (tableName.equalsIgnoreCase(curr.relationPrimary().getText())) {
                    if (curr.identifier() != null) {
                        return curr.identifier().getText();
                    } else {
                        return "";
                    }
                }
            }
        }
        return null;
    }

    /**
     * 检查SQL语法是否完全合法
     *
     * @return true 合法，false 不合法
     */
    public static boolean checkGrammar(String sql) {
        try {
            SqlBaseParser parser = getParser(sql.trim().toUpperCase().replace(";", ""));
            MyErrorStrategy myError = new MyErrorStrategy();
            parser.setErrorHandler(myError);
            parser.query();//执行一次语法分析
            return myError.isValidated();
        } catch (Exception e) {
            logger.error("", e);
            return false;
        }
    }

    /**
     * 获取创建的表名
     *
     * @param sql
     * @return null:没有别名
     */
    public static String getCreateTableName(String sql) {
        Set<ParseTree> rs = InstructionBuilder.build("createTableAsSelect").query(SqlAstHelper.getParser(sql).statement());
        if (rs.size() > 0) {
            SqlBaseParser.CreateTableAsSelectContext st = (SqlBaseParser.CreateTableAsSelectContext) rs.iterator().next();
            //修复antlerHelper的缺陷 去除一下空字符串
            return SqlAstHelper.toSql(st.qualifiedName()).replace(" ", "");
        } else {
            return null;
        }
    }

    /**
     * 在排除排版格式的情况下比较多个SQL语义是否一致
     *
     * @param sql
     * @return
     */
    public static boolean sqlEquals(String... sql) {
        try {
            Set<String> set = new HashSet();
            for (String s : sql) {
                SqlBaseParser parser = getParser(s.trim().toUpperCase().replace(";", ""));
                set.add(parser.query().toStringTree());
            }
            return set.size() == 1;
        } catch (Exception e) {
            logger.error("", e);
            return false;
        }
    }

    private static final String filterDsl = "((booleanExpression>),(booleanExpression( :not(logicalBinary:is(>or)))logicalBinary:is(>and):is(>booleanExpression:not( logicalBinary)) ))predicated:is(>predicate)";

    /**
     * 获取sql中所有的where条件
     * or条件的不筛选 只筛选and条件
     *
     * @return
     */
    @Deprecated
    public static Set<ParseTree> getAllFilters(QueryContext query) {
        String dsl = "querySpecification>" + filterDsl;
        return InstructionBuilder.build(dsl).query(query);
    }

    public static Set<ParseTree> getAllFilters(BooleanExpressionContext where) {
        String dsl = filterDsl;
        return InstructionBuilder.build(dsl).query(where);
    }

    @Deprecated
    public static Set<ParseTree> getAllFilters(String sql) {
        QueryContext root = getParser(sql).query();
        return getAllFilters(root);
    }

    /**
     * 判断是否是聚合函数
     *
     * @param selectItemContext
     * @return
     */
    public static boolean isAggregate(SelectItemContext selectItemContext) {
        Pattern pattern = Pattern.compile("^(arbitrary|avg|bool_and|bool_or|count|every|max_by|min_by|max" +
                "|min|sum|map_agg|approx_distinct|approx_distinct|approx_percentile|numeric_histogram" +
                "|stddev|stddev_pop|stddev_samp|variance|var_pop|var_samp)$"
        );
        boolean flag = false;
        String dsl = "functionCall";
        Set<ParseTree> funcs = InstructionBuilder.build(dsl).query(selectItemContext);
        for (ParseTree func : funcs) {
            String funcName = SqlAstHelper.toSql(((SqlBaseParser.FunctionCallContext) func).qualifiedName()).toLowerCase();
            Matcher matcher = pattern.matcher(funcName);
            flag |= matcher.find();
        }
        return flag;
    }

    public static void removeUselessJoinTable(QuerySpecificationContext query) {
        removeUselessJoinTable(query, new ArrayList<>());
    }

    /**
     * 删除无用JOIN表(不包括join的子查询)
     * 去掉带"别名"的join的多余"辅表"（该表的别名没有出现在select和where里的字段中）
     * select t1.a from A t1 join B t2 on t1.ss=ss where t1.x=xxx
     *
     * @param query            待修剪的语法树节点
     * @param ignoreTableNames 需要忽略的表(不需要remove，不是无用的表)
     */
    public static void removeUselessJoinTable(QuerySpecificationContext query, List<String> ignoreTableNames) {
        //是否是joinRelation
        if (query.relation().size() <= 0) {
            return;
        }
        for (SqlBaseParser.RelationContext relationContext : query.relation()) {
            if (relationContext instanceof SqlBaseParser.JoinRelationContext) {
                continue;
            }
            return;
        }
        MyListener listener = new MyListener();
        new ParseTreeWalker().walk(listener, query);
        AliasedRelationContext primaryTable = listener.getPrimaryTableName();
        //join项
        List<AliasedRelationContext> aliasRelations = AntlrHelper.downs(query, AliasedRelationContext.class);
        Iterator<AliasedRelationContext> aliasIt = aliasRelations.iterator();
        while (aliasIt.hasNext()) {
            AliasedRelationContext currAliasRelation = aliasIt.next();
            if (currAliasRelation == primaryTable || AntlrHelper.down(currAliasRelation, SubqueryRelationContext.class) != null) {
                aliasIt.remove();//排除join项中的主表和子查询
            } else {
                currAliasRelation.identifier();
            }
        }
        for (AliasedRelationContext aliaContext : aliasRelations) {
            if (aliaContext.identifier() == null) {//没有别名的join项跳过不处理
                continue;
            }
            String alia = aliaContext.identifier().getText();
            //1、别名是否出现在selectItem中
            boolean flag = false;
            for (SelectItemContext selectItemContext : query.selectItem()) {
                if (selectItemContext instanceof SqlBaseParser.SelectSingleContext) {
                    String dsl = "selectSingle dereference columnReference identifier";
                    ComplexQuery complexQ = InstructionBuilder.build(dsl);
                    ParseTree parseTree = complexQ.first(selectItemContext);
                    if (parseTree != null && parseTree.getText().equals(alia)) {
                        flag = true;
                    }
                }
            }
            //2、是否出现在where部分
            if (query.where.getText().contains(alia)) {
                flag = true;
            }
            //当符合1，2条件且不在排出的表里时 ：删除join alias on部分即无用的表
            if (!flag && !ignoreTableNames.contains(SqlAstHelper.toSql(aliaContext.relationPrimary()))) {
                SqlBaseParser.JoinRelationContext relationContext = AntlrHelper.up(aliaContext, SqlBaseParser.JoinRelationContext.class);
                int i = 1;//joinRelation 只需删除右测的部分 0不能删除，从1个index开始删除
                for (; relationContext.children.size() > 1; ) {
                    logger.debug("待删除的joinRelation子节点=>" + relationContext.children.get(i).getText());
                    AntlrHelper.remove(relationContext.children.get(i));
                }
            }
        }
        logger.debug("修剪后的query=>" + toSql(query));
    }

    /**
     * 获取最终SQL的最外层所有查询字段名
     *
     * @param sql
     * @return
     */
    public static List<String> getFinalQueryFields(String sql) {
        SqlBaseParser parser = SqlAstHelper.getParser(sql);
        QuerySpecificationContext query = parser.querySpecification();
        List<ParseTree> allFieldNames = getAllQueryFieldNames(query);
        List<ParseTree> subFieldNames = getSubQueryFieldNames(query);
        allFieldNames.removeAll(subFieldNames);
        List<ParseTree> primaryFieldNames = allFieldNames;
        List<String> fields = new ArrayList<>();
        for (ParseTree parseTree : primaryFieldNames) {
            fields.add(parseTree.getText());
        }
        if (fields.isEmpty()) {
            Set<String> tmp = new HashSet<>();
            for (ParseTree parseTree : subFieldNames) {
                tmp.add(parseTree.getText());
            }
            fields.addAll(tmp);
        }
        return fields;
    }

    /**
     * 获取所有的子查询字段
     *
     * @param query
     * @return
     */
    public static List<ParseTree> getSubQueryFieldNames(QuerySpecificationContext query) {
        //todo
//        String dsl = "querySpecification:not( subqueryRelation) selectSingle";
        String dsl = "subqueryRelation querySpecification selectSingle";
        Set<ParseTree> parseTs = InstructionBuilder.build(dsl).query(query);
        if (parseTs != null) {
            return new ArrayList<>(parseTs);
        }
        return null;
    }

    /**
     * 获取SQL中所有的查询字段
     *
     * @param query
     * @return
     */
    public static List<ParseTree> getAllQueryFieldNames(QuerySpecificationContext query) {
        String dsl = "querySpecification selectSingle";
        Set<ParseTree> parseTs = InstructionBuilder.build(dsl).query(query);
        if (parseTs != null) {
            return new ArrayList<>(parseTs);
        }
        return null;
    }

    /**
     * ============================================================================================
     * ||select a,b from A
     * ||select * from (select b from B) t1
     * ||select a from A join (select b from B) on A.a = B.b
     * ||select a from A join C ...
     *
     * @param args
     */
    public static void main(String[] args) {
        String sql = "select t2.a from (select t2.b  from B) as t2";
//        String sql = "select a,b from A";
//        String sql = "select a from A join (select b from B) on A.a = B.b";
//        String sql = "select a from A join C";
//        String sql = "select * from (select c from B) t1 join (select c from C) t2 on t1.c = t2.c";
        List<String> strs = getFinalQueryFields(sql);
        for (int i = 0; i < strs.size(); i++) {
            System.out.println(strs.get(i));
        }
    }

    /**
     * string -> parser
     *
     * @param txt
     * @return
     */
    public static SqlBaseParser getParser(String txt) {
        TokenStream origin = AntlrHelper.getTokenStream(txt);
        AntlrHelper.attachTokenStream(origin, txt);
        return new SqlBaseParser(origin);
    }


    /**
     * tree -> sql string
     *
     * @param ruleContext
     * @return
     */
    public static String toSql(ParseTree ruleContext) {
        return AntlrHelper.getRewrite(ruleContext).getText(ruleContext.getSourceInterval());
    }


    public static QuerySpecificationContext addSelect(QuerySpecificationContext query, List<SelectItemContext> selects) {
        for (SelectItemContext select : selects) {
            query = addSelect(query, select);
        }
        return query;
    }

    /**
     * @param query
     * @param selectItemContext
     * @return 返回替换后的ast node（query对象）
     */
    public static QuerySpecificationContext addSelect(QuerySpecificationContext query, SelectItemContext selectItemContext) {
        int lastIndex;
        if (query.selectItem().size() == 0) {
            lastIndex = query.children.indexOf(query.SELECT());
            query.children.add(lastIndex + 1, selectItemContext);
            return query;
        } else {
            QuerySpecificationContext queryCopy = SqlAstHelper.getParser(SqlAstHelper.toSql(query)).querySpecification();
            SelectItemContext lastSelectItem = queryCopy.selectItem(queryCopy.selectItem().size() - 1);
            TokenStreamRewriter rw = AntlrHelper.getRewrite(queryCopy);
            rw.insertAfter(lastSelectItem.stop, "," + SqlAstHelper.toSql(selectItemContext));
            QuerySpecificationContext newQ = SqlAstHelper.getParser(rw.getText(queryCopy.getSourceInterval())).querySpecification();
            AntlrHelper.replace(query, newQ);
            return newQ;
        }
    }

    /**
     * 解析sql中的表与字段
     *
     * @return
     */
    public Map<String, List<String>> getTableFieldMapings(String sql) {
        ComplexQuery complexQuery_table = InstructionBuilder.build("querySpecification tableName");

        Set<ParseTree> query_t = complexQuery_table.query(SqlAstHelper.getParser(sql).query());
        Map<String, List<String>> tableFieldMappings = new HashMap();
        for (ParseTree tablePt : query_t) {
            tableFieldMappings.put(SqlAstHelper.toSql(tablePt), new ArrayList<>());
            ComplexQuery complexQuery_field = InstructionBuilder.build("querySpecification(含有该tableName) selectItem");
            Set<ParseTree> query = complexQuery_field.query(SqlAstHelper.getParser(sql).query());
            for (ParseTree parseTree : query) {
                String field = SqlAstHelper.toSql(parseTree);
            }
        }
        return Collections.EMPTY_MAP;
    }
}

class MyListener extends SqlBaseBaseListener {
    private SqlBaseParser.AliasedRelationContext primaryTable = null;
    private boolean flag = false;

    /**
     * {@inheritDoc}
     * <p>
     * <p>The default implementation does nothing.</p>
     *
     * @param ctx
     */
    @Override
    public void enterJoinRelation(SqlBaseParser.JoinRelationContext ctx) {
        if (!flag) {
            if (AntlrHelper.down(ctx.left, SqlBaseParser.SubqueryRelationContext.class) == null) {
                SqlBaseParser.AliasedRelationContext aliasRelation = AntlrHelper.down(ctx.left, SqlBaseParser.AliasedRelationContext.class);
                if (aliasRelation != null) {
                    primaryTable = aliasRelation;
                    return;
                }
            }
            flag = true;
        }
    }

    public SqlBaseParser.AliasedRelationContext getPrimaryTableName() {
        return primaryTable;
    }
}
