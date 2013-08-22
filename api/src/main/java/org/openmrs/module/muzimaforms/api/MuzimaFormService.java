package org.openmrs.module.muzimaforms.api;

import org.dom4j.DocumentException;
import org.openmrs.api.OpenmrsService;
import org.openmrs.module.muzimaforms.MuzimaForm;
import org.openmrs.module.muzimaforms.MuzimaXForm;
import org.springframework.transaction.annotation.Transactional;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.xpath.XPathExpressionException;
import java.io.IOException;
import java.util.List;

public interface MuzimaFormService extends OpenmrsService {
    @Transactional(readOnly = true)
    List<MuzimaForm> getAll();

    @Transactional(readOnly = true)
    List<MuzimaXForm> getXForms();

    @Transactional
    MuzimaForm importExisting(Integer xFormId, String name, String description) throws IOException, TransformerException, ParserConfigurationException, XPathExpressionException, SAXException, DocumentException;

    MuzimaForm findById(Integer id);

    MuzimaForm findByUniqueId(String uuid);

    @Transactional
    MuzimaForm create(String xformXml, String description, String name) throws IOException, TransformerException, ParserConfigurationException, DocumentException;
}
