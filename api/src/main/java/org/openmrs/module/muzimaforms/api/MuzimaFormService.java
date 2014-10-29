package org.openmrs.module.muzimaforms.api;

import org.dom4j.DocumentException;
import org.javarosa.xform.parse.ValidationMessages;
import org.openmrs.api.OpenmrsService;
import org.openmrs.module.muzimaforms.MuzimaForm;
import org.openmrs.module.muzimaforms.MuzimaXForm;
import org.springframework.transaction.annotation.Transactional;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.xpath.XPathExpressionException;
import java.io.IOException;
import java.util.Date;
import java.util.List;

public interface MuzimaFormService extends OpenmrsService {
    @Transactional(readOnly = true)
    List<MuzimaForm> getAll();

    @Transactional(readOnly = true)
    List<MuzimaXForm> getXForms();

    @Transactional
    MuzimaForm importExisting(Integer xFormId, String name, String form, String description, String discriminator, String version) throws Exception;

    MuzimaForm findById(Integer id);

    MuzimaForm findByUniqueId(String uuid);

    List<MuzimaForm> findByName(final String name, final Date syncDate);

    @Transactional
    MuzimaForm create(String xformXml, String name, String form, String description, String discriminator, String version) throws Exception;

    @Transactional
    MuzimaForm update(String html, String name, String form) throws Exception;

    @Transactional
    MuzimaForm importODK(String xformXml, String name, String form, String description, String discriminator, String version) throws Exception;

    @Transactional
    MuzimaForm createHTMLForm(String html, String name, String form, String description, String discriminator, String version) throws Exception;

    @Transactional
    MuzimaForm updateHTMLForm(String html, String name, String form) throws Exception;

    @Transactional
    MuzimaForm save(MuzimaForm form) throws Exception;

    ValidationMessages validateJavaRosa(String xml);

    ValidationMessages validateODK(String xml) throws Exception;
}
