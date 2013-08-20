package org.openmrs.module.muzimaforms;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.openmrs.Form;
import org.openmrs.module.xforms.Xform;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@JsonIgnoreProperties(ignoreUnknown = true)
public class MuzimaForm extends BaseMuzimaData {
    private Integer id;
    private Set<MuzimaFormTag> tags = new HashSet<MuzimaFormTag>();

    String model;
    String html;
    private String name;
    private String description;

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

    public MuzimaForm() {
    }    // used by hibernate

    public String getDescription() {
        return description == null ? "" : description;
    }

    public String getName() {
        return name;
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

        MuzimaForm muzimaForm = (MuzimaForm) o;

        if (getDescription() != null ? !getDescription().equals(muzimaForm.getDescription()) : muzimaForm.getDescription() != null)
            return false;
        if (id != null ? !id.equals(muzimaForm.id) : muzimaForm.id != null) return false;
        if (getName() != null ? !getName().equals(muzimaForm.getName()) : muzimaForm.getName() != null) return false;
        if (tags != null ? !tags.equals(muzimaForm.tags) : muzimaForm.tags != null) return false;

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
        return "MuzimaForm{" +
                "id=" + id +
                ", uuid=" + getUuid() +
                ", name='" + getName() + '\'' +
                ", description='" + getDescription() + '\'' +
                ", tags=" + tags +
                '}';
    }

    @JsonIgnore
    public List<String> getTagNames() {
        List<String> tagNames = new ArrayList<String>();
        for (MuzimaFormTag tag : tags) {
            tagNames.add(tag.getName());
        }
        return tagNames;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
