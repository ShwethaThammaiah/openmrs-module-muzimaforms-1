package org.openmrs.module.muzimaforms.api.db.hibernate.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.openmrs.module.muzimaforms.MuzimaForm;
import org.openmrs.module.muzimaforms.MuzimaXForm;
import org.openmrs.module.muzimaforms.api.db.hibernate.MuzimaFormDAO;
import org.openmrs.module.xforms.Xform;

import java.util.List;

public class MuzimaFormDAOImpl implements MuzimaFormDAO {
    private SessionFactory factory;

    public MuzimaFormDAOImpl(SessionFactory factory) {
        this.factory = factory;
    }

    public List<MuzimaForm> getAll() {
        return (List<MuzimaForm>) session().createCriteria(MuzimaForm.class).list();

    }

    //TODO: Move this to a named query
    public List<MuzimaXForm> getXForms() {
        return (List<MuzimaXForm>) session().createCriteria(MuzimaXForm.class).list();
    }

    public void saveForm(MuzimaForm form) {
        session().saveOrUpdate(form);
    }

    public MuzimaForm findById(Integer id) {
        return (MuzimaForm) session().get(MuzimaForm.class, id);
    }

    public Xform getXform(int id) {
        return (Xform) session().get(Xform.class, id);
    }

    public MuzimaForm findByUuid(String uuid) {
        return (MuzimaForm) session().createQuery("from MuzimaForm form where form.uuid = '" + uuid + "'").uniqueResult();
    }

    private Session session() {
        return factory.getCurrentSession();
    }
}
