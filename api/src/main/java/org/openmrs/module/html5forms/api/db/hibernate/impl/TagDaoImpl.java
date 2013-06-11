package org.openmrs.module.html5forms.api.db.hibernate.impl;

import org.hibernate.SessionFactory;
import org.openmrs.module.html5forms.Tag;
import org.openmrs.module.html5forms.api.db.hibernate.TagDAO;

import java.util.List;

public class TagDAOImpl implements TagDAO {
    private SessionFactory factory;

    public TagDAOImpl(SessionFactory factory) {
        this.factory = factory;
    }

    public List<Tag> getAll() {
        return (List<Tag>)factory.getCurrentSession().createCriteria(Tag.class).list();
    }
}
