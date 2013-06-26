package org.openmrs.module.html5forms.api.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openmrs.api.impl.BaseOpenmrsService;
import org.openmrs.module.html5forms.HTML5Form;
import org.openmrs.module.html5forms.HTML5XForm;
import org.openmrs.module.html5forms.api.EnketoService;
import org.openmrs.module.html5forms.api.HTML5FormService;
import org.openmrs.module.html5forms.api.db.hibernate.HTML5FormDAO;
import org.openmrs.module.xforms.Xform;

import java.io.IOException;
import java.util.List;

public class HTML5FormServiceImpl extends BaseOpenmrsService implements HTML5FormService {
    private EnketoService enketoService;
    private HTML5FormDAO dao;
    Log logger = LogFactory.getLog(HTML5FormServiceImpl.class);


    public HTML5FormServiceImpl(HTML5FormDAO dao, EnketoService enketoService) {
        this.dao = dao;
        this.enketoService = enketoService;

    }

    public List<HTML5Form> getAll() {
        return dao.getAll();
    }

    //TODO: Handle records which do not have a form in the forms table.
    public List<HTML5XForm> getXForms() {
        return dao.getXForms();
    }

    public void saveForm(HTML5Form form) throws IOException {
        Xform xform = dao.getXform(form.getId()) ;
        String xformXml = xform.getXformXml();
        String transform = enketoService.transform(xformXml);
        form.setHtml(transform);
        dao.saveForm(form);
    }

    public HTML5Form findById(Integer id) {
        return dao.findById(id);
    }
}
