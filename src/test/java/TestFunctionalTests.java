import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import sw4.ast.BuildAstVisitor;
import sw4.ast.SemanticCheckVisitor;
import sw4.ast.nodes.ProgNode;
import sw4.errorhandling.SemanticErrorHandler;
import sw4.errorhandling.SyntaxErrorHandler;
import sw4.errorhandling.SyntaxErrorListener;
import sw4.gen.jamlLexer;
import sw4.gen.jamlParser;
import sw4.symboltable.SymbolTable;

import java.util.ArrayList;
import java.util.List;

/****** The runFile function in this file contains a code snippet from Main(), which is used to compile files ******/

public class TestFunctionalTests {
    SemanticErrorHandler semanticErrorHandler = new SemanticErrorHandler();
    List<String> testResult = new ArrayList<>();

    @BeforeEach
    void setSystemErr() {
        semanticErrorHandler.ClearErrors();
        testResult.clear();
    }

    void runFile(String fileName) {
        try {
            //Create antlr lexer and parser
            CharStream input = CharStreams.fromFileName(fileName);
            jamlLexer lexer = new jamlLexer(input);
            CommonTokenStream tokens = new CommonTokenStream(lexer);
            jamlParser parser = new jamlParser(tokens);

            //Remove standard error handler and replace with our own
            SyntaxErrorHandler syntaxErrorHandler = new SyntaxErrorHandler();

            lexer.removeErrorListeners();
            lexer.addErrorListener(new SyntaxErrorListener());
            parser.removeErrorListeners();
            parser.addErrorListener(new SyntaxErrorListener());
            parser.setErrorHandler(syntaxErrorHandler);

            //Create ast from cst provided by antlr
            ProgNode ast = (ProgNode) new BuildAstVisitor().visitProg(parser.prog());

            SymbolTable symbolTbl = new SymbolTable();


            //Semantic Checker instance
            SemanticCheckVisitor scVisitor = new SemanticCheckVisitor(semanticErrorHandler, symbolTbl);

            if (syntaxErrorHandler.isErrorFree) {
                //Semantic check
                scVisitor.Visit(ast);
            }
        } catch (Exception e) {
        }
    }


    @Test
    void testLegalFile() {
        runFile("src/test/java/GeneralTestFilesWithErrors/testLegalFile.txt");
        assertEquals(0, semanticErrorHandler.GetErrors().size());
    }

    @Test
    void testSegmentIdentifierAlreadyDeclared() {
        runFile("src/test/java/GeneralTestFilesWithErrors/testSegmentIdentifierAlreadyDeclared.txt");
        assertEquals("Semantic error at Line [2:0] - Identifier \"SegmentA\" is already declared", semanticErrorHandler.GetErrors().get(0));
    }

    @Test
    void testChordIdentifierAlreadyDeclared() {
        runFile("src/test/java/GeneralTestFilesWithErrors/testChordIdentifierAlreadyDeclared.txt");
        assertEquals("Semantic error at Line [2:0] - Identifier \"ChordA\" is already declared", semanticErrorHandler.GetErrors().get(0));
    }

    @Test
    void testTitleAlreadyDeclared() {
        runFile("src/test/java/GeneralTestFilesWithErrors/testTitleAlreadyDeclared.txt");
        assertEquals("Semantic error at Line [2:0] - Title is already assigned", semanticErrorHandler.GetErrors().get(0));
    }

    @Test
    void testInvalidTempo() {
        runFile("src/test/java/GeneralTestFilesWithErrors/testInvalidTempo.txt");

        testResult.add("Semantic error at Line [1:0] - Invalid tempo: 0");
        testResult.add("Semantic error at Line [3:0] - Invalid tempo: 1000");
        assertEquals(testResult, semanticErrorHandler.GetErrors());
    }

    @Test
    void testInvalidTimeSignatureNumerator() {
        runFile("src/test/java/GeneralTestFilesWithErrors/testInvalidTimeSignatureNumerator.txt");

        testResult.add("Semantic error at Line [1:0] - TimeSignature invalid numerator: 0");
        testResult.add("Semantic error at Line [3:0] - TimeSignature invalid numerator: 17");
        assertEquals(testResult, semanticErrorHandler.GetErrors());
    }

    @Test
    void testInvalidTimeSignatureDenominator() {
        runFile("src/test/java/GeneralTestFilesWithErrors/testInvalidTimeSignatureDenominator.txt");

        testResult.add("Semantic error at Line [1:0] - TimeSignature invalid denominator: 0");
        testResult.add("Semantic error at Line [3:0] - TimeSignature invalid denominator: 11");
        testResult.add("Semantic error at Line [5:0] - TimeSignature invalid denominator: 119");
        assertEquals(testResult, semanticErrorHandler.GetErrors());
    }

    @Test
    void testUndeclaredVariable() {
        runFile("src/test/java/GeneralTestFilesWithErrors/testUndeclaredVariable.txt");
        assertEquals("Semantic error at Line [2:0] - Undeclared constant: SegmentB", semanticErrorHandler.GetErrors().get(0));
    }

    @Test
    void testInvalidOctaveSwitch() {
        runFile("src/test/java/GeneralTestFilesWithErrors/testInvalidOctaveSwitch.txt");

        testResult.add("Semantic error at Line [1:0] - Invalid octave: -3");
        testResult.add("Semantic error at Line [6:0] - Invalid octave: 9");
        assertEquals(testResult, semanticErrorHandler.GetErrors());
    }

    @Test
    void testIllegalOctUp() {
        runFile("src/test/java/GeneralTestFilesWithErrors/testIllegalOctUp.txt");
        assertEquals("Semantic error at Line [2:0] - Current Octave is too high to increment", semanticErrorHandler.GetErrors().get(0));
    }

    @Test
    void testIllegalOctDown() {
        runFile("src/test/java/GeneralTestFilesWithErrors/testIllegalOctDown.txt");
        assertEquals("Semantic error at Line [2:0] - Current Octave is too low to decrement", semanticErrorHandler.GetErrors().get(0));
    }

    @Test
    void testIllegalNoteLength() {
        runFile("src/test/java/GeneralTestFilesWithErrors/testIllegalNoteLength.txt");

        testResult.add("Semantic error at Line [1:0] - Note length 0 is invalid");
        testResult.add("Semantic error at Line [3:0] - Note length 11 is invalid");
        testResult.add("Semantic error at Line [6:0] - Note length 65 is invalid");

        assertEquals(testResult, semanticErrorHandler.GetErrors());
    }

    @Test
    void testIllegalVelocitySwitch() {
        runFile("src/test/java/GeneralTestFilesWithErrors/testIllegalVelocitySwitch.txt");

        testResult.add("Semantic error at Line [3:0] - Invalid Velocity: 999");
        testResult.add("Semantic error at Line [4:0] - Invalid Velocity: 1000");
        testResult.add("Semantic error at Line [5:0] - Invalid Velocity: 2000");

        assertEquals(testResult, semanticErrorHandler.GetErrors());
    }

    @Test
    void testIllegalNoteNodeLength() {
        runFile("src/test/java/GeneralTestFilesWithErrors/testIllegalNoteNodeLength.txt");

        testResult.add("Semantic error at Line [2:0] - Note length 3 is invalid");
        testResult.add("Semantic error at Line [5:0] - Note length 65 is invalid");
        assertEquals(testResult, semanticErrorHandler.GetErrors());
    }

    @Test
    void testIllegalNoteNodeOctave() {
        runFile("src/test/java/GeneralTestFilesWithErrors/testIllegalNoteNodeOctave.txt");
        assertEquals("Semantic error at Line [4:0] - Invalid octave: 9", semanticErrorHandler.GetErrors().get(0));
    }

    @Test
    void testIllegalNoteNodeDots() {
        runFile("src/test/java/GeneralTestFilesWithErrors/testIllegalNoteNodeDots.txt");
        assertEquals("Semantic error at Line Too many dots in note \"a.........................\" at [2:0], precision is lost", semanticErrorHandler.GetErrors().get(0));
    }

    @Test
    void testIllegalTiedNotes() {
        runFile("src/test/java/GeneralTestFilesWithErrors/testIllegalTiedNotes.txt");

        testResult.add("Semantic error at Line [3:0] - Invalid tie node");
        testResult.add("Semantic error at Line [5:0] - Invalid tie node");
        assertEquals(testResult, semanticErrorHandler.GetErrors());
    }

    @Test
    void testCorrectInstruments() {
        runFile( "src/test/java/GeneralTestFilesWithErrors/testCorrectInstruments.txt");
        assertEquals(0, semanticErrorHandler.GetErrors().size());
    }

    @Test
    void testIllegalNumberOfInstruments() {
        runFile("src/test/java/GeneralTestFilesWithErrors/testIllegalNumberOfInstruments.txt");
        assertEquals("Semantic error at Line [16:0] - No more instruments can be added", semanticErrorHandler.GetErrors().get(0));
    }

    @Test
    void testIllegalRestDots() {
        runFile("src/test/java/GeneralTestFilesWithErrors/testIllegalRestDots.txt");
        assertEquals("Semantic error at Line Too many dots in rest \"r...............\" at [3:0], precision is lost", semanticErrorHandler.GetErrors().get(0));
    }

    @Test
    void testIllegalSharpAccidentals() {
        runFile("src/test/java/GeneralTestFilesWithErrors/testIllegalSharpAccidentals.txt");
        assertEquals("Semantic error at Line [3:0] - This note's octave is too high to increment", semanticErrorHandler.GetErrors().get(0));
    }

    @Test
    void testIllegalFlatAccidentals() {
        runFile("src/test/java/GeneralTestFilesWithErrors/testIllegalFlatAccidentals.txt");
        assertEquals("Semantic error at Line [2:0] - This note's octave is too low to decrement", semanticErrorHandler.GetErrors().get(0));
    }
}
