package org.openmrs.module.html5forms;

import org.openmrs.module.xforms.Xform;

public class HTML5FormBuilder extends Builder<HTML5Form> {
    private Integer id;
    private String name;
    private String description;
    private Xform xform;

    private HTML5FormBuilder() {
    }

    @Override
    public HTML5Form instance() {
        HTML5Form html5Form = new HTML5Form(name, description);
        html5Form.setId(id);
        html5Form.setXform(xform);
        return html5Form;
    }

    public static HTML5FormBuilder html5Form() {
        return new HTML5FormBuilder();
    }


    public HTML5FormBuilder withId(Integer id) {
        this.id = id;
        return this;
    }

    public HTML5FormBuilder withName(String name) {
        this.name = name;
        return this;
    }

    public HTML5FormBuilder withDescription(String description){
        this.description = description;
        return this;
    }

    public HTML5FormBuilder withXFrom(XFormBuilder xFormBuilder) {
        this.xform =  xFormBuilder.instance();
        return this;
    }
}
