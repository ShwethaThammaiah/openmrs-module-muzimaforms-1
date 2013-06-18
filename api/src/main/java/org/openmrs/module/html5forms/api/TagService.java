package org.openmrs.module.html5forms.api;

import org.openmrs.api.OpenmrsService;
import org.openmrs.module.html5forms.HTML5FormTag;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface TagService extends OpenmrsService {
    @Transactional(readOnly = true)
    public List<HTML5FormTag> getAll();

    @Transactional
    public HTML5FormTag add(String name);
}
