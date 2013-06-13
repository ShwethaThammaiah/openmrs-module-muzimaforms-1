package org.openmrs.module.html5forms.api.db.hibernate;

import org.openmrs.module.html5forms.HTML5FormTag;

import java.util.List;

public interface TagDAO {
    List<HTML5FormTag> getAll();

    void add(HTML5FormTag tag);
}
