package com.antlr.util;

import com.antlr.sqlbase.SqlBaseLexer;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonToken;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenSource;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.TokenStreamRewriter;
import org.antlr.v4.runtime.misc.Interval;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.TerminalNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.WeakHashMap;

import static org.antlr.v4.runtime.IntStream.EOF;

/**
 *
 * @author prs
 * @date 2019/08/21
 */
public class AntlrHelper {
    private static Logger logger = LoggerFactory.getLogger(AntlrHelper.class);
    /**
     * token流 弱引用Map
     */
    private static Map<TokenSource, TokenStreamRewriter> tokenSource2RewriteMap = Collections.synchronizedMap(new WeakHashMap<>());
    /**
     * tokenSource的重指向(replace的时候会出现重指向)
     * value的部分不允许出现2次重定向
     */
    private static Map<TokenSource, TokenSource> tokenSourceLink = Collections.synchronizedMap(new WeakHashMap<>());

    public static <T extends ParseTree> T up(ParseTree curr, Class<T> target) {
        while (curr != null) {
            curr = curr.getParent();
            if (target.isInstance(curr)) {
                return (T) curr;
            }
        }
        return null;
    }

    public static <T extends ParseTree> T down(ParseTree curr, Class<T> target) {
        List<T> arr = downs(curr, target);
        if (arr.size() > 0) {
            return arr.get(0);
        } else {
            return null;
        }
    }

    public static <T extends ParseTree> List<T> downs(ParseTree curr, Class<T> target) {
        List<T> children = new ArrayList();
        if (curr != null) {
            if (target.isInstance(curr)) {
                children.add((T) curr);
            } else {
                for (int i = 0; i < curr.getChildCount(); i++) {
                    ParseTree c = curr.getChild(i);
                    children.addAll(downs(c, target));
                }
            }
        }
        return children;
    }

    public static <T extends ParseTree> List<T> downs(ParseTree curr, Class<T> include, Class exclude) {
        List<T> children = new ArrayList();
        if (curr != null) {
            if (!exclude.isInstance(curr) && include.isInstance(curr)) {
                children.add((T) curr);
            } else {
                for (int i = 0; i < curr.getChildCount(); i++) {
                    ParseTree c = curr.getChild(i);
                    children.addAll(downs(c, include, exclude));
                }
            }
        }
        return children;
    }

    public static <T extends ParseTree> List<T> downsAll(ParseTree curr, Class<T> target) {
        List<T> children = new ArrayList();
        if (curr != null) {
            if (target.isInstance(curr)) {
                children.add((T) curr);
            }
            for (int i = 0; i < curr.getChildCount(); i++) {
                ParseTree c = curr.getChild(i);
                children.addAll(downsAll(c, target));
            }
        }
        return children;
    }

    public static void attachTokenStream(TokenStream origin, String txt) {
        TokenStreamRewriter rewrite = new TokenStreamRewriter(getTokenStream(txt));
        //如果不消费一遍就和origin tokenStream不一致了
        while (rewrite.getTokenStream().LA(1) != EOF) {
            rewrite.getTokenStream().consume();
        }
        //弱引用记录一下11对应关系
        tokenSource2RewriteMap.put(origin.getTokenSource(), rewrite);
    }

    static TokenStream getTokenStream(String txt) {
        ANTLRInputStream input = new ANTLRInputStream(txt);
        CommonTokenStream tokenStream = new CommonTokenStream(new SqlBaseLexer(input));
        return tokenStream;
    }

    /**
     * 删除节点
     *
     * @param origin
     */
    public static void remove(ParseTree origin) {
        replace(origin, null);
    }

    /**
     * 节点替换
     *
     * @param origin  原节点
     * @param replace 可以为null 代表删除origin节点
     *                replace节点的相关变化
     *                1：AST 结构里被替换
     *                2，replace sourceInterval被改写
     *                3, replace 下所有token的TokenSource被替换会origin的
     *                4，rewriteTokenStream执行字符的重写
     * @return
     */
    public static boolean replace(ParseTree origin, ParseTree replace) {
        if (origin == null) {
            return false;
        } else {
            //rewrite token流重写
            TokenStreamRewriter rewrite = getRewrite(origin);
            Interval interval = origin.getSourceInterval();
            if (replace == null && interval.a <= interval.b) {
                rewrite.delete(interval.a, interval.b);
            } else {
                if (replace != null) {
                    String replaceStr = SqlAstHelper.toSql(replace);
                    if (origin instanceof ParserRuleContext) {
                        ((CommonToken) (((ParserRuleContext) replace).getStart())).setTokenIndex(interval.a);
                        ((CommonToken) (((ParserRuleContext) replace).getStop())).setTokenIndex(interval.b);
                    } else if (origin instanceof TerminalNode) {
                        ((CommonToken) ((TerminalNode) replace).getSymbol()).setTokenIndex(interval.a);
                    } else {
                        throw new RuntimeException("parse error");
                    }
                    rewrite.replace(interval.a, interval.b, replaceStr);
                }
            }
            //AST内部对象之间关系的重赋予
            ParserRuleContext parent = null;
            if (origin.getParent() != null) {
                parent = (ParserRuleContext) origin.getParent();
            }
            if (parent != null) {
                int index = parent.children.indexOf(origin);
                if (index != -1) {
                    if (replace == null) {
                        parent.children.remove(index);
                    } else {
                        parent.children.set(index, replace);
                    }
                }
                if (replace != null && replace instanceof ParserRuleContext) {
                    ((ParserRuleContext) replace).parent = parent;
                }
                if (origin instanceof ParserRuleContext) {
                    ((ParserRuleContext) origin).parent = null;
                }
                try {
                    Field[] e = parent.getClass().getDeclaredFields();
                    Field[] var8 = e;
                    int var7 = e.length;
                    for (int var6 = 0; var6 < var7; ++var6) {
                        Field field = var8[var6];
                        field.setAccessible(true);
                        if (field.get(parent) == origin) {
                            field.set(parent, replace);
                        }
                    }
                } catch (IllegalAccessException var9) {
                    logger.error("", var9);
                }
            }

            if (replace != null) {
                //replace的rewriteTokenStream换成origin的(通过软link)
                TokenSource originTokenSource = getTokenSource(origin);
                TokenSource replaceTokenSource = getTokenSource(replace);
                //发生了变化
                if (originTokenSource != replaceTokenSource) {
                    tokenSourceLink.put(replaceTokenSource, originTokenSource);
                }
            }

            return true;
        }
    }

    public static ParseTree getRoot(ParseTree tree) {
        ParseTree curr = tree;
        while (curr.getParent() != null) {
            curr = curr.getParent();
        }
        return curr;
    }

    public static TokenStreamRewriter getRewrite(ParseTree node) {
        return tokenSource2RewriteMap.get(getTokenSource(node));
    }

    static Token getToken(ParseTree node) {
        Class<TerminalNode> c = TerminalNode.class;
        Token token = null;
        TerminalNode terminalNode = down(node, c);
        if (terminalNode == null) {
            ParserRuleContext n = down(node, ParserRuleContext.class);
            token = n.start;
        } else {
            token = terminalNode.getSymbol();
        }
        return token;
    }

    static TokenSource getTokenSource(ParseTree node) {
        TokenSource source = getToken(node).getTokenSource();
        //是否有软link重新指向
        if (tokenSourceLink.containsKey(source)) {
            return tokenSourceLink.get(source);
        } else {
            return source;
        }
    }

    public static Map<TokenSource, TokenStreamRewriter> getTokenSource2RewriteMap() {
        return tokenSource2RewriteMap;
    }

    public static Map<TokenSource, TokenSource> getTokenSourceLink() {
        return tokenSourceLink;
    }
}


