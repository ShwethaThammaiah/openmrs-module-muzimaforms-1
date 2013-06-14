package org.openmrs.module.html5forms;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.openmrs.Form;
import org.openmrs.module.xforms.Xform;

import java.util.Set;

public class HTML5Form {
    private Integer id;
    private String name;
    private String description;
    private Form form;
    private Xform xform;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        HTML5Form html5Form = (HTML5Form) o;

        if (id != null ? !id.equals(html5Form.id) : html5Form.id != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    @JsonIgnore
    public Form getForm() {
        return form;
    }

    public void setForm(Form form) {
        this.form = form;
    }

    @JsonIgnore
    public Xform getXform() {
        return xform;
    }

    public void setXform(Xform xform) {
        this.xform = xform;
    }

    public HTML5Form(){}    // used by hibernate

    public HTML5Form(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getDescription() {
        return getForm().getDescription();
    }

    public String getName() {
        return getForm().getName();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


}
