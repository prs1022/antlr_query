// Generated from D:/space/antlr-query/src/main/java/com/vip/vma/antlr/aquery\Aquery.g4 by ANTLR 4.5.1
package com.antlr.aquery;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link AqueryParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface AqueryVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by the {@code single_query}
	 * labeled alternative in {@link AqueryParser#query}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSingle_query(AqueryParser.Single_queryContext ctx);
	/**
	 * Visit a parse tree produced by the {@code muti_query}
	 * labeled alternative in {@link AqueryParser#query}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMuti_query(AqueryParser.Muti_queryContext ctx);
	/**
	 * Visit a parse tree produced by the {@code test_query}
	 * labeled alternative in {@link AqueryParser#q_single}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTest_query(AqueryParser.Test_queryContext ctx);
	/**
	 * Visit a parse tree produced by the {@code base_query}
	 * labeled alternative in {@link AqueryParser#q_single}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBase_query(AqueryParser.Base_queryContext ctx);
	/**
	 * Visit a parse tree produced by the {@code breed_query}
	 * labeled alternative in {@link AqueryParser#q_single}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBreed_query(AqueryParser.Breed_queryContext ctx);
	/**
	 * Visit a parse tree produced by the {@code unit_query}
	 * labeled alternative in {@link AqueryParser#q_single}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUnit_query(AqueryParser.Unit_queryContext ctx);
	/**
	 * Visit a parse tree produced by {@link AqueryParser#q_breed}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitQ_breed(AqueryParser.Q_breedContext ctx);
	/**
	 * Visit a parse tree produced by {@link AqueryParser#q_test}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitQ_test(AqueryParser.Q_testContext ctx);
	/**
	 * Visit a parse tree produced by {@link AqueryParser#q_node}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitQ_node(AqueryParser.Q_nodeContext ctx);
	/**
	 * Visit a parse tree produced by {@link AqueryParser#q_level_op}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitQ_level_op(AqueryParser.Q_level_opContext ctx);
	/**
	 * Visit a parse tree produced by {@link AqueryParser#reg}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitReg(AqueryParser.RegContext ctx);
	/**
	 * Visit a parse tree produced by the {@code filter_eq}
	 * labeled alternative in {@link AqueryParser#q_filter}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFilter_eq(AqueryParser.Filter_eqContext ctx);
	/**
	 * Visit a parse tree produced by the {@code filter_attr}
	 * labeled alternative in {@link AqueryParser#q_filter}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFilter_attr(AqueryParser.Filter_attrContext ctx);
	/**
	 * Visit a parse tree produced by the {@code filter_is}
	 * labeled alternative in {@link AqueryParser#q_filter}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFilter_is(AqueryParser.Filter_isContext ctx);
	/**
	 * Visit a parse tree produced by the {@code filter_not}
	 * labeled alternative in {@link AqueryParser#q_filter}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFilter_not(AqueryParser.Filter_notContext ctx);
	/**
	 * Visit a parse tree produced by the {@code filter_text}
	 * labeled alternative in {@link AqueryParser#q_filter}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFilter_text(AqueryParser.Filter_textContext ctx);
	/**
	 * Visit a parse tree produced by {@link AqueryParser#rule_name}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRule_name(AqueryParser.Rule_nameContext ctx);
}