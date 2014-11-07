package org.openmrs.module.muzimaforms;

import org.junit.Test;
import org.openmrs.Form;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class MuzimaFormTest {
    @Test
    public void description_shouldReturnAnEmptyStringIfItIsNull() throws Exception {
        MuzimaForm muzimaForm = new MuzimaForm();
        Form form = new Form();
        muzimaForm.setFormDefinition(form);
        assertThat(muzimaForm.getDescription(), is(""));
    }
}
