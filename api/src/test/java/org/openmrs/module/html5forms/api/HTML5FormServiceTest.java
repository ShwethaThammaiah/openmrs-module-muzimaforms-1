package org.openmrs.module.html5forms.api;

import org.junit.Before;
import org.junit.Test;
import org.openmrs.api.context.Context;
import org.openmrs.module.html5forms.HTML5Form;
import org.openmrs.module.html5forms.HTML5XForm;
import org.openmrs.test.BaseModuleContextSensitiveTest;

import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.openmrs.module.html5forms.HTML5FormBuilder.html5Form;
import static org.openmrs.module.html5forms.XFormBuilder.xForm;

public class HTML5FormServiceTest extends BaseModuleContextSensitiveTest {

    private HTML5FormService service;

    @Before
    public void setUp() throws Exception {
        service = Context.getService(HTML5FormService.class);
        executeDataSet("xformTestData.xml");
    }

    @Test
    public void getAll_shouldGetAllForms() throws Exception {
        List<HTML5Form> list = service.getAll();
        assertThat(list.contains(html5Form().withId(1).withName("Registration Form").withDescription("Form for registration")
                .withXFrom(xForm().withId(1))
                .instance()), is(true));
        assertThat(list.contains(html5Form().withId(2).withName("PMTCT Form").withDescription("Form for PMTCT")
                .withXFrom(xForm().withId(2))
                .instance()), is(true));
        assertThat(list.contains(html5Form().withId(3).withName("Ante-Natal Form").withDescription("Form for ante-natal care")
                .instance()), is(true));
        assertThat(list.size(), is(3));
    }

    @Test
    public void getXform_shouldLoadForm() throws Exception {
        List<HTML5XForm> xform = service.getXForms();
        assertThat(xform.size(), is(3));
    }

}
