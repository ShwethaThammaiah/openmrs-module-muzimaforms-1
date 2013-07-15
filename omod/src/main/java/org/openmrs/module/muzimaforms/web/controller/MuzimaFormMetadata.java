package org.openmrs.module.muzimaforms.web.controller;

import org.apache.struts.action.ActionMessages;
import org.openmrs.module.muzimaforms.MuzimaFormTag;

import java.util.List;
import java.util.Set;
import java.util.UUID;

public class MuzimaFormMetadata {

    private Integer id;
    private String description;
    private String name;
    private Set<MuzimaFormTag> tags;
    private String uuid;

    public MuzimaFormMetadata(Integer id, String uuid, String name, String description, Set<MuzimaFormTag> tags) {
        this.id = id;
        this.uuid = uuid;
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

    public String getUUID() {
        return uuid;
    }
}
