package org.openmrs.module.muzimaforms.api;

import org.openmrs.api.OpenmrsService;
import org.openmrs.module.muzimaforms.MuzimaForm;
import org.openmrs.module.muzimaforms.MuzimaFormTag;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface MuzimaTagService extends OpenmrsService {
    @Transactional(readOnly = true)
    public List<MuzimaFormTag> getAll();

    @Transactional
    public MuzimaFormTag add(String name);

}
