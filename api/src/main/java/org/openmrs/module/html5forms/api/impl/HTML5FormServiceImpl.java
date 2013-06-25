package org.openmrs.module.html5forms.api.impl;

import org.openmrs.api.impl.BaseOpenmrsService;
import org.openmrs.module.html5forms.HTML5Form;
import org.openmrs.module.html5forms.HTML5XForm;
import org.openmrs.module.html5forms.api.HTML5FormService;
import org.openmrs.module.html5forms.api.db.hibernate.HTML5FormDAO;
import org.openmrs.module.xforms.Xform;

import java.util.List;

public class HTML5FormServiceImpl extends BaseOpenmrsService implements HTML5FormService {
    private HTML5FormDAO dao;

    public HTML5FormServiceImpl(HTML5FormDAO dao) {
        this.dao = dao;
    }

    public List<HTML5Form> getAll() {
        return dao.getAll();
    }

    //TODO: Handle records which do not have a form in the forms table.
    public List<HTML5XForm> getXForms() {
        return dao.getXForms();
    }

    public void saveForm(HTML5Form form) {
        Xform xform = form.getXform();
        dao.saveForm(form);
    }

    public HTML5Form findById(Integer id) {
        return dao.findById(id);
    }
}
