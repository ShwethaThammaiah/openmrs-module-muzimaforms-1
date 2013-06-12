package org.openmrs.module.html5forms;

public class XForm {
    private Integer id;
    private String name;
    private String description;

    public XForm(){}    // used by hibernate

    public XForm(String name, String description) {
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

        XForm XForm = (XForm) o;

        if (description != null ? !description.equals(XForm.description) : XForm.description != null) return false;
        if (id != null ? !id.equals(XForm.id) : XForm.id != null) return false;
        if (name != null ? !name.equals(XForm.name) : XForm.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        return result;
    }
}
