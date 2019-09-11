import org.antlr.v4.runtime.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sw4.errorhandling.SyntaxErrorHandler;
import sw4.gen.jamlLexer;
import sw4.gen.jamlParser;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestSyntaxErrorHandler {
    PrintStream errTemp = System.err;
    ByteArrayOutputStream errorMessage = new ByteArrayOutputStream();

    @BeforeEach
    void setSystemErr() {
        System.setErr(new PrintStream(errorMessage));
    }

    @AfterEach
    void resetSystemErr() {
        System.setErr(errTemp);
    }



    @Test
    void testReportNoViableAlternative() {
        try {
            CharStream file = CharStreams.fromFileName("src/test/java/GeneralTestFilesWithErrors/testLegalFile.txt");

            SyntaxErrorHandler eh = new SyntaxErrorHandler();
            jamlLexer lexer = new jamlLexer(file);
            CommonTokenStream tokens = new CommonTokenStream(lexer);
            jamlParser parser = new jamlParser(tokens);

            eh.reportNoViableAlternative(parser, new NoViableAltException(parser));

        } catch (Exception e) {}

        assertEquals("line 1:0 'Title' is not a functional identifier\r\n", errorMessage.toString());
    }

    @Test
    void testReportUnwantedToken() {
        try {
            CharStream file = CharStreams.fromFileName("src/test/java/GeneralTestFilesWithErrors/testLegalFile.txt");

            SyntaxErrorHandler eh = new SyntaxErrorHandler();
            jamlLexer lexer = new jamlLexer(file);
            CommonTokenStream tokens = new CommonTokenStream(lexer);
            jamlParser parser = new jamlParser(tokens);

            eh.reportUnwantedToken(parser);

        } catch (Exception e) {}

        assertEquals("line 1:0 Unknown input Error. 'Title' is not a functional identifier\r\n", errorMessage.toString());
    }

    @Test
    void testReportMissingToken() {
        try {
            CharStream file = CharStreams.fromFileName("src/test/java/GeneralTestFilesWithErrors/testLegalFile.txt");

            SyntaxErrorHandler eh = new SyntaxErrorHandler();
            jamlLexer lexer = new jamlLexer(file);
            CommonTokenStream tokens = new CommonTokenStream(lexer);
            jamlParser parser = new jamlParser(tokens);

            eh.reportMissingToken(parser);

        } catch (Exception e) {}

        assertEquals("line 1:0 Illegal character. Expected instruction termination command before: \"Title\"\r\n", errorMessage.toString());
    }

    @Test
    void testReportInputMismatch() {
        try {
            CharStream file = CharStreams.fromFileName("src/test/java/GeneralTestFilesWithErrors/testLegalFile.txt");

            SyntaxErrorHandler eh = new SyntaxErrorHandler();
            jamlLexer lexer = new jamlLexer(file);
            CommonTokenStream tokens = new CommonTokenStream(lexer);
            jamlParser parser = new jamlParser(tokens);

            eh.reportInputMismatch(parser, new InputMismatchException(parser));

        } catch (Exception e) {}

        assertEquals("line 1:0 mismatched input 'Title'. An instruction might not be started or terminated correctly\r\n", errorMessage.toString());
    }
}
