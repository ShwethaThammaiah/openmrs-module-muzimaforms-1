package org.openmrs.module.html5forms;

import java.util.Collection;

public class TagsAccessor {
    private Tags tags;

    public TagsAccessor(Tags tags) {
        this.tags = tags;
    }

    public Collection<Tag> getList() {
        return tags.list;
    }
}
