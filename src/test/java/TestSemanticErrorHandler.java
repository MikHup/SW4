import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import sw4.ast.SemanticCheckVisitor;
import sw4.ast.nodes.*;
import sw4.errorhandling.SemanticErrorHandler;
import sw4.symboltable.SymbolTable;

import java.util.LinkedList;

/*
    All tests in this file checks either whether an error is thrown,
    or which error message is created.
 */


public class TestSemanticErrorHandler {
    SemanticErrorHandler eh = new SemanticErrorHandler();
    SymbolTable st = new SymbolTable();
    SemanticCheckVisitor tc = new SemanticCheckVisitor(eh, st);


    //SegmentAssignNode
    @Test
    void testSegmentAssignNodeCorrectIdentifier() {
        SegmentNode sn = new SegmentNode();
        SegmentAssignNode san = new SegmentAssignNode("SegmentA", sn, "2", "a");
        tc.Visit(san);
        assertEquals(0, eh.GetErrors().size());
    }

    @Test
    void testSegmentAssignNodeIdentifierAlreadyDeclared() {
        SegmentNode sn = new SegmentNode();
        SegmentAssignNode san = new SegmentAssignNode("SegmentA", sn, "2", "a");
        tc.Visit(san);
        SegmentAssignNode san2 = new SegmentAssignNode("SegmentA", sn, "2", "a");
        tc.Visit(san2);
        assertEquals(1, eh.GetErrors().size());
    }

    @Test
    void testSegmentAssignNodeIdentifierAlreadyDeclaredMessage() {
        SegmentNode sn = new SegmentNode();
        SegmentAssignNode san = new SegmentAssignNode("SegmentA", sn, "2", "a");
        tc.Visit(san);
        SegmentAssignNode san2 = new SegmentAssignNode("SegmentA", sn, "2", "a");
        tc.Visit(san2);
        assertEquals("Semantic error at Line 2 - Identifier \"SegmentA\" is already declared", eh.GetErrors().get(0));
    }


    //ChordAssignNode
    @Test
    void testChordAssignNodeCorrectIdentifier() {
        ChordNode cn = new ChordNode();
        ChordAssignNode can = new ChordAssignNode("ChordA", cn, "2", "a");
        tc.Visit(can);
        assertEquals(0, eh.GetErrors().size());
    }

    @Test
    void testChordAssignNodeIdentifierAlreadyDeclared() {
        ChordNode cn = new ChordNode();
        ChordAssignNode can = new ChordAssignNode("ChordA", cn, "2", "a");
        tc.Visit(can);
        tc.Visit(can);
        assertEquals(1, eh.GetErrors().size());
    }

    @Test
    void testChordAssignNodeIdentifierAlreadyDeclaredMessage() {
        ChordNode cn = new ChordNode();
        ChordAssignNode can = new ChordAssignNode("ChordA", cn, "2", "a");
        tc.Visit(can);
        tc.Visit(can);
        assertEquals("Semantic error at Line 2 - Identifier \"ChordA\" is already declared", eh.GetErrors().get(0));
    }


    //TitleAssignNode
    @Test
    void testTitleAssignNodeCorrectIdentifier() {
        TitleAssignNode tan = new TitleAssignNode("TitleA", "2", "a");
        tc.Visit(tan);
        assertEquals(0, eh.GetErrors().size());
    }

    @Test
    void testTitleAssignNodeIdentifierAlreadyDeclared() {
        TitleAssignNode tan = new TitleAssignNode("TitleA", "2", "a");
        tc.Visit(tan);
        tc.Visit(tan);
        assertEquals(1, eh.GetErrors().size());
    }

    @Test
    void testTitleAssignNodeIdentifierAlreadyDeclaredMessage() {
        TitleAssignNode tan = new TitleAssignNode("TitleA", "2", "a");
        tc.Visit(tan);
        tc.Visit(tan);
        assertEquals("Semantic error at Line 2 - Title is already assigned", eh.GetErrors().get(0));
    }


    //TempoAssignNode
    @Test
    void testTempoAssignNodeCorrectValue() {
        TempoAssignNode tan = new TempoAssignNode(500, "2", "a");
        tc.Visit(tan);
        assertEquals(0, eh.GetErrors().size());
    }

    @Test
    void testTempoAssignNodeValueTooHigh() {
        TempoAssignNode tan = new TempoAssignNode(1000, "2", "a");
        tc.Visit(tan);
        assertEquals(1, eh.GetErrors().size());
    }

    @Test
    void testTempoAssignNodeValueTooHighMessage() {
        TempoAssignNode tan = new TempoAssignNode(1000, "2", "a");
        tc.Visit(tan);
        assertEquals("Semantic error at Line 2 - Invalid tempo: 1000", eh.GetErrors().get(0));
    }

    @Test
    void testTempoAssignNodeValueTooLow() {
        TempoAssignNode tan = new TempoAssignNode(0, "2", "a");
        tc.Visit(tan);
        assertEquals(1, eh.GetErrors().size());
    }

    @Test
    void testTempoAssignNodeValueTooLowMessage() {
        TempoAssignNode tan = new TempoAssignNode(0, "2", "a");
        tc.Visit(tan);
        assertEquals("Semantic error at Line 2 - Invalid tempo: 0", eh.GetErrors().get(0));
    }


    //TimeSignatureAssignNode
    @Test
    void testTimeSignatureAssignNodeCorrectValues() {
        TimeSignatureAssignNode tsan = new TimeSignatureAssignNode(8, 8, "2", "a");
        tc.Visit(tsan);
        assertEquals(0, eh.GetErrors().size());
    }

    @Test
    void testTimeSignatureAssignNodeNumeratorValueTooLow() {
        TimeSignatureAssignNode tsan = new TimeSignatureAssignNode(0, 8, "2", "a");
        tc.Visit(tsan);
        assertEquals(1, eh.GetErrors().size());
    }

    @Test
    void testTimeSignatureAssignNodeNumeratorValueTooLowMessage() {
        TimeSignatureAssignNode tsan = new TimeSignatureAssignNode(0, 8, "2", "a");
        tc.Visit(tsan);
        assertEquals("Semantic error at Line 2 - TimeSignature invalid numerator: 0", eh.GetErrors().get(0));
    }

    @Test
    void testTimeSignatureAssignNodeNumeratorValueTooHigh() {
        TimeSignatureAssignNode tsan = new TimeSignatureAssignNode(17, 8, "2", "a");
        tc.Visit(tsan);
        assertEquals(1, eh.GetErrors().size());
    }

    @Test
    void testTimeSignatureAssignNodeNumeratorValueTooHighMessage() {
        TimeSignatureAssignNode tsan = new TimeSignatureAssignNode(17, 8, "2", "a");
        tc.Visit(tsan);
        assertEquals("Semantic error at Line 2 - TimeSignature invalid numerator: 17", eh.GetErrors().get(0));
    }

    @Test
    void testTimeSignatureAssignNodeDenominatorValueTooLow() {
        TimeSignatureAssignNode tsan = new TimeSignatureAssignNode(8, 0, "2", "a");
        tc.Visit(tsan);
        assertEquals(1, eh.GetErrors().size());
    }

    @Test
    void testTimeSignatureAssignNodeDenominatorValueTooLowMessage() {
        TimeSignatureAssignNode tsan = new TimeSignatureAssignNode(8, 0, "2", "a");
        tc.Visit(tsan);
        assertEquals("Semantic error at Line 2 - TimeSignature invalid denominator: 0", eh.GetErrors().get(0));
    }

    @Test
    void testTimeSignatureAssignNodeDenominatorIllegalValue() {
        TimeSignatureAssignNode tsan = new TimeSignatureAssignNode(8, 12, "2", "a");
        tc.Visit(tsan);
        assertEquals(1, eh.GetErrors().size());
    }

    @Test
    void testTimeSignatureAssignNodeDenominatorIllegalValueMessage() {
        TimeSignatureAssignNode tsan = new TimeSignatureAssignNode(8, 12, "2", "a");
        tc.Visit(tsan);
        assertEquals("Semantic error at Line 2 - TimeSignature invalid denominator: 12", eh.GetErrors().get(0));
    }

    @Test
    void testTimeSignatureAssignNodeDenominatorValueTooHigh() {
        TimeSignatureAssignNode tsan = new TimeSignatureAssignNode(8, 65, "2", "a");
        tc.Visit(tsan);
        assertEquals(1, eh.GetErrors().size());
    }

    @Test
    void testTimeSignatureAssignNodeDenominatorValueTooHighMessage() {
        TimeSignatureAssignNode tsan = new TimeSignatureAssignNode(8, 65, "2", "a");
        tc.Visit(tsan);
        assertEquals("Semantic error at Line 2 - TimeSignature invalid denominator: 65", eh.GetErrors().get(0));
    }





    //IdentifierNode
    @Test
    void testIdentifierNodeIllegalValue() {
        IdentifierNode in = new IdentifierNode("IdentifierA", "2", "a");
        tc.Visit(in);
        assertEquals(1, eh.GetErrors().size());
    }

    @Test
    void testIdentifierNodeIllegalValueMessage() {
        IdentifierNode in = new IdentifierNode("IdentifierA", "2", "a");
        tc.Visit(in);
        assertEquals("Semantic error at Line 2 - Undeclared constant: IdentifierA", eh.GetErrors().get(0));
    }


    //OctSwitchNode
    @Test
    void testOctSwitchNodeCorrectValue() {
        OctSwitchNode osn = new OctSwitchNode(4, "2", "a");
        tc.Visit(osn);
        assertEquals(0, eh.GetErrors().size());
    }

    @Test
    void testOctSwitchNodeValueTooHigh() {
        OctSwitchNode osn = new OctSwitchNode(9, "2", "a");
        tc.Visit(osn);
        assertEquals(1, eh.GetErrors().size());
    }

    @Test
    void testOctSwitchNodeValueTooHighMessage() {
        OctSwitchNode osn = new OctSwitchNode(9, "2", "a");
        tc.Visit(osn);
        assertEquals("Semantic error at Line 2 - Invalid octave: 9", eh.GetErrors().get(0));
    }

    @Test
    void testOctSwitchNodeValueTooLow() {
        OctSwitchNode osn = new OctSwitchNode(-2, "2", "a");
        tc.Visit(osn);
        assertEquals(1, eh.GetErrors().size());
    }

    @Test
    void testOctSwitchNodeValueTooLowMessage() {
        OctSwitchNode osn = new OctSwitchNode(-2, "2", "a");
        tc.Visit(osn);
        assertEquals("Semantic error at Line 2 - Invalid octave: -2", eh.GetErrors().get(0));
    }


    //OctUpNode
    @Test
    void testOctUpNodeWithinOctBounds() {
        OctSwitchNode osn = new OctSwitchNode(4, "2", "a");
        OctUpNode oun = new OctUpNode("2", "a");
        tc.Visit(osn);
        tc.Visit(oun);
        assertEquals(0, eh.GetErrors().size());
    }

    @Test
    void testOctUpNodeOnOctUpperBound() {
        OctSwitchNode osn = new OctSwitchNode(8, "2", "a");
        OctUpNode oun = new OctUpNode("2", "a");
        tc.Visit(osn);
        tc.Visit(oun);
        assertEquals(1, eh.GetErrors().size());
    }

    @Test
    void testOctUpNodeOnOctUpperBoundMessage() {
        OctSwitchNode osn = new OctSwitchNode(8, "2", "a");
        OctUpNode oun = new OctUpNode("2", "a");
        tc.Visit(osn);
        tc.Visit(oun);
        assertEquals("Semantic error at Line 2 - Current Octave is too high to increment", eh.GetErrors().get(0));
    }


    //OctDownNode
    @Test
    void testOctDownNodeWithinOctBounds() {
        OctSwitchNode osn = new OctSwitchNode(4, "2", "a");
        OctDownNode odn = new OctDownNode("2", "a");
        tc.Visit(osn);
        tc.Visit(odn);
        assertEquals(0, eh.GetErrors().size());
    }

    @Test
    void testOctDownNodeOnOctLowerBound() {
        OctSwitchNode osn = new OctSwitchNode(-1, "2", "a");
        OctDownNode odn = new OctDownNode("2", "a");
        tc.Visit(osn);
        tc.Visit(odn);
        assertEquals(1, eh.GetErrors().size());
    }

    @Test
    void testOctDownNodeOnOctLowerBoundMessage() {
        OctSwitchNode osn = new OctSwitchNode(-1, "2", "a");
        OctDownNode odn = new OctDownNode("2", "a");
        tc.Visit(osn);
        tc.Visit(odn);
        assertEquals("Semantic error at Line 2 - Current Octave is too low to decrement", eh.GetErrors().get(0));
    }


    //LengthSwitchNode
    @Test
    void testLengthSwitchNodeCorrectValue() {
        LengthSwitchNode lsn = new LengthSwitchNode(8, "2", "a");
        tc.Visit(lsn);
        assertEquals(0, eh.GetErrors().size());
    }

    @Test
    void testLengthSwitchNodeIllegalValue() {
        LengthSwitchNode lsn = new LengthSwitchNode(3, "2", "a");
        tc.Visit(lsn);
        assertEquals(1, eh.GetErrors().size());
    }

    @Test
    void testLengthSwitchNodeIllegalValueMessage() {
        LengthSwitchNode lsn = new LengthSwitchNode(3, "2", "a");
        tc.Visit(lsn);
        assertEquals("Semantic error at Line 2 - Note length 3 is invalid", eh.GetErrors().get(0));
    }


    //VelocitySwitchNode
    @Test
    void testVelocitySwitchNodeCorrectValue() {
        VelocitySwitchNode vsn = new VelocitySwitchNode(50, "2", "a");
        tc.Visit(vsn);
        assertEquals(0, eh.GetErrors().size());
    }

    @Test
    void testVelocitySwitchNodeValueTooHigh() {
        VelocitySwitchNode vsn = new VelocitySwitchNode(128, "2", "a");
        tc.Visit(vsn);
        assertEquals(1, eh.GetErrors().size());
    }

    @Test
    void testVelocitySwitchNodeValueTooHighMessage() {
        VelocitySwitchNode vsn = new VelocitySwitchNode(128, "2", "a");
        tc.Visit(vsn);
        assertEquals("Semantic error at Line 2 - Invalid Velocity: 128", eh.GetErrors().get(0));
    }

    @Test
    void testVelocitySwitchNodeValueTooLow() {
        VelocitySwitchNode vsn = new VelocitySwitchNode(-1, "2", "a");
        tc.Visit(vsn);
        assertEquals(1, eh.GetErrors().size());
    }

    @Test
    void testVelocitySwitchNodeValueTooLowMessage() {
        VelocitySwitchNode vsn = new VelocitySwitchNode(-1, "2", "a");
        tc.Visit(vsn);
        assertEquals("Semantic error at Line 2 - Invalid Velocity: -1", eh.GetErrors().get(0));
    }


    //SegmentNode
    @Test
    void testSegmentNodeSharpNodesListExists() {
        SegmentNode sn = new SegmentNode();
        assertEquals(0, sn.sharpNodes.size());
    }

    @Test
    void testSegmentNodeFlatNodesListExists() {
        SegmentNode sn = new SegmentNode();
        assertEquals(0, sn.flatNodes.size());
    }

    @Test
    void testSegmentNodeContentsListExists() {
        SegmentNode sn = new SegmentNode();
        assertEquals(0, sn.contents.size());
    }


    //ChordNode
    @Test
    void testChordNodeOctValueTooLow() {
        ChordNode cn = new ChordNode();
        cn.notes = new LinkedList<>();
        cn.notes.add(new NoteNode(4, "f", -2, "38", "a"));
        tc.Visit(cn.notes.get(0));
        assertEquals(1, eh.GetErrors().size());
    }

    @Test
    void testChordNodeOctValueTooHigh() {
        ChordNode cn = new ChordNode();
        cn.notes = new LinkedList<>();
        cn.notes.add(new NoteNode(4, "f", 9, "38", "a"));
        tc.Visit(cn.notes.get(0));
        assertEquals(1, eh.GetErrors().size());
    }

    @Test
    void testChordNodeOctValueCorrect() {
        ChordNode cn = new ChordNode();
        cn.notes = new LinkedList<>();
        cn.notes.add(new NoteNode(4, "f", 4, "38", "a"));
        tc.Visit(cn.notes.get(0));
        assertEquals(0, eh.GetErrors().size());
    }


    //NoteNode
    @Test
    void testNoteNodeCorrectValues() {
        NoteNode nn = new NoteNode(8, "f", 4, "2", "a");
        tc.Visit(nn);
        assertEquals(0, eh.GetErrors().size());
    }
    // - NoteNode.noteL
    @Test
    void testNoteLValueTooLow() {
        NoteNode nn = new NoteNode(0, "f", 4, "2", "a");
        tc.Visit(nn);
        assertEquals(1, eh.GetErrors().size());
    }

    @Test
    void testNoteLIncorrectValue() {
        NoteNode nn = new NoteNode(11, "f", 4, "2", "a");
        tc.Visit(nn);
        assertEquals(1, eh.GetErrors().size());
    }

    @Test
    void testNoteLValueTooHigh() {
        NoteNode nn = new NoteNode(120, "f", 4, "2", "a");
        tc.Visit(nn);
        assertEquals(1, eh.GetErrors().size());
    }

    @Test
    void testNoteLValueIncorrectMessage() {
        NoteNode nn = new NoteNode(120, "f", 4, "2", "a");
        tc.Visit(nn);
        assertEquals("Semantic error at Line 2 - Note length 120 is invalid", eh.GetErrors().get(0));
    }

    // - NoteNode.toneval
    @Test
    void testTonevalIllegalValueIsAccepted() {
        NoteNode nn = new NoteNode(4, "s", -1, "2", "a");
        nn.flatNodes.add(new FlatNode());
        tc.Visit(nn);
        assertEquals(0, eh.GetErrors().size());
    }

    @Test
    void testTonevalValueCorrect() {
        NoteNode nn = new NoteNode(4, "b", 4, "2", "a");
        tc.Visit(nn);
        assertEquals(0, eh.GetErrors().size());
    }

    // - NoteNode.oct
    @Test
    void testOctValueTooHigh() {
        NoteNode nn = new NoteNode(4, "f", 96, "2", "a");
        tc.Visit(nn);
        assertEquals(1, eh.GetErrors().size());
    }

    @Test
    void testOctValueCorrect() {
        NoteNode nn = new NoteNode(4, "f", 3, "2", "a");
        tc.Visit(nn);
        assertEquals(0, eh.GetErrors().size());
    }

    @Test
    void testOctValueTooLow() {
        NoteNode nn = new NoteNode(4, "f", -2, "2", "a");
        tc.Visit(nn);
        assertEquals(1, eh.GetErrors().size());
    }

    @Test
    void testOctValueIncorrectMessage() {
        NoteNode nn = new NoteNode(4, "f", 77, "2", "a");
        tc.Visit(nn);
        assertEquals("Semantic error at Line 2 - Invalid octave: 77", eh.GetErrors().get(0));
    }

    // - NoteNode.lineinfo
    @Test
    void testLineInfoEquals2() {
        NoteNode nn = new NoteNode(4, "f", 77, "2", "a");
        tc.Visit(nn);
        assertEquals("Semantic error at Line 2 - Invalid octave: 77", eh.GetErrors().get(0));
    }

    @Test
    void testLineInfoEquals38() {
        NoteNode nn = new NoteNode(4, "f", 77, "38", "a");
        tc.Visit(nn);
        assertEquals("Semantic error at Line 38 - Invalid octave: 77", eh.GetErrors().get(0));
    }

    // - NoteNode.sharpNodes
    @Test
    void testSharpNodeCorrect() {
        NoteNode nn = new NoteNode(4, "b", 4, "2", "a");
        nn.sharpNodes.add(new SharpNode());
        nn.sharpNodes.add(new SharpNode());
        nn.sharpNodes.add(new SharpNode());
        tc.Visit(nn);
        assertEquals(0, eh.GetErrors().size());
    }

    @Test
    void testSharpNodeOutOfOctBound() {
        NoteNode nn = new NoteNode(4, "b", 8, "2", "a");
        nn.sharpNodes.add(new SharpNode());
        tc.Visit(nn);
        assertEquals(1, eh.GetErrors().size());
    }

    @Test
    void testSharpNodeOutOfOctBoundMessage() {
        NoteNode nn = new NoteNode(4, "b", 8, "2", "a");
        nn.sharpNodes.add(new SharpNode());
        tc.Visit(nn);
        assertEquals("Semantic error at Line 2 - This note's octave is too high to increment", eh.GetErrors().get(0));
    }

    // - NoteNode.sharpNode & NoteNode.flatNode
    @Test
    void testFlatNodesAndSharpNodesCorrect() {
        NoteNode nn = new NoteNode(4, "b", 4, "2", "a");
        nn.flatNodes.add(new FlatNode());
        nn.flatNodes.add(new FlatNode());
        nn.sharpNodes.add(new SharpNode());
        nn.sharpNodes.add(new SharpNode());
        tc.Visit(nn);
        assertEquals(0, eh.GetErrors().size());
    }

    // - NoteNode.flatNodes
    @Test
    void testFlatNodeCorrect() {
        NoteNode nn = new NoteNode(4, "b", 4, "2", "a");
        nn.flatNodes.add(new FlatNode());
        tc.Visit(nn);
        assertEquals(0, eh.GetErrors().size());
    }

    @Test
    void testFlatNodeOutOfOctBound() {
        NoteNode nn = new NoteNode(4, "c", -1, "2", "a");
        nn.flatNodes.add(new FlatNode());
        tc.Visit(nn);
        assertEquals(1, eh.GetErrors().size());
    }

    @Test
    void testFlatNodeOutOfOctBoundMessage() {
        NoteNode nn = new NoteNode(4, "c", -1, "2", "a");
        nn.flatNodes.add(new FlatNode());
        tc.Visit(nn);
        assertEquals("Semantic error at Line 2 - This note's octave is too low to decrement", eh.GetErrors().get(0));
    }

    // - NoteNode.dots
    @Test
    void testCorrectDotAmount() {
        NoteNode nn = new NoteNode(4, "f", 4, "38", "a");
        nn.dots.add(new DotNode());
        tc.Visit(nn);
        assertEquals(0, eh.GetErrors().size());
    }

    @Test
    void testTooManyDots() {
        NoteNode nn = new NoteNode(4, "f", 4, "38", "a");

        for (int i = 0; i < 7; i++)
            nn.dots.add(new DotNode());

        tc.Visit(nn);
        assertEquals(1, eh.GetErrors().size());
    }

    @Test
    void testTooManyDotsMessage() {
        NoteNode nn = new NoteNode(4, "f", 4, "38", "a");

        for (int i = 0; i < 7; i++)
            nn.dots.add(new DotNode());

        tc.Visit(nn);
        assertEquals("Semantic error at Line Too many dots in note \"a\" at 38, precision is lost", eh.GetErrors().get(0));
    }


    //RepeatNode
    @Test
    void testRepeatNodeCorrectValues() {
        NoteNode nn = new NoteNode(4, "f", 4, "2", "a");
        RepeatNode rn = new RepeatNode(nn, 3, "2", "a");
        tc.Visit(rn);
        assertEquals(0, eh.GetErrors().size());
    }

    @Test
    void testRepeatNodeOperandWrongOctValue() {
        NoteNode nn = new NoteNode(4, "f", 10, "2", "a");
        RepeatNode rn = new RepeatNode(nn, 3, "2", "a");
        tc.Visit(rn);
        assertEquals(3, eh.GetErrors().size());
    }

    @Test
    void testRepeatNodeRepeatValueIsNegativeIsAccepted() {
        NoteNode nn = new NoteNode(4, "f", 4, "2", "a");
        RepeatNode rn = new RepeatNode(nn, -1, "2", "a");
        tc.Visit(rn);
        assertEquals(0, eh.GetErrors().size());
    }


    //TieNode
    @Test
    void testTieNodeEqualTonevalValues() {
        TieNode tn = new TieNode();
        tn.notes = new LinkedList<>();
        tn.notes.add(new NoteNode(4, "a", 4, "38", "a"));
        tn.notes.add(new NoteNode(4, "a", 4, "38", "a"));
        tc.Visit(tn);
        assertEquals(0, eh.GetErrors().size());
    }

    @Test
    void testTieNodeUnequalTonevalValues() {
        TieNode tn = new TieNode();
        tn.notes = new LinkedList<>();
        tn.notes.add(new NoteNode(4, "a", 4, "38", "a"));
        tn.notes.add(new NoteNode(4, "g", 4, "38", "a"));
        tc.Visit(tn);
        assertEquals(1, eh.GetErrors().size());
    }

    @Test
    void testTieNodeUnequalTonevalValuesMessage() {
        TieNode tn = new TieNode();
        tn.notes = new LinkedList<>();
        tn.notes.add(new NoteNode(4, "a", 4, "38", "a"));
        tn.notes.add(new NoteNode(4, "g", 4, "38", "a"));
        tc.Visit(tn);
        assertEquals("Semantic error at Line null - Invalid tie node", eh.GetErrors().get(0));
    }


    //InstrumentNode
    @Test
    void testInstrumentNodeCorrectAmount() {
        InstrumentNode in = new InstrumentNode("Piano", "2", "a");
        tc.Visit(in);
        assertEquals(0, eh.GetErrors().size());
    }

    @Test
    void testInstrumentNodeTooManyInstruments() {
        InstrumentNode in = new InstrumentNode("Piano", "2", "a");

        for (int i = 0; i < 16; i++)
            tc.Visit(in);

        assertEquals(1, eh.GetErrors().size());
    }

    @Test
    void testInstrumentNodeTooManyInstrumentsMessage() {
        InstrumentNode in = new InstrumentNode("Piano", "2", "a");

        for (int i = 0; i < 16; i++)
            tc.Visit(in);

        assertEquals("Semantic error at Line 2 - No more instruments can be added", eh.GetErrors().get(0));
    }


    //RestNode
    @Test
    void testRestNodeCorrectDotAmount() {
        RestNode rn = new RestNode("a", 4, "2", "a");
        rn.dots.add(new DotNode());
        tc.Visit(rn);
        assertEquals(0, eh.GetErrors().size());
    }

    @Test
    void testRestNodeTooManyDots() {
        RestNode rn = new RestNode("a", 4, "2", "a");

        for (int i = 0; i < 7; i++)
            rn.dots.add(new DotNode());

        tc.Visit(rn);
        assertEquals(1, eh.GetErrors().size());
    }

    @Test
    void testRestNodeTooManyDotsMessage() {
        RestNode rn = new RestNode("a", 4, "2", "a");

        for (int i = 0; i < 7; i++)
            rn.dots.add(new DotNode());

        tc.Visit(rn);
        assertEquals("Semantic error at Line Too many dots in rest \"a\" at 2, precision is lost", eh.GetErrors().get(0));
    }


    //ApplySharpAccidentals
    @Test
    void testApplySharpAccidentalsCorrectValue() {
        NoteNode nn = new NoteNode(4, "f", 4, "2", "a");
        tc.ApplySharpAccidentals(nn);
        assertEquals(0, eh.GetErrors().size());
    }

    @Test
    void testApplySharpAccidentalsIllegalTonevalIncrement() {
        NoteNode nn = new NoteNode(4, "b", 8, "2", "a");
        tc.ApplySharpAccidentals(nn);
        assertEquals(1, eh.GetErrors().size());
    }

    @Test
    void testApplySharpAccidentalsIllegalTonevalIncrementMessage() {
        NoteNode nn = new NoteNode(4, "b", 8, "2", "a");
        tc.ApplySharpAccidentals(nn);
        assertEquals("Semantic error at Line 2 - This note's octave is too high to increment", eh.GetErrors().get(0));
    }


    //ApplyFlatAccidentals
    @Test
    void testApplyFlatAccidentalsCorrectValue() {
        NoteNode nn = new NoteNode(4, "f", 4, "2", "a");
        tc.ApplySharpAccidentals(nn);
        assertEquals(0, eh.GetErrors().size());
    }

    @Test
    void testApplyFlatAccidentalsIllegalTonevalDecrement() {
        NoteNode nn = new NoteNode(4, "c", -1, "2", "a");
        tc.ApplyFlatAccidentals(nn);
        assertEquals(1, eh.GetErrors().size());
    }

}
