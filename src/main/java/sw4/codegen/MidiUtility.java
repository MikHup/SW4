package sw4.codegen;

public class MidiUtility {

    public static double CalculateTick(Integer length, int dots) {
        double tickVal;
        tickVal = NoteLengthToMidiLength(length);
        //Add note length according to dots
        if(dots != 0)
            tickVal = tickVal * (2 - 1/(Math.pow(2,dots)));
        return tickVal;
    }

    private static long NoteLengthToMidiLength(Integer noteLength) {
        long midiLength;

        switch (noteLength)
        {
            case 1:
                midiLength = 64*4; break;
            case 2:
                midiLength = 32*4; break;
            case 4:
                midiLength = 16*4; break;
            case 8:
                midiLength = 8*4; break;
            case 16:
                midiLength = 4*4; break;
            case 32:
                midiLength = 2*4; break;
            case 64:
                midiLength = 4; break;
            default:
                midiLength = 64;
        }
        return midiLength;
    }

    public static int NoteToMidi(int octave, String note){
        switch (octave){
            case -1:
                switch(note){
                    case "c":
                        return 0;
                    case "c+":
                        return 1;
                    case "d":
                        return 2;
                    case "d+":
                        return 3;
                    case "e":
                        return 4;
                    case "f":
                        return 5;
                    case "f+":
                        return 6;
                    case "g":
                        return 7;
                    case "g+":
                        return 8;
                    case "a":
                        return 9;
                    case "a+":
                        return 10;
                    case "b":
                        return 11;
                }
                break;
            case 0:
                switch(note){
                    case "c":
                        return 12;
                    case "c+":
                        return 13;
                    case "d":
                        return 14;
                    case "d+":
                        return 15;
                    case "e":
                        return 16;
                    case "f":
                        return 17;
                    case "f+":
                        return 18;
                    case "g":
                        return 19;
                    case "g+":
                        return 20;
                    case "a":
                        return 21;
                    case "a+":
                        return 22;
                    case "b":
                        return 23;
                }
                break;
            case 1:
                switch(note){
                    case "c":
                        return 24;
                    case "c+":
                        return 25;
                    case "d":
                        return 26;
                    case "d+":
                        return 27;
                    case "e":
                        return 28;
                    case "f":
                        return 29;
                    case "f+":
                        return 30;
                    case "g":
                        return 31;
                    case "g+":
                        return 32;
                    case "a":
                        return 33;
                    case "a+":
                        return 34;
                    case "b":
                        return 35;
                }
                break;
            case 2:
                switch(note){
                    case "c":
                        return 36;
                    case "c+":
                        return 37;
                    case "d":
                        return 38;
                    case "d+":
                        return 39;
                    case "e":
                        return 40;
                    case "f":
                        return 41;
                    case "f+":
                        return 42;
                    case "g":
                        return 43;
                    case "g+":
                        return 44;
                    case "a":
                        return 45;
                    case "a+":
                        return 46;
                    case "b":
                        return 47;
                }
                break;
            case 3:
                switch(note){
                    case "c":
                        return 48;
                    case "c+":
                        return 49;
                    case "d":
                        return 50;
                    case "d+":
                        return 51;
                    case "e":
                        return 52;
                    case "f":
                        return 53;
                    case "f+":
                        return 54;
                    case "g":
                        return 55;
                    case "g+":
                        return 56;
                    case "a":
                        return 57;
                    case "a+":
                        return 58;
                    case "b":
                        return 59;
                }
                break;
            case 4:
                switch(note){
                    case "c":
                        return 60;
                    case "c+":
                        return 61;
                    case "d":
                        return 62;
                    case "d+":
                        return 63;
                    case "e":
                        return 64;
                    case "f":
                        return 65;
                    case "f+":
                        return 66;
                    case "g":
                        return 67;
                    case "g+":
                        return 68;
                    case "a":
                        return 69;
                    case "a+":
                        return 70;
                    case "b":
                        return 71;
                }
                break;
            case 5:
                switch(note){
                    case "c":
                        return 72;
                    case "c+":
                        return 73;
                    case "d":
                        return 74;
                    case "d+":
                        return 75;
                    case "e":
                        return 76;
                    case "f":
                        return 77;
                    case "f+":
                        return 78;
                    case "g":
                        return 79;
                    case "g+":
                        return 80;
                    case "a":
                        return 81;
                    case "a+":
                        return 82;
                    case "b":
                        return 83;
                }
                break;
            case 6:
                switch(note){
                    case "c":
                        return 84;
                    case "c+":
                        return 85;
                    case "d":
                        return 86;
                    case "d+":
                        return 87;
                    case "e":
                        return 88;
                    case "f":
                        return 89;
                    case "f+":
                        return 90;
                    case "g":
                        return 91;
                    case "g+":
                        return 92;
                    case "a":
                        return 93;
                    case "a+":
                        return 94;
                    case "b":
                        return 95;
                }
                break;
            case 7:
                switch(note){
                    case "c":
                        return 96;
                    case "c+":
                        return 97;
                    case "d":
                        return 98;
                    case "d+":
                        return 99;
                    case "e":
                        return 100;
                    case "f":
                        return 101;
                    case "f+":
                        return 102;
                    case "g":
                        return 103;
                    case "g+":
                        return 104;
                    case "a":
                        return 105;
                    case "a+":
                        return 106;
                    case "b":
                        return 107;
                }
                break;
            case 8:
                switch(note){
                    case "c":
                        return 108;
                    case "c+":
                        return 109;
                    case "d":
                        return 110;
                    case "d+":
                        return 111;
                    case "e":
                        return 112;
                    case "f":
                        return 113;
                    case "f+":
                        return 114;
                    case "g":
                        return 115;
                    case "g+":
                        return 116;
                    case "a":
                        return 117;
                    case "a+":
                        return 118;
                    case "b":
                        return 119;
                }
                break;
            case 9:
                switch(note){
                    case "c":
                        return 120;
                    case "c+":
                        return 121;
                    case "d":
                        return 122;
                    case "d+":
                        return 123;
                    case "e":
                        return 124;
                    case "f":
                        return 125;
                    case "f+":
                        return 126;
                    case "g":
                        return 127;
                    case "g+":
                        return -1; //Out of midi range
                    case "a":
                        return -1; //Out of midi range
                    case "a+":
                        return -1; //Out of midi range
                    case "b":
                        return -1; //Out of midi range
                }
                break;
            default:
                return -1; //Out of midi range
        }
        return -1;
    }
}
