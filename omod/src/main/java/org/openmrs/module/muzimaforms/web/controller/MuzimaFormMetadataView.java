package org.openmrs.module.muzimaforms.web.controller;

import org.openmrs.module.muzimaforms.MuzimaForm;

import java.util.ArrayList;
import java.util.List;

public class MuzimaFormMetadataView {
    public List<MuzimaFormMetadata> load(List<MuzimaForm> forms) {
        ArrayList<MuzimaFormMetadata> result = new ArrayList<MuzimaFormMetadata>();
        for (MuzimaForm form : forms) {
            result.add(new MuzimaFormMetadata(form.getId(), form.getName(), form.getDescription(), form.getTags()));
        }
        return result;
    }
}
