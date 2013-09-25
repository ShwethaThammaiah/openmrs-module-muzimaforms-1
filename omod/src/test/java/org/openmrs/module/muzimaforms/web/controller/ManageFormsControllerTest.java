package org.openmrs.module.muzimaforms.web.controller;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class ManageFormsControllerTest {
    @Test
    public void showForm_shouldDisplayTheManageFormsJSP() throws Exception {
        assertThat(new ManageFormsController().showForm(), is("/module/muzimaforms/manageForms"));

    }
}
