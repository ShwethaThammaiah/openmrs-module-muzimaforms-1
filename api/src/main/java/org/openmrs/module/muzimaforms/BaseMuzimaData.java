package org.openmrs.module.muzimaforms;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.openmrs.User;

import java.util.Date;
import java.util.UUID;

public abstract class BaseMuzimaData extends org.openmrs.BaseOpenmrsObject implements org.openmrs.OpenmrsData {
    protected org.openmrs.User creator;
    private java.util.Date dateCreated;
    private org.openmrs.User changedBy;
    private java.util.Date dateChanged;
    private java.lang.Boolean voided;
    private java.util.Date dateVoided;
    private org.openmrs.User voidedBy;
    private java.lang.String voidReason;
    protected String uuid = UUID.randomUUID().toString();

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getUuid() {
        return uuid;
    }

    @JsonIgnore
    public User getCreator() {
        return creator;
    }

    public void setCreator(User creator) {
        this.creator = creator;
    }

    @JsonIgnore
    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    @JsonIgnore
    public User getChangedBy() {
        return changedBy;
    }

    public void setChangedBy(User changedBy) {
        this.changedBy = changedBy;
    }

    @JsonIgnore
    public Date getDateChanged() {
        return dateChanged;
    }

    public void setDateChanged(Date dateChanged) {
        this.dateChanged = dateChanged;
    }

    @JsonIgnore
    public Boolean getVoided() {
        return voided;
    }

    public void setVoided(Boolean voided) {
        this.voided = voided;
    }

    @JsonIgnore
    public Date getDateVoided() {
        return dateVoided;
    }

    public void setDateVoided(Date dateVoided) {
        this.dateVoided = dateVoided;
    }

    @JsonIgnore
    public User getVoidedBy() {
        return voidedBy;
    }

    public void setVoidedBy(User voidedBy) {
        this.voidedBy = voidedBy;
    }

    @JsonIgnore
    public String getVoidReason() {
        return voidReason;
    }

    public void setVoidReason(String voidReason) {
        this.voidReason = voidReason;
    }

    @JsonIgnore
    public Boolean isVoided() {
        return getVoided();
    }


}
