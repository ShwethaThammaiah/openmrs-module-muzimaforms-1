package org.openmrs.module.muzimaforms.api.impl;

import org.openmrs.api.impl.BaseOpenmrsService;
import org.openmrs.module.muzimaforms.MuzimaFormTag;
import org.openmrs.module.muzimaforms.api.MuzimaTagService;
import org.openmrs.module.muzimaforms.api.db.hibernate.TagDAO;

import java.util.List;

public class MuzimaTagServiceImpl extends BaseOpenmrsService implements MuzimaTagService {
    private TagDAO dao;

    public MuzimaTagServiceImpl(TagDAO dao) {
        this.dao = dao;
    }

    public List<MuzimaFormTag> getAll() {
        return dao.getAll();
    }

    public MuzimaFormTag add(String name) {
        MuzimaFormTag tag = new MuzimaFormTag(name);
        dao.save(tag);
        return tag;
    }
}
