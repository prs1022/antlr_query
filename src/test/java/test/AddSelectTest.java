package test;

import com.antlr.sqlbase.SqlBaseParser;
import com.antlr.util.AntlrHelper;
import com.antlr.util.SqlAstHelper;
import org.antlr.runtime.Token;
import org.antlr.v4.runtime.CommonToken;
import org.antlr.v4.runtime.tree.TerminalNodeImpl;
import org.apache.commons.lang3.math.NumberUtils;

import java.util.List;

public class AddSelectTest {


    public static void main(String[] args) {

//        String floatType = "-?([1-9]\\d*.\\d*|0.\\d*[1-9]\\d*)";
//        String intType = "-?[1-9]\\d*";
//
//        Pattern pattern = Pattern.compile(floatType);
//        Matcher matcher = pattern.matcher("9L05");
//        if(matcher.matches()){
//            System.out.println("匹配成功");
//        }

        System.out.println(NumberUtils.isNumber("99.99"));

//        String sql = "select a,b from A";
//        SqlBaseParser parseTree = SqlAstHelper.getParser(sql);
//        TokenStreamRewriter tokenStreamWriter = new TokenStreamRewriter(parseTree.getTokenStream());
//        tokenStreamWriter.insertAfter(0,"aaa");
//        System.out.println(SqlAstHelper.toSql(parseTree.querySpecification()));
    }


    public static void main1(String[] args) {
        String sql = "select a,b from A";
//        ComplexQuery complexQ = InstructionBuilder.build("querySpecification >querySpecification");
        SqlBaseParser parseTree = SqlAstHelper.getParser(sql);
//        SqlBaseParser.QuerySpecificationContext querySpecificationContext = parseTree.querySpecification();
//        Set<ParseTree> query = complexQ.query(querySpecificationContext);
//        SqlBaseParser.QuerySpecificationContext querySpecificationContext1 = (SqlBaseParser.QuerySpecificationContext) query.toArray()[0];
        SqlBaseParser.QuerySpecificationContext querySpecificationContext1 = parseTree.querySpecification();
        System.out.println(SqlAstHelper.toSql(querySpecificationContext1));
        addSelect(querySpecificationContext1, querySpecificationContext1.selectItem());
        System.out.println(SqlAstHelper.toSql(querySpecificationContext1));

    }

    private static void addSelect(SqlBaseParser.QuerySpecificationContext querySpecificationContext, List<SqlBaseParser.SelectItemContext> selectItemContexts) {
        SqlBaseParser.SelectItemContext lastSelectItem = querySpecificationContext.selectItem(querySpecificationContext.selectItem().size() - 1);
        for (SqlBaseParser.SelectItemContext selectItem : selectItemContexts) {
            AntlrHelper.getRewrite(querySpecificationContext).insertAfter(lastSelectItem.stop.getTokenIndex(), new TerminalNodeImpl(new CommonToken(Token.DEFAULT_CHANNEL, ",")));
            AntlrHelper.getRewrite(querySpecificationContext).insertAfter(lastSelectItem.stop.getTokenIndex(), selectItem.getText());
        }
    }
}
