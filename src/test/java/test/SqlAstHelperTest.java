package test;

import com.antlr.instruction.InstructionBuilder;
import com.antlr.sqlbase.SqlBaseParser;
import com.antlr.util.AntlrHelper;
import com.antlr.util.SqlAstHelper;
import org.antlr.v4.runtime.tree.ParseTree;
import org.junit.Test;

/**
 * Created by god on 2018/5/17.
 */
public class SqlAstHelperTest {

    public static void main(String[] args) throws InterruptedException {
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                String sql = "SELECT /*test*/distinct t1799. month_name AS \"10029\" \n" +
                        "FROM vipdm.dm_log_traffic_pageview_day AS t21074\n" +
                        "JOIN vipdm . dim_date AS t1799\n" +
                        "  ON t21074 . log_date = t1799 . log_date\n" +
                        "JOIN vipdm . dim_hm AS t20028\n" +
                        "  ON t21074 . hm = t20028 . hm\n" +
                        "WHERE t21074 . log_date\n" +
                        "  BETWEEN '2018-04-01'\n" +
                        "    AND '2018-05-10'\n" +
                        "    AND t21074 . app_name IN ( '特卖会' )\n" +
                        "    AND t21074 . dt\n" +
                        "  BETWEEN '20180401'\n" +
                        "    AND '20180510'";

                SqlBaseParser.QueryContext qq = SqlAstHelper.getParser(sql).query();
                SqlBaseParser.QuerySpecificationContext q = AntlrHelper.down(qq, SqlBaseParser.QuerySpecificationContext.class);
                SqlAstHelper.removeUselessJoinTable(q);
                AntlrHelper.replace(InstructionBuilder.build("tableName").query(qq).iterator().next(), SqlAstHelper.getParser("tt").qualifiedName());
                System.out.println(SqlAstHelper.toSql(q));
            }
        });
        t.start();
        t.join();
        System.gc();
        try {
            Thread.sleep(10000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(AntlrHelper.getTokenSourceLink().size());
        System.out.println(AntlrHelper.getTokenSource2RewriteMap().size());
        System.gc();
        try {
            Thread.sleep(10000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(AntlrHelper.getTokenSourceLink().size());
        System.out.println(AntlrHelper.getTokenSource2RewriteMap().size());
    }

    @Test
    public void testAddSelect() {
        String sql = "select 1 from (select DISTINCT userid from t) as t";
        SqlBaseParser.QuerySpecificationContext q = SqlAstHelper.getParser(sql).querySpecification();
        ParseTree subQ = InstructionBuilder.build("subqueryRelation querySpecification").query(q).stream().findFirst().get();
        SqlAstHelper.addSelect((SqlBaseParser.QuerySpecificationContext) subQ, SqlAstHelper.getParser("1 as tag").selectItem());
        System.out.println(SqlAstHelper.toSql(q));
    }
}
