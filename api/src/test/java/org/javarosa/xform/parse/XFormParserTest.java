package org.javarosa.xform.parse;

import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.FileReader;

import static org.javarosa.xform.parse.ValidationMessageBuilder.validationMessage;
import static org.junit.Assert.assertThat;
import static org.junit.matchers.JUnitMatchers.hasItem;

public class XFormParserTest {
    @Test
    public void validate_shouldReturnErrorIfRootElementIsInvalid() throws Exception {
        XFormParser parser = new XFormParser(getFile("javarosa/invalidRootElement.xml"));
        ValidationMessages messages = parser.validate();
        assertThat(messages.list, hasItem(validationMessage().withMessage("XForm Parse: Unrecognized element [xforms]. Ignoring and processing children...\n" +
                "    Problem found at nodeset: /xforms\n" +
                "    With element <xforms>\n").withType(ValidationMessage.Type.ERROR).instance()));
    }

    private FileReader getFile(String file) throws FileNotFoundException {
        return new FileReader(this.getClass().getClassLoader().getResource(file).getFile());
    }
}
