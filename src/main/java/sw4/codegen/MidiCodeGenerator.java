package sw4.codegen;

import javax.sound.midi.*;
import java.util.List;

public class MidiCodeGenerator {
    public Sequencer sequencer;
    public Sequence sequence;
    public Track track;
    private int resolution;
    public byte byteEventType;


    public MidiCodeGenerator(){
        resolution = 64;

        try {
            sequencer = MidiSystem.getSequencer();
            sequencer.open();
            sequence = new Sequence(Sequence.PPQ, resolution);
            track = sequence.createTrack();
        } catch (Exception e) {
            System.err.println("ERROR WITH MIDI SEQUENCER");
        }
    }

    public void EmitNewMetaEvent(Byte eventType, byte[] eventInfo, int eventLength, long time){
        try {
            MetaMessage metaMessage = new MetaMessage();
            metaMessage.setMessage(eventType, eventInfo, eventLength);
            MidiEvent midiMessage = new MidiEvent(metaMessage, time);
            track.add(midiMessage);
            byteEventType = eventType;
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    public void EmitNewMidiEvent(int currentChannel, int midiVal, int velocity, long length, long time){
        try {
            ShortMessage messageNoteOn = new ShortMessage();
            messageNoteOn.setMessage(ShortMessage.NOTE_ON, currentChannel, midiVal, velocity);
            MidiEvent noteOn = new MidiEvent(messageNoteOn, time);
            track.add(noteOn);

            ShortMessage messageNoteOff = new ShortMessage();
            messageNoteOff.setMessage(ShortMessage.NOTE_OFF, currentChannel, midiVal, 0);
            MidiEvent noteOff = new MidiEvent(messageNoteOff, time + length);
            track.add(noteOff);

        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    public void EmitNewProgramChangeEvent(int currentChannel, int instrument, long time){
        try {
            ShortMessage instrumentMessage = new ShortMessage();
            instrumentMessage.setMessage(ShortMessage.PROGRAM_CHANGE, currentChannel, instrument, 0);
            track.add(new MidiEvent(instrumentMessage, time));
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
}

