package org.openmrs.module.html5forms.api.db.hibernate.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.openmrs.module.html5forms.HTML5Form;
import org.openmrs.module.html5forms.api.db.hibernate.HTML5FormDAO;

import java.util.List;

public class HTML5FromDAOImpl implements HTML5FormDAO {
    private SessionFactory factory;

    public HTML5FromDAOImpl(SessionFactory factory) {
        this.factory = factory;
    }

    //TODO: Move this to a named query
    public List<HTML5Form> getAll() {
        return (List<HTML5Form>) session().createCriteria(HTML5Form.class).list();

    }

    private Session session() {
        return factory.getCurrentSession();
    }
}
