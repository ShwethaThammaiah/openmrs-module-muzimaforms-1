package org.javarosa.xform.parse;

import org.openmrs.module.muzimaforms.Builder;

public class ValidationMessageBuilder extends Builder<ValidationMessage> {
    private String message;
    private ValidationMessage.Type type;

    @Override
    protected ValidationMessage instance() {
        return new ValidationMessage(message, type);
    }

    public static ValidationMessageBuilder validationMessage() {
        return new ValidationMessageBuilder();
    }

    public ValidationMessageBuilder withMessage(String message) {
        this.message = message;
        return this;
    }

    public ValidationMessageBuilder withType(ValidationMessage.Type type) {
        this.type = type;
        return this;
    }

}
