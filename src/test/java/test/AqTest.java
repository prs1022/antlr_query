package test;

import com.antlr.instruction.ComplexQuery;
import com.antlr.instruction.InstructionBuilder;
import com.antlr.sqlbase.SqlBaseParser;
import com.antlr.util.AntlrHelper;
import com.antlr.util.SqlAstHelper;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenSource;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.TerminalNodeImpl;
import org.springframework.test.context.jdbc.Sql;

/**
 * Created by rensong.pu on 2016/12/15.
 */
public class AqTest {
    public static void main(String[] args) {

        String dsl = "querySpecification tableName";
        String sql = "select a.org_name as label,a.org_id as value from (select t.org_name,t.org_code as org_id from platform.dim_shop20181114" +
                "where seller_type_id='0' and open_date is not null and `status`=1 group by org_code) as a";
        ComplexQuery build = InstructionBuilder.build(dsl);
        Iterable<? extends ParseTree> parseTrees = build.query(SqlAstHelper.getParser(sql).query());

        for (ParseTree parseTree : parseTrees) {
            System.out.println("当前的表名:"+parseTree.getText());
            SqlBaseParser.QuerySpecificationContext up = AntlrHelper.up(parseTree, SqlBaseParser.QuerySpecificationContext.class);
            System.out.println(SqlAstHelper.getTableAlias(up, "platform.dim_shop20181114"));
        }

//        String str="\t\t\n\rsas@@assad\n\r\t@@@dasdas   a\n\r\t";//java 转义字符
//        System.out.printf("你好\n你");
//        System.out.println(str);
//        System.out.println(str.split("@@")[0].replaceAll("\\t","").replaceAll("\\n","").replaceAll("\\r",""));
        //        String sql = "select * from A join(select * from B where yy between y1 and y2) on 1 =1  where xx between a and b";
//        ComplexQuery cq = InstructionBuilder.build("where");
//        Set<ParseTree> pts = cq.query(SqlAstHelper.getParser(sql).query());
//        pts.parallelStream().collect(toList()).forEach(e -> {
//            Token token = ((TerminalNodeImpl) e).getSymbol();
//            System.out.println(SqlAstHelper.toSql(e));
//            TokenSource in = token.getTokenSource();
//            System.out.print(in);
//        });
    }
}
