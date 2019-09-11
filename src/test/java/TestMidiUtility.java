import org.junit.jupiter.api.Test;
import sw4.ast.nodes.DotNode;
import sw4.ast.nodes.NoteNode;
import sw4.codegen.MidiUtility;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class TestMidiUtility {
    @Test
    void testCalculateTick(){
        NoteNode nn = new NoteNode(4, "c", 6, "2", "2");
        double tickVal = MidiUtility.CalculateTick(nn.noteL, nn.dots.size());
        assertEquals(64.0, tickVal);
    }
    @Test
    void testCalculateTickDotGetsApplied(){
        NoteNode nn = new NoteNode(4, "c", 6, "2", "2");
        DotNode dn = new DotNode();
        nn.dots.add(dn);
        nn.dots.add(dn);
        assertEquals(2, nn.dots.size());
        double tickVal = MidiUtility.CalculateTick(nn.noteL, nn.dots.size());
        assertEquals(112, tickVal);
    }
    @Test
    void testNoteToMidiOctMinus1(){
        NoteNode nn = new NoteNode(4, "c", -1, "2", "2");
        int midiNoteVal = MidiUtility.NoteToMidi(nn.oct, nn.toneval);
        assertEquals(0, midiNoteVal);
    }
    @Test
    void testNoteToMidiOct0(){
        NoteNode nn = new NoteNode(4, "c", 0, "2", "2");
        int midiNoteVal = MidiUtility.NoteToMidi(nn.oct, nn.toneval);
        assertEquals(12, midiNoteVal);
    }
    @Test
    void testNoteToMidiOct1(){
        NoteNode nn = new NoteNode(4, "a+", 1, "2", "2");
        int midiNoteVal = MidiUtility.NoteToMidi(nn.oct, nn.toneval);
        assertEquals(34, midiNoteVal);
    }
    @Test
    void testNoteToMidiOct2(){
        NoteNode nn = new NoteNode(4, "c", 2, "2", "2");
        int midiNoteVal = MidiUtility.NoteToMidi(nn.oct, nn.toneval);
        assertEquals(36, midiNoteVal);
    }
    @Test
    void testNoteToMidiOct3(){
        NoteNode nn = new NoteNode(4, "b", 3, "2", "2");
        int midiNoteVal = MidiUtility.NoteToMidi(nn.oct, nn.toneval);
        assertEquals(59, midiNoteVal);
    }
    @Test
    void testNoteToMidiOct4(){
        NoteNode nn = new NoteNode(4, "g+", 4, "2", "2");
        int midiNoteVal = MidiUtility.NoteToMidi(nn.oct, nn.toneval);
        assertEquals(68, midiNoteVal);
    }
    @Test
    void testNoteToMidiOct5(){
        NoteNode nn = new NoteNode(4, "d", 5, "2", "2");
        int midiNoteVal = MidiUtility.NoteToMidi(nn.oct, nn.toneval);
        assertEquals(74, midiNoteVal);
    }
    @Test
    void testNoteToMidiOct6(){
        NoteNode nn = new NoteNode(4, "d", 6, "2", "2");
        int midiNoteVal = MidiUtility.NoteToMidi(nn.oct, nn.toneval);
        assertEquals(86, midiNoteVal);
    }
    @Test
    void testNoteToMidiOct7(){
        NoteNode nn = new NoteNode(4, "e", 7, "2", "2");
        int midiNoteVal = MidiUtility.NoteToMidi(nn.oct, nn.toneval);
        assertEquals(100, midiNoteVal);
    }
    @Test
    void testNoteToMidiOct8(){
        NoteNode nn = new NoteNode(4, "f+", 8, "2", "2");
        int midiNoteVal = MidiUtility.NoteToMidi(nn.oct, nn.toneval);
        assertEquals(114, midiNoteVal);
    }
    @Test
    void testNoteToMidiOct9(){
        NoteNode nn = new NoteNode(4, "a", 9, "2", "2");
        int midiNoteVal = MidiUtility.NoteToMidi(nn.oct, nn.toneval);
        assertEquals(-1, midiNoteVal);
    }
}