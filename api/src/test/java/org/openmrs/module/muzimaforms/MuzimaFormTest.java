package org.openmrs.module.muzimaforms;

import org.junit.Test;
import org.openmrs.Form;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class MuzimaFormTest {
    @Test
    public void testName() throws Exception {
        Form form = new Form();
        form.setName("name");
        MuzimaForm muzimaForm = new MuzimaForm();
        muzimaForm.setForm(form);
        assertThat(muzimaForm.getName(), is("name"));
    }

    @Test
    public void testNullFormName() throws Exception {
        MuzimaForm muzimaForm = new MuzimaForm();
        assertThat(muzimaForm.getName(), is(""));
    }

    @Test
    public void testDescription() throws Exception {
        Form form = new Form();
        form.setDescription("description");
        MuzimaForm muzimaForm = new MuzimaForm();
        muzimaForm.setForm(form);
        assertThat(muzimaForm.getDescription(), is("description"));

    }

    @Test
    public void testNullFormDescription() throws Exception {
        MuzimaForm muzimaForm = new MuzimaForm();
        assertThat(muzimaForm.getDescription(), is(""));

    }
}
