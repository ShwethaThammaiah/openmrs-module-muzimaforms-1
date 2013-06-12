package org.openmrs.module.html5forms;

public class XForm {
    private int id;
    private String xml;

    public String getXml() {
        return xml;
    }

    public void setXml(String xml) {
        this.xml = xml;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        XForm xForm = (XForm) o;

        if (id != xForm.id) return false;
        if (xml != null ? !xml.equals(xForm.xml) : xForm.xml != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (xml != null ? xml.hashCode() : 0);
        return result;
    }
}
