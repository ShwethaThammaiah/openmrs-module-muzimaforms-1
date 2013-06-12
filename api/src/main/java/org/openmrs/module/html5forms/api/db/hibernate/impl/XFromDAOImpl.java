package org.openmrs.module.html5forms.api.db.hibernate.impl;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.classic.Session;
import org.hibernate.transform.Transformers;
import org.openmrs.module.html5forms.XForm;
import org.openmrs.module.html5forms.api.db.hibernate.XFromDAO;

import java.util.List;

public class XFromDAOImpl implements XFromDAO {
    private SessionFactory factory;

    public XFromDAOImpl(SessionFactory factory) {
        this.factory = factory;
    }
    //TODO: Move this to a named query
    public List<XForm> getAll() {
        Session session = factory.getCurrentSession();
        Query query = session.createSQLQuery("select " +
                "form.form_id as id, " +
                "form.name as name, " +
                "form.description as description " +
                "from form form, xforms_xform xform " +
                "where xform.form_id = form.form_id ")
                .addScalar("id")
                .addScalar("name")
                .addScalar("description")
                .setResultTransformer(Transformers.aliasToBean(XForm.class));
        return query.list();
    }
}
