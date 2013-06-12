package org.openmrs.module.html5forms.api;

import org.openmrs.api.OpenmrsService;
import org.openmrs.module.html5forms.XForms;
import org.springframework.transaction.annotation.Transactional;

public interface XFormService extends OpenmrsService {
    @Transactional(readOnly = true)
    XForms getAll();
}
