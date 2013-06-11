package org.openmrs.module.html5forms.api;

import org.openmrs.api.OpenmrsService;
import org.openmrs.module.html5forms.Tag;
import org.openmrs.module.html5forms.Tags;
import org.springframework.transaction.annotation.Transactional;

public interface TagService extends OpenmrsService {
    @Transactional(readOnly = true)
    public Tags getAll();

    @Transactional
    public Tag add(String name);
}
