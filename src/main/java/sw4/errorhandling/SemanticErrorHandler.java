package sw4.errorhandling;

import java.util.ArrayList;
import java.util.List;

public class SemanticErrorHandler {
    private List<String> errors;

    public SemanticErrorHandler() {
        errors = new ArrayList<String>();
    }

    public boolean IsErrorFree() {
        return errors.isEmpty();
    }

    public void AddError(String errorMsg) {
        errors.add("Semantic error at Line " + errorMsg);
    }

    public List<String> GetErrors() {
        return errors;
    }

    public void ClearErrors() {
        errors.clear();
    }

    public void PrintError() {
        if (!(errors.isEmpty())) {
            for (String error : errors) {
                System.err.println(error);
            }
            errors.clear();
        }
    }
}