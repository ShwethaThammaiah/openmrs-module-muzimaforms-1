package org.openmrs.module.muzimaforms.web.controller;

import org.apache.struts.action.ActionMessages;
import org.openmrs.module.muzimaforms.MuzimaFormTag;

import java.util.List;
import java.util.Set;

public class MuzimaFormMetadata {

    private Integer id;
    private String description;
    private String name;
    private Set<MuzimaFormTag> tags;

    public MuzimaFormMetadata(Integer id, String name, String description, Set<MuzimaFormTag> tags) {
        this.id = id;
        this.description = description;
        this.name = name;
        this.tags = tags;
    }

    public Integer getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public String getName() {
        return name;
    }

    public Set<MuzimaFormTag> getTags() {
        return tags;
    }
}
