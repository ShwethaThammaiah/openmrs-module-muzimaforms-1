package org.openmrs.module.muzimaforms;

import org.openmrs.Form;
import org.openmrs.module.xforms.Xform;

import java.util.HashSet;
import java.util.Set;

public class MuzimaFormBuilder extends Builder<MuzimaForm> {
    private Integer id;
    private String uuid;
    private Set<MuzimaFormTag> tags = new HashSet<MuzimaFormTag>();
    private String name;
    private String form;
    private String discriminator;
    private String description;

    private MuzimaFormBuilder() {
    }

    @Override
    public MuzimaForm instance() {
        MuzimaForm muzimaForm = new MuzimaForm();
        muzimaForm.setId(id);
        muzimaForm.setUuid(uuid);
        muzimaForm.setTags(tags);
        muzimaForm.setName(name);
        muzimaForm.setForm(form);
        muzimaForm.setDiscriminator(discriminator);
        muzimaForm.setDescription(description);
        return muzimaForm;
    }

    public static MuzimaFormBuilder muzimaform() {
        return new MuzimaFormBuilder();
    }


    public MuzimaFormBuilder withId(Integer id) {
        this.id = id;
        return this;
    }

    public MuzimaFormBuilder withUuid(String uuid) {
        this.uuid = uuid;
        return this;
    }

    public MuzimaFormBuilder with(MuzimaFormTagBuilder tagBuilder) {
        this.tags.add(tagBuilder.instance());
        return this;
    }

    public MuzimaFormBuilder withName(String name) {
        this.name = name;
        return this;
    }

    public MuzimaFormBuilder withForm(String form) {
        this.form = form;
        return this;
    }

    public MuzimaFormBuilder withDiscriminator(String discriminator) {
        this.discriminator = discriminator;
        return this;
    }

    public MuzimaFormBuilder withDescription(String description) {
        this.description = description;
        return this;
    }
}
