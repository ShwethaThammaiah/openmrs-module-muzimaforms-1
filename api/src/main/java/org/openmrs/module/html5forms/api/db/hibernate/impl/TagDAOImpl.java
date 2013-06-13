package org.openmrs.module.html5forms.api.db.hibernate.impl;

import org.hibernate.SessionFactory;
import org.hibernate.classic.Session;
import org.openmrs.module.html5forms.HTML5FormTag;
import org.openmrs.module.html5forms.api.db.hibernate.TagDAO;

import java.util.List;

public class TagDAOImpl implements TagDAO {
    private SessionFactory factory;

    public TagDAOImpl(SessionFactory factory) {
        this.factory = factory;
    }

    public List<HTML5FormTag> getAll() {
        return (List<HTML5FormTag>) session().createCriteria(HTML5FormTag.class).list();
    }

    public void add(HTML5FormTag tag) {
        session().save(tag);
    }

    private Session session() {
        return factory.getCurrentSession();
    }

}
