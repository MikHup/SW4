grammar jaml;

//Productions
prog
    : part* EOF
    ;

part
    : assignment
    | scorePart
    ;

assignment
    : left=ID ASSIGN segment                     #segmentAss
    | left=ID ASSIGN chord                       #chordAss
    | left=TITLE ASSIGN right=STRING             #titleAss
    | left=KEY ASSIGN right=KEYVALUE             #keyAss
    | left=TEMPO ASSIGN right=INTLITERAL         #tempoAss
    | left=TIMESIGNATURE ASSIGN timeSignature    #timeSignatureAss
    | instr=INSTRUMENT COLON                     #instrumentAss
    ;

segment
    : LPAREN scorePart* RPAREN accidental*
    ;

scorePart
    : note
    | restNote
    | expr
    ;

note
    : (toneVal | noteOct | noteLength | noteOctLength)       //Accidentals and dots are placed at a lower level and not here because of the way we build the AST
    ;

restNote
    : (rest | restLength)
    ;

toneVal
    : TONEVAL accidental* dot*
    ;

noteOct
    : NOTEOCT accidental* dot*
    ;

noteLength
    : NOTELENGTH accidental* dot*
    ;

noteOctLength
    : NOTEOCTLENGTH accidental* dot*
    ;

rest
    : REST dot*
    ;

restLength
    : RESTLENGTH dot*
    ;

accidental
    : acc=SHARP
    | acc=FLAT
    ;
dot
    : DOT
    ;

expr
    : note REPEAT value=INTLITERAL                               #noteRepeatExpr
    | restNote REPEAT value=INTLITERAL                           #restRepeatExpr
    | segment (REPEAT value=INTLITERAL)?                         #segmentRepeatExpr
    | identifier=ID accidental* (REPEAT value=INTLITERAL)?       #idRepeatExpr
    | chord                                                      #chordExpr
    | note (TIE note)+                                           #tiedNotesExpr
    | OCTDOWN                                                    #octaveDownExpr
    | OCTUP                                                      #octaveUpExpr
    | OCTAVESWITCH                                               #octaveSwitchExpr
    | LENGTHSWITCH                                               #lengthSwitchExpr
    | VELOCITYSWITCH                                             #velocitySwitchExpr
    ;

chord
    : note (CHORDSLASH note)+
    ;

timeSignature
    : numerator=INTLITERAL CHORDSLASH denominator=INTLITERAL
    ;

//Tokens

//Keywords
TEMPO: 'Tempo';
TITLE: 'Title';
KEY: 'Key';
TIMESIGNATURE: 'TimeSignature';
INSTRUMENT: 'Piano' | 'Marimba' | 'Organ' | 'Guitar' | 'Cello' | 'Strings' | 'Saxophone' | 'Ocarina' | 'Lead' | 'Pad' | 'Banjo' | 'Drum' | 'Trumpet' | 'Bass' ;

//Literals
KEYVALUE: (('C'|'G'|'D'|'A'|'E'|'B'|'F#'|'C#'|'F'|'Bb'|'Eb'|'Ab'|'Db'|'Gb'|'Cb') ('maj')) | (('A'|'E'|'B'|'F#'|'C#'|'G#'|'D#'|'A#'|'D'|'G'|'C'|'F'|'Bb'|'Eb'|'Ab') ('min'));
TONEVAL: 'c+'|'d+'|'f+'|'g+'|'a+'|[a-g];
NOTEOCT: TONEVAL OCTVAL;
NOTELENGTH: NOTEL TONEVAL;
NOTEOCTLENGTH: NOTEL TONEVAL OCTVAL;
REST: 'r';
RESTLENGTH: NOTEL 'r';
OCTAVESWITCH: 'O' OCTVAL;
LENGTHSWITCH: 'L' NOTEL;
VELOCITYSWITCH: 'V' DIGIT+; //NEED FIXING
INTLITERAL: DIGIT+;
STRING: '"'(LETTER | DIGIT | ' ' | '-')+'"' ;

//Seperators
COLON: ':';
CHORDSLASH: '/';
LPAREN: '(';
RPAREN: ')';

//Operators
ASSIGN: '=';
REPEAT: '*';
SHARP: '+';
FLAT: '-';
TIE: '_';
DOT: '.';
OCTDOWN: '<';
OCTUP: '>';


//Identifiers
ID: ( [h-q] | [s-z] | ULETTER) | LETTER (LETTER | DIGIT)+;

//Whitespace
WS: ([ \t]+ | '|') -> skip;                             //I got this from ANTLR lexer rules and added '|'
NEWLINE: '\r'? '\n' -> skip;                            //I got this from ANTLR lexer rules

//Comments
COMMENT: '//' ~[\r\n]* '\r'? -> skip;                   //I got this from ANTLR lexer rules

//Fragments (helper rules for tokens)
fragment LETTER: ([a-z] | [A-Z]);
fragment ULETTER: [A-Z];
fragment LLETTER: [a-z];
fragment DIGIT: [0-9];
fragment NOTEL: DIGIT+;
fragment OCTVAL: '-'?[0-9]+;