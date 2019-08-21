// Generated from D:/space/antlr-query/src/main/java/com/vip/vma/antlr/aquery\Aquery.g4 by ANTLR 4.5.1
package com.antlr.aquery;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class AqueryLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.5.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		T__9=10, T__10=11, GT=12, WS=13, TILDE=14, PLUS=15, ASTERISK=16, WORD=17, 
		COMMA=18, REG=19;
	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] ruleNames = {
		"T__0", "T__1", "T__2", "T__3", "T__4", "T__5", "T__6", "T__7", "T__8", 
		"T__9", "T__10", "GT", "WS", "TILDE", "PLUS", "ASTERISK", "WORD", "COMMA", 
		"REG", "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", 
		"N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z", "HANZI", 
		"DIGIT", "LETTER"
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


	public AqueryLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "Aquery.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public static final String _serializedATN =
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\2\25\u00e1\b\1\4\2"+
		"\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4"+
		"\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22"+
		"\t\22\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31"+
		"\t\31\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t"+
		" \4!\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\4)\t)\4*\t*\4+\t"+
		"+\4,\t,\4-\t-\4.\t.\4/\t/\4\60\t\60\4\61\t\61\3\2\3\2\3\3\3\3\3\4\3\4"+
		"\3\4\3\4\3\4\3\5\3\5\3\6\3\6\3\7\3\7\3\b\3\b\3\b\3\t\3\t\3\t\3\n\3\n\3"+
		"\n\3\n\3\n\3\13\3\13\3\13\3\13\3\13\3\13\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3"+
		"\r\3\r\3\16\6\16\u008e\n\16\r\16\16\16\u008f\3\17\3\17\3\20\3\20\3\21"+
		"\3\21\3\22\3\22\3\22\6\22\u009b\n\22\r\22\16\22\u009c\3\23\3\23\3\24\3"+
		"\24\3\24\6\24\u00a4\n\24\r\24\16\24\u00a5\3\25\3\25\3\26\3\26\3\27\3\27"+
		"\3\30\3\30\3\31\3\31\3\32\3\32\3\33\3\33\3\34\3\34\3\35\3\35\3\36\3\36"+
		"\3\37\3\37\3 \3 \3!\3!\3\"\3\"\3#\3#\3$\3$\3%\3%\3&\3&\3\'\3\'\3(\3(\3"+
		")\3)\3*\3*\3+\3+\3,\3,\3-\3-\3.\3.\3/\3/\3\60\3\60\3\61\3\61\2\2\62\3"+
		"\3\5\4\7\5\t\6\13\7\r\b\17\t\21\n\23\13\25\f\27\r\31\16\33\17\35\20\37"+
		"\21!\22#\23%\24\'\25)\2+\2-\2/\2\61\2\63\2\65\2\67\29\2;\2=\2?\2A\2C\2"+
		"E\2G\2I\2K\2M\2O\2Q\2S\2U\2W\2Y\2[\2]\2_\2a\2\3\2!\5\2\13\f\17\17\"\""+
		"\6\2$$&&\60\60aa\6\2AA]`}}\177\177\4\2CCcc\4\2DDdd\4\2EEee\4\2FFff\4\2"+
		"GGgg\4\2HHhh\4\2IIii\4\2JJjj\4\2KKkk\4\2LLll\4\2MMmm\4\2NNnn\4\2OOoo\4"+
		"\2PPpp\4\2QQqq\4\2RRrr\4\2SSss\4\2TTtt\4\2UUuu\4\2VVvv\4\2WWww\4\2XXx"+
		"x\4\2YYyy\4\2[[{{\4\2\\\\||\3\2\u4e02\u9fa7\3\2\62;\4\2C\\c|\u00ca\2\3"+
		"\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2"+
		"\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31"+
		"\3\2\2\2\2\33\3\2\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2\2\2"+
		"\2%\3\2\2\2\2\'\3\2\2\2\3c\3\2\2\2\5e\3\2\2\2\7g\3\2\2\2\tl\3\2\2\2\13"+
		"n\3\2\2\2\rp\3\2\2\2\17r\3\2\2\2\21u\3\2\2\2\23x\3\2\2\2\25}\3\2\2\2\27"+
		"\u0083\3\2\2\2\31\u008a\3\2\2\2\33\u008d\3\2\2\2\35\u0091\3\2\2\2\37\u0093"+
		"\3\2\2\2!\u0095\3\2\2\2#\u009a\3\2\2\2%\u009e\3\2\2\2\'\u00a3\3\2\2\2"+
		")\u00a7\3\2\2\2+\u00a9\3\2\2\2-\u00ab\3\2\2\2/\u00ad\3\2\2\2\61\u00af"+
		"\3\2\2\2\63\u00b1\3\2\2\2\65\u00b3\3\2\2\2\67\u00b5\3\2\2\29\u00b7\3\2"+
		"\2\2;\u00b9\3\2\2\2=\u00bb\3\2\2\2?\u00bd\3\2\2\2A\u00bf\3\2\2\2C\u00c1"+
		"\3\2\2\2E\u00c3\3\2\2\2G\u00c5\3\2\2\2I\u00c7\3\2\2\2K\u00c9\3\2\2\2M"+
		"\u00cb\3\2\2\2O\u00cd\3\2\2\2Q\u00cf\3\2\2\2S\u00d1\3\2\2\2U\u00d3\3\2"+
		"\2\2W\u00d5\3\2\2\2Y\u00d7\3\2\2\2[\u00d9\3\2\2\2]\u00db\3\2\2\2_\u00dd"+
		"\3\2\2\2a\u00df\3\2\2\2cd\7*\2\2d\4\3\2\2\2ef\7+\2\2f\6\3\2\2\2gh\7<\2"+
		"\2hi\7g\2\2ij\7s\2\2jk\7*\2\2k\b\3\2\2\2lm\7]\2\2m\n\3\2\2\2no\7#\2\2"+
		"o\f\3\2\2\2pq\7&\2\2q\16\3\2\2\2rs\7?\2\2st\7)\2\2t\20\3\2\2\2uv\7)\2"+
		"\2vw\7_\2\2w\22\3\2\2\2xy\7<\2\2yz\7k\2\2z{\7u\2\2{|\7*\2\2|\24\3\2\2"+
		"\2}~\7<\2\2~\177\7p\2\2\177\u0080\7q\2\2\u0080\u0081\7v\2\2\u0081\u0082"+
		"\7*\2\2\u0082\26\3\2\2\2\u0083\u0084\7<\2\2\u0084\u0085\7v\2\2\u0085\u0086"+
		"\7g\2\2\u0086\u0087\7z\2\2\u0087\u0088\7v\2\2\u0088\u0089\7*\2\2\u0089"+
		"\30\3\2\2\2\u008a\u008b\7@\2\2\u008b\32\3\2\2\2\u008c\u008e\t\2\2\2\u008d"+
		"\u008c\3\2\2\2\u008e\u008f\3\2\2\2\u008f\u008d\3\2\2\2\u008f\u0090\3\2"+
		"\2\2\u0090\34\3\2\2\2\u0091\u0092\7\u0080\2\2\u0092\36\3\2\2\2\u0093\u0094"+
		"\7-\2\2\u0094 \3\2\2\2\u0095\u0096\7,\2\2\u0096\"\3\2\2\2\u0097\u009b"+
		"\5_\60\2\u0098\u009b\5a\61\2\u0099\u009b\t\3\2\2\u009a\u0097\3\2\2\2\u009a"+
		"\u0098\3\2\2\2\u009a\u0099\3\2\2\2\u009b\u009c\3\2\2\2\u009c\u009a\3\2"+
		"\2\2\u009c\u009d\3\2\2\2\u009d$\3\2\2\2\u009e\u009f\7.\2\2\u009f&\3\2"+
		"\2\2\u00a0\u00a4\t\4\2\2\u00a1\u00a4\5%\23\2\u00a2\u00a4\7~\2\2\u00a3"+
		"\u00a0\3\2\2\2\u00a3\u00a1\3\2\2\2\u00a3\u00a2\3\2\2\2\u00a4\u00a5\3\2"+
		"\2\2\u00a5\u00a3\3\2\2\2\u00a5\u00a6\3\2\2\2\u00a6(\3\2\2\2\u00a7\u00a8"+
		"\t\5\2\2\u00a8*\3\2\2\2\u00a9\u00aa\t\6\2\2\u00aa,\3\2\2\2\u00ab\u00ac"+
		"\t\7\2\2\u00ac.\3\2\2\2\u00ad\u00ae\t\b\2\2\u00ae\60\3\2\2\2\u00af\u00b0"+
		"\t\t\2\2\u00b0\62\3\2\2\2\u00b1\u00b2\t\n\2\2\u00b2\64\3\2\2\2\u00b3\u00b4"+
		"\t\13\2\2\u00b4\66\3\2\2\2\u00b5\u00b6\t\f\2\2\u00b68\3\2\2\2\u00b7\u00b8"+
		"\t\r\2\2\u00b8:\3\2\2\2\u00b9\u00ba\t\16\2\2\u00ba<\3\2\2\2\u00bb\u00bc"+
		"\t\17\2\2\u00bc>\3\2\2\2\u00bd\u00be\t\20\2\2\u00be@\3\2\2\2\u00bf\u00c0"+
		"\t\21\2\2\u00c0B\3\2\2\2\u00c1\u00c2\t\22\2\2\u00c2D\3\2\2\2\u00c3\u00c4"+
		"\t\23\2\2\u00c4F\3\2\2\2\u00c5\u00c6\t\24\2\2\u00c6H\3\2\2\2\u00c7\u00c8"+
		"\t\25\2\2\u00c8J\3\2\2\2\u00c9\u00ca\t\26\2\2\u00caL\3\2\2\2\u00cb\u00cc"+
		"\t\27\2\2\u00ccN\3\2\2\2\u00cd\u00ce\t\30\2\2\u00ceP\3\2\2\2\u00cf\u00d0"+
		"\t\31\2\2\u00d0R\3\2\2\2\u00d1\u00d2\t\32\2\2\u00d2T\3\2\2\2\u00d3\u00d4"+
		"\t\33\2\2\u00d4V\3\2\2\2\u00d5\u00d6\4zz\2\u00d6X\3\2\2\2\u00d7\u00d8"+
		"\t\34\2\2\u00d8Z\3\2\2\2\u00d9\u00da\t\35\2\2\u00da\\\3\2\2\2\u00db\u00dc"+
		"\t\36\2\2\u00dc^\3\2\2\2\u00dd\u00de\t\37\2\2\u00de`\3\2\2\2\u00df\u00e0"+
		"\t \2\2\u00e0b\3\2\2\2\b\2\u008f\u009a\u009c\u00a3\u00a5\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}