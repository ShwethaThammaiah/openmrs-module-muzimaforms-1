package org.openmrs.module.muzimaforms.web.controller;

import org.openmrs.module.muzimaforms.BaseMuzimaData;
import org.openmrs.module.muzimaforms.MuzimaForm;
import org.openmrs.module.muzimaforms.MuzimaFormTag;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.Set;

public class MuzimaFormMetadata extends BaseMuzimaData {

    private Integer id;
    private String description;
    private String name;
    private Set<MuzimaFormTag> tags;
    private String uuid;

    public MuzimaFormMetadata(MuzimaForm muzimaForm) {

        this.id = muzimaForm.getId();
        this.uuid = muzimaForm.getUuid();
        this.description = muzimaForm.getDescription();
        this.name = muzimaForm.getName();
        this.tags = muzimaForm.getTags();

        //audit
        this.setChangedBy(muzimaForm.getChangedBy());
        this.setCreator(muzimaForm.getCreator());
        this.setDateChanged(muzimaForm.getDateChanged());
        this.setDateCreated(muzimaForm.getDateCreated());
        this.setVoided(muzimaForm.getVoided());
        this.setVoidedBy(muzimaForm.getVoidedBy());
        this.setDateVoided(muzimaForm.getDateVoided());
        this.setVoidReason(muzimaForm.getVoidReason());
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer integer) {
        throw new NotImplementedException();
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

    public String getUuid() {
        return uuid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MuzimaFormMetadata that = (MuzimaFormMetadata) o;

        if (description != null ? !description.equals(that.description) : that.description != null) return false;
        if (!id.equals(that.id)) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (tags != null ? !tags.equals(that.tags) : that.tags != null) return false;
        if (uuid != null ? !uuid.equals(that.uuid) : that.uuid != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (tags != null ? tags.hashCode() : 0);
        result = 31 * result + (uuid != null ? uuid.hashCode() : 0);
        return result;
    }
}
