package org.openmrs.module.html5forms.api.impl;

import org.openmrs.api.impl.BaseOpenmrsService;
import org.openmrs.module.html5forms.HTML5Forms;
import org.openmrs.module.html5forms.api.HTML5FormService;
import org.openmrs.module.html5forms.api.db.hibernate.HTML5FormDAO;

public class XFormServiceImpl extends BaseOpenmrsService implements HTML5FormService {
    private HTML5FormDAO dao;

    public XFormServiceImpl(HTML5FormDAO dao) {
        this.dao = dao;
    }

    public HTML5Forms getAll() {
        return new HTML5Forms(dao.getAll());
    }
}
