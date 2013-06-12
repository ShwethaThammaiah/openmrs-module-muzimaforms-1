package org.openmrs.module.html5forms.api.db.hibernate;

import org.openmrs.module.html5forms.HTML5Form;

import java.util.List;

public interface HTML5FormDAO {
    public List<HTML5Form> getAll();
}
