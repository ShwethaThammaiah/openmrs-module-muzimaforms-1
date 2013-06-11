package org.openmrs.module.html5forms.api.impl;

import org.openmrs.api.impl.BaseOpenmrsService;
import org.openmrs.module.html5forms.Tag;
import org.openmrs.module.html5forms.Tags;
import org.openmrs.module.html5forms.api.TagService;
import org.openmrs.module.html5forms.api.db.hibernate.TagDAO;

public class TagServiceImpl extends BaseOpenmrsService implements TagService {
    private TagDAO dao;

    public TagServiceImpl(TagDAO dao) {
        this.dao = dao;
    }

    public Tags getAll() {
        return new Tags(dao.getAll());
    }

    public Tag add(String name) {
        Tag tag = new Tag(name);
        dao.add(tag);
        return tag;
    }
}
