package com.antlr.cucumber;

import com.antlr.instruction.ComplexQuery;
import com.antlr.instruction.InstructionBuilder;
import com.antlr.sqlbase.SqlBaseParser;
import com.antlr.util.AntlrHelper;
import com.antlr.util.SqlAstHelper;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import org.antlr.v4.runtime.tree.ParseTree;
import org.apache.commons.io.FileUtils;
import org.springframework.util.CollectionUtils;

import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 查找测试
 *
 * @author prs
 * @date 2019/08/21
 */
public class QueryStepdefs {
    private String dsl;//输入DSL
    private String sql;//输入SQL
    private Set<ParseTree> set;//查找到的语法树节点
    private String removeSql;
    private SqlBaseParser parser;//生成语法树
    private SqlBaseParser.QueryContext queryContext;

    @Given("^输入DSL\"([^\"]*)\"$")
    public void 输入DSL(String dsl) throws Throwable {
        System.out.println("============输入sql=================");
        this.dsl = readFile("yhdsl/" + dsl)[0];
    }

    @And("^输入SQL\"([^\"]*)\"$")
    public void 输入SQL(String sql) throws Throwable {
        this.sql = readFile("yhsql/" + sql)[0];
        if (sql.contains("remove")) {
            removeSql = this.sql;
        }
    }

    @Then("^查找节点$")
    public void 查找节点() throws Throwable {
        ComplexQuery aQuery = InstructionBuilder.build(dsl);
        SqlBaseParser parser = SqlAstHelper.getParser(sql);
        set = aQuery.query(parser.query());
        set.forEach(e -> {
            System.out.println(SqlAstHelper.toSql(e));
        });
    }

    @Then("^查找结果为\"([^\"]*)\"$")
    public void 查找结果为(String txt) throws Throwable {
        boolean flag = true;
        List rs = CollectionUtils.arrayToList(readFile("yh_result/" + txt));
        Collections.sort(rs);
        List<String> sqls = set.stream().map(
                pt -> SqlAstHelper.toSql(pt)
        ).collect(Collectors.toList());
        Collections.sort(sqls);
        int index = 0;
        if (rs.size() == sqls.size()) {
            for (String currSql : sqls) {
                String currRs = rs.get(index++).toString();
                flag &= SqlAstHelper.sqlEquals(currSql, currRs);
                if (!flag) {
                    System.out.println("比对不一致:\n" + currSql + "\n对比\n" + currRs);
                }
            }
        } else {
            System.out.println("数量不一致:input:" + rs.size() + " output:" + set.size());
            for (ParseTree s : set) {
                System.out.println(SqlAstHelper.toSql(s));
            }
            flag = false;
        }
        assert flag && set.size() > 0;
    }

    private String[] readFile(String dir) {
        try {
            return FileUtils.readFileToString(new File(getClass().getResource("/" + dir).getFile()), "utf8").split("[\\r\\n]{4,}");
        } catch (IOException e) {
            e.printStackTrace();
            return new String[1];
        }
    }

    @Then("^输出结果\"([^\"]*)\"$")
    public void 输出结果(String arg0) throws Throwable {
        List rs = CollectionUtils.arrayToList(readFile("result/" + arg0));
        SqlBaseParser.QuerySpecificationContext query = SqlAstHelper.getParser(removeSql).querySpecification();
        SqlAstHelper.removeUselessJoinTable(query);
        String sql1 = rs.get(0).toString();
        String sql2 = SqlAstHelper.toSql(query);
        boolean flag = SqlAstHelper.sqlEquals(sql1, sql2);
        if (!flag) {
            System.out.println("expect:\n" + sql1 + " \nbut:\n" + sql2);
        }
        assert flag;
    }

    @Then("^生成语法树$")
    public void 生成语法树() throws Throwable {
        parser = SqlAstHelper.getParser(sql);
        queryContext = parser.query();
    }

    @Then("^TO_SQL\"([^\"]*)\"$")
    public void to_sql(String arg0) throws Throwable {
        String re = FileUtils.readFileToString(new File(getClass().getResource("/result/" + arg0).getFile()), "utf8");
        String tmpSql = SqlAstHelper.toSql(queryContext);
        System.out.println("tmpSql:" + tmpSql);
        assert tmpSql.equals(re);
    }

    @Then("^表名替换$")
    public void 表名替换() throws Throwable {
        ComplexQuery aQuery = InstructionBuilder.build("tableName");
        Set<ParseTree> arr = aQuery.query(queryContext);
        for (ParseTree t : arr) {
            AntlrHelper.replace(t, SqlAstHelper.getParser("(select * from test)").relationPrimary());
        }
    }

    @Then("^追加条件$")
    public void 追加条件() throws Throwable {
        SqlBaseParser.QuerySpecificationContext q = AntlrHelper.down(queryContext, SqlBaseParser.QuerySpecificationContext.class);
        AntlrHelper.replace(q.where, SqlAstHelper.getParser(SqlAstHelper.toSql(q.where) + " and 1=1").booleanExpression());
        AntlrHelper.replace(q.where, SqlAstHelper.getParser(SqlAstHelper.toSql(q.where) + " and 1=1").booleanExpression());
    }
}
