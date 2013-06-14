package org.openmrs.module.html5forms.web.controller;

import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.openmrs.module.html5forms.HTML5Forms;
import org.openmrs.test.BaseModuleContextSensitiveTest;

/**
 * Created with IntelliJ IDEA.
 * User: r
 * Date: 13/06/13
 * Time: 6:45 PM
 * To change this template use File | Settings | File Templates.
 */
public class HTML5FormsControllerTest extends BaseModuleContextSensitiveTest {
    @Before
    public void setUp() throws Exception {
        executeDataSet("xformomodTestData.xml");

    }

    @Test
    public void testForms() throws Exception {
        HTML5FormsController controller = new HTML5FormsController();
        HTML5Forms forms = controller.forms();
        ObjectMapper mapper = new ObjectMapper();
        System.out.println(mapper.writeValueAsString(forms));

    }
}
