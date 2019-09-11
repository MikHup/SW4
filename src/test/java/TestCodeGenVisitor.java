import org.junit.jupiter.api.Test;

import static javax.print.attribute.standard.MediaSizeName.B;
import static org.junit.jupiter.api.Assertions.assertEquals;

import sw4.ast.CodeGenVisitor;
import sw4.ast.nodes.*;
import sw4.codegen.MidiUtility;
import sw4.symboltable.SymbolTable;

import java.util.ArrayList;
import java.util.List;

public class TestCodeGenVisitor {

    SymbolTable st = new SymbolTable();
    CodeGenVisitor cgv = new CodeGenVisitor(st);

    @Test
    void testTitleAssignNode() {
        TitleAssignNode tan = new TitleAssignNode("NewTitle", "1", "1");
        cgv.Visit(tan);
        assertEquals(0x03, cgv.midiGen.byteEventType);
    }

    @Test
    void testKeyAssignNode() {
        KeyAssignNode kan = new KeyAssignNode("Gmaj", "1", "1");
        cgv.Visit(kan);
        assertEquals(0x59, cgv.midiGen.byteEventType);
    }

    @Test
    void testTempoAssignNodeCalculateTempoMilliseconds() {
        TempoAssignNode tan = new TempoAssignNode(120, "1", "1");
        cgv.Visit(tan);
        assertEquals(500000, cgv.tempo);
    }

    @Test
    void testTempoAssignNode2() {
        TempoAssignNode tan = new TempoAssignNode(120, "1", "1");
        cgv.Visit(tan);
        assertEquals(0x51, cgv.midiGen.byteEventType);
    }

    @Test
    void testTimeSignatureAssignNode() {
        TimeSignatureAssignNode tsan = new TimeSignatureAssignNode(2, 4, "1", "1");
        cgv.Visit(tsan);
        assertEquals(0x58, cgv.midiGen.byteEventType);
    }

    @Test
    void testIdentifierNode() {

    }

    @Test
    void testOctSwitchNode() {
        OctSwitchNode osn = new OctSwitchNode(2, "1", "1");
        cgv.currentOct = 4;
        cgv.Visit(osn);
        assertEquals(2, cgv.currentOct);
    }

    @Test
    void testOctUpNode() {
        OctUpNode osn = new OctUpNode("2", "a");
        cgv.currentOct = 4;
        cgv.Visit(osn);
        assertEquals(5, cgv.currentOct);
    }

    @Test
    void testOctDownNode() {
        OctDownNode osn = new OctDownNode("2", "a");
        cgv.currentOct = 4;
        cgv.Visit(osn);
        assertEquals(3, cgv.currentOct);
    }

    @Test
    void testLengthSwitchNode() {
        LengthSwitchNode lsn = new LengthSwitchNode(8, "1", "1");
        cgv.currentLength = 4;
        cgv.Visit(lsn);
        assertEquals(8, cgv.currentLength);
    }

    @Test
    void testVelocitySwitchNode() {
        VelocitySwitchNode lsn = new VelocitySwitchNode(90, "1", "1");
        cgv.velocity = 100;
        cgv.Visit(lsn);
        assertEquals(90, cgv.velocity);
    }

    @Test
    void testTiedNodes() {
        long noteTick = 0;
        List<NoteNode> tiedNotesList = new ArrayList();
        NoteNode nn = new NoteNode(4, "f", 2, "2", "a");
        NoteNode nnn = new NoteNode(8, "f", 2, "2", "a");
        tiedNotesList.add(nn);
        tiedNotesList.add(nnn);
        TieNode tn = new TieNode(tiedNotesList, "1", "1");
        cgv.Visit(tn);
        noteTick = tn.notes.get(0).tick;
        assertEquals(96, noteTick);
    }

    @Test
    void testInstrumentNode() {
        InstrumentNode in = new InstrumentNode("Organ", "1", "1");
        InstrumentNode node = new InstrumentNode("Piano", "1", "1");
        cgv.Visit(in);
        assertEquals(1, cgv.currentChannel);
        cgv.Visit(node);
        assertEquals(2, cgv.currentChannel);
    }
}
