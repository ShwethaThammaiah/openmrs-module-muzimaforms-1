package org.openmrs.module.muzimaforms;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.openmrs.BaseOpenmrsData;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@JsonIgnoreProperties(ignoreUnknown = true)
public class MuzimaForm extends BaseOpenmrsData {
    private Integer id;
    private String name;
    private String description;
    private String discriminator;
    private String model;
    private String html;
    private String modelJson;
    private Set<MuzimaFormTag> tags = new HashSet<MuzimaFormTag>();

    public MuzimaForm() {
    }    // used by hibernate

    public MuzimaForm(String name, String discriminator, String description, String model, String html, String modelJson) {
        // Correcting for the fact that in v1.8.2 of the stand-alone server the uuid of BaseOpenmrsObject is
        // not computed each time the empty constructor is called as is the case in v1.9.3
        if (getUuid()==null) {
            setUuid(UUID.randomUUID().toString());
        }

        this.name = name;
        this.description = description;
        this.discriminator = discriminator;
        this.model = model;
        this.html = html;
        this.modelJson = modelJson;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description == null ? "" : description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDiscriminator() {
        return discriminator == null ? "" : discriminator;
    }

    public void setDiscriminator(final String discriminator) {
        this.discriminator = discriminator;
    }

    public String getModelJson() {
        return modelJson;
    }

    public void setModelJson(String modelJson) {
        this.modelJson = modelJson;
    }

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
        if (getDiscriminator() != null ? !getDiscriminator().equals(muzimaForm.getDiscriminator()) : muzimaForm.getDiscriminator() != null) return false;
        if (tags != null ? !tags.equals(muzimaForm.tags) : muzimaForm.tags != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (getName() != null ? getName().hashCode() : 0);
        result = 31 * result + (getDiscriminator() != null ? getDiscriminator().hashCode() : 0);
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
                ", discriminator='" + getDiscriminator() + '\'' +
                ", description='" + getDescription() + '\'' +
                ", tags=" + tags +
                '}';
    }
}
