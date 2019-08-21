// Generated from D:/space/antlr-query/src/main/java/com/vip/vma/antlr/aquery\Aquery.g4 by ANTLR 4.5.1
package com.antlr.aquery;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link AqueryParser}.
 */
public interface AqueryListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by the {@code single_query}
	 * labeled alternative in {@link AqueryParser#query}.
	 * @param ctx the parse tree
	 */
	void enterSingle_query(AqueryParser.Single_queryContext ctx);
	/**
	 * Exit a parse tree produced by the {@code single_query}
	 * labeled alternative in {@link AqueryParser#query}.
	 * @param ctx the parse tree
	 */
	void exitSingle_query(AqueryParser.Single_queryContext ctx);
	/**
	 * Enter a parse tree produced by the {@code muti_query}
	 * labeled alternative in {@link AqueryParser#query}.
	 * @param ctx the parse tree
	 */
	void enterMuti_query(AqueryParser.Muti_queryContext ctx);
	/**
	 * Exit a parse tree produced by the {@code muti_query}
	 * labeled alternative in {@link AqueryParser#query}.
	 * @param ctx the parse tree
	 */
	void exitMuti_query(AqueryParser.Muti_queryContext ctx);
	/**
	 * Enter a parse tree produced by the {@code test_query}
	 * labeled alternative in {@link AqueryParser#q_single}.
	 * @param ctx the parse tree
	 */
	void enterTest_query(AqueryParser.Test_queryContext ctx);
	/**
	 * Exit a parse tree produced by the {@code test_query}
	 * labeled alternative in {@link AqueryParser#q_single}.
	 * @param ctx the parse tree
	 */
	void exitTest_query(AqueryParser.Test_queryContext ctx);
	/**
	 * Enter a parse tree produced by the {@code base_query}
	 * labeled alternative in {@link AqueryParser#q_single}.
	 * @param ctx the parse tree
	 */
	void enterBase_query(AqueryParser.Base_queryContext ctx);
	/**
	 * Exit a parse tree produced by the {@code base_query}
	 * labeled alternative in {@link AqueryParser#q_single}.
	 * @param ctx the parse tree
	 */
	void exitBase_query(AqueryParser.Base_queryContext ctx);
	/**
	 * Enter a parse tree produced by the {@code breed_query}
	 * labeled alternative in {@link AqueryParser#q_single}.
	 * @param ctx the parse tree
	 */
	void enterBreed_query(AqueryParser.Breed_queryContext ctx);
	/**
	 * Exit a parse tree produced by the {@code breed_query}
	 * labeled alternative in {@link AqueryParser#q_single}.
	 * @param ctx the parse tree
	 */
	void exitBreed_query(AqueryParser.Breed_queryContext ctx);
	/**
	 * Enter a parse tree produced by the {@code unit_query}
	 * labeled alternative in {@link AqueryParser#q_single}.
	 * @param ctx the parse tree
	 */
	void enterUnit_query(AqueryParser.Unit_queryContext ctx);
	/**
	 * Exit a parse tree produced by the {@code unit_query}
	 * labeled alternative in {@link AqueryParser#q_single}.
	 * @param ctx the parse tree
	 */
	void exitUnit_query(AqueryParser.Unit_queryContext ctx);
	/**
	 * Enter a parse tree produced by {@link AqueryParser#q_breed}.
	 * @param ctx the parse tree
	 */
	void enterQ_breed(AqueryParser.Q_breedContext ctx);
	/**
	 * Exit a parse tree produced by {@link AqueryParser#q_breed}.
	 * @param ctx the parse tree
	 */
	void exitQ_breed(AqueryParser.Q_breedContext ctx);
	/**
	 * Enter a parse tree produced by {@link AqueryParser#q_test}.
	 * @param ctx the parse tree
	 */
	void enterQ_test(AqueryParser.Q_testContext ctx);
	/**
	 * Exit a parse tree produced by {@link AqueryParser#q_test}.
	 * @param ctx the parse tree
	 */
	void exitQ_test(AqueryParser.Q_testContext ctx);
	/**
	 * Enter a parse tree produced by {@link AqueryParser#q_node}.
	 * @param ctx the parse tree
	 */
	void enterQ_node(AqueryParser.Q_nodeContext ctx);
	/**
	 * Exit a parse tree produced by {@link AqueryParser#q_node}.
	 * @param ctx the parse tree
	 */
	void exitQ_node(AqueryParser.Q_nodeContext ctx);
	/**
	 * Enter a parse tree produced by {@link AqueryParser#q_level_op}.
	 * @param ctx the parse tree
	 */
	void enterQ_level_op(AqueryParser.Q_level_opContext ctx);
	/**
	 * Exit a parse tree produced by {@link AqueryParser#q_level_op}.
	 * @param ctx the parse tree
	 */
	void exitQ_level_op(AqueryParser.Q_level_opContext ctx);
	/**
	 * Enter a parse tree produced by {@link AqueryParser#reg}.
	 * @param ctx the parse tree
	 */
	void enterReg(AqueryParser.RegContext ctx);
	/**
	 * Exit a parse tree produced by {@link AqueryParser#reg}.
	 * @param ctx the parse tree
	 */
	void exitReg(AqueryParser.RegContext ctx);
	/**
	 * Enter a parse tree produced by the {@code filter_eq}
	 * labeled alternative in {@link AqueryParser#q_filter}.
	 * @param ctx the parse tree
	 */
	void enterFilter_eq(AqueryParser.Filter_eqContext ctx);
	/**
	 * Exit a parse tree produced by the {@code filter_eq}
	 * labeled alternative in {@link AqueryParser#q_filter}.
	 * @param ctx the parse tree
	 */
	void exitFilter_eq(AqueryParser.Filter_eqContext ctx);
	/**
	 * Enter a parse tree produced by the {@code filter_attr}
	 * labeled alternative in {@link AqueryParser#q_filter}.
	 * @param ctx the parse tree
	 */
	void enterFilter_attr(AqueryParser.Filter_attrContext ctx);
	/**
	 * Exit a parse tree produced by the {@code filter_attr}
	 * labeled alternative in {@link AqueryParser#q_filter}.
	 * @param ctx the parse tree
	 */
	void exitFilter_attr(AqueryParser.Filter_attrContext ctx);
	/**
	 * Enter a parse tree produced by the {@code filter_is}
	 * labeled alternative in {@link AqueryParser#q_filter}.
	 * @param ctx the parse tree
	 */
	void enterFilter_is(AqueryParser.Filter_isContext ctx);
	/**
	 * Exit a parse tree produced by the {@code filter_is}
	 * labeled alternative in {@link AqueryParser#q_filter}.
	 * @param ctx the parse tree
	 */
	void exitFilter_is(AqueryParser.Filter_isContext ctx);
	/**
	 * Enter a parse tree produced by the {@code filter_not}
	 * labeled alternative in {@link AqueryParser#q_filter}.
	 * @param ctx the parse tree
	 */
	void enterFilter_not(AqueryParser.Filter_notContext ctx);
	/**
	 * Exit a parse tree produced by the {@code filter_not}
	 * labeled alternative in {@link AqueryParser#q_filter}.
	 * @param ctx the parse tree
	 */
	void exitFilter_not(AqueryParser.Filter_notContext ctx);
	/**
	 * Enter a parse tree produced by the {@code filter_text}
	 * labeled alternative in {@link AqueryParser#q_filter}.
	 * @param ctx the parse tree
	 */
	void enterFilter_text(AqueryParser.Filter_textContext ctx);
	/**
	 * Exit a parse tree produced by the {@code filter_text}
	 * labeled alternative in {@link AqueryParser#q_filter}.
	 * @param ctx the parse tree
	 */
	void exitFilter_text(AqueryParser.Filter_textContext ctx);
	/**
	 * Enter a parse tree produced by {@link AqueryParser#rule_name}.
	 * @param ctx the parse tree
	 */
	void enterRule_name(AqueryParser.Rule_nameContext ctx);
	/**
	 * Exit a parse tree produced by {@link AqueryParser#rule_name}.
	 * @param ctx the parse tree
	 */
	void exitRule_name(AqueryParser.Rule_nameContext ctx);
}