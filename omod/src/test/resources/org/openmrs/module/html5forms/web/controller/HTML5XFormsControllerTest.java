package org.openmrs.module.html5forms.web.controller;

import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.openmrs.module.html5forms.HTML5XForm;
import org.openmrs.test.BaseModuleContextSensitiveTest;

import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class HTML5XFormsControllerTest extends BaseModuleContextSensitiveTest {
    @Before
    public void setUp() throws Exception {

    }

    @Test
    public void testXForms() throws Exception {
        executeDataSet("xformomodTestData.xml");
        HTML5XFormsController controller = new HTML5XFormsController();
        List<HTML5XForm> forms = controller.xForms();
        assertThat(forms.size(), is(2));
    }

    @Test
    public void testNoXFormsData() throws Exception {
        HTML5XFormsController controller = new HTML5XFormsController();
        List<HTML5XForm> forms = controller.xForms();
        ObjectMapper mapper = new ObjectMapper();
        System.out.println(mapper.writeValueAsString(forms));

    }
}
