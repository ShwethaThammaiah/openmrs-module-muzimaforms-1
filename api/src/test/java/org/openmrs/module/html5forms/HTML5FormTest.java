package org.openmrs.module.html5forms;

import org.junit.Test;
import org.openmrs.Form;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Created with IntelliJ IDEA.
 * User: r
 * Date: 13/06/13
 * Time: 6:38 PM
 * To change this template use File | Settings | File Templates.
 */
public class HTML5FormTest {
    @Test
    public void testName() throws Exception {
        Form form = new Form();
        form.setName("name");
        HTML5Form html5Form = new HTML5Form();
        html5Form.setForm(form);
        assertThat(html5Form.getName(), is("name"));

    }

    @Test
    public void testDescription() throws Exception {
        Form form = new Form();
        form.setDescription("description");
        HTML5Form html5Form = new HTML5Form();
        html5Form.setForm(form);
        assertThat(html5Form.getDescription(), is("description"));

    }
}
