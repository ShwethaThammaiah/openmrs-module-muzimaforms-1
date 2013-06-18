package org.openmrs.module.html5forms.web.controller;

import org.junit.Before;
import org.junit.Test;
import org.openmrs.module.html5forms.HTML5Form;
import org.openmrs.test.BaseModuleContextSensitiveTest;

import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class HTML5FormsControllerTest extends BaseModuleContextSensitiveTest {
    @Before
    public void setUp() throws Exception {
        executeDataSet("xformomodTestData.xml");
    }

    @Test
    public void forms_shouldGetAllForms() throws Exception {
        HTML5FormsController controller = new HTML5FormsController();
        List<HTML5Form> forms = controller.forms();
        assertThat(forms.size(), is(2));
    }

    @Test
    public void save_shouldSaveAForm() throws Exception {
        HTML5FormsController controller = new HTML5FormsController();
        HTML5Form form = new HTML5Form();
        form.setId(3);
        controller.create(form);
        List<HTML5Form> forms = controller.forms();
        assertThat(forms.size(), is(3));
        assertThat(forms.get(2).getId(), is(3));
    }

}
