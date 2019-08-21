package com.antlr.instruction;

import org.antlr.v4.runtime.tree.ParseTree;
import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 多选择器并行查询
 * 逗号隔开
 *
 * @author prs
 * @date 2019/08/21
 */
public class MultiQuery extends QueryInstruction {

    private List<ComplexQuery> queryList;

    public MultiQuery(List<ComplexQuery> queryList) {
        this.queryList = queryList;
    }

    @Override
    Set<ParseTree> query(ParseTree tree) {
        return queryList.parallelStream().flatMap(
                q -> q.query(tree).stream()
        ).collect(Collectors.toSet());
    }

    @Override
    public String toString() {
        return StringUtils.join(queryList, ",");
    }
}
