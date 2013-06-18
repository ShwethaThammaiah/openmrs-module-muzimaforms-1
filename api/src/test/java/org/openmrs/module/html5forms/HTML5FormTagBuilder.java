package org.openmrs.module.html5forms;

public class HTML5FormTagBuilder extends Builder<HTML5FormTag> {
    private Integer id;
    private String name;

    private HTML5FormTagBuilder() {
    }

    @Override
    public HTML5FormTag instance() {
        HTML5FormTag tag = new HTML5FormTag(name);
        tag.setId(id);
        return tag;
    }

    public static HTML5FormTagBuilder tag() {
        return new HTML5FormTagBuilder();
    }


    public HTML5FormTagBuilder withId(Integer id) {
        this.id = id;
        return this;
    }

    public HTML5FormTagBuilder withName(String name) {
        this.name = name;
        return this;
    }
}
