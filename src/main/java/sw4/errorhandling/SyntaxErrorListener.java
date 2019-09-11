package sw4.errorhandling;

import org.antlr.v4.runtime.*;

public class SyntaxErrorListener extends BaseErrorListener {
    public void syntaxError(Recognizer<?, ?> recognizer, Object offendingSymbol, int line, int charPositionInLine, String msg, RecognitionException e) {
        if (!msg.equals("")) {
            System.err.println("Syntax error at Line [" + line + ":" + charPositionInLine + "] - " + msg);
        }
    }
}

