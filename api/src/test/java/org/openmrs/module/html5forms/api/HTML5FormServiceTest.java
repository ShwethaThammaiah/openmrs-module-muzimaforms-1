package org.openmrs.module.html5forms.api;

import org.junit.Before;
import org.junit.Test;
import org.openmrs.api.context.Context;
import org.openmrs.module.html5forms.HTML5Form;
import org.openmrs.module.html5forms.HTML5FormTag;
import org.openmrs.module.html5forms.HTML5XForm;
import org.openmrs.test.BaseModuleContextSensitiveTest;

import java.util.List;
import java.util.Set;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertThat;
import static org.junit.matchers.JUnitMatchers.hasItem;
import static org.openmrs.module.html5forms.FormBuilder.form;
import static org.openmrs.module.html5forms.HTML5FormBuilder.html5Form;
import static org.openmrs.module.html5forms.HTML5FormTagBuilder.tag;
import static org.openmrs.module.html5forms.XFormBuilder.xForm;

public class HTML5FormServiceTest extends BaseModuleContextSensitiveTest {

    private HTML5FormService service;

    @Before
    public void setUp() throws Exception {
        service = Context.getService(HTML5FormService.class);
        executeDataSet("tagTestData.xml");
        executeDataSet("xformTestData.xml");
    }

    @Test
    public void getAll_shouldGetAllForms() throws Exception {
        List<HTML5Form> list = service.getAll();
        assertThat(list, hasItem(html5Form().withId(1)
                .with(tag().withId(1).withName("Registration"))
                .with(tag().withId(2).withName("Patient"))
                .with(xForm().withId(1))
                .with(form().withId(1).withName("Registration Form").withDescription("Form for registration"))
                .instance()));
        assertThat(list, hasItem(html5Form().withId(2)
                .with(tag().withId(1).withName("Registration"))
                .with(tag().withId(3).withName("Encounter"))
                .with(tag().withId(4).withName("HIV"))
                .with(xForm().withId(2))
                .with(form().withId(2).withName("PMTCT Form").withDescription("Form for PMTCT"))
                .instance()));
        assertThat(list, hasItem(html5Form().withId(3)
                .with(form().withId(3).withName("Ante-Natal Form").withDescription("Form for ante-natal care"))
                .instance()));
        assertThat(list.size(), is(3));
    }

    @Test
    public void getXform_shouldLoadForm() throws Exception {
        List<HTML5XForm> xform = service.getXForms();
        assertThat(xform.size(), is(1));
        assertThat(xform.get(0).getId(), is(0));
    }

    @Test
    public void saveForm_shouldAssignAnExistingTag() {
        service.saveForm(html5Form().withId(1).with(tag().withId(1).withName("Registration")).instance());
        List<HTML5Form> list = service.getAll();
        assertThat(list, hasItem(html5Form().withId(1).with(tag().withId(1).withName("Registration")).instance()));
    }

    @Test
    public void saveForm_shouldAssignANewTag() {
        HTML5Form form = html5Form().withId(1).with(tag().withName("New Tag")).with(tag().withName("Another Tag")).instance();
        service.saveForm(form);
        Set<HTML5FormTag> formTags = form.getTags();
        assertThat(formTags.size(), is(2));
        HTML5FormTag newTag = (HTML5FormTag) formTags.toArray()[0];
        assertThat(newTag.getId(), notNullValue());
        TagService tagService = Context.getService(TagService.class);
        List<HTML5FormTag> tags = tagService.getAll();
        assertThat(tags, hasItem(newTag));
    }

    @Test
    public void findById_shouldFindFormById() {
        HTML5Form form = service.findById(1);
        assertThat(form, is(html5Form().withId(1)
                .with(tag().withId(1).withName("Registration"))
                .with(tag().withId(2).withName("Patient"))
                .with(xForm().withId(1))
                .with(form().withId(1).withName("Registration Form").withDescription("Form for registration"))
                .instance()));
    }
}
