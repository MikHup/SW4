package sw4.gen;
// Generated from C:/Users/Thomas Rusbjerg/IdeaProjects/SW4/src/main/antlr\jaml.g4 by ANTLR 4.7
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class jamlParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.7", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		TEMPO=1, TITLE=2, KEY=3, TIMESIGNATURE=4, INSTRUMENT=5, KEYVALUE=6, TONEVAL=7, 
		NOTEOCT=8, NOTELENGTH=9, NOTEOCTLENGTH=10, REST=11, RESTLENGTH=12, OCTAVESWITCH=13, 
		LENGTHSWITCH=14, VELOCITYSWITCH=15, INTLITERAL=16, STRING=17, COLON=18, 
		CHORDSLASH=19, LPAREN=20, RPAREN=21, ASSIGN=22, REPEAT=23, SHARP=24, FLAT=25, 
		TIE=26, DOT=27, OCTDOWN=28, OCTUP=29, ID=30, WS=31, NEWLINE=32, COMMENT=33;
	public static final int
		RULE_prog = 0, RULE_part = 1, RULE_assignment = 2, RULE_segment = 3, RULE_scorePart = 4, 
		RULE_note = 5, RULE_restNote = 6, RULE_toneVal = 7, RULE_noteOct = 8, 
		RULE_noteLength = 9, RULE_noteOctLength = 10, RULE_rest = 11, RULE_restLength = 12, 
		RULE_accidental = 13, RULE_dot = 14, RULE_expr = 15, RULE_chord = 16, 
		RULE_timeSignature = 17;
	public static final String[] ruleNames = {
		"prog", "part", "assignment", "segment", "scorePart", "note", "restNote", 
		"toneVal", "noteOct", "noteLength", "noteOctLength", "rest", "restLength", 
		"accidental", "dot", "expr", "chord", "timeSignature"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'Tempo'", "'Title'", "'Key'", "'TimeSignature'", null, null, null, 
		null, null, null, "'r'", null, null, null, null, null, null, "':'", "'/'", 
		"'('", "')'", "'='", "'*'", "'+'", "'-'", "'_'", "'.'", "'<'", "'>'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, "TEMPO", "TITLE", "KEY", "TIMESIGNATURE", "INSTRUMENT", "KEYVALUE", 
		"TONEVAL", "NOTEOCT", "NOTELENGTH", "NOTEOCTLENGTH", "REST", "RESTLENGTH", 
		"OCTAVESWITCH", "LENGTHSWITCH", "VELOCITYSWITCH", "INTLITERAL", "STRING", 
		"COLON", "CHORDSLASH", "LPAREN", "RPAREN", "ASSIGN", "REPEAT", "SHARP", 
		"FLAT", "TIE", "DOT", "OCTDOWN", "OCTUP", "ID", "WS", "NEWLINE", "COMMENT"
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
	public String getGrammarFileName() { return "jaml.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public jamlParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}
	public static class ProgContext extends ParserRuleContext {
		public TerminalNode EOF() { return getToken(jamlParser.EOF, 0); }
		public List<PartContext> part() {
			return getRuleContexts(PartContext.class);
		}
		public PartContext part(int i) {
			return getRuleContext(PartContext.class,i);
		}
		public ProgContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_prog; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof jamlVisitor ) return ((jamlVisitor<? extends T>)visitor).visitProg(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ProgContext prog() throws RecognitionException {
		ProgContext _localctx = new ProgContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_prog);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(39);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << TEMPO) | (1L << TITLE) | (1L << KEY) | (1L << TIMESIGNATURE) | (1L << INSTRUMENT) | (1L << TONEVAL) | (1L << NOTEOCT) | (1L << NOTELENGTH) | (1L << NOTEOCTLENGTH) | (1L << REST) | (1L << RESTLENGTH) | (1L << OCTAVESWITCH) | (1L << LENGTHSWITCH) | (1L << VELOCITYSWITCH) | (1L << LPAREN) | (1L << OCTDOWN) | (1L << OCTUP) | (1L << ID))) != 0)) {
				{
				{
				setState(36);
				part();
				}
				}
				setState(41);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(42);
			match(EOF);
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

	public static class PartContext extends ParserRuleContext {
		public AssignmentContext assignment() {
			return getRuleContext(AssignmentContext.class,0);
		}
		public ScorePartContext scorePart() {
			return getRuleContext(ScorePartContext.class,0);
		}
		public PartContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_part; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof jamlVisitor ) return ((jamlVisitor<? extends T>)visitor).visitPart(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PartContext part() throws RecognitionException {
		PartContext _localctx = new PartContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_part);
		try {
			setState(46);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,1,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(44);
				assignment();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(45);
				scorePart();
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

	public static class AssignmentContext extends ParserRuleContext {
		public AssignmentContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_assignment; }
	 
		public AssignmentContext() { }
		public void copyFrom(AssignmentContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class TitleAssContext extends AssignmentContext {
		public Token left;
		public Token right;
		public TerminalNode ASSIGN() { return getToken(jamlParser.ASSIGN, 0); }
		public TerminalNode TITLE() { return getToken(jamlParser.TITLE, 0); }
		public TerminalNode STRING() { return getToken(jamlParser.STRING, 0); }
		public TitleAssContext(AssignmentContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof jamlVisitor ) return ((jamlVisitor<? extends T>)visitor).visitTitleAss(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class TimeSignatureAssContext extends AssignmentContext {
		public Token left;
		public TerminalNode ASSIGN() { return getToken(jamlParser.ASSIGN, 0); }
		public TimeSignatureContext timeSignature() {
			return getRuleContext(TimeSignatureContext.class,0);
		}
		public TerminalNode TIMESIGNATURE() { return getToken(jamlParser.TIMESIGNATURE, 0); }
		public TimeSignatureAssContext(AssignmentContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof jamlVisitor ) return ((jamlVisitor<? extends T>)visitor).visitTimeSignatureAss(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class SegmentAssContext extends AssignmentContext {
		public Token left;
		public TerminalNode ASSIGN() { return getToken(jamlParser.ASSIGN, 0); }
		public SegmentContext segment() {
			return getRuleContext(SegmentContext.class,0);
		}
		public TerminalNode ID() { return getToken(jamlParser.ID, 0); }
		public SegmentAssContext(AssignmentContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof jamlVisitor ) return ((jamlVisitor<? extends T>)visitor).visitSegmentAss(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class InstrumentAssContext extends AssignmentContext {
		public Token instr;
		public TerminalNode COLON() { return getToken(jamlParser.COLON, 0); }
		public TerminalNode INSTRUMENT() { return getToken(jamlParser.INSTRUMENT, 0); }
		public InstrumentAssContext(AssignmentContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof jamlVisitor ) return ((jamlVisitor<? extends T>)visitor).visitInstrumentAss(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class TempoAssContext extends AssignmentContext {
		public Token left;
		public Token right;
		public TerminalNode ASSIGN() { return getToken(jamlParser.ASSIGN, 0); }
		public TerminalNode TEMPO() { return getToken(jamlParser.TEMPO, 0); }
		public TerminalNode INTLITERAL() { return getToken(jamlParser.INTLITERAL, 0); }
		public TempoAssContext(AssignmentContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof jamlVisitor ) return ((jamlVisitor<? extends T>)visitor).visitTempoAss(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ChordAssContext extends AssignmentContext {
		public Token left;
		public TerminalNode ASSIGN() { return getToken(jamlParser.ASSIGN, 0); }
		public ChordContext chord() {
			return getRuleContext(ChordContext.class,0);
		}
		public TerminalNode ID() { return getToken(jamlParser.ID, 0); }
		public ChordAssContext(AssignmentContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof jamlVisitor ) return ((jamlVisitor<? extends T>)visitor).visitChordAss(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class KeyAssContext extends AssignmentContext {
		public Token left;
		public Token right;
		public TerminalNode ASSIGN() { return getToken(jamlParser.ASSIGN, 0); }
		public TerminalNode KEY() { return getToken(jamlParser.KEY, 0); }
		public TerminalNode KEYVALUE() { return getToken(jamlParser.KEYVALUE, 0); }
		public KeyAssContext(AssignmentContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof jamlVisitor ) return ((jamlVisitor<? extends T>)visitor).visitKeyAss(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AssignmentContext assignment() throws RecognitionException {
		AssignmentContext _localctx = new AssignmentContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_assignment);
		try {
			setState(68);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,2,_ctx) ) {
			case 1:
				_localctx = new SegmentAssContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(48);
				((SegmentAssContext)_localctx).left = match(ID);
				setState(49);
				match(ASSIGN);
				setState(50);
				segment();
				}
				break;
			case 2:
				_localctx = new ChordAssContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(51);
				((ChordAssContext)_localctx).left = match(ID);
				setState(52);
				match(ASSIGN);
				setState(53);
				chord();
				}
				break;
			case 3:
				_localctx = new TitleAssContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(54);
				((TitleAssContext)_localctx).left = match(TITLE);
				setState(55);
				match(ASSIGN);
				setState(56);
				((TitleAssContext)_localctx).right = match(STRING);
				}
				break;
			case 4:
				_localctx = new KeyAssContext(_localctx);
				enterOuterAlt(_localctx, 4);
				{
				setState(57);
				((KeyAssContext)_localctx).left = match(KEY);
				setState(58);
				match(ASSIGN);
				setState(59);
				((KeyAssContext)_localctx).right = match(KEYVALUE);
				}
				break;
			case 5:
				_localctx = new TempoAssContext(_localctx);
				enterOuterAlt(_localctx, 5);
				{
				setState(60);
				((TempoAssContext)_localctx).left = match(TEMPO);
				setState(61);
				match(ASSIGN);
				setState(62);
				((TempoAssContext)_localctx).right = match(INTLITERAL);
				}
				break;
			case 6:
				_localctx = new TimeSignatureAssContext(_localctx);
				enterOuterAlt(_localctx, 6);
				{
				setState(63);
				((TimeSignatureAssContext)_localctx).left = match(TIMESIGNATURE);
				setState(64);
				match(ASSIGN);
				setState(65);
				timeSignature();
				}
				break;
			case 7:
				_localctx = new InstrumentAssContext(_localctx);
				enterOuterAlt(_localctx, 7);
				{
				setState(66);
				((InstrumentAssContext)_localctx).instr = match(INSTRUMENT);
				setState(67);
				match(COLON);
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

	public static class SegmentContext extends ParserRuleContext {
		public TerminalNode LPAREN() { return getToken(jamlParser.LPAREN, 0); }
		public TerminalNode RPAREN() { return getToken(jamlParser.RPAREN, 0); }
		public List<ScorePartContext> scorePart() {
			return getRuleContexts(ScorePartContext.class);
		}
		public ScorePartContext scorePart(int i) {
			return getRuleContext(ScorePartContext.class,i);
		}
		public List<AccidentalContext> accidental() {
			return getRuleContexts(AccidentalContext.class);
		}
		public AccidentalContext accidental(int i) {
			return getRuleContext(AccidentalContext.class,i);
		}
		public SegmentContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_segment; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof jamlVisitor ) return ((jamlVisitor<? extends T>)visitor).visitSegment(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SegmentContext segment() throws RecognitionException {
		SegmentContext _localctx = new SegmentContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_segment);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(70);
			match(LPAREN);
			setState(74);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << TONEVAL) | (1L << NOTEOCT) | (1L << NOTELENGTH) | (1L << NOTEOCTLENGTH) | (1L << REST) | (1L << RESTLENGTH) | (1L << OCTAVESWITCH) | (1L << LENGTHSWITCH) | (1L << VELOCITYSWITCH) | (1L << LPAREN) | (1L << OCTDOWN) | (1L << OCTUP) | (1L << ID))) != 0)) {
				{
				{
				setState(71);
				scorePart();
				}
				}
				setState(76);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(77);
			match(RPAREN);
			setState(81);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==SHARP || _la==FLAT) {
				{
				{
				setState(78);
				accidental();
				}
				}
				setState(83);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
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

	public static class ScorePartContext extends ParserRuleContext {
		public NoteContext note() {
			return getRuleContext(NoteContext.class,0);
		}
		public RestNoteContext restNote() {
			return getRuleContext(RestNoteContext.class,0);
		}
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public ScorePartContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_scorePart; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof jamlVisitor ) return ((jamlVisitor<? extends T>)visitor).visitScorePart(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ScorePartContext scorePart() throws RecognitionException {
		ScorePartContext _localctx = new ScorePartContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_scorePart);
		try {
			setState(87);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,5,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(84);
				note();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(85);
				restNote();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(86);
				expr();
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

	public static class NoteContext extends ParserRuleContext {
		public ToneValContext toneVal() {
			return getRuleContext(ToneValContext.class,0);
		}
		public NoteOctContext noteOct() {
			return getRuleContext(NoteOctContext.class,0);
		}
		public NoteLengthContext noteLength() {
			return getRuleContext(NoteLengthContext.class,0);
		}
		public NoteOctLengthContext noteOctLength() {
			return getRuleContext(NoteOctLengthContext.class,0);
		}
		public NoteContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_note; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof jamlVisitor ) return ((jamlVisitor<? extends T>)visitor).visitNote(this);
			else return visitor.visitChildren(this);
		}
	}

	public final NoteContext note() throws RecognitionException {
		NoteContext _localctx = new NoteContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_note);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(93);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case TONEVAL:
				{
				setState(89);
				toneVal();
				}
				break;
			case NOTEOCT:
				{
				setState(90);
				noteOct();
				}
				break;
			case NOTELENGTH:
				{
				setState(91);
				noteLength();
				}
				break;
			case NOTEOCTLENGTH:
				{
				setState(92);
				noteOctLength();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
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

	public static class RestNoteContext extends ParserRuleContext {
		public RestContext rest() {
			return getRuleContext(RestContext.class,0);
		}
		public RestLengthContext restLength() {
			return getRuleContext(RestLengthContext.class,0);
		}
		public RestNoteContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_restNote; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof jamlVisitor ) return ((jamlVisitor<? extends T>)visitor).visitRestNote(this);
			else return visitor.visitChildren(this);
		}
	}

	public final RestNoteContext restNote() throws RecognitionException {
		RestNoteContext _localctx = new RestNoteContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_restNote);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(97);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case REST:
				{
				setState(95);
				rest();
				}
				break;
			case RESTLENGTH:
				{
				setState(96);
				restLength();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
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

	public static class ToneValContext extends ParserRuleContext {
		public TerminalNode TONEVAL() { return getToken(jamlParser.TONEVAL, 0); }
		public List<AccidentalContext> accidental() {
			return getRuleContexts(AccidentalContext.class);
		}
		public AccidentalContext accidental(int i) {
			return getRuleContext(AccidentalContext.class,i);
		}
		public List<DotContext> dot() {
			return getRuleContexts(DotContext.class);
		}
		public DotContext dot(int i) {
			return getRuleContext(DotContext.class,i);
		}
		public ToneValContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_toneVal; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof jamlVisitor ) return ((jamlVisitor<? extends T>)visitor).visitToneVal(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ToneValContext toneVal() throws RecognitionException {
		ToneValContext _localctx = new ToneValContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_toneVal);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(99);
			match(TONEVAL);
			setState(103);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==SHARP || _la==FLAT) {
				{
				{
				setState(100);
				accidental();
				}
				}
				setState(105);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(109);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==DOT) {
				{
				{
				setState(106);
				dot();
				}
				}
				setState(111);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
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

	public static class NoteOctContext extends ParserRuleContext {
		public TerminalNode NOTEOCT() { return getToken(jamlParser.NOTEOCT, 0); }
		public List<AccidentalContext> accidental() {
			return getRuleContexts(AccidentalContext.class);
		}
		public AccidentalContext accidental(int i) {
			return getRuleContext(AccidentalContext.class,i);
		}
		public List<DotContext> dot() {
			return getRuleContexts(DotContext.class);
		}
		public DotContext dot(int i) {
			return getRuleContext(DotContext.class,i);
		}
		public NoteOctContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_noteOct; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof jamlVisitor ) return ((jamlVisitor<? extends T>)visitor).visitNoteOct(this);
			else return visitor.visitChildren(this);
		}
	}

	public final NoteOctContext noteOct() throws RecognitionException {
		NoteOctContext _localctx = new NoteOctContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_noteOct);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(112);
			match(NOTEOCT);
			setState(116);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==SHARP || _la==FLAT) {
				{
				{
				setState(113);
				accidental();
				}
				}
				setState(118);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(122);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==DOT) {
				{
				{
				setState(119);
				dot();
				}
				}
				setState(124);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
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

	public static class NoteLengthContext extends ParserRuleContext {
		public TerminalNode NOTELENGTH() { return getToken(jamlParser.NOTELENGTH, 0); }
		public List<AccidentalContext> accidental() {
			return getRuleContexts(AccidentalContext.class);
		}
		public AccidentalContext accidental(int i) {
			return getRuleContext(AccidentalContext.class,i);
		}
		public List<DotContext> dot() {
			return getRuleContexts(DotContext.class);
		}
		public DotContext dot(int i) {
			return getRuleContext(DotContext.class,i);
		}
		public NoteLengthContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_noteLength; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof jamlVisitor ) return ((jamlVisitor<? extends T>)visitor).visitNoteLength(this);
			else return visitor.visitChildren(this);
		}
	}

	public final NoteLengthContext noteLength() throws RecognitionException {
		NoteLengthContext _localctx = new NoteLengthContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_noteLength);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(125);
			match(NOTELENGTH);
			setState(129);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==SHARP || _la==FLAT) {
				{
				{
				setState(126);
				accidental();
				}
				}
				setState(131);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(135);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==DOT) {
				{
				{
				setState(132);
				dot();
				}
				}
				setState(137);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
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

	public static class NoteOctLengthContext extends ParserRuleContext {
		public TerminalNode NOTEOCTLENGTH() { return getToken(jamlParser.NOTEOCTLENGTH, 0); }
		public List<AccidentalContext> accidental() {
			return getRuleContexts(AccidentalContext.class);
		}
		public AccidentalContext accidental(int i) {
			return getRuleContext(AccidentalContext.class,i);
		}
		public List<DotContext> dot() {
			return getRuleContexts(DotContext.class);
		}
		public DotContext dot(int i) {
			return getRuleContext(DotContext.class,i);
		}
		public NoteOctLengthContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_noteOctLength; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof jamlVisitor ) return ((jamlVisitor<? extends T>)visitor).visitNoteOctLength(this);
			else return visitor.visitChildren(this);
		}
	}

	public final NoteOctLengthContext noteOctLength() throws RecognitionException {
		NoteOctLengthContext _localctx = new NoteOctLengthContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_noteOctLength);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(138);
			match(NOTEOCTLENGTH);
			setState(142);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==SHARP || _la==FLAT) {
				{
				{
				setState(139);
				accidental();
				}
				}
				setState(144);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(148);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==DOT) {
				{
				{
				setState(145);
				dot();
				}
				}
				setState(150);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
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

	public static class RestContext extends ParserRuleContext {
		public TerminalNode REST() { return getToken(jamlParser.REST, 0); }
		public List<DotContext> dot() {
			return getRuleContexts(DotContext.class);
		}
		public DotContext dot(int i) {
			return getRuleContext(DotContext.class,i);
		}
		public RestContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_rest; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof jamlVisitor ) return ((jamlVisitor<? extends T>)visitor).visitRest(this);
			else return visitor.visitChildren(this);
		}
	}

	public final RestContext rest() throws RecognitionException {
		RestContext _localctx = new RestContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_rest);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(151);
			match(REST);
			setState(155);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==DOT) {
				{
				{
				setState(152);
				dot();
				}
				}
				setState(157);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
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

	public static class RestLengthContext extends ParserRuleContext {
		public TerminalNode RESTLENGTH() { return getToken(jamlParser.RESTLENGTH, 0); }
		public List<DotContext> dot() {
			return getRuleContexts(DotContext.class);
		}
		public DotContext dot(int i) {
			return getRuleContext(DotContext.class,i);
		}
		public RestLengthContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_restLength; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof jamlVisitor ) return ((jamlVisitor<? extends T>)visitor).visitRestLength(this);
			else return visitor.visitChildren(this);
		}
	}

	public final RestLengthContext restLength() throws RecognitionException {
		RestLengthContext _localctx = new RestLengthContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_restLength);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(158);
			match(RESTLENGTH);
			setState(162);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==DOT) {
				{
				{
				setState(159);
				dot();
				}
				}
				setState(164);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
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

	public static class AccidentalContext extends ParserRuleContext {
		public Token acc;
		public TerminalNode SHARP() { return getToken(jamlParser.SHARP, 0); }
		public TerminalNode FLAT() { return getToken(jamlParser.FLAT, 0); }
		public AccidentalContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_accidental; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof jamlVisitor ) return ((jamlVisitor<? extends T>)visitor).visitAccidental(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AccidentalContext accidental() throws RecognitionException {
		AccidentalContext _localctx = new AccidentalContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_accidental);
		try {
			setState(167);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case SHARP:
				enterOuterAlt(_localctx, 1);
				{
				setState(165);
				((AccidentalContext)_localctx).acc = match(SHARP);
				}
				break;
			case FLAT:
				enterOuterAlt(_localctx, 2);
				{
				setState(166);
				((AccidentalContext)_localctx).acc = match(FLAT);
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

	public static class DotContext extends ParserRuleContext {
		public TerminalNode DOT() { return getToken(jamlParser.DOT, 0); }
		public DotContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_dot; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof jamlVisitor ) return ((jamlVisitor<? extends T>)visitor).visitDot(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DotContext dot() throws RecognitionException {
		DotContext _localctx = new DotContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_dot);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(169);
			match(DOT);
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

	public static class ExprContext extends ParserRuleContext {
		public ExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expr; }
	 
		public ExprContext() { }
		public void copyFrom(ExprContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class IdRepeatExprContext extends ExprContext {
		public Token identifier;
		public Token value;
		public TerminalNode ID() { return getToken(jamlParser.ID, 0); }
		public List<AccidentalContext> accidental() {
			return getRuleContexts(AccidentalContext.class);
		}
		public AccidentalContext accidental(int i) {
			return getRuleContext(AccidentalContext.class,i);
		}
		public TerminalNode REPEAT() { return getToken(jamlParser.REPEAT, 0); }
		public TerminalNode INTLITERAL() { return getToken(jamlParser.INTLITERAL, 0); }
		public IdRepeatExprContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof jamlVisitor ) return ((jamlVisitor<? extends T>)visitor).visitIdRepeatExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class SegmentRepeatExprContext extends ExprContext {
		public Token value;
		public SegmentContext segment() {
			return getRuleContext(SegmentContext.class,0);
		}
		public TerminalNode REPEAT() { return getToken(jamlParser.REPEAT, 0); }
		public TerminalNode INTLITERAL() { return getToken(jamlParser.INTLITERAL, 0); }
		public SegmentRepeatExprContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof jamlVisitor ) return ((jamlVisitor<? extends T>)visitor).visitSegmentRepeatExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class LengthSwitchExprContext extends ExprContext {
		public TerminalNode LENGTHSWITCH() { return getToken(jamlParser.LENGTHSWITCH, 0); }
		public LengthSwitchExprContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof jamlVisitor ) return ((jamlVisitor<? extends T>)visitor).visitLengthSwitchExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class OctaveUpExprContext extends ExprContext {
		public TerminalNode OCTUP() { return getToken(jamlParser.OCTUP, 0); }
		public OctaveUpExprContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof jamlVisitor ) return ((jamlVisitor<? extends T>)visitor).visitOctaveUpExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class TiedNotesExprContext extends ExprContext {
		public List<NoteContext> note() {
			return getRuleContexts(NoteContext.class);
		}
		public NoteContext note(int i) {
			return getRuleContext(NoteContext.class,i);
		}
		public List<TerminalNode> TIE() { return getTokens(jamlParser.TIE); }
		public TerminalNode TIE(int i) {
			return getToken(jamlParser.TIE, i);
		}
		public TiedNotesExprContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof jamlVisitor ) return ((jamlVisitor<? extends T>)visitor).visitTiedNotesExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class VelocitySwitchExprContext extends ExprContext {
		public TerminalNode VELOCITYSWITCH() { return getToken(jamlParser.VELOCITYSWITCH, 0); }
		public VelocitySwitchExprContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof jamlVisitor ) return ((jamlVisitor<? extends T>)visitor).visitVelocitySwitchExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class RestRepeatExprContext extends ExprContext {
		public Token value;
		public RestNoteContext restNote() {
			return getRuleContext(RestNoteContext.class,0);
		}
		public TerminalNode REPEAT() { return getToken(jamlParser.REPEAT, 0); }
		public TerminalNode INTLITERAL() { return getToken(jamlParser.INTLITERAL, 0); }
		public RestRepeatExprContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof jamlVisitor ) return ((jamlVisitor<? extends T>)visitor).visitRestRepeatExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class OctaveSwitchExprContext extends ExprContext {
		public TerminalNode OCTAVESWITCH() { return getToken(jamlParser.OCTAVESWITCH, 0); }
		public OctaveSwitchExprContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof jamlVisitor ) return ((jamlVisitor<? extends T>)visitor).visitOctaveSwitchExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class NoteRepeatExprContext extends ExprContext {
		public Token value;
		public NoteContext note() {
			return getRuleContext(NoteContext.class,0);
		}
		public TerminalNode REPEAT() { return getToken(jamlParser.REPEAT, 0); }
		public TerminalNode INTLITERAL() { return getToken(jamlParser.INTLITERAL, 0); }
		public NoteRepeatExprContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof jamlVisitor ) return ((jamlVisitor<? extends T>)visitor).visitNoteRepeatExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class OctaveDownExprContext extends ExprContext {
		public TerminalNode OCTDOWN() { return getToken(jamlParser.OCTDOWN, 0); }
		public OctaveDownExprContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof jamlVisitor ) return ((jamlVisitor<? extends T>)visitor).visitOctaveDownExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ChordExprContext extends ExprContext {
		public ChordContext chord() {
			return getRuleContext(ChordContext.class,0);
		}
		public ChordExprContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof jamlVisitor ) return ((jamlVisitor<? extends T>)visitor).visitChordExpr(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExprContext expr() throws RecognitionException {
		ExprContext _localctx = new ExprContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_expr);
		int _la;
		try {
			setState(208);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,23,_ctx) ) {
			case 1:
				_localctx = new NoteRepeatExprContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(171);
				note();
				setState(172);
				match(REPEAT);
				setState(173);
				((NoteRepeatExprContext)_localctx).value = match(INTLITERAL);
				}
				break;
			case 2:
				_localctx = new RestRepeatExprContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(175);
				restNote();
				setState(176);
				match(REPEAT);
				setState(177);
				((RestRepeatExprContext)_localctx).value = match(INTLITERAL);
				}
				break;
			case 3:
				_localctx = new SegmentRepeatExprContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(179);
				segment();
				setState(182);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==REPEAT) {
					{
					setState(180);
					match(REPEAT);
					setState(181);
					((SegmentRepeatExprContext)_localctx).value = match(INTLITERAL);
					}
				}

				}
				break;
			case 4:
				_localctx = new IdRepeatExprContext(_localctx);
				enterOuterAlt(_localctx, 4);
				{
				setState(184);
				((IdRepeatExprContext)_localctx).identifier = match(ID);
				setState(188);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==SHARP || _la==FLAT) {
					{
					{
					setState(185);
					accidental();
					}
					}
					setState(190);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(193);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==REPEAT) {
					{
					setState(191);
					match(REPEAT);
					setState(192);
					((IdRepeatExprContext)_localctx).value = match(INTLITERAL);
					}
				}

				}
				break;
			case 5:
				_localctx = new ChordExprContext(_localctx);
				enterOuterAlt(_localctx, 5);
				{
				setState(195);
				chord();
				}
				break;
			case 6:
				_localctx = new TiedNotesExprContext(_localctx);
				enterOuterAlt(_localctx, 6);
				{
				setState(196);
				note();
				setState(199); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(197);
					match(TIE);
					setState(198);
					note();
					}
					}
					setState(201); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( _la==TIE );
				}
				break;
			case 7:
				_localctx = new OctaveDownExprContext(_localctx);
				enterOuterAlt(_localctx, 7);
				{
				setState(203);
				match(OCTDOWN);
				}
				break;
			case 8:
				_localctx = new OctaveUpExprContext(_localctx);
				enterOuterAlt(_localctx, 8);
				{
				setState(204);
				match(OCTUP);
				}
				break;
			case 9:
				_localctx = new OctaveSwitchExprContext(_localctx);
				enterOuterAlt(_localctx, 9);
				{
				setState(205);
				match(OCTAVESWITCH);
				}
				break;
			case 10:
				_localctx = new LengthSwitchExprContext(_localctx);
				enterOuterAlt(_localctx, 10);
				{
				setState(206);
				match(LENGTHSWITCH);
				}
				break;
			case 11:
				_localctx = new VelocitySwitchExprContext(_localctx);
				enterOuterAlt(_localctx, 11);
				{
				setState(207);
				match(VELOCITYSWITCH);
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

	public static class ChordContext extends ParserRuleContext {
		public List<NoteContext> note() {
			return getRuleContexts(NoteContext.class);
		}
		public NoteContext note(int i) {
			return getRuleContext(NoteContext.class,i);
		}
		public List<TerminalNode> CHORDSLASH() { return getTokens(jamlParser.CHORDSLASH); }
		public TerminalNode CHORDSLASH(int i) {
			return getToken(jamlParser.CHORDSLASH, i);
		}
		public ChordContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_chord; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof jamlVisitor ) return ((jamlVisitor<? extends T>)visitor).visitChord(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ChordContext chord() throws RecognitionException {
		ChordContext _localctx = new ChordContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_chord);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(210);
			note();
			setState(213); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(211);
				match(CHORDSLASH);
				setState(212);
				note();
				}
				}
				setState(215); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==CHORDSLASH );
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

	public static class TimeSignatureContext extends ParserRuleContext {
		public Token numerator;
		public Token denominator;
		public TerminalNode CHORDSLASH() { return getToken(jamlParser.CHORDSLASH, 0); }
		public List<TerminalNode> INTLITERAL() { return getTokens(jamlParser.INTLITERAL); }
		public TerminalNode INTLITERAL(int i) {
			return getToken(jamlParser.INTLITERAL, i);
		}
		public TimeSignatureContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_timeSignature; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof jamlVisitor ) return ((jamlVisitor<? extends T>)visitor).visitTimeSignature(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TimeSignatureContext timeSignature() throws RecognitionException {
		TimeSignatureContext _localctx = new TimeSignatureContext(_ctx, getState());
		enterRule(_localctx, 34, RULE_timeSignature);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(217);
			((TimeSignatureContext)_localctx).numerator = match(INTLITERAL);
			setState(218);
			match(CHORDSLASH);
			setState(219);
			((TimeSignatureContext)_localctx).denominator = match(INTLITERAL);
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

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3#\u00e0\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\3\2\7\2(\n\2\f\2\16\2+\13\2\3\2\3\2\3\3\3\3\5\3\61\n\3\3\4"+
		"\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3"+
		"\4\3\4\5\4G\n\4\3\5\3\5\7\5K\n\5\f\5\16\5N\13\5\3\5\3\5\7\5R\n\5\f\5\16"+
		"\5U\13\5\3\6\3\6\3\6\5\6Z\n\6\3\7\3\7\3\7\3\7\5\7`\n\7\3\b\3\b\5\bd\n"+
		"\b\3\t\3\t\7\th\n\t\f\t\16\tk\13\t\3\t\7\tn\n\t\f\t\16\tq\13\t\3\n\3\n"+
		"\7\nu\n\n\f\n\16\nx\13\n\3\n\7\n{\n\n\f\n\16\n~\13\n\3\13\3\13\7\13\u0082"+
		"\n\13\f\13\16\13\u0085\13\13\3\13\7\13\u0088\n\13\f\13\16\13\u008b\13"+
		"\13\3\f\3\f\7\f\u008f\n\f\f\f\16\f\u0092\13\f\3\f\7\f\u0095\n\f\f\f\16"+
		"\f\u0098\13\f\3\r\3\r\7\r\u009c\n\r\f\r\16\r\u009f\13\r\3\16\3\16\7\16"+
		"\u00a3\n\16\f\16\16\16\u00a6\13\16\3\17\3\17\5\17\u00aa\n\17\3\20\3\20"+
		"\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\5\21\u00b9\n\21"+
		"\3\21\3\21\7\21\u00bd\n\21\f\21\16\21\u00c0\13\21\3\21\3\21\5\21\u00c4"+
		"\n\21\3\21\3\21\3\21\3\21\6\21\u00ca\n\21\r\21\16\21\u00cb\3\21\3\21\3"+
		"\21\3\21\3\21\5\21\u00d3\n\21\3\22\3\22\3\22\6\22\u00d8\n\22\r\22\16\22"+
		"\u00d9\3\23\3\23\3\23\3\23\3\23\2\2\24\2\4\6\b\n\f\16\20\22\24\26\30\32"+
		"\34\36 \"$\2\2\2\u00f7\2)\3\2\2\2\4\60\3\2\2\2\6F\3\2\2\2\bH\3\2\2\2\n"+
		"Y\3\2\2\2\f_\3\2\2\2\16c\3\2\2\2\20e\3\2\2\2\22r\3\2\2\2\24\177\3\2\2"+
		"\2\26\u008c\3\2\2\2\30\u0099\3\2\2\2\32\u00a0\3\2\2\2\34\u00a9\3\2\2\2"+
		"\36\u00ab\3\2\2\2 \u00d2\3\2\2\2\"\u00d4\3\2\2\2$\u00db\3\2\2\2&(\5\4"+
		"\3\2\'&\3\2\2\2(+\3\2\2\2)\'\3\2\2\2)*\3\2\2\2*,\3\2\2\2+)\3\2\2\2,-\7"+
		"\2\2\3-\3\3\2\2\2.\61\5\6\4\2/\61\5\n\6\2\60.\3\2\2\2\60/\3\2\2\2\61\5"+
		"\3\2\2\2\62\63\7 \2\2\63\64\7\30\2\2\64G\5\b\5\2\65\66\7 \2\2\66\67\7"+
		"\30\2\2\67G\5\"\22\289\7\4\2\29:\7\30\2\2:G\7\23\2\2;<\7\5\2\2<=\7\30"+
		"\2\2=G\7\b\2\2>?\7\3\2\2?@\7\30\2\2@G\7\22\2\2AB\7\6\2\2BC\7\30\2\2CG"+
		"\5$\23\2DE\7\7\2\2EG\7\24\2\2F\62\3\2\2\2F\65\3\2\2\2F8\3\2\2\2F;\3\2"+
		"\2\2F>\3\2\2\2FA\3\2\2\2FD\3\2\2\2G\7\3\2\2\2HL\7\26\2\2IK\5\n\6\2JI\3"+
		"\2\2\2KN\3\2\2\2LJ\3\2\2\2LM\3\2\2\2MO\3\2\2\2NL\3\2\2\2OS\7\27\2\2PR"+
		"\5\34\17\2QP\3\2\2\2RU\3\2\2\2SQ\3\2\2\2ST\3\2\2\2T\t\3\2\2\2US\3\2\2"+
		"\2VZ\5\f\7\2WZ\5\16\b\2XZ\5 \21\2YV\3\2\2\2YW\3\2\2\2YX\3\2\2\2Z\13\3"+
		"\2\2\2[`\5\20\t\2\\`\5\22\n\2]`\5\24\13\2^`\5\26\f\2_[\3\2\2\2_\\\3\2"+
		"\2\2_]\3\2\2\2_^\3\2\2\2`\r\3\2\2\2ad\5\30\r\2bd\5\32\16\2ca\3\2\2\2c"+
		"b\3\2\2\2d\17\3\2\2\2ei\7\t\2\2fh\5\34\17\2gf\3\2\2\2hk\3\2\2\2ig\3\2"+
		"\2\2ij\3\2\2\2jo\3\2\2\2ki\3\2\2\2ln\5\36\20\2ml\3\2\2\2nq\3\2\2\2om\3"+
		"\2\2\2op\3\2\2\2p\21\3\2\2\2qo\3\2\2\2rv\7\n\2\2su\5\34\17\2ts\3\2\2\2"+
		"ux\3\2\2\2vt\3\2\2\2vw\3\2\2\2w|\3\2\2\2xv\3\2\2\2y{\5\36\20\2zy\3\2\2"+
		"\2{~\3\2\2\2|z\3\2\2\2|}\3\2\2\2}\23\3\2\2\2~|\3\2\2\2\177\u0083\7\13"+
		"\2\2\u0080\u0082\5\34\17\2\u0081\u0080\3\2\2\2\u0082\u0085\3\2\2\2\u0083"+
		"\u0081\3\2\2\2\u0083\u0084\3\2\2\2\u0084\u0089\3\2\2\2\u0085\u0083\3\2"+
		"\2\2\u0086\u0088\5\36\20\2\u0087\u0086\3\2\2\2\u0088\u008b\3\2\2\2\u0089"+
		"\u0087\3\2\2\2\u0089\u008a\3\2\2\2\u008a\25\3\2\2\2\u008b\u0089\3\2\2"+
		"\2\u008c\u0090\7\f\2\2\u008d\u008f\5\34\17\2\u008e\u008d\3\2\2\2\u008f"+
		"\u0092\3\2\2\2\u0090\u008e\3\2\2\2\u0090\u0091\3\2\2\2\u0091\u0096\3\2"+
		"\2\2\u0092\u0090\3\2\2\2\u0093\u0095\5\36\20\2\u0094\u0093\3\2\2\2\u0095"+
		"\u0098\3\2\2\2\u0096\u0094\3\2\2\2\u0096\u0097\3\2\2\2\u0097\27\3\2\2"+
		"\2\u0098\u0096\3\2\2\2\u0099\u009d\7\r\2\2\u009a\u009c\5\36\20\2\u009b"+
		"\u009a\3\2\2\2\u009c\u009f\3\2\2\2\u009d\u009b\3\2\2\2\u009d\u009e\3\2"+
		"\2\2\u009e\31\3\2\2\2\u009f\u009d\3\2\2\2\u00a0\u00a4\7\16\2\2\u00a1\u00a3"+
		"\5\36\20\2\u00a2\u00a1\3\2\2\2\u00a3\u00a6\3\2\2\2\u00a4\u00a2\3\2\2\2"+
		"\u00a4\u00a5\3\2\2\2\u00a5\33\3\2\2\2\u00a6\u00a4\3\2\2\2\u00a7\u00aa"+
		"\7\32\2\2\u00a8\u00aa\7\33\2\2\u00a9\u00a7\3\2\2\2\u00a9\u00a8\3\2\2\2"+
		"\u00aa\35\3\2\2\2\u00ab\u00ac\7\35\2\2\u00ac\37\3\2\2\2\u00ad\u00ae\5"+
		"\f\7\2\u00ae\u00af\7\31\2\2\u00af\u00b0\7\22\2\2\u00b0\u00d3\3\2\2\2\u00b1"+
		"\u00b2\5\16\b\2\u00b2\u00b3\7\31\2\2\u00b3\u00b4\7\22\2\2\u00b4\u00d3"+
		"\3\2\2\2\u00b5\u00b8\5\b\5\2\u00b6\u00b7\7\31\2\2\u00b7\u00b9\7\22\2\2"+
		"\u00b8\u00b6\3\2\2\2\u00b8\u00b9\3\2\2\2\u00b9\u00d3\3\2\2\2\u00ba\u00be"+
		"\7 \2\2\u00bb\u00bd\5\34\17\2\u00bc\u00bb\3\2\2\2\u00bd\u00c0\3\2\2\2"+
		"\u00be\u00bc\3\2\2\2\u00be\u00bf\3\2\2\2\u00bf\u00c3\3\2\2\2\u00c0\u00be"+
		"\3\2\2\2\u00c1\u00c2\7\31\2\2\u00c2\u00c4\7\22\2\2\u00c3\u00c1\3\2\2\2"+
		"\u00c3\u00c4\3\2\2\2\u00c4\u00d3\3\2\2\2\u00c5\u00d3\5\"\22\2\u00c6\u00c9"+
		"\5\f\7\2\u00c7\u00c8\7\34\2\2\u00c8\u00ca\5\f\7\2\u00c9\u00c7\3\2\2\2"+
		"\u00ca\u00cb\3\2\2\2\u00cb\u00c9\3\2\2\2\u00cb\u00cc\3\2\2\2\u00cc\u00d3"+
		"\3\2\2\2\u00cd\u00d3\7\36\2\2\u00ce\u00d3\7\37\2\2\u00cf\u00d3\7\17\2"+
		"\2\u00d0\u00d3\7\20\2\2\u00d1\u00d3\7\21\2\2\u00d2\u00ad\3\2\2\2\u00d2"+
		"\u00b1\3\2\2\2\u00d2\u00b5\3\2\2\2\u00d2\u00ba\3\2\2\2\u00d2\u00c5\3\2"+
		"\2\2\u00d2\u00c6\3\2\2\2\u00d2\u00cd\3\2\2\2\u00d2\u00ce\3\2\2\2\u00d2"+
		"\u00cf\3\2\2\2\u00d2\u00d0\3\2\2\2\u00d2\u00d1\3\2\2\2\u00d3!\3\2\2\2"+
		"\u00d4\u00d7\5\f\7\2\u00d5\u00d6\7\25\2\2\u00d6\u00d8\5\f\7\2\u00d7\u00d5"+
		"\3\2\2\2\u00d8\u00d9\3\2\2\2\u00d9\u00d7\3\2\2\2\u00d9\u00da\3\2\2\2\u00da"+
		"#\3\2\2\2\u00db\u00dc\7\22\2\2\u00dc\u00dd\7\25\2\2\u00dd\u00de\7\22\2"+
		"\2\u00de%\3\2\2\2\33)\60FLSY_ciov|\u0083\u0089\u0090\u0096\u009d\u00a4"+
		"\u00a9\u00b8\u00be\u00c3\u00cb\u00d2\u00d9";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}