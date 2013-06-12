package org.openmrs.module.html5forms.api.db.hibernate;

import org.openmrs.module.html5forms.XForm;

import java.util.List;

public interface XFromDAO {
    public List<XForm> getAll();
}
