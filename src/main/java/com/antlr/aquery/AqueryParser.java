// Generated from D:/space/antlr-query/src/main/java/com/vip/vma/antlr/aquery\Aquery.g4 by ANTLR 4.5.1
package com.antlr.aquery;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class AqueryParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.5.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		T__9=10, T__10=11, GT=12, WS=13, TILDE=14, PLUS=15, ASTERISK=16, WORD=17, 
		COMMA=18, REG=19;
	public static final int
		RULE_query = 0, RULE_q_single = 1, RULE_q_breed = 2, RULE_q_test = 3, 
		RULE_q_node = 4, RULE_q_level_op = 5, RULE_reg = 6, RULE_q_filter = 7, 
		RULE_rule_name = 8;
	public static final String[] ruleNames = {
		"query", "q_single", "q_breed", "q_test", "q_node", "q_level_op", "reg", 
		"q_filter", "rule_name"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'('", "')'", "':eq('", "'['", "'!'", "'$'", "'=''", "'']'", "':is('", 
		"':not('", "':text('", "'>'", null, "'~'", "'+'", "'*'", null, "','"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, null, null, null, null, null, null, null, null, null, null, null, 
		"GT", "WS", "TILDE", "PLUS", "ASTERISK", "WORD", "COMMA", "REG"
	};
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}

	@Override
	public String getGrammarFileName() { return "Aquery.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public AqueryParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}
	public static class QueryContext extends ParserRuleContext {
		public QueryContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_query; }
	 
		public QueryContext() { }
		public void copyFrom(QueryContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class Single_queryContext extends QueryContext {
		public Q_singleContext q_single() {
			return getRuleContext(Q_singleContext.class,0);
		}
		public Single_queryContext(QueryContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AqueryListener ) ((AqueryListener)listener).enterSingle_query(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AqueryListener ) ((AqueryListener)listener).exitSingle_query(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AqueryVisitor ) return ((AqueryVisitor<? extends T>)visitor).visitSingle_query(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class Muti_queryContext extends QueryContext {
		public List<Q_singleContext> q_single() {
			return getRuleContexts(Q_singleContext.class);
		}
		public Q_singleContext q_single(int i) {
			return getRuleContext(Q_singleContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(AqueryParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(AqueryParser.COMMA, i);
		}
		public Muti_queryContext(QueryContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AqueryListener ) ((AqueryListener)listener).enterMuti_query(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AqueryListener ) ((AqueryListener)listener).exitMuti_query(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AqueryVisitor ) return ((AqueryVisitor<? extends T>)visitor).visitMuti_query(this);
			else return visitor.visitChildren(this);
		}
	}

	public final QueryContext query() throws RecognitionException {
		QueryContext _localctx = new QueryContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_query);
		int _la;
		try {
			setState(26);
			switch ( getInterpreter().adaptivePredict(_input,1,_ctx) ) {
			case 1:
				_localctx = new Single_queryContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(18);
				q_single(0);
				}
				break;
			case 2:
				_localctx = new Muti_queryContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(19);
				q_single(0);
				setState(22); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(20);
					match(COMMA);
					setState(21);
					q_single(0);
					}
					}
					setState(24); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( _la==COMMA );
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Q_singleContext extends ParserRuleContext {
		public Q_singleContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_q_single; }
	 
		public Q_singleContext() { }
		public void copyFrom(Q_singleContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class Test_queryContext extends Q_singleContext {
		public Q_testContext q_test() {
			return getRuleContext(Q_testContext.class,0);
		}
		public Test_queryContext(Q_singleContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AqueryListener ) ((AqueryListener)listener).enterTest_query(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AqueryListener ) ((AqueryListener)listener).exitTest_query(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AqueryVisitor ) return ((AqueryVisitor<? extends T>)visitor).visitTest_query(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class Base_queryContext extends Q_singleContext {
		public List<Q_singleContext> q_single() {
			return getRuleContexts(Q_singleContext.class);
		}
		public Q_singleContext q_single(int i) {
			return getRuleContext(Q_singleContext.class,i);
		}
		public Base_queryContext(Q_singleContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AqueryListener ) ((AqueryListener)listener).enterBase_query(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AqueryListener ) ((AqueryListener)listener).exitBase_query(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AqueryVisitor ) return ((AqueryVisitor<? extends T>)visitor).visitBase_query(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class Breed_queryContext extends Q_singleContext {
		public Q_breedContext q_breed() {
			return getRuleContext(Q_breedContext.class,0);
		}
		public Breed_queryContext(Q_singleContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AqueryListener ) ((AqueryListener)listener).enterBreed_query(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AqueryListener ) ((AqueryListener)listener).exitBreed_query(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AqueryVisitor ) return ((AqueryVisitor<? extends T>)visitor).visitBreed_query(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class Unit_queryContext extends Q_singleContext {
		public QueryContext query() {
			return getRuleContext(QueryContext.class,0);
		}
		public Unit_queryContext(Q_singleContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AqueryListener ) ((AqueryListener)listener).enterUnit_query(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AqueryListener ) ((AqueryListener)listener).exitUnit_query(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AqueryVisitor ) return ((AqueryVisitor<? extends T>)visitor).visitUnit_query(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Q_singleContext q_single() throws RecognitionException {
		return q_single(0);
	}

	private Q_singleContext q_single(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		Q_singleContext _localctx = new Q_singleContext(_ctx, _parentState);
		Q_singleContext _prevctx = _localctx;
		int _startState = 2;
		enterRecursionRule(_localctx, 2, RULE_q_single, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(35);
			switch ( getInterpreter().adaptivePredict(_input,2,_ctx) ) {
			case 1:
				{
				_localctx = new Breed_queryContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;

				setState(29);
				q_breed();
				}
				break;
			case 2:
				{
				_localctx = new Test_queryContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(30);
				q_test();
				}
				break;
			case 3:
				{
				_localctx = new Unit_queryContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(31);
				match(T__0);
				setState(32);
				query();
				setState(33);
				match(T__1);
				}
				break;
			}
			_ctx.stop = _input.LT(-1);
			setState(41);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,3,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					{
					_localctx = new Base_queryContext(new Q_singleContext(_parentctx, _parentState));
					pushNewRecursionContext(_localctx, _startState, RULE_q_single);
					setState(37);
					if (!(precpred(_ctx, 1))) throw new FailedPredicateException(this, "precpred(_ctx, 1)");
					setState(38);
					q_single(2);
					}
					} 
				}
				setState(43);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,3,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public static class Q_breedContext extends ParserRuleContext {
		public Q_level_opContext q_level_op() {
			return getRuleContext(Q_level_opContext.class,0);
		}
		public Q_breedContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_q_breed; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AqueryListener ) ((AqueryListener)listener).enterQ_breed(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AqueryListener ) ((AqueryListener)listener).exitQ_breed(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AqueryVisitor ) return ((AqueryVisitor<? extends T>)visitor).visitQ_breed(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Q_breedContext q_breed() throws RecognitionException {
		Q_breedContext _localctx = new Q_breedContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_q_breed);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(44);
			q_level_op();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Q_testContext extends ParserRuleContext {
		public Q_nodeContext q_node() {
			return getRuleContext(Q_nodeContext.class,0);
		}
		public Q_filterContext q_filter() {
			return getRuleContext(Q_filterContext.class,0);
		}
		public Q_testContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_q_test; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AqueryListener ) ((AqueryListener)listener).enterQ_test(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AqueryListener ) ((AqueryListener)listener).exitQ_test(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AqueryVisitor ) return ((AqueryVisitor<? extends T>)visitor).visitQ_test(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Q_testContext q_test() throws RecognitionException {
		Q_testContext _localctx = new Q_testContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_q_test);
		try {
			setState(48);
			switch (_input.LA(1)) {
			case ASTERISK:
			case WORD:
				enterOuterAlt(_localctx, 1);
				{
				setState(46);
				q_node();
				}
				break;
			case T__2:
			case T__3:
			case T__8:
			case T__9:
			case T__10:
				enterOuterAlt(_localctx, 2);
				{
				setState(47);
				q_filter();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Q_nodeContext extends ParserRuleContext {
		public Rule_nameContext rule_name() {
			return getRuleContext(Rule_nameContext.class,0);
		}
		public TerminalNode ASTERISK() { return getToken(AqueryParser.ASTERISK, 0); }
		public Q_nodeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_q_node; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AqueryListener ) ((AqueryListener)listener).enterQ_node(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AqueryListener ) ((AqueryListener)listener).exitQ_node(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AqueryVisitor ) return ((AqueryVisitor<? extends T>)visitor).visitQ_node(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Q_nodeContext q_node() throws RecognitionException {
		Q_nodeContext _localctx = new Q_nodeContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_q_node);
		try {
			setState(52);
			switch (_input.LA(1)) {
			case WORD:
				enterOuterAlt(_localctx, 1);
				{
				setState(50);
				rule_name();
				}
				break;
			case ASTERISK:
				enterOuterAlt(_localctx, 2);
				{
				setState(51);
				match(ASTERISK);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Q_level_opContext extends ParserRuleContext {
		public Q_level_opContext level;
		public Q_filterContext filter;
		public TerminalNode WS() { return getToken(AqueryParser.WS, 0); }
		public TerminalNode TILDE() { return getToken(AqueryParser.TILDE, 0); }
		public TerminalNode PLUS() { return getToken(AqueryParser.PLUS, 0); }
		public TerminalNode GT() { return getToken(AqueryParser.GT, 0); }
		public Q_level_opContext q_level_op() {
			return getRuleContext(Q_level_opContext.class,0);
		}
		public Q_filterContext q_filter() {
			return getRuleContext(Q_filterContext.class,0);
		}
		public Q_level_opContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_q_level_op; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AqueryListener ) ((AqueryListener)listener).enterQ_level_op(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AqueryListener ) ((AqueryListener)listener).exitQ_level_op(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AqueryVisitor ) return ((AqueryVisitor<? extends T>)visitor).visitQ_level_op(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Q_level_opContext q_level_op() throws RecognitionException {
		Q_level_opContext _localctx = new Q_level_opContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_q_level_op);
		try {
			setState(63);
			switch (_input.LA(1)) {
			case WS:
				enterOuterAlt(_localctx, 1);
				{
				setState(54);
				match(WS);
				}
				break;
			case TILDE:
				enterOuterAlt(_localctx, 2);
				{
				setState(55);
				match(TILDE);
				}
				break;
			case PLUS:
				enterOuterAlt(_localctx, 3);
				{
				setState(56);
				match(PLUS);
				}
				break;
			case GT:
				enterOuterAlt(_localctx, 4);
				{
				setState(57);
				match(GT);
				}
				break;
			case T__0:
				enterOuterAlt(_localctx, 5);
				{
				setState(58);
				match(T__0);
				setState(59);
				((Q_level_opContext)_localctx).level = q_level_op();
				setState(60);
				((Q_level_opContext)_localctx).filter = q_filter();
				setState(61);
				match(T__1);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class RegContext extends ParserRuleContext {
		public List<TerminalNode> REG() { return getTokens(AqueryParser.REG); }
		public TerminalNode REG(int i) {
			return getToken(AqueryParser.REG, i);
		}
		public List<TerminalNode> WORD() { return getTokens(AqueryParser.WORD); }
		public TerminalNode WORD(int i) {
			return getToken(AqueryParser.WORD, i);
		}
		public List<TerminalNode> PLUS() { return getTokens(AqueryParser.PLUS); }
		public TerminalNode PLUS(int i) {
			return getToken(AqueryParser.PLUS, i);
		}
		public List<TerminalNode> ASTERISK() { return getTokens(AqueryParser.ASTERISK); }
		public TerminalNode ASTERISK(int i) {
			return getToken(AqueryParser.ASTERISK, i);
		}
		public RegContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_reg; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AqueryListener ) ((AqueryListener)listener).enterReg(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AqueryListener ) ((AqueryListener)listener).exitReg(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AqueryVisitor ) return ((AqueryVisitor<? extends T>)visitor).visitReg(this);
			else return visitor.visitChildren(this);
		}
	}

	public final RegContext reg() throws RecognitionException {
		RegContext _localctx = new RegContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_reg);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(66); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(65);
				_la = _input.LA(1);
				if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << PLUS) | (1L << ASTERISK) | (1L << WORD) | (1L << REG))) != 0)) ) {
				_errHandler.recoverInline(this);
				} else {
					consume();
				}
				}
				}
				setState(68); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << PLUS) | (1L << ASTERISK) | (1L << WORD) | (1L << REG))) != 0) );
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Q_filterContext extends ParserRuleContext {
		public Q_filterContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_q_filter; }
	 
		public Q_filterContext() { }
		public void copyFrom(Q_filterContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class Filter_textContext extends Q_filterContext {
		public RegContext reg() {
			return getRuleContext(RegContext.class,0);
		}
		public Filter_textContext(Q_filterContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AqueryListener ) ((AqueryListener)listener).enterFilter_text(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AqueryListener ) ((AqueryListener)listener).exitFilter_text(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AqueryVisitor ) return ((AqueryVisitor<? extends T>)visitor).visitFilter_text(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class Filter_eqContext extends Q_filterContext {
		public TerminalNode WORD() { return getToken(AqueryParser.WORD, 0); }
		public Filter_eqContext(Q_filterContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AqueryListener ) ((AqueryListener)listener).enterFilter_eq(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AqueryListener ) ((AqueryListener)listener).exitFilter_eq(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AqueryVisitor ) return ((AqueryVisitor<? extends T>)visitor).visitFilter_eq(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class Filter_attrContext extends Q_filterContext {
		public List<TerminalNode> WORD() { return getTokens(AqueryParser.WORD); }
		public TerminalNode WORD(int i) {
			return getToken(AqueryParser.WORD, i);
		}
		public Filter_attrContext(Q_filterContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AqueryListener ) ((AqueryListener)listener).enterFilter_attr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AqueryListener ) ((AqueryListener)listener).exitFilter_attr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AqueryVisitor ) return ((AqueryVisitor<? extends T>)visitor).visitFilter_attr(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class Filter_isContext extends Q_filterContext {
		public QueryContext query() {
			return getRuleContext(QueryContext.class,0);
		}
		public Filter_isContext(Q_filterContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AqueryListener ) ((AqueryListener)listener).enterFilter_is(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AqueryListener ) ((AqueryListener)listener).exitFilter_is(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AqueryVisitor ) return ((AqueryVisitor<? extends T>)visitor).visitFilter_is(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class Filter_notContext extends Q_filterContext {
		public QueryContext query() {
			return getRuleContext(QueryContext.class,0);
		}
		public Filter_notContext(Q_filterContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AqueryListener ) ((AqueryListener)listener).enterFilter_not(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AqueryListener ) ((AqueryListener)listener).exitFilter_not(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AqueryVisitor ) return ((AqueryVisitor<? extends T>)visitor).visitFilter_not(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Q_filterContext q_filter() throws RecognitionException {
		Q_filterContext _localctx = new Q_filterContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_q_filter);
		int _la;
		try {
			setState(93);
			switch (_input.LA(1)) {
			case T__2:
				_localctx = new Filter_eqContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(70);
				match(T__2);
				setState(71);
				match(WORD);
				setState(72);
				match(T__1);
				}
				break;
			case T__3:
				_localctx = new Filter_attrContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(73);
				match(T__3);
				setState(74);
				match(WORD);
				setState(76);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__4) | (1L << T__5) | (1L << ASTERISK))) != 0)) {
					{
					setState(75);
					_la = _input.LA(1);
					if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__4) | (1L << T__5) | (1L << ASTERISK))) != 0)) ) {
					_errHandler.recoverInline(this);
					} else {
						consume();
					}
					}
				}

				setState(78);
				match(T__6);
				setState(79);
				match(WORD);
				setState(80);
				match(T__7);
				}
				break;
			case T__8:
				_localctx = new Filter_isContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(81);
				match(T__8);
				setState(82);
				query();
				setState(83);
				match(T__1);
				}
				break;
			case T__9:
				_localctx = new Filter_notContext(_localctx);
				enterOuterAlt(_localctx, 4);
				{
				setState(85);
				match(T__9);
				setState(86);
				query();
				setState(87);
				match(T__1);
				}
				break;
			case T__10:
				_localctx = new Filter_textContext(_localctx);
				enterOuterAlt(_localctx, 5);
				{
				setState(89);
				match(T__10);
				setState(90);
				reg();
				setState(91);
				match(T__1);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Rule_nameContext extends ParserRuleContext {
		public TerminalNode WORD() { return getToken(AqueryParser.WORD, 0); }
		public Rule_nameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_rule_name; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AqueryListener ) ((AqueryListener)listener).enterRule_name(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AqueryListener ) ((AqueryListener)listener).exitRule_name(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AqueryVisitor ) return ((AqueryVisitor<? extends T>)visitor).visitRule_name(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Rule_nameContext rule_name() throws RecognitionException {
		Rule_nameContext _localctx = new Rule_nameContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_rule_name);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(95);
			match(WORD);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public boolean sempred(RuleContext _localctx, int ruleIndex, int predIndex) {
		switch (ruleIndex) {
		case 1:
			return q_single_sempred((Q_singleContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean q_single_sempred(Q_singleContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0:
			return precpred(_ctx, 1);
		}
		return true;
	}

	public static final String _serializedATN =
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\3\25d\4\2\t\2\4\3\t"+
		"\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\3\2\3\2\3\2"+
		"\3\2\6\2\31\n\2\r\2\16\2\32\5\2\35\n\2\3\3\3\3\3\3\3\3\3\3\3\3\3\3\5\3"+
		"&\n\3\3\3\3\3\7\3*\n\3\f\3\16\3-\13\3\3\4\3\4\3\5\3\5\5\5\63\n\5\3\6\3"+
		"\6\5\6\67\n\6\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\5\7B\n\7\3\b\6\bE\n"+
		"\b\r\b\16\bF\3\t\3\t\3\t\3\t\3\t\3\t\5\tO\n\t\3\t\3\t\3\t\3\t\3\t\3\t"+
		"\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\5\t`\n\t\3\n\3\n\3\n\2\3\4\13\2\4"+
		"\6\b\n\f\16\20\22\2\4\4\2\21\23\25\25\4\2\7\b\22\22k\2\34\3\2\2\2\4%\3"+
		"\2\2\2\6.\3\2\2\2\b\62\3\2\2\2\n\66\3\2\2\2\fA\3\2\2\2\16D\3\2\2\2\20"+
		"_\3\2\2\2\22a\3\2\2\2\24\35\5\4\3\2\25\30\5\4\3\2\26\27\7\24\2\2\27\31"+
		"\5\4\3\2\30\26\3\2\2\2\31\32\3\2\2\2\32\30\3\2\2\2\32\33\3\2\2\2\33\35"+
		"\3\2\2\2\34\24\3\2\2\2\34\25\3\2\2\2\35\3\3\2\2\2\36\37\b\3\1\2\37&\5"+
		"\6\4\2 &\5\b\5\2!\"\7\3\2\2\"#\5\2\2\2#$\7\4\2\2$&\3\2\2\2%\36\3\2\2\2"+
		"% \3\2\2\2%!\3\2\2\2&+\3\2\2\2\'(\f\3\2\2(*\5\4\3\4)\'\3\2\2\2*-\3\2\2"+
		"\2+)\3\2\2\2+,\3\2\2\2,\5\3\2\2\2-+\3\2\2\2./\5\f\7\2/\7\3\2\2\2\60\63"+
		"\5\n\6\2\61\63\5\20\t\2\62\60\3\2\2\2\62\61\3\2\2\2\63\t\3\2\2\2\64\67"+
		"\5\22\n\2\65\67\7\22\2\2\66\64\3\2\2\2\66\65\3\2\2\2\67\13\3\2\2\28B\7"+
		"\17\2\29B\7\20\2\2:B\7\21\2\2;B\7\16\2\2<=\7\3\2\2=>\5\f\7\2>?\5\20\t"+
		"\2?@\7\4\2\2@B\3\2\2\2A8\3\2\2\2A9\3\2\2\2A:\3\2\2\2A;\3\2\2\2A<\3\2\2"+
		"\2B\r\3\2\2\2CE\t\2\2\2DC\3\2\2\2EF\3\2\2\2FD\3\2\2\2FG\3\2\2\2G\17\3"+
		"\2\2\2HI\7\5\2\2IJ\7\23\2\2J`\7\4\2\2KL\7\6\2\2LN\7\23\2\2MO\t\3\2\2N"+
		"M\3\2\2\2NO\3\2\2\2OP\3\2\2\2PQ\7\t\2\2QR\7\23\2\2R`\7\n\2\2ST\7\13\2"+
		"\2TU\5\2\2\2UV\7\4\2\2V`\3\2\2\2WX\7\f\2\2XY\5\2\2\2YZ\7\4\2\2Z`\3\2\2"+
		"\2[\\\7\r\2\2\\]\5\16\b\2]^\7\4\2\2^`\3\2\2\2_H\3\2\2\2_K\3\2\2\2_S\3"+
		"\2\2\2_W\3\2\2\2_[\3\2\2\2`\21\3\2\2\2ab\7\23\2\2b\23\3\2\2\2\f\32\34"+
		"%+\62\66AFN_";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}