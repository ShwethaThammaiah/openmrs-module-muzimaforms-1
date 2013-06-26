package org.openmrs.module.html5forms.api;

import org.hamcrest.CoreMatchers;
import org.junit.Before;
import org.junit.Test;
import org.openmrs.api.context.Context;
import org.openmrs.module.html5forms.HTML5Form;
import org.openmrs.module.html5forms.HTML5FormTag;
import org.openmrs.module.html5forms.HTML5XForm;
import org.openmrs.module.html5forms.XFormBuilder;
import org.openmrs.module.html5forms.api.db.hibernate.HTML5FormDAO;
import org.openmrs.module.html5forms.api.db.hibernate.impl.HTML5FormDAOImpl;
import org.openmrs.test.BaseModuleContextSensitiveTest;

import java.io.IOException;
import java.util.List;
import java.util.Set;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertThat;
import static org.junit.matchers.JUnitMatchers.hasItem;
import static org.mockito.Mockito.*;
import static org.openmrs.module.html5forms.FormBuilder.form;
import static org.openmrs.module.html5forms.HTML5FormBuilder.html5Form;
import static org.openmrs.module.html5forms.HTML5FormTagBuilder.tag;
import static org.openmrs.module.html5forms.XFormBuilder.xForm;

public class HTML5FormDAOTest extends BaseModuleContextSensitiveTest {

    private HTML5FormDAO dao;

    @Before
    public void setUp() throws Exception {
        dao = Context.getService(HTML5FormDAO.class);
        executeDataSet("tagTestData.xml");
        executeDataSet("xformTestData.xml");
    }

    @Test
    public void getAll_shouldGetAll(){
        List<HTML5Form> list = dao.getAll();
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
    public void getXForms_shouldGetXForms(){
        List<HTML5XForm> all = dao.getXForms();
        assertThat(all.size(), is(1));
    }
    @Test
    public void findById_shouldFindById(){
        HTML5Form form = dao.findById(1);
        assertThat(form, is(html5Form().withId(1)
                .with(tag().withId(1).withName("Registration"))
                .with(tag().withId(2).withName("Patient"))
                .with(xForm().withId(1))
                .with(form().withId(1).withName("Registration Form").withDescription("Form for registration"))
                .instance()));
    }
    @Test
    public void saveForm_shouldSaveForm(){
        dao.saveForm(html5Form().withId(1).with(tag().withId(1).withName("Registration")).instance());
        List<HTML5Form> list = dao.getAll();
        assertThat(list, hasItem(html5Form().withId(1).with(tag().withId(1).withName("Registration")).instance()));
    }

    @Test
    public void saveForm_shouldAssignAnExistingTag() throws IOException {
        dao.saveForm(html5Form().withId(1).with(tag().withId(1).withName("Registration")).instance());
        List<HTML5Form> list = dao.getAll();
        assertThat(list, hasItem(html5Form().withId(1).with(tag().withId(1).withName("Registration")).instance()));
    }

    @Test
    public void saveForm_shouldAssignANewTag() throws IOException {
        HTML5Form form = html5Form().withId(1).with(tag().withName("New Tag")).with(tag().withName("Another Tag")).instance();
        dao.saveForm(form);
        Set<HTML5FormTag> formTags = form.getTags();
        assertThat(formTags.size(), is(2));
        HTML5FormTag newTag = (HTML5FormTag) formTags.toArray()[0];
        assertThat(newTag.getId(), notNullValue());
        TagService tagService = Context.getService(TagService.class);
        List<HTML5FormTag> tags = tagService.getAll();
        assertThat(tags, hasItem(newTag));
    }
}
