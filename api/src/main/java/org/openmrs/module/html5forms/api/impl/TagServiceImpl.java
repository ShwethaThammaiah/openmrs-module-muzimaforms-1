package org.openmrs.module.html5forms.api.impl;

import org.openmrs.api.impl.BaseOpenmrsService;
import org.openmrs.module.html5forms.HTML5FormTag;
import org.openmrs.module.html5forms.api.TagService;
import org.openmrs.module.html5forms.api.db.hibernate.TagDAO;

import java.util.List;

public class TagServiceImpl extends BaseOpenmrsService implements TagService {
    private TagDAO dao;

    public TagServiceImpl(TagDAO dao) {
        this.dao = dao;
    }

    public List<HTML5FormTag> getAll() {
        return dao.getAll();
    }

    public HTML5FormTag add(String name) {
        HTML5FormTag tag = new HTML5FormTag(name);
        dao.add(tag);
        return tag;
    }
}
