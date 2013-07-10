package org.openmrs.module.muzimaforms;

import org.openmrs.Form;
import org.openmrs.module.xforms.Xform;

import java.util.HashSet;
import java.util.Set;

public class MuzimaFormBuilder extends Builder<MuzimaForm> {
    private Integer id;
    private Form form;
    private Xform xform;
    private Set<MuzimaFormTag> tags = new HashSet<MuzimaFormTag>();

    private MuzimaFormBuilder() {
    }

    @Override
    public MuzimaForm instance() {
        MuzimaForm muzimaForm = new MuzimaForm();
        muzimaForm.setId(id);
        muzimaForm.setXform(xform);
        muzimaForm.setTags(tags);
        muzimaForm.setForm(form);
        return muzimaForm;
    }

    public static MuzimaFormBuilder muzimaform() {
        return new MuzimaFormBuilder();
    }


    public MuzimaFormBuilder withId(Integer id) {
        this.id = id;
        return this;
    }

    public MuzimaFormBuilder with(XFormBuilder xFormBuilder) {
        this.xform = xFormBuilder.instance();
        return this;
    }

    public MuzimaFormBuilder with(MuzimaFormTagBuilder tagBuilder) {
        this.tags.add(tagBuilder.instance());
        return this;
    }

    public MuzimaFormBuilder with(FormBuilder form) {
        this.form = form.instance();
        return this;
    }
}
