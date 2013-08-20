package org.openmrs.module.muzimaforms;

import org.junit.Test;
import org.openmrs.Form;

import java.util.HashSet;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class MuzimaFormTest {
    @Test
    public void description_shouldReturnAnEmptyStringIfItIsNull() throws Exception {
        MuzimaForm muzimaForm = new MuzimaForm();
        assertThat(muzimaForm.getDescription(), is(""));
    }
}
