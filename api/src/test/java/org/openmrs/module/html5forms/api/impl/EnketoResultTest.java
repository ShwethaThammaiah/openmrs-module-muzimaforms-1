package org.openmrs.module.html5forms.api.impl;

import org.dom4j.DocumentException;
import org.junit.Test;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;
import java.io.IOException;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.isEmptyOrNullString;
import static org.junit.Assert.assertThat;


public class EnketoResultTest {
    @Test(expected = NullPointerException.class)
    public void getForm_shouldEmpty() throws ParserConfigurationException, XPathExpressionException, SAXException, IOException, DocumentException {
        EnketoResult enketoResult = new EnketoResult("<root><form><ul><li/><li/></ul></form></root>");
        assertThat(enketoResult.getModel(), isEmptyOrNullString());

        enketoResult = new EnketoResult("<root><model><x/><y/></model></root>");
        assertThat(enketoResult.getForm(), isEmptyOrNullString());
    }

    @Test
    public void getForm_shouldGetForm() throws ParserConfigurationException, XPathExpressionException, SAXException, IOException, DocumentException {
        String result = "<root><model><x/><y/></model><form><ul><li/><li/></ul></form></root>";
        EnketoResult enketoResult = new EnketoResult(result);
        assertThat(enketoResult.getForm(), is("<form><ul><li/><li/></ul></form>"));
    }

    @Test
    public void getForm_shouldGetModel() throws ParserConfigurationException, XPathExpressionException, SAXException, IOException, DocumentException {
        String result = "<root><model><x/><y/></model><form><ul><li/><li/></ul></form></root>";
        EnketoResult enketoResult = new EnketoResult(result);
        assertThat(enketoResult.getForm(), is("<form><ul><li/><li/></ul></form>"));
    }

}
