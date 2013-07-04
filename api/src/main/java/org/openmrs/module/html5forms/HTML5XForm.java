package org.openmrs.module.html5forms;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.openmrs.Form;

public class HTML5XForm {
    private Integer id;
    private String name;
    private String description;
    private Form form;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        HTML5XForm html5Form = (HTML5XForm) o;

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

    public HTML5XForm() {
    }    // used by hibernate

    public HTML5XForm(String name, String description) {
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
