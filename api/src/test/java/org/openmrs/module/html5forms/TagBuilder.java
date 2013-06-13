package org.openmrs.module.html5forms;

public class TagBuilder extends Builder<HTML5FormTag> {
    private Integer id;
    private String name;

    private TagBuilder() {
    }

    @Override
    public HTML5FormTag instance() {
        HTML5FormTag tag = new HTML5FormTag(name);
        tag.setId(id);
        return tag;
    }

    public static TagBuilder tag() {
        return new TagBuilder();
    }


    public TagBuilder withId(Integer id) {
        this.id = id;
        return this;
    }

    public TagBuilder withName(String name) {
        this.name = name;
        return this;
    }
}
