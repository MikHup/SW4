package sw4.ast;

import sw4.ast.nodes.*;
import sw4.gen.jamlBaseVisitor;
import sw4.gen.jamlLexer;
import sw4.gen.*;
import java.util.ArrayList;
import java.util.List;

public class BuildAstVisitor extends jamlBaseVisitor<AstNode> {

    @Override
    public AstNode visitProg(jamlParser.ProgContext ctx) {
        List<AstNode> nodes = new ArrayList<>();
        for(int i = 0; i < ctx.getChildCount()-1; i++) { //getChildCount -1 to skip EOF
                nodes.add(visit(ctx.getChild(i)));
        }
        return new ProgNode(nodes);
    }

    //Skip this node
    @Override
    public AstNode visitPart(jamlParser.PartContext ctx) { return visitChildren(ctx); }

    //Skip this node
    @Override
    public AstNode visitScorePart(jamlParser.ScorePartContext ctx) { return visitChildren(ctx); }

    /*
     * Assignment visit methods
     */
    @Override
    public AstNode visitSegmentAss(jamlParser.SegmentAssContext ctx) {
        String startLineInfo = "[" + ctx.getStart().getLine() + ":" + ctx.getStart().getCharPositionInLine() + "]";
        String tokenInfo = ctx.getText();

        return new SegmentAssignNode((ctx.left.getText()), (SegmentNode) visit(ctx.segment()), startLineInfo, tokenInfo);
    }

    @Override
    public AstNode visitChordAss(jamlParser.ChordAssContext ctx) {
        String startLineInfo = "[" + ctx.getStart().getLine() + ":" + ctx.getStart().getCharPositionInLine() + "]";
        String tokenInfo = ctx.getText();

        return new ChordAssignNode(ctx.left.getText(), (ChordNode) visit(ctx.chord()), startLineInfo, tokenInfo);
    }

    @Override
    public AstNode visitTitleAss(jamlParser.TitleAssContext ctx) {
        String startLineInfo = "[" + ctx.getStart().getLine() + ":" + ctx.getStart().getCharPositionInLine() + "]";
        String tokenInfo = ctx.getText();
        //Remove citations
        String title = ctx.right.getText().replace("\"","");
        return new TitleAssignNode(title, startLineInfo, tokenInfo);
    }

    @Override
    public AstNode visitKeyAss(jamlParser.KeyAssContext ctx) {
        String startLineInfo = "[" + ctx.getStart().getLine() + ":" + ctx.getStart().getCharPositionInLine() + "]";
        String tokenInfo = ctx.getText();
        return new KeyAssignNode(ctx.right.getText(), startLineInfo, tokenInfo);
    }

    @Override
    public AstNode visitTempoAss(jamlParser.TempoAssContext ctx) {
        String startLineInfo = "[" + ctx.getStart().getLine() + ":" + ctx.getStart().getCharPositionInLine() + "]";
        String tokenInfo = ctx.getText();
        return new TempoAssignNode(Integer.parseInt(ctx.right.getText()), startLineInfo, tokenInfo);
    }

    @Override
    public AstNode visitTimeSignatureAss(jamlParser.TimeSignatureAssContext ctx) {
        String startLineInfo = "[" + ctx.getStart().getLine() + ":" + ctx.getStart().getCharPositionInLine() + "]";
        String tokenInfo = ctx.getText();

        return new TimeSignatureAssignNode(
                Integer.parseInt(ctx.timeSignature().numerator.getText()),
                Integer.parseInt(ctx.timeSignature().denominator.getText()), startLineInfo, tokenInfo
        );
    }

    @Override
    public AstNode visitInstrumentAss(jamlParser.InstrumentAssContext ctx){
        String startLineInfo = "[" + ctx.getStart().getLine() + ":" + ctx.getStart().getCharPositionInLine() + "]";
        String tokenInfo = ctx.getText();

        return new InstrumentNode(ctx.instr.getText(), startLineInfo, tokenInfo);
    }

    @Override
    public AstNode visitSegment(jamlParser.SegmentContext ctx) {
        String startLineInfo = "[" + ctx.getStart().getLine() + ":" + ctx.getStart().getCharPositionInLine() + "]";
        String tokenInfo = ctx.getText();

        List<AstNode> nodes = new ArrayList<>();
        int count = ctx.getChildCount();
        for(int i = 0; i < count; i++) {
            boolean isAccidental = (ctx.getChild(i).getClass().equals(jamlParser.AccidentalContext.class));
            String value = ctx.getChild(i).getText();
            if (!(isAccidental ||value.equals("(") || value.equals(")"))){
                nodes.add(visit(ctx.getChild(i)));
            }
        }
        SegmentNode segmentNode = new SegmentNode(nodes, startLineInfo, tokenInfo);

        //Add potential accidentals
        if (!ctx.accidental().isEmpty()) {
            segmentNode.flatNodes = GetFlatAccidentals(ctx.accidental());
            segmentNode.sharpNodes = GetSharpAccidentals(ctx.accidental());
        }

        return segmentNode;
    }

    //Skip this node
    @Override
    public AstNode visitNote(jamlParser.NoteContext ctx) { return visitChildren(ctx); }

    //Skip this node
    @Override
    public AstNode visitRestNote(jamlParser.RestNoteContext ctx) { return visitChildren(ctx); }

    @Override
    public AstNode visitToneVal(jamlParser.ToneValContext ctx) {
        String startLineInfo = "[" + ctx.getStart().getLine() + ":" + ctx.getStart().getCharPositionInLine() + "]";
        String tokenInfo = ctx.getText();

        String toneVal = ctx.getChild(0).getText();

        NoteNode noteNode = new NoteNode(null, toneVal, null, startLineInfo, tokenInfo);
        //Add potential accidentals
        if (!ctx.accidental().isEmpty()) {
            noteNode.flatNodes = GetFlatAccidentals(ctx.accidental());
            noteNode.sharpNodes = GetSharpAccidentals(ctx.accidental());
        }

        //Add potential dots
        if (!ctx.dot().isEmpty()) {
            noteNode.dots = GetDots(ctx.dot());
        }
        return noteNode;
    }

    @Override
    public AstNode visitNoteOct(jamlParser.NoteOctContext ctx) {
        String startLineInfo = "[" + ctx.getStart().getLine() + ":" + ctx.getStart().getCharPositionInLine() + "]";
        String tokenInfo = ctx.getText();

        String value = ctx.getChild(0).getText();

        String toneVal = value.replaceAll("[0-9 -]","");
        int oct = Integer.parseInt(value.replaceAll("[a-z]",""));
        NoteNode noteNode = new NoteNode(null, toneVal, oct, startLineInfo, tokenInfo);

        //Add potential accidentals
        if (!ctx.accidental().isEmpty()) {
            noteNode.flatNodes = GetFlatAccidentals(ctx.accidental());
            noteNode.sharpNodes = GetSharpAccidentals(ctx.accidental());
        }
        //Add potential dots
        if (!ctx.dot().isEmpty()) {
            noteNode.dots = GetDots(ctx.dot());
        }
        return noteNode;
    }

    public AstNode visitNoteLength(jamlParser.NoteLengthContext ctx) {
        String startLineInfo = "[" + ctx.getStart().getLine() + ":" + ctx.getStart().getCharPositionInLine() + "]";
        String tokenInfo = ctx.getText();

        String value = ctx.getChild(0).getText();

        Integer noteLength = Integer.parseInt(value.split("[a-z]")[0]);
        String toneVal = value.replaceAll("[0-9]","");

        NoteNode noteNode = new NoteNode(noteLength, toneVal, null, startLineInfo, tokenInfo);

        //Add potential accidentals
        if (!ctx.accidental().isEmpty()) {
            noteNode.flatNodes = GetFlatAccidentals(ctx.accidental());
            noteNode.sharpNodes = GetSharpAccidentals(ctx.accidental());
        }
        //Add potential dots
        if (!ctx.dot().isEmpty()) {
            noteNode.dots = GetDots(ctx.dot());
        }
        return noteNode;
    }

    @Override
    public AstNode visitNoteOctLength(jamlParser.NoteOctLengthContext ctx) {
        String startLineInfo = "[" + ctx.getStart().getLine() + ":" + ctx.getStart().getCharPositionInLine() + "]";
        String tokenInfo = ctx.getText();

        String value = ctx.getChild(0).getText();
        String valueParts[] = value.split("[a-z]");


        int noteLength = Integer.parseInt(valueParts[0]);
        String toneVal = value.replaceAll("[0-9 -]","");
        int oct = Integer.parseInt(valueParts[1]);
        NoteNode noteNode = new NoteNode(noteLength, toneVal, oct, startLineInfo, tokenInfo);

        //Add potential accidentals
        if (!ctx.accidental().isEmpty()) {
            noteNode.flatNodes = GetFlatAccidentals(ctx.accidental());
            noteNode.sharpNodes = GetSharpAccidentals(ctx.accidental());
        }
        //Add potential dots
        if (!ctx.dot().isEmpty()) {
            noteNode.dots = GetDots(ctx.dot());
        }
        return noteNode;
    }

    @Override public AstNode visitDot(jamlParser.DotContext ctx) {
        return new DotNode();
    }



    @Override
    public AstNode visitRest(jamlParser.RestContext ctx) {
        String startLineInfo = "[" + ctx.getStart().getLine() + ":" + ctx.getStart().getCharPositionInLine() + "]";
        String tokenInfo = ctx.getText();

        String restVal = ctx.getChild(0).getText();
        RestNode restNode = new RestNode(restVal, null, startLineInfo, tokenInfo);
        //Add potential dots
        if (!ctx.dot().isEmpty()) {
            restNode.dots = GetDots(ctx.dot());
        }
        return restNode;
    }
    @Override
    public AstNode visitRestLength(jamlParser.RestLengthContext ctx) {
        String startLineInfo = "[" + ctx.getStart().getLine() + ":" + ctx.getStart().getCharPositionInLine() + "]";
        String tokenInfo = ctx.getText();

        String value = ctx.getChild(0).getText();
        int restLength = Integer.parseInt(value.replaceAll("[a-z]", ""));
        String restVal = value.replaceAll("[0-9]","");
        RestNode restNode = new RestNode(restVal, restLength, startLineInfo, tokenInfo);
        //Add potential dots
        if (!ctx.dot().isEmpty()) {
            restNode.dots = GetDots(ctx.dot());
        }
        return restNode;
    }

    @Override
    public AstNode visitAccidental(jamlParser.AccidentalContext ctx) {
        if(ctx.acc.getType() == jamlLexer.SHARP)
            return new SharpNode();
        else
            return new FlatNode();
    }

    @Override
    public AstNode visitChord(jamlParser.ChordContext ctx) {
        String startLineInfo = "[" + ctx.getStart().getLine() + ":" + ctx.getStart().getCharPositionInLine() + "]";
        String tokenInfo = ctx.getText();

        List<NoteNode> noteNodes = new ArrayList<>();
        //Cycle through each note in the chord and add it to list
        for (jamlParser.NoteContext note: ctx.note()) {
            noteNodes.add( (NoteNode) visit(note));
        }
        return new ChordNode(noteNodes, startLineInfo, tokenInfo);
    }

/*
 * Expr visit methods
 */
    public AstNode visitNoteRepeatExpr(jamlParser.NoteRepeatExprContext ctx) {
        String startLineInfo = "[" + ctx.getStart().getLine() + ":" + ctx.getStart().getCharPositionInLine() + "]";
        String tokenInfo = ctx.getText();

        return new RepeatNode(
                visit(ctx.note()),
                Integer.parseInt(ctx.value.getText()), startLineInfo, tokenInfo
                );
    }
    public AstNode visitRestRepeatExpr(jamlParser.RestRepeatExprContext ctx) {
        String startLineInfo = "[" + ctx.getStart().getLine() + ":" + ctx.getStart().getCharPositionInLine() + "]";
        String tokenInfo = ctx.getText();

        return new RepeatNode(
                visit(ctx.restNote()),
                Integer.parseInt(ctx.value.getText()), startLineInfo, tokenInfo
        );
    }


    @Override public AstNode visitTiedNotesExpr(jamlParser.TiedNotesExprContext ctx) {
        String startLineInfo = "[" + ctx.getStart().getLine() + ":" + ctx.getStart().getCharPositionInLine() + "]";
        String tokenInfo = ctx.getText();
        List<NoteNode> notes = new ArrayList<>();

        //Cycle through each note in the expression and add it to list
        for (jamlParser.NoteContext note: ctx.note()) {
            notes.add( (NoteNode) visit(note));
        }

        return new TieNode(notes, startLineInfo, tokenInfo);
    }

    @Override
    public AstNode visitOctaveDownExpr(jamlParser.OctaveDownExprContext ctx) {
        String startLineInfo = "[" + ctx.getStart().getLine() + ":" + ctx.getStart().getCharPositionInLine() + "]";
        String tokenInfo = ctx.getText();
        return new OctDownNode(startLineInfo, tokenInfo);
    }

    @Override
    public AstNode visitOctaveUpExpr(jamlParser.OctaveUpExprContext ctx) {
        String startLineInfo = "[" + ctx.getStart().getLine() + ":" + ctx.getStart().getCharPositionInLine() + "]";
        String tokenInfo = ctx.getText();
        return new OctUpNode(startLineInfo, tokenInfo);
    }


    @Override public AstNode visitSegmentRepeatExpr(jamlParser.SegmentRepeatExprContext ctx) {
        String startLineInfo = "[" + ctx.getStart().getLine() + ":" + ctx.getStart().getCharPositionInLine() + "]";
        String tokenInfo = ctx.getText();

        SegmentNode segmentNode = (SegmentNode) visit(ctx.segment());

        // Create repeatnode if there is a repeat value
        if (!(ctx.value == null)){
            return new RepeatNode(segmentNode,Integer.parseInt(ctx.value.getText()), startLineInfo, tokenInfo);
        }
        return segmentNode;
    }
    //Skip this node
    @Override public AstNode visitChordExpr(jamlParser.ChordExprContext ctx) { return visitChildren(ctx); }

    @Override public AstNode visitOctaveSwitchExpr(jamlParser.OctaveSwitchExprContext ctx) {
        String startLineInfo = "[" + ctx.getStart().getLine() + ":" + ctx.getStart().getCharPositionInLine() + "]";
        String tokenInfo = ctx.getText();

        //Gets the number part of the child string and converts it to int
        return new OctSwitchNode(Integer.parseInt(ctx.getChild(0).getText().substring(1)), startLineInfo, tokenInfo);
    }

    @Override public AstNode visitLengthSwitchExpr(jamlParser.LengthSwitchExprContext ctx) {
        String startLineInfo = "[" + ctx.getStart().getLine() + ":" + ctx.getStart().getCharPositionInLine() + "]";
        String tokenInfo = ctx.getText();
        //Gets the number part of the child string and converts it to int
        return new LengthSwitchNode(Integer.parseInt(ctx.getChild(0).getText().substring(1)), startLineInfo, tokenInfo);
    }


    @Override public AstNode visitVelocitySwitchExpr(jamlParser.VelocitySwitchExprContext ctx) {
        String startLineInfo = "[" + ctx.getStart().getLine() + ":" + ctx.getStart().getCharPositionInLine() + "]";
        String tokenInfo = ctx.getText();

        //Gets the number part of the child string and converts it to int
        return new VelocitySwitchNode(Integer.parseInt(ctx.getChild(0).getText().substring(1)), startLineInfo, tokenInfo);
    }

    @Override public AstNode visitIdRepeatExpr(jamlParser.IdRepeatExprContext ctx) {
        String startLineInfo = "[" + ctx.getStart().getLine() + ":" + ctx.getStart().getCharPositionInLine() + "]";
        String tokenInfo = ctx.getText();

        IdentifierNode identifierNode = new IdentifierNode(ctx.identifier.getText(), startLineInfo, tokenInfo);

        //Add potential accidentals
        if (!ctx.accidental().isEmpty()){
            identifierNode.flatNodes = GetFlatAccidentals(ctx.accidental());
            identifierNode.sharpNodes = GetSharpAccidentals(ctx.accidental());
        }
        else{
            identifierNode.flatNodes = new ArrayList<FlatNode>();
            identifierNode.sharpNodes = new ArrayList<SharpNode>();
        }
        // Create repeatnode if there is a repeat value
        if (!(ctx.value == null)){
            return new RepeatNode(identifierNode,Integer.parseInt(ctx.value.getText()), startLineInfo, tokenInfo);
        }
        return identifierNode;
    }

    private List<FlatNode> GetFlatAccidentals(List<jamlParser.AccidentalContext> accidentalContexts){
        List<FlatNode> accidentals = new ArrayList<>();
        for (jamlParser.AccidentalContext accidental: accidentalContexts) {
            if(accidental.acc.getType() == jamlLexer.FLAT) {
                accidentals.add((FlatNode) visit(accidental));
            }
        }
        return accidentals;
    }

    private List<SharpNode> GetSharpAccidentals(List<jamlParser.AccidentalContext> accidentalContexts){
        List<SharpNode> accidentals = new ArrayList<>();
        for (jamlParser.AccidentalContext accidental: accidentalContexts) {
            if(accidental.acc.getType() == jamlLexer.SHARP) {
                accidentals.add((SharpNode) visit(accidental));
            }
        }
        return accidentals;
    }

    private List<DotNode> GetDots(List<jamlParser.DotContext> dotContexts){
        List<DotNode> dots = new ArrayList<>();
        for (jamlParser.DotContext dot : dotContexts) {
            dots.add((DotNode) visit(dot));
        }
        return dots;
    }
}