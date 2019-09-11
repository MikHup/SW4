import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sw4.ast.SemanticCheckVisitor;
import sw4.ast.nodes.*;
import sw4.codegen.MidiUtility;
import sw4.errorhandling.SemanticErrorHandler;
import sw4.symboltable.Symbol;
import sw4.symboltable.SymbolTable;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class TestSemanticCheckVisitor {
    SemanticErrorHandler eh = new SemanticErrorHandler();
    SymbolTable st = new SymbolTable();
    SemanticCheckVisitor sc = new SemanticCheckVisitor(eh, st);

    @BeforeEach
    void clearErrorList(){
        eh.GetErrors().clear();
    }

    @Test
    void testSegmentAssignNodeAddSymbol(){
        SegmentNode sn = new SegmentNode();
        SegmentAssignNode san = new SegmentAssignNode("segmentNode", sn, "1", "1");
        Symbol s = new Symbol("%", san);
        st.AddSymbol(s);
        assertEquals(true, st.ContainsSymbol(s.name));
    }

    @Test
    void testSegmentAssignNodeOnlyAddsSymbolOneTime(){
        SegmentNode sn = new SegmentNode();
        SegmentAssignNode san = new SegmentAssignNode("segmentNode", sn, "1", "1");
        Symbol s = new Symbol("%", san);
        Symbol s1 = new Symbol("%", san);
        st.AddSymbol(s);
        st.AddSymbol(s1);
        assertEquals(1, st.getSizeOfHashTable());
    }

    @Test
    void testChordAssignNodeAddSymbol(){
        ChordNode sn = new ChordNode();
        ChordAssignNode san = new ChordAssignNode("chordNode", sn, "1", "1");
        Symbol s = new Symbol("%", san);
        st.AddSymbol(s);
        assertEquals(true, st.ContainsSymbol(s.name));
    }

    @Test
    void testChordAssignNodeOnlyAddsSymbolOneTime(){
        ChordNode sn = new ChordNode();
        ChordAssignNode san = new ChordAssignNode("chordNode", sn, "1", "1");
        Symbol s = new Symbol("%", san);
        Symbol s1 = new Symbol("%", san);
        st.AddSymbol(s);
        st.AddSymbol(s1);
        assertEquals(1, st.getSizeOfHashTable());
    }
    @Test
    void testTitleVisitorTrue(){
        TitleAssignNode tan = new TitleAssignNode("NewTitle", "1", "1");
        sc.Visit(tan);
        assertEquals(true, sc.titleAssigned);
    }
    @Test
    void testTitleVisitorFalse(){
        assertEquals(false, sc.titleAssigned);
    }
    @Test
    void testIdentifierNodeVisit(){
        IdentifierNode in = new IdentifierNode("IdName", "2", "3");
        sc.currentOct = 4;
        sc.currentLength = 4;
        sc.Visit(in);
        assertEquals(4, sc.currentOct);
        assertEquals(4, sc.currentLength);
    }
    @Test
    void testOctSwitchNodeVisitor(){
        OctSwitchNode osn = new OctSwitchNode(3, "1", "1");
        sc.currentOct = 4;
        sc.Visit(osn);
        assertEquals(3, sc.currentOct);
    }
    @Test
    void testOctSwitchNodeVisitorFail(){
        OctSwitchNode osn = new OctSwitchNode(11, "1", "1");
        sc.currentOct = 4;
        sc.Visit(osn);
        assertEquals(4, sc.currentOct);
    }
    @Test
    void testOctUpNodeVisitor(){
        OctUpNode oun = new OctUpNode("2", "a");
        sc.currentOct = 4;
        sc.Visit(oun);
        assertEquals(5, sc.currentOct);
    }
    @Test
    void testOctDownNodeVisitor(){
        OctDownNode oun = new OctDownNode("2", "a");
        sc.currentOct = 4;
        sc.Visit(oun);
        assertEquals(3, sc.currentOct);
    }
    @Test
    void testLengthSwitchNode(){
        LengthSwitchNode lsn = new LengthSwitchNode(2, "2", "2");
        sc.currentOct = 4;
        sc.Visit(lsn);
        assertEquals(2, sc.currentLength);
    }
    @Test
    void testApplyFlatAccidentalOnNote(){
        NoteNode nn = new NoteNode(4, "f", 3, "2", "a");
        nn.flatNodes.add(new FlatNode());
        nn.flatNodes.add(new FlatNode());
        sc.Visit(nn);
        assertEquals("d+", nn.toneval);
    }
    @Test
    void testApplySharpAccidentalOnNote(){
        NoteNode nn = new NoteNode(4, "d", 3, "2", "a");
        nn.sharpNodes.add(new SharpNode());
        nn.sharpNodes.add(new SharpNode());
        sc.Visit(nn);
        assertEquals("e", nn.toneval);
    }
    @Test
    void testApplyFlatAndSharpAccidentalOnNote(){
        NoteNode nn = new NoteNode(4, "f", 3, "2", "a");
        nn.flatNodes.add(new FlatNode());
        nn.flatNodes.add(new FlatNode());
        nn.sharpNodes.add(new SharpNode());
        nn.sharpNodes.add(new SharpNode());
        nn.flatNodes.add(new FlatNode());
        sc.Visit(nn);
        assertEquals("e", nn.toneval);
    }
    @Test
    void testOctValueCorrectValue(){
        NoteNode nn = new NoteNode(4, "f", 3, "2", "a");
        sc.Visit(nn);
        int octvalue = nn.oct;
        assertEquals(3, octvalue);
    }
    @Test
    void testNoteLValueCorrectValue(){
        NoteNode nn = new NoteNode(4, "f", 3, "2", "a");
        sc.Visit(nn);
        int noteLValue = nn.noteL;
        assertEquals(4, noteLValue);
    }
    @Test
    void testTickValueCorrect(){
        NoteNode nn = new NoteNode(4, "f", 3, "2", "a");
        sc.Visit(nn);
        long tickValue = nn.tick;
        assertEquals(64, tickValue);
    }
    @Test
    void testInstrumentNodeVisitorStartChannel(){
        InstrumentNode in = new InstrumentNode("Guitar", "2", "2");
        sc.Visit(in);
        assertEquals(1, sc.currentChannel);
        sc.Visit(in);
        assertEquals(2, sc.currentChannel);
    }
    @Test
    void testInstrumentNodeVisitorChangeChannel(){
        InstrumentNode in = new InstrumentNode("Guitar", "2", "2");
        sc.Visit(in);
        sc.Visit(in);
        assertEquals(2, sc.currentChannel);
    }
    @Test
    void testRestNodeVisitor(){
        RestNode rn = new RestNode("r", 4, "2", "2");
        sc.Visit(rn);
        assertEquals(4, sc.currentLength);
    }
    @Test
    void testRestNodeVisitor2(){
        RestNode rn = new RestNode("r", 8, "2", "2");
        sc.Visit(rn);
        double tickVal = MidiUtility.CalculateTick(rn.length, rn.dots.size());
        assertEquals(32.0, tickVal);
    }
}