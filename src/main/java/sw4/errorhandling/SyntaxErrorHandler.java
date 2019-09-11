package sw4.errorhandling;

import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.IntervalSet;
import org.antlr.v4.runtime.misc.Pair;

public class SyntaxErrorHandler extends DefaultErrorStrategy {
    public static boolean isErrorFree = true;

    @Override
    public void reportNoViableAlternative(Parser parser, NoViableAltException e) throws RecognitionException
    {
        // ANTLR generates Parser subclasses from grammars and
        // Parser extends Recognizer. Parameter parser is a
        // pointer to the parser that detected the error

        Token t = parser.getCurrentToken();
        String tokenName = getTokenErrorDisplay(t);

        isErrorFree = false;
        String msg = tokenName + " is not a functional identifier"; // nonstandard msg
        parser.notifyErrorListeners(e.getOffendingToken(), msg, e);
    }

    @Override
    public void reportUnwantedToken(Parser parser) {
        if (inErrorRecoveryMode(parser)) {
            return;
        }

        beginErrorCondition(parser);

        Token t = parser.getCurrentToken();
        String tokenName = getTokenErrorDisplay(t);

        isErrorFree = false;

        String msg;
        if (tokenName.equals("':'"))
        {
            msg = "Instrument declaration Error before " + tokenName + ". Instrument might be spelled incorrectly";
        }
        else
        {
            msg = "Unknown input Error. " + tokenName + " is not a functional identifier";
        }
        parser.notifyErrorListeners(t, msg, null);
    }

    @Override
    public void reportMissingToken(Parser parser) {
        if (inErrorRecoveryMode(parser)) {
            return;
        }

        beginErrorCondition(parser);
        Token t = parser.getCurrentToken();

        isErrorFree = false;
        String msg = "Illegal character. Expected instruction termination command before: \"" + t.getText() + "\"";
        parser.notifyErrorListeners(t, msg, null);
    }

    @Override
    public void reportInputMismatch(Parser parser,
                                       InputMismatchException e)
    {
        isErrorFree = false;

        String msg;
        if (getTokenErrorDisplay(e.getOffendingToken()).equals("'<EOF>'"))
        {
            msg = "Missing input before end of file. Last instruction might not be terminated correctly";
        }
        else
        {
            msg = "mismatched input "+getTokenErrorDisplay(e.getOffendingToken())+
                    ". An instruction might not be started or terminated correctly";
        }

        parser.notifyErrorListeners(e.getOffendingToken(), msg, e);
    }
}

