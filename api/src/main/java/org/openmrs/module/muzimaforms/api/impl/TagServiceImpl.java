package org.openmrs.module.muzimaforms.api.impl;

import org.openmrs.api.impl.BaseOpenmrsService;
import org.openmrs.module.muzimaforms.MuzimaFormTag;
import org.openmrs.module.muzimaforms.api.TagService;
import org.openmrs.module.muzimaforms.api.db.hibernate.TagDAO;

import java.util.List;

public class TagServiceImpl extends BaseOpenmrsService implements TagService {
    private TagDAO dao;

    public TagServiceImpl(TagDAO dao) {
        this.dao = dao;
    }

    public List<MuzimaFormTag> getAll() {
        return dao.getAll();
    }

    public MuzimaFormTag add(String name) {
        MuzimaFormTag tag = new MuzimaFormTag(name);
        dao.add(tag);
        return tag;
    }
}
