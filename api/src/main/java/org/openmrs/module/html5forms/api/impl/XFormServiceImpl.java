package org.openmrs.module.html5forms.api.impl;

import org.openmrs.api.impl.BaseOpenmrsService;
import org.openmrs.module.html5forms.XForms;
import org.openmrs.module.html5forms.api.XFormService;
import org.openmrs.module.html5forms.api.db.hibernate.XFromDAO;

public class XFormServiceImpl extends BaseOpenmrsService implements XFormService {
    private XFromDAO dao;

    public XFormServiceImpl(XFromDAO dao) {
        this.dao = dao;
    }

    public XForms getAll() {
        return new XForms(dao.getAll());
    }
}
