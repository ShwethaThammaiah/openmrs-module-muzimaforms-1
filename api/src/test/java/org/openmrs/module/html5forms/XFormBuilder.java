package org.openmrs.module.html5forms;

import org.openmrs.module.xforms.Xform;

public class XFormBuilder extends Builder<Xform> {

    private Integer id;
    private String xFormXml;

    public static XFormBuilder xForm() {
        return new XFormBuilder();
    }

    @Override
    protected Xform instance() {
        Xform xForm = new Xform();
        xForm.setFormId(id);
        xForm.setXformXml(xFormXml);
        return xForm;
    }

    public XFormBuilder withId(Integer id) {
        this.id = id;
        return this;
    }

    public XFormBuilder withXFormXml(String xFormXml) {
        this.xFormXml = xFormXml;
        return this;
    }
}
