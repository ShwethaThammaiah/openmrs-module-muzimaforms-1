package org.openmrs.module.muzimaforms;

import org.junit.Test;
import org.openmrs.Form;

import java.util.HashSet;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class MuzimaFormTest {
    @Test
    public void name_shouldGetTheNameOfAssociatedForm() throws Exception {
        Form form = new Form();
        form.setName("name");
        MuzimaForm muzimaForm = new MuzimaForm();
        muzimaForm.setForm(form);
        assertThat(muzimaForm.getName(), is("name"));
    }

    @Test
    public void name_shouldReturnEmptyStringIfThereIsNoAssocatedForm() throws Exception {
        MuzimaForm muzimaForm = new MuzimaForm();
        assertThat(muzimaForm.getName(), is(""));
    }

    @Test
    public void description_shouldGetTheDescriptionOfAssociatedForm() throws Exception {
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

    @Test
    public void getTagNames_shouldGetTagNamesOnForm() {
        MuzimaForm form = new MuzimaForm();
        HashSet<MuzimaFormTag> tags = new HashSet<MuzimaFormTag>();
        tags.add(new MuzimaFormTag("foo"));
        tags.add(new MuzimaFormTag("bar"));
        tags.add(new MuzimaFormTag("baz"));
        form.setTags(tags);

        assertThat(form.getTagNames().size(), is(3));
        assertThat(form.getTagNames().contains("foo"), is(true));
        assertThat(form.getTagNames().contains("bar"), is(true));
        assertThat(form.getTagNames().contains("baz"), is(true));
    }
}
