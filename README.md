# The JAML compiler
AAU - SW4 - sw409f18
### Needed tools
The JAML compiler is devoloped using: 

* [Java 10](http://www.oracle.com/technetwork/java/javase/downloads/jdk10-downloads-4416644.html) - Development environment
* [Gradle v4.6](https://gradle.org/install/) - Build tool
* [ANTLR4 v4.7.1](http://www.antlr.org/download.html) - Parser generator 
* [JUnit v5.2.0](https://junit.org/junit5/) - Testing framework

Java is required to compile the source code.

Gradle is required to handle dependencies such as ANTLR4-runtime as well as JUnit for testing.

### Compile help:

To compile a input file, type: 'jaml' followed by the filepath to a jaml file

Example:
```
jaml C:/folder/filename.jaml
```

The default behavior of the compiler is to play back the given input, without generating a midifile to disk.

To change this behavior, make use of the following compiler options.

```
Compiler options:
    -stop
    -midi
    -print
```

The '-stop' option cancels the playback of the input.

The '-midi' option generates a midi file at the same location of the input file.

The '-print' option print the ast nodes generated from the input file.

Example:
```
jaml C:/folder/filename.jaml -stop -midi -print
```

To stop the program type 'exit' or 'quit'

### Test program
Create a txt file named "jamltest" at any location with write access.

Example:
```
C:\Jaml\jamltest.txt
```

Copy/Paste the following test program into this txt file.

```
Title = "Turkish March Mozart"
Tempo = 120

//Melody piano
    MelodyIntro = (
        O4 L16
        b a g+ a > 8c 8r
        d c b4 c 8e 8r
        f e d+ e (b a g+ a)*2 > 4c
        L8 a5 c <
        (b f+/a e/g f+/a)*2
        b f+/a e/g d+/f+ 4e
    )

    MelodySecondPart = (
        O5 L8
        (c/e d/f e/g e/g 16a 16g 16f 16e b4/d g4)*2
    )

//Bass piano
    BassIntro = (
        O4 L8
        4r |
        (a3 (c/e)*3)*2 |
        (a3 c/e)*2  |
        a3 (c/e)*3  |
        (e3 (b3/e)*3)*2 |
        e3 b3/e b3 b |
        4e
    )

Piano:
    MelodyIntro
    MelodySecondPart

Piano:
    BassIntro
```

To compile this test file, run the JAML's compiler and input the following command:

```
jaml C:\Jaml\jamltest.txt -midi
```
This will playback the test file and generate a MIDI file containing the compiled input program.
The generated MIDI file will be placed at the same file path as the input source program.
