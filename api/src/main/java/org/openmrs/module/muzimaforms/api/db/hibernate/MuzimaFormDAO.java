package org.openmrs.module.muzimaforms.api.db.hibernate;

import org.openmrs.module.muzimaforms.MuzimaForm;
import org.openmrs.module.muzimaforms.MuzimaXForm;
import org.openmrs.module.xforms.Xform;

import java.util.List;

public interface MuzimaFormDAO {
    public List<MuzimaForm> getAll();

    public List<MuzimaXForm> getXForms();

    void saveForm(MuzimaForm form);

    MuzimaForm findById(Integer id);

    Xform getXform(int id);

    MuzimaForm findByUuid(String uuid);

    List<MuzimaForm> findByName(final String name);
}
