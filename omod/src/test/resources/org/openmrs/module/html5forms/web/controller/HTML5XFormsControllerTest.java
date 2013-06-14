package org.openmrs.module.html5forms.web.controller;

import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.openmrs.module.html5forms.HTML5XForms;
import org.openmrs.test.BaseModuleContextSensitiveTest;

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
        HTML5XForms forms = controller.xForms();
        assertThat(forms.getList().size(), is(2));
    }

    @Test
    public void testNoXFormsData() throws Exception {
        HTML5XFormsController controller = new HTML5XFormsController();
        HTML5XForms forms = controller.xForms();
        ObjectMapper mapper = new ObjectMapper();
        System.out.println(mapper.writeValueAsString(forms));

    }
}
