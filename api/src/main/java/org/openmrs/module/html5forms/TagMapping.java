package org.openmrs.module.html5forms;

import java.io.Serializable;

public class TagMapping implements Serializable {

    private Integer formId;
    private Integer tagId;

    public Integer getTagId() {
        return tagId;
    }

    public void setTagId(Integer tagId) {
        this.tagId = tagId;
    }

    public int getFormId() {
        return formId;
    }

    public void setFormId(Integer formId) {
        this.formId = formId;
    }
}
