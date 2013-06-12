package org.openmrs.module.html5forms;

public class XFormBuilder extends Builder<XForm>{

    private Integer id;

    public static XFormBuilder xForm() {
        return new XFormBuilder();
    }

    @Override
    protected XForm instance() {
        XForm xForm = new XForm();
        xForm.setId(id);
        return xForm;
    }

    public XFormBuilder withId(Integer id) {
        this.id = id;
        return this;
    }
}
