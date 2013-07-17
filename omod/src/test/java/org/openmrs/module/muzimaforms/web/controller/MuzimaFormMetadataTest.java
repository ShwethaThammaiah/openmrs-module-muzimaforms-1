package org.openmrs.module.muzimaforms.web.controller;

import org.junit.Test;
import org.openmrs.Form;
import org.openmrs.User;
import org.openmrs.module.muzimaforms.MuzimaForm;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class MuzimaFormMetadataTest {
    @Test
    public void fromMuzimaForm_shouldLoadFromMuzimaForm(){
        MuzimaForm testMuzimaForm = getTestMuzimaForm();
        MuzimaFormMetadata metadata = new MuzimaFormMetadata(testMuzimaForm);
        assertThat(metadata.getUuid(), is("foooooooo"));
        assertThat(metadata.getChangedBy().getId(), is(4));
    }

    private MuzimaForm getTestMuzimaForm() {
        MuzimaForm muzimaForm = new MuzimaForm();
        muzimaForm.setId(1);
        muzimaForm.setUuid("foooooooo");
        Form form = new Form();
        form.setName("name");
        form.setDescription("desc");
        User changedBy = new User(4);
        muzimaForm.setChangedBy(changedBy);
        muzimaForm.setForm(form);
        return muzimaForm;
    }
}
