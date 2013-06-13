package org.openmrs.module.html5forms;

import java.util.Collection;

public class TagsAccessor {
    private Tags tags;

    public TagsAccessor(Tags tags) {
        this.tags = tags;
    }

    public Collection<HTML5FormTag> getList() {
        return tags.list;
    }
}
