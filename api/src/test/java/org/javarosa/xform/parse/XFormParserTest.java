package org.javarosa.xform.parse;

import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.FileReader;

import static org.javarosa.xform.parse.ValidationMessage.Type;
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
                "    With element <xforms>\n").withType(Type.ERROR).instance()));
    }

    private FileReader getFile(String file) throws FileNotFoundException {
        return new FileReader(this.getClass().getClassLoader().getResource(file).getFile());
    }

    @Test
    public void validate_shouldReturnWarningIfModelHasInvalidAttribute() throws Exception {
        XFormParser parser = new XFormParser(getFile("javarosa/invalidModelAttribute.xml"));
        ValidationMessages messages = parser.validate();
        assertThat(messages.list, hasItem(validationMessage().withMessage("Warning: 1 Unrecognized attributes found in Element [model] and will be ignored: [id] Location:\n" +
                "\n" +
                "    Problem found at nodeset: /html/model\n" +
                "    With element <model id=\"openmrs_model\">\n").withType(Type.WARNING).instance()));
    }

    @Test
    public void validate_shouldReturnErrorIfModelHasInvalidChild() throws Exception {
        XFormParser parser = new XFormParser(getFile("javarosa/invalidModelChild.xml"));
        ValidationMessages messages = parser.validate();
        assertThat(messages.list, hasItem(validationMessage().withMessage("Unrecognized top-level tag [invalidChildTag] found within <model>\n" +
                "    Problem found at nodeset: /html/head/model/invalidChildTag\n" +
                "    With element <invalidChildTag>\n").withType(Type.ERROR).instance()));
    }

    @Test
    public void validate_shouldReturnErrorIfThereAreMultipleModels() throws Exception {
        XFormParser parser = new XFormParser(getFile("javarosa/multipleModels.xml"));
        ValidationMessages messages = parser.validate();
        assertThat(messages.list, hasItem(validationMessage().withMessage("Multiple models not supported. Ignoring subsequent models.\n" +
                "    Problem found at nodeset: /html/head/model\n" +
                "    With element <model>\n").withType(Type.ERROR).instance()));
    }

    @Test
    public void validate_shouldReturnErrorIfDocumentIsNotAValidXML() throws Exception {
        XFormParser parser = new XFormParser(getFile("javarosa/emptyDocument.xml"));
        ValidationMessages messages = parser.validate();
        assertThat(messages.list, hasItem(validationMessage().withMessage("Document has no root element!").withType(Type.ERROR).instance()));
    }
}
