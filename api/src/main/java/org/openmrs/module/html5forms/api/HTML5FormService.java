package org.openmrs.module.html5forms.api;

import org.dom4j.DocumentException;
import org.openmrs.api.OpenmrsService;
import org.openmrs.module.html5forms.HTML5Form;
import org.openmrs.module.html5forms.HTML5XForm;
import org.springframework.transaction.annotation.Transactional;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.xpath.XPathExpressionException;
import java.io.IOException;
import java.util.List;

public interface HTML5FormService extends OpenmrsService {
    @Transactional(readOnly = true)
    List<HTML5Form> getAll();

    @Transactional(readOnly = true)
    List<HTML5XForm> getXForms();

    @Transactional
    void saveForm(HTML5Form form) throws IOException, TransformerException, ParserConfigurationException, XPathExpressionException, SAXException, DocumentException;

    HTML5Form findById(Integer id);
}
