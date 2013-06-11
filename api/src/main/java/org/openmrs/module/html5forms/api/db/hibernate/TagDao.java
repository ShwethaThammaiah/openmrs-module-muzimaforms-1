package org.openmrs.module.html5forms.api.db.hibernate;

import org.openmrs.module.html5forms.Tag;

import java.util.List;

public interface TagDAO {
    List<Tag> getAll();

    void add(Tag tag);
}
