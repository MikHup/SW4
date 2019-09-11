package sw4.ast;

import sw4.ast.nodes.*;
import sw4.codegen.MidiCodeGenerator;
import sw4.codegen.MidiUtility;
import sw4.symboltable.SymbolTable;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

public class CodeGenVisitor extends AstBaseVisitor {
    public MidiCodeGenerator midiGen;
    private SymbolTable symbolTbl;
    public int currentOct;
    public int velocity;
    public int tempo;
    public long time;
    public int currentLength;
    public int currentChannel;
    public int midiVal;

    public CodeGenVisitor(SymbolTable symbolTbl) {
        this.symbolTbl = symbolTbl;
        midiGen = new MidiCodeGenerator();
        //Default values:
        currentOct = 4;
        velocity = 100;
        tempo = 120;
        time = 0;
        currentLength = 4;
        currentChannel = 0;
    }

    @Override
    public void Visit(ProgNode node) {
        for (AstNode part : node.parts) {
            part.Accept(this);
        }
    }

    @Override
    public void Visit(TitleAssignNode node) {
        String trackName = node.title;
        byte eventType = 0x03;
        midiGen.EmitNewMetaEvent(eventType, trackName.getBytes(), trackName.length(), 0);
    }

    @Override
    public void Visit(KeyAssignNode node) {
    String keyTemp = node.key;
    String[] split = keyTemp.split("m");
    String first = split[0];
    String second = split[1];

    int noteKey = 0;
    int majorMinorKey = 0;

        switch (second) {
            case "aj":
                majorMinorKey = 0; break;
            case "in":
                majorMinorKey = 1; break;
        }

        if (majorMinorKey == 0)
        {
            switch (first) {
                case "C":
                    noteKey = 0; break;
                case "G":
                    noteKey = 1; break;
                case "D":
                    noteKey = 2; break;
                case "A":
                    noteKey = 3; break;
                case "E":
                    noteKey = 4; break;
                case "B":
                    noteKey = 5; break;
                case "F#":
                    noteKey = 6; break;
                case "C#":
                    noteKey = 7; break;
                case "F":
                    noteKey = -1; break;
                case "Bb":
                    noteKey = -2; break;
                case "Eb":
                    noteKey = -3; break;
                case "Ab":
                    noteKey = -4; break;
                case "Db":
                    noteKey = -5; break;
                case "Gb":
                    noteKey = -6; break;
                case "Cb":
                    noteKey = -7; break;
            }
        }
        else if (majorMinorKey == 1)
        {
            switch (first) {
                case "A":
                    noteKey = 0; break;
                case "E":
                    noteKey = 1; break;
                case "B":
                    noteKey = 2; break;
                case "F#":
                    noteKey = 3; break;
                case "C#":
                    noteKey = 4; break;
                case "G#":
                    noteKey = 5; break;
                case "D#":
                    noteKey = 6; break;
                case "A#":
                    noteKey = 7; break;
                case "D":
                    noteKey = -1; break;
                case "G":
                    noteKey = -2; break;
                case "C":
                    noteKey = -3; break;
                case "F":
                    noteKey = -4; break;
                case "Bb":
                    noteKey = -5; break;
                case "Eb":
                    noteKey = -6; break;
                case "Ab":
                    noteKey = -7; break;
            }
        }

        byte[] key = new byte[2];
        key[0] = (byte) noteKey;
        key[1] = (byte) majorMinorKey;

        byte eventType = 0x59;
        int length = 2;

        midiGen.EmitNewMetaEvent(eventType, key, length, time);
    }

    @Override
    public void Visit(TempoAssignNode node) {
        int tempoMs = (60000000 / node.tempo);
        byte[] tempoTemp = ByteBuffer.allocate(4).putInt(tempoMs).array();
        byte[] tempo = new byte[3];
        tempo[0] = tempoTemp[1];
        tempo[1] = tempoTemp[2];
        tempo[2] = tempoTemp[3];
        this.tempo = tempoMs;

        byte eventType = 0x51;
        midiGen.EmitNewMetaEvent(eventType, tempo, tempo.length, time);
    }

    @Override
    public void Visit(TimeSignatureAssignNode node) {
        double i = (double) node.denominator;
        double k = (Math.log(i) / Math.log(2));
        int j = (int) k;

        byte[] numeratorTemp = ByteBuffer.allocate(4).putInt(node.numerator).array();
        byte[] denominatorTemp = ByteBuffer.allocate(4).putInt(j).array();

        byte[] timeSig = new byte[4];
        timeSig[0] = numeratorTemp[3];
        timeSig[1] = denominatorTemp[3];
        timeSig[2] = (byte) 0x00;
        timeSig[3] = (byte) 0x00;

        byte eventType = 0x58;
        midiGen.EmitNewMetaEvent(eventType, timeSig, timeSig.length, time);
    }

    @Override
    public void Visit(IdentifierNode identifierNode) {
        int tempOct = currentOct;
        int tempLength = currentLength;

        AstNode nodeVal = symbolTbl.getSymbol(identifierNode.name);
        if (nodeVal.getClass().equals(SegmentNode.class)) {
            if (!(identifierNode.sharpNodes.isEmpty() && identifierNode.flatNodes.isEmpty())) {
                SegmentNode clone = (SegmentNode) nodeVal.Clone(nodeVal);
                clone.sharpNodes.addAll(identifierNode.sharpNodes);
                clone.flatNodes.addAll(identifierNode.flatNodes);
                clone.Accept(this);
            } else {
                nodeVal.Accept(this);
            }
        } else if (nodeVal.getClass().equals(ChordNode.class)) {
            if (!(identifierNode.sharpNodes.isEmpty() && identifierNode.flatNodes.isEmpty())) {
                ChordNode clone = (ChordNode) nodeVal.Clone(nodeVal);
                for (NoteNode noteNode : clone.notes) {
                    noteNode.sharpNodes.addAll(identifierNode.sharpNodes);
                    noteNode.flatNodes.addAll(identifierNode.flatNodes);
                }
                clone.Accept(this);
            } else {
                nodeVal.Accept(this);
            }
        }
        currentOct = tempOct;
        currentLength = tempLength;
    }

    @Override
    public void Visit(OctSwitchNode node) {
        currentOct = node.value;
    }

    @Override
    public void Visit(OctUpNode node) {
        currentOct++;
    }

    @Override
    public void Visit(OctDownNode node) {
        currentOct--;
    }

    @Override
    public void Visit(LengthSwitchNode node) {
        currentLength = node.value;
    }

    @Override
    public void Visit(VelocitySwitchNode node) { velocity = node.value; }

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
        currentOct = tempOct;
        currentLength = tempLength;
    }

    @Override
    public void Visit(ChordNode node) {
        long longestNote = 0;

        for (int i = 0; i < node.notes.size(); i++) {
            NoteNode noteNode = node.notes.get(i);
            HandleNoteNode(noteNode);
            if(noteNode.tick > longestNote){
                longestNote = noteNode.tick;
            }
        }
        time = time + longestNote;
    }

    @Override
    public void Visit(NoteNode noteNode) {
        HandleNoteNode(noteNode);
        time = time + noteNode.tick;
    }

    public void HandleNoteNode (NoteNode noteNode){
        //Creating MIDI note values with either single note octave or current global octave as parameter

        if (noteNode.oct == null)
            midiVal = MidiUtility.NoteToMidi(currentOct, noteNode.toneval);
        else
            midiVal = MidiUtility.NoteToMidi(noteNode.oct, noteNode.toneval);

        //Manipulating MIDI values according to accidentals
        if (!(noteNode.sharpNodes == null)) {
            for (AstNode accidental : noteNode.sharpNodes) {
                midiVal++;
            }
        }
        if (!(noteNode.flatNodes == null)) {
            for (AstNode accidental : noteNode.flatNodes) {
                midiVal--;
            }
        }

        if (noteNode.noteL == null)
            noteNode.noteL = currentLength;

        midiGen.EmitNewMidiEvent(currentChannel, midiVal, velocity, noteNode.tick, time);
    }

    @Override
    public void Visit(RepeatNode node) {
        for (int i = 0; i < node.repeatValue; i++) {
            node.operand.Accept(this);
        }
    }

    @Override
    public void Visit(TieNode node) {
        long noteTick = 0;
        for (NoteNode note : node.notes) {
            if (note.noteL == null)
                note.noteL = currentLength;

            if(note.tick == null) {
                note.tick = (long) MidiUtility.CalculateTick(note.noteL, note.dots.size());
            }
            noteTick += note.tick;
        }
        NoteNode noteNode = node.notes.get(0);
        noteNode.tick = noteTick;

        noteNode.Accept(this);
    }

    @Override
    public void Visit(InstrumentNode node) {
        int instrument = 0;
        String instrumentName = node.instrument;

        switch (instrumentName) {
            case "Piano":
                instrument = 0;
                break;
            case "Marimba":
                instrument = 12;
                break;
            case "Organ":
                instrument = 19;
                break;
            case "Guitar":
                instrument = 26;
                break;
            case "Bass":
                instrument = 36;
                break;
            case "Cello":
                instrument = 42;
                break;
            case "Strings":
                instrument = 48;
                break;
            case "Trumpet":
                instrument = 56;
                break;
            case "Saxophone":
                instrument = 65;
                break;
            case "Ocarina":
                instrument = 79;
                break;
            case "Lead":
                instrument = 87;
                break;
            case "Pad":
                instrument = 89;
                break;
            case "Banjo":
                instrument = 105;
                break;
            case "Drum":
                instrument = 115;
                break;
        }

        if (currentChannel == 9)
        {
            currentChannel++;
        }
        currentChannel++;
        time = 0;
        midiGen.EmitNewProgramChangeEvent(currentChannel, instrument, 0);
        currentOct = 4;
        currentLength = 4;
    }

    @Override
    public void Visit(RestNode restNode) {
        if (restNode.length == null)
            restNode.length = currentLength;
        time += restNode.tick;
    }
}
