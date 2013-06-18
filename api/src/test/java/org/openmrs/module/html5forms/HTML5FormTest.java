package org.openmrs.module.html5forms;

import org.junit.Test;
import org.openmrs.Form;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

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
    public void testNullFormName() throws Exception {
        HTML5Form html5Form = new HTML5Form();
        assertThat(html5Form.getName(), is(""));
    }

    @Test
    public void testDescription() throws Exception {
        Form form = new Form();
        form.setDescription("description");
        HTML5Form html5Form = new HTML5Form();
        html5Form.setForm(form);
        assertThat(html5Form.getDescription(), is("description"));

    }

    @Test
    public void testNullFormDescription() throws Exception {
        HTML5Form html5Form = new HTML5Form();
        assertThat(html5Form.getDescription(), is(""));

    }
}
