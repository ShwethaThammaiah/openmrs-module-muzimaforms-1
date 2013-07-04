package org.openmrs.module.html5forms.api.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dom4j.DocumentException;
import org.openmrs.api.impl.BaseOpenmrsService;
import org.openmrs.module.html5forms.HTML5Form;
import org.openmrs.module.html5forms.HTML5XForm;
import org.openmrs.module.html5forms.api.HTML5FormService;
import org.openmrs.module.html5forms.api.db.hibernate.HTML5FormDAO;
import org.openmrs.module.html5forms.xForm2Html5Transform.XForm2Html5Transformer;
import org.openmrs.module.xforms.Xform;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.IOException;
import java.util.List;

public class HTML5FormServiceImpl extends BaseOpenmrsService implements HTML5FormService {
    private XForm2Html5Transformer html5Transformer;
    private HTML5FormDAO dao;
    private static final Log log = LogFactory.getLog(HTML5FormServiceImpl.class);

    public HTML5FormServiceImpl(HTML5FormDAO dao, XForm2Html5Transformer html5Transformer) {
        this.dao = dao;
        this.html5Transformer = html5Transformer;

    }

    public List<HTML5Form> getAll() {
        return dao.getAll();
    }

    //TODO: Handle records which do not have a form in the forms table.
    public List<HTML5XForm> getXForms() {
        return dao.getXForms();
    }

    public void saveForm(HTML5Form form) throws DocumentException, ParserConfigurationException, TransformerException, IOException {
        try {
            Xform xform = dao.getXform(form.getId());
            String xformXml = xform.getXformXml();
            log.info("xform --> ");
            log.info(xformXml);
            EnketoResult enketoResult = html5Transformer.transform(xformXml);
            log.info("converted -->");
            log.info(enketoResult.getResult());
            form.setHtml(enketoResult.getForm());
            form.setModel(enketoResult.getModel());
            dao.saveForm(form);
        } catch (Exception e) {
            log.debug("Possible XForm to HTML5 transformation failure.", e);
        }
    }

    public HTML5Form findById(Integer id) {
        return dao.findById(id);
    }
}
