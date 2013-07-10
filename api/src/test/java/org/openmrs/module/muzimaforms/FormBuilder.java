package org.openmrs.module.muzimaforms;

import org.openmrs.Form;

public class FormBuilder extends Builder<Form> {

    private Integer id;
    private String name;
    private String description;

    @Override
    public Form instance() {
        Form form = new Form();
        form.setId(id);
        form.setName(name);
        form.setDescription(description);
        return form;
    }

    public static FormBuilder form() {
        return new FormBuilder();
    }

    public FormBuilder withId(Integer id) {
        this.id = id;
        return this;
    }

    public FormBuilder withName(String name) {
        this.name = name;
        return this;
    }

    public FormBuilder withDescription(String description) {
        this.description = description;
        return this;
    }
}
