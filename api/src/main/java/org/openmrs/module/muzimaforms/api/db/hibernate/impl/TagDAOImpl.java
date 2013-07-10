package org.openmrs.module.muzimaforms.api.db.hibernate.impl;

import org.hibernate.SessionFactory;
import org.hibernate.classic.Session;
import org.openmrs.module.muzimaforms.MuzimaFormTag;
import org.openmrs.module.muzimaforms.api.db.hibernate.TagDAO;

import java.util.List;

public class TagDAOImpl implements TagDAO {
    private SessionFactory factory;

    public TagDAOImpl(SessionFactory factory) {
        this.factory = factory;
    }

    public List<MuzimaFormTag> getAll() {
        return (List<MuzimaFormTag>) session().createCriteria(MuzimaFormTag.class).list();
    }

    public void save(MuzimaFormTag tag) {
        session().save(tag);
    }

    private Session session() {
        return factory.getCurrentSession();
    }

}
