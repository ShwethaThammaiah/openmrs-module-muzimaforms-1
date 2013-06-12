package org.openmrs.module.html5forms;

public class XFormBuilder extends Builder<XForm> {
    private Integer id;
    private String name;
    private String description;

    private XFormBuilder() {
    }

    @Override
    public XForm instance() {
        XForm XForm = new XForm(name, description);
        XForm.setId(id);
        return XForm;
    }

    public static XFormBuilder xForm() {
        return new XFormBuilder();
    }


    public XFormBuilder withId(Integer id) {
        this.id = id;
        return this;
    }

    public XFormBuilder withName(String name) {
        this.name = name;
        return this;
    }

    public XFormBuilder withDescription(String description){
        this.description = description;
        return this;
    }
}
