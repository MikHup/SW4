package sw4.ast;

import sw4.ast.nodes.*;
import sw4.codegen.MidiUtility;
import sw4.errorhandling.SemanticErrorHandler;
import sw4.symboltable.Symbol;
import sw4.symboltable.SymbolTable;


public class SemanticCheckVisitor extends AstBaseVisitor {
    public SemanticErrorHandler semanticErrorHandler;
    public SymbolTable symbolTbl;
    public boolean titleAssigned;
    public int currentOct;
    public int currentLength;
    public int currentChannel = 0;
    private final int MIN_OCT = -1;
    private final int MAX_OCT = 8;
    private final int MIN_TEMPO = 1;
    private final int MAX_TEMPO = 999;
    private final int MIN_VELOCITY = 0;
    private final int MAX_VELOCITY = 127;
    private final int MIN_NUMERATOR = 1;
    private final int MAX_NUMERATOR = 16;

    public SemanticCheckVisitor(SemanticErrorHandler semanticErrorHandler, SymbolTable symbolTbl){
        this.semanticErrorHandler = semanticErrorHandler;
        this.symbolTbl = symbolTbl;
        titleAssigned = false;
        currentOct = 4;
        currentLength = 4;
    }

    @Override
    public void Visit(ProgNode node) {
        for (AstNode part: node.parts) {
            part.Accept(this);
        }
    }

    @Override
    public void Visit(SegmentAssignNode node){
        if(symbolTbl.ContainsSymbol(node.id)){
            semanticErrorHandler.AddError(node.lineInfo + " - Identifier \"" + node.id + "\" is already declared");
        } else{
            symbolTbl.AddSymbol(new Symbol(node.id, node.node));
        }
    }

    @Override
    public void Visit(ChordAssignNode node){
        if(symbolTbl.ContainsSymbol(node.id)){
            semanticErrorHandler.AddError(node.lineInfo + " - Identifier \"" + node.id + "\" is already declared");
        } else{
            symbolTbl.AddSymbol(new Symbol(node.id, node.node));
        }
    }

    @Override
    public void Visit(TitleAssignNode node) {
        if(!titleAssigned){
            titleAssigned = true;
        } else{
            semanticErrorHandler.AddError(node.lineInfo + " - Title is already assigned");
        }
    }

    @Override
    public void Visit(TempoAssignNode node) {
        if (node.tempo < MIN_TEMPO || node.tempo > MAX_TEMPO){
            semanticErrorHandler.AddError(node.lineInfo + " - Invalid tempo: " + node.tempo);
        }
    }

    @Override
    public void Visit(TimeSignatureAssignNode node) {
        if(!(node.denominator == 1 ||
                node.denominator == 2  ||
                node.denominator == 4  ||
                node.denominator == 8  ||
                node.denominator == 16 ||
                node.denominator == 32 ||
                node.denominator == 64 )){
            semanticErrorHandler.AddError(node.lineInfo + " - TimeSignature invalid denominator: " + node.denominator);
        }
        if(node.numerator < MIN_NUMERATOR || node.numerator > MAX_NUMERATOR){
            semanticErrorHandler.AddError(node.lineInfo + " - TimeSignature invalid numerator: " + node.numerator);
        }
    }

    @Override
    public void Visit(IdentifierNode identifierNode) {
        int tempOct = currentOct;
        int tempLength = currentLength;

        if (!(symbolTbl.ContainsSymbol(identifierNode.name))){
            semanticErrorHandler.AddError(identifierNode.lineInfo + " - Undeclared constant: " + identifierNode.name);
        }
        else {
            AstNode nodeVal = symbolTbl.getSymbol(identifierNode.name);
            if (nodeVal.getClass().equals(SegmentNode.class)) {
                if (!(identifierNode.sharpNodes.isEmpty() && identifierNode.flatNodes.isEmpty())) {
                    SegmentNode clone = (SegmentNode) nodeVal.Clone(nodeVal);
                    clone.sharpNodes.addAll(identifierNode.sharpNodes);
                    clone.flatNodes.addAll(identifierNode.flatNodes);
                    clone.Accept(this);
                }
            } else if (nodeVal.getClass().equals(ChordNode.class)) {
                if (!(identifierNode.sharpNodes.isEmpty() && identifierNode.flatNodes.isEmpty())) {
                    ChordNode clone = (ChordNode) nodeVal.Clone(nodeVal);
                    for (NoteNode noteNode : clone.notes) {
                        noteNode.sharpNodes.addAll(identifierNode.sharpNodes);
                        noteNode.flatNodes.addAll(identifierNode.flatNodes);
                    }
                    clone.Accept(this);
                }
            }
            //In order to apply tick values to notes and such
            nodeVal.Accept(this);
        }
        currentOct = tempOct;
        currentLength = tempLength;
    }

    @Override
    public void Visit(OctSwitchNode node) {
        if(node.value < MIN_OCT || node.value > MAX_OCT){
            semanticErrorHandler.AddError(node.lineInfo + " - Invalid octave: " + node.value);
        } else {
            currentOct = node.value;
        }
    }

    @Override
    public void Visit(OctUpNode node) {
        if(currentOct == MAX_OCT){
            semanticErrorHandler.AddError(node.lineInfo + " - Current Octave is too high to increment");
        } else{
            currentOct++;
        }
    }

    @Override
    public void Visit(OctDownNode node) {
        if(currentOct == MIN_OCT){
            semanticErrorHandler.AddError(node.lineInfo + " - Current Octave is too low to decrement");
        } else{
            currentOct--;
        }
    }

    @Override
    public void Visit(LengthSwitchNode node) {
        if(!(node.value == 1 ||
                node.value == 2  ||
                node.value == 4  ||
                node.value == 8  ||
                node.value == 16 ||
                node.value == 32 ||
                node.value == 64 )){
            semanticErrorHandler.AddError(node.lineInfo + " - Note length " + node.value + " is invalid");
        }
        else {
            currentLength = node.value;
        }
    }

    @Override
    public void Visit(VelocitySwitchNode node) {
        if(node.value < MIN_VELOCITY || node.value > MAX_VELOCITY){
            semanticErrorHandler.AddError(node.lineInfo + " - Invalid Velocity: " + node.value);
        }
    }

    @Override
    public void Visit(SegmentNode node) {
        int tempOct = currentOct;
        int tempLength = currentLength;

        for (AstNode content : node.contents) {
            if (content.getClass().equals(NoteNode.class)) {
                NoteNode noteNode = (NoteNode) content;
                noteNode.sharpNodes.addAll(node.sharpNodes);
                noteNode.flatNodes.addAll(node.flatNodes);
            }
            if (content.getClass().equals(ChordNode.class)) {
                ChordNode chordNode = (ChordNode) content;
                for (NoteNode noteNode : chordNode.notes) {
                    noteNode.sharpNodes.addAll(node.sharpNodes);
                    noteNode.flatNodes.addAll(node.flatNodes);
                }
            }
            if (content.getClass().equals(SegmentNode.class)) {
                SegmentNode segmentNode = (SegmentNode) content;
                segmentNode.sharpNodes.addAll(node.sharpNodes);
                segmentNode.flatNodes.addAll(node.flatNodes);
            }
            if (content.getClass().equals(TieNode.class)) {
                TieNode tieNode = (TieNode) content;
                for (NoteNode noteNode : tieNode.notes) {
                    noteNode.sharpNodes.addAll(node.sharpNodes);
                    noteNode.flatNodes.addAll(node.flatNodes);
                }
            }
            if (content.getClass().equals(RepeatNode.class)) {
                RepeatNode repeatNode = (RepeatNode) content;
                if (repeatNode.operand.getClass().equals(NoteNode.class)) {
                    NoteNode noteNode = (NoteNode) repeatNode.operand;
                    noteNode.sharpNodes.addAll(node.sharpNodes);
                    noteNode.flatNodes.addAll(node.flatNodes);
                } else if (repeatNode.operand.getClass().equals(SegmentNode.class)) {
                    SegmentNode segmentNode = (SegmentNode) repeatNode.operand;
                    segmentNode.sharpNodes.addAll(node.sharpNodes);
                    segmentNode.flatNodes.addAll(node.flatNodes);
                } else if (repeatNode.operand.getClass().equals(IdentifierNode.class)) {
                    IdentifierNode identifierNode = (IdentifierNode) repeatNode.operand;
                    identifierNode.sharpNodes.addAll(node.sharpNodes);
                    identifierNode.flatNodes.addAll(node.flatNodes);
                }
            }
            if (content.getClass().equals(IdentifierNode.class)) {
                IdentifierNode identifierNode = (IdentifierNode) content;
                identifierNode.sharpNodes.addAll(node.sharpNodes);
                identifierNode.flatNodes.addAll(node.flatNodes);
            }
            content.Accept(this);
        }
        node.sharpNodes.clear();
        node.flatNodes.clear();

        currentOct = tempOct;
        currentLength = tempLength;
    }


    @Override
    public void Visit(ChordNode node) {
        for (NoteNode note: node.notes) {
            note.Accept(this);
        }
    }

    @Override
    public void Visit(NoteNode noteNode) {
        //Check note length val
        if (noteNode.noteL != null) {
            if (!(noteNode.noteL == 1 ||
                    noteNode.noteL == 2 ||
                    noteNode.noteL == 4 ||
                    noteNode.noteL == 8 ||
                    noteNode.noteL == 16 ||
                    noteNode.noteL == 32 ||
                    noteNode.noteL == 64)) {
                semanticErrorHandler.AddError(noteNode.lineInfo + " - Note length " + noteNode.noteL + " is invalid");
            }
        }

        //Check octave val
        if (noteNode.oct != null) {
            if (noteNode.oct < MIN_OCT || noteNode.oct > MAX_OCT) {
                semanticErrorHandler.AddError(noteNode.lineInfo + " - Invalid octave: " + noteNode.oct);
            }
        }
        else{
            noteNode.oct = currentOct;
        }

            for (AstNode accidental : noteNode.sharpNodes) {
                 noteNode.toneval = ApplySharpAccidentals(noteNode);
            }
            noteNode.sharpNodes.clear();

            for (AstNode accidental : noteNode.flatNodes) {
                noteNode.toneval = ApplyFlatAccidentals(noteNode);
            }
            noteNode.flatNodes.clear();

        if (noteNode.noteL == null)
            noteNode.noteL = currentLength;

            double tickVal = MidiUtility.CalculateTick(noteNode.noteL, noteNode.dots.size());
            if ((tickVal % 1) != 0) {
                semanticErrorHandler.AddError("Too many dots in note \"" + noteNode.tokenInfo + "\" at " + noteNode.lineInfo + ", precision is lost");
            }
            noteNode.tick = (long) tickVal;
            noteNode.dots.clear();

    }

    @Override
    public void Visit(RepeatNode node) {
        for(int i = 0; i < node.repeatValue; i++)
        node.operand.Accept(this);
    }

    @Override
    public void Visit(TieNode node) {
        for (NoteNode note: node.notes) {
            note.Accept(this);
        }
        String toneVal = node.notes.get(0).toneval;
        int oct = node.notes.get(0).oct;

        for (NoteNode note: node.notes) {
            if(!(note.toneval.equals(toneVal) && note.oct.equals(oct))){
                semanticErrorHandler.AddError(node.lineInfo + " - Invalid tie node");
            }
        }
    }

    @Override
    public void Visit(InstrumentNode node) {
        currentOct = 4;
        currentLength = 4;
        if (currentChannel > 15)
        {
            semanticErrorHandler.AddError(node.lineInfo + " - No more instruments can be added");
        }

        if (currentChannel == 9)
        {
            currentChannel++;
        }
        currentChannel++;

    }

    @Override
    public void Visit(RestNode restNode) {
        if (restNode.length == null)
            restNode.length = currentLength;
            double tickVal = MidiUtility.CalculateTick(restNode.length, restNode.dots.size());
            if ((tickVal % 1) != 0) {
                semanticErrorHandler.AddError("Too many dots in rest \"" + restNode.tokenInfo + "\" at " + restNode.lineInfo + ", precision is lost");
            }
            restNode.tick = (long) tickVal;
            restNode.dots.clear();
    }

    public String ApplySharpAccidentals(NoteNode noteNode){
        switch (noteNode.toneval) {
                case "c":
                    return "c+";
                case "c+":
                    return "d";
                case "d":
                    return "d+";
                case "d+":
                    return "e";
                case "e":
                    return "f";
                case "f":
                    return "f+";
                case "f+":
                    return "g";
                case "g":
                    return "g+";
                case "g+":
                    return "a";
                case "a":
                    return "a+";
                case "a+":
                    return "b";
                case "b":
                    if(noteNode.oct < MAX_OCT){
                        noteNode.oct++;
                        return "c";
                    }
                    else{
                        semanticErrorHandler.AddError(noteNode.lineInfo + " - This note's octave is too high to increment");
                        return "b";
                    }
            }
        return null;
    }

    public String ApplyFlatAccidentals(NoteNode noteNode){
        switch (noteNode.toneval) {
            case "c":
                if(noteNode.oct > MIN_OCT) {
                    noteNode.oct--;
                    return "b";
                }
                else{
                    semanticErrorHandler.AddError(noteNode.lineInfo + " - This note's octave is too low to decrement");
                    return "c";
                }
            case "c+":
                return "c";
            case "d":
                return "c+";
            case "d+":
                return "d";
            case "e":
                return "d+";
            case "f":
                return "e";
            case "f+":
                return "f";
            case "g":
                return "f+";
            case "g+":
                return "g";
            case "a":
                return "g+";
            case "a+":
                return "a";
            case "b":
                return "a+";
        }
        return null;
    }
}
