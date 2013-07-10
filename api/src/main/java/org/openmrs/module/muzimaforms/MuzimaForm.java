package org.openmrs.module.muzimaforms;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.openmrs.Form;
import org.openmrs.module.xforms.Xform;

import java.util.HashSet;
import java.util.Set;

@JsonIgnoreProperties(ignoreUnknown = true)
public class MuzimaForm {
    private Integer id;
    private Form form;
    private Xform xform;
    private Set<MuzimaFormTag> tags = new HashSet<MuzimaFormTag>();

    String model;
    String html;

    public String getModelJson() {
        return modelJson;
    }

    public void setModelJson(String modelJson) {
        this.modelJson = modelJson;
    }

    String modelJson;

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getHtml() {
        return html;
    }

    public void setHtml(String html) {
        this.html = html;
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

    public MuzimaForm() {
    }    // used by hibernate

    public String getDescription() {
        return getForm() == null ? "" : getForm().getDescription();
    }

    public String getName() {
        return getForm() == null ? "" : getForm().getName();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Set<MuzimaFormTag> getTags() {
        return tags;
    }

    public void setTags(Set<MuzimaFormTag> tags) {
        this.tags = tags;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MuzimaForm html5Form = (MuzimaForm) o;

        if (getDescription() != null ? !getDescription().equals(html5Form.getDescription()) : html5Form.getDescription() != null)
            return false;
        if (id != null ? !id.equals(html5Form.id) : html5Form.id != null) return false;
        if (getName() != null ? !getName().equals(html5Form.getName()) : html5Form.getName() != null) return false;
        if (tags != null ? !tags.equals(html5Form.tags) : html5Form.tags != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (getName() != null ? getName().hashCode() : 0);
        result = 31 * result + (getDescription() != null ? getDescription().hashCode() : 0);
        result = 31 * result + (tags != null ? tags.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "HTML5Form{" +
                "id=" + id +
                ", name='" + getName() + '\'' +
                ", description='" + getDescription() + '\'' +
                ", tags=" + tags +
                '}';
    }
}
