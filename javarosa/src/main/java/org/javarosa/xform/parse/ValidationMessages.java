package org.javarosa.xform.parse;

import java.util.ArrayList;
import java.util.List;

public class ValidationMessages {
    protected List<ValidationMessage> list = new ArrayList<ValidationMessage>();

    public void addWarning(String warning) {
        list.add(new ValidationMessage(warning, ValidationMessage.Type.WARNING));
    }

    public void clear() {
        list.clear();
    }

    public void addError(String error) {
        list.add(new ValidationMessage(error, ValidationMessage.Type.ERROR));
    }
}
