package org.openmrs.module.html5forms;

import java.util.List;

public class TagsAccessor {
    private Tags tags;

    public TagsAccessor(Tags tags) {
        this.tags = tags;
    }

    public List<Tag> getList() {
        return tags.list;
    }
}
