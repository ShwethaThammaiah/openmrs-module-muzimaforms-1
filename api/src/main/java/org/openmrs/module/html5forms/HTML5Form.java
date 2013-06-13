package org.openmrs.module.html5forms;

import java.util.Set;

public class HTML5Form {
    private Integer id;
    private String name;
    private String description;
    private XForm xform;
    private Tags tags;

    public Tags getTags() {
        return tags;
    }

    public void setTags(Set<Tag> tags) {
        this.tags = new Tags(tags);
    }

    public XForm getXform() {
        return xform;
    }

    public void setXform(XForm xform) {
        this.xform = xform;
    }

    public HTML5Form(){}    // used by hibernate

    public HTML5Form(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        HTML5Form html5Form = (HTML5Form) o;

        if (description != null ? !description.equals(html5Form.description) : html5Form.description != null)
            return false;
        if (id != null ? !id.equals(html5Form.id) : html5Form.id != null) return false;
        if (name != null ? !name.equals(html5Form.name) : html5Form.name != null) return false;
        if (xform != null ? !xform.equals(html5Form.xform) : html5Form.xform != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (xform != null ? xform.hashCode() : 0);
        return result;
    }
}
