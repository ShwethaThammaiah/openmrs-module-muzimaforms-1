package org.openmrs.module.muzimaforms.api.db.hibernate;

import org.openmrs.module.muzimaforms.MuzimaForm;
import org.openmrs.module.muzimaforms.MuzimaXForm;

import java.util.List;

public interface MuzimaFormDAO {
    public List<MuzimaForm> getAll();

    public List<MuzimaXForm> getXForms();

    void saveForm(MuzimaForm form);

    MuzimaForm findById(Integer id);

    org.openmrs.module.xforms.Xform getXform(int id);
}
