package org.openmrs.module.html5forms;

import org.openmrs.Form;
import org.openmrs.module.xforms.Xform;

import java.util.HashSet;
import java.util.Set;

public class HTML5FormBuilder extends Builder<HTML5Form> {
    private Integer id;
    private Form form;
    private Xform xform;
    private Set<HTML5FormTag> tags = new HashSet<HTML5FormTag>();

    private HTML5FormBuilder() {
    }

    @Override
    public HTML5Form instance() {
        HTML5Form html5Form = new HTML5Form();
        html5Form.setId(id);
        html5Form.setXform(xform);
        html5Form.setTags(tags);
        html5Form.setForm(form);
        return html5Form;
    }

    public static HTML5FormBuilder html5Form() {
        return new HTML5FormBuilder();
    }


    public HTML5FormBuilder withId(Integer id) {
        this.id = id;
        return this;
    }

    public HTML5FormBuilder with(XFormBuilder xFormBuilder) {
        this.xform = xFormBuilder.instance();
        return this;
    }

    public HTML5FormBuilder with(HTML5FormTagBuilder tagBuilder) {
        this.tags.add(tagBuilder.instance());
        return this;
    }

    public HTML5FormBuilder with(FormBuilder form) {
        this.form = form.instance();
        return this;
    }
}
