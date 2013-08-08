package org.javarosa.xform.parse;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.javarosa.xform.parse.ValidationMessageBuilder.validationMessage;
import static org.junit.Assert.assertThat;
import static org.junit.matchers.JUnitMatchers.hasItem;

public class ValidationMessagesTest {
    @Test
    public void addWarning_shouldAddAValidationMessageOfTypeWarning() throws Exception {
        ValidationMessages messages = new ValidationMessages();
        messages.addWarning("A warning Message");
        assertThat(messages.list, hasItem(validationMessage().withMessage("A warning Message").withType(ValidationMessage.Type.WARNING).instance()));
    }

    @Test
    public void addError_shouldAddAValidationMessageOfTypeError() throws Exception {
        ValidationMessages messages = new ValidationMessages();
        messages.addError("An Error Message");
        assertThat(messages.list, hasItem(validationMessage().withMessage("An Error Message").withType(ValidationMessage.Type.ERROR).instance()));
    }


    @Test
    public void clear_shouldDeleteAllExistingValidationMessages() throws Exception {
        ValidationMessages messages = new ValidationMessages();
        messages.list.add(validationMessage().withMessage("a message").instance());
        messages.clear();
        assertThat(messages.list.isEmpty(), is(true));
    }
}
