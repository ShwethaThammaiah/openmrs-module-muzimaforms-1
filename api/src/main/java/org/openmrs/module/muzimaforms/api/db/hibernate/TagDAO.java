package org.openmrs.module.muzimaforms.api.db.hibernate;

import org.openmrs.module.muzimaforms.MuzimaFormTag;

import java.util.List;

public interface TagDAO {
    List<MuzimaFormTag> getAll();

    void add(MuzimaFormTag tag);
}
