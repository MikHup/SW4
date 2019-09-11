package sw4.gen;
// Generated from C:/Users/Thomas Rusbjerg/IdeaProjects/SW4/src/main/antlr\jaml.g4 by ANTLR 4.7
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link jamlParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface jamlVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link jamlParser#prog}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProg(jamlParser.ProgContext ctx);
	/**
	 * Visit a parse tree produced by {@link jamlParser#part}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPart(jamlParser.PartContext ctx);
	/**
	 * Visit a parse tree produced by the {@code segmentAss}
	 * labeled alternative in {@link jamlParser#assignment}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSegmentAss(jamlParser.SegmentAssContext ctx);
	/**
	 * Visit a parse tree produced by the {@code chordAss}
	 * labeled alternative in {@link jamlParser#assignment}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitChordAss(jamlParser.ChordAssContext ctx);
	/**
	 * Visit a parse tree produced by the {@code titleAss}
	 * labeled alternative in {@link jamlParser#assignment}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTitleAss(jamlParser.TitleAssContext ctx);
	/**
	 * Visit a parse tree produced by the {@code keyAss}
	 * labeled alternative in {@link jamlParser#assignment}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitKeyAss(jamlParser.KeyAssContext ctx);
	/**
	 * Visit a parse tree produced by the {@code tempoAss}
	 * labeled alternative in {@link jamlParser#assignment}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTempoAss(jamlParser.TempoAssContext ctx);
	/**
	 * Visit a parse tree produced by the {@code timeSignatureAss}
	 * labeled alternative in {@link jamlParser#assignment}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTimeSignatureAss(jamlParser.TimeSignatureAssContext ctx);
	/**
	 * Visit a parse tree produced by the {@code instrumentAss}
	 * labeled alternative in {@link jamlParser#assignment}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInstrumentAss(jamlParser.InstrumentAssContext ctx);
	/**
	 * Visit a parse tree produced by {@link jamlParser#segment}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSegment(jamlParser.SegmentContext ctx);
	/**
	 * Visit a parse tree produced by {@link jamlParser#scorePart}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitScorePart(jamlParser.ScorePartContext ctx);
	/**
	 * Visit a parse tree produced by {@link jamlParser#note}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNote(jamlParser.NoteContext ctx);
	/**
	 * Visit a parse tree produced by {@link jamlParser#restNote}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRestNote(jamlParser.RestNoteContext ctx);
	/**
	 * Visit a parse tree produced by {@link jamlParser#toneVal}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitToneVal(jamlParser.ToneValContext ctx);
	/**
	 * Visit a parse tree produced by {@link jamlParser#noteOct}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNoteOct(jamlParser.NoteOctContext ctx);
	/**
	 * Visit a parse tree produced by {@link jamlParser#noteLength}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNoteLength(jamlParser.NoteLengthContext ctx);
	/**
	 * Visit a parse tree produced by {@link jamlParser#noteOctLength}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNoteOctLength(jamlParser.NoteOctLengthContext ctx);
	/**
	 * Visit a parse tree produced by {@link jamlParser#rest}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRest(jamlParser.RestContext ctx);
	/**
	 * Visit a parse tree produced by {@link jamlParser#restLength}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRestLength(jamlParser.RestLengthContext ctx);
	/**
	 * Visit a parse tree produced by {@link jamlParser#accidental}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAccidental(jamlParser.AccidentalContext ctx);
	/**
	 * Visit a parse tree produced by {@link jamlParser#dot}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDot(jamlParser.DotContext ctx);
	/**
	 * Visit a parse tree produced by the {@code noteRepeatExpr}
	 * labeled alternative in {@link jamlParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNoteRepeatExpr(jamlParser.NoteRepeatExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code restRepeatExpr}
	 * labeled alternative in {@link jamlParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRestRepeatExpr(jamlParser.RestRepeatExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code segmentRepeatExpr}
	 * labeled alternative in {@link jamlParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSegmentRepeatExpr(jamlParser.SegmentRepeatExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code idRepeatExpr}
	 * labeled alternative in {@link jamlParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIdRepeatExpr(jamlParser.IdRepeatExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code chordExpr}
	 * labeled alternative in {@link jamlParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitChordExpr(jamlParser.ChordExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code tiedNotesExpr}
	 * labeled alternative in {@link jamlParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTiedNotesExpr(jamlParser.TiedNotesExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code octaveDownExpr}
	 * labeled alternative in {@link jamlParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOctaveDownExpr(jamlParser.OctaveDownExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code octaveUpExpr}
	 * labeled alternative in {@link jamlParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOctaveUpExpr(jamlParser.OctaveUpExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code octaveSwitchExpr}
	 * labeled alternative in {@link jamlParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOctaveSwitchExpr(jamlParser.OctaveSwitchExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code lengthSwitchExpr}
	 * labeled alternative in {@link jamlParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLengthSwitchExpr(jamlParser.LengthSwitchExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code velocitySwitchExpr}
	 * labeled alternative in {@link jamlParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVelocitySwitchExpr(jamlParser.VelocitySwitchExprContext ctx);
	/**
	 * Visit a parse tree produced by {@link jamlParser#chord}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitChord(jamlParser.ChordContext ctx);
	/**
	 * Visit a parse tree produced by {@link jamlParser#timeSignature}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTimeSignature(jamlParser.TimeSignatureContext ctx);
}