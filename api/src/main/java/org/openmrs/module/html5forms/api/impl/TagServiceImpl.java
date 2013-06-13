package org.openmrs.module.html5forms.api.impl;

import org.openmrs.api.impl.BaseOpenmrsService;
import org.openmrs.module.html5forms.HTML5FormTag;
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

    public HTML5FormTag add(String name) {
        HTML5FormTag tag = new HTML5FormTag(name);
        dao.add(tag);
        return tag;
    }
}
