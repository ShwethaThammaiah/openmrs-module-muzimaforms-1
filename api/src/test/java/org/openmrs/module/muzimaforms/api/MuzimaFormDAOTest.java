package org.openmrs.module.muzimaforms.api;

import org.junit.Before;
import org.junit.Test;
import org.openmrs.api.context.Context;
import org.openmrs.module.muzimaforms.MuzimaForm;
import org.openmrs.module.muzimaforms.MuzimaFormTag;
import org.openmrs.module.muzimaforms.MuzimaXForm;
import org.openmrs.module.muzimaforms.api.db.hibernate.MuzimaFormDAO;
import org.openmrs.test.BaseModuleContextSensitiveTest;
import org.springframework.test.context.ContextConfiguration;

import java.io.IOException;
import java.util.List;
import java.util.Set;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertThat;
import static org.junit.matchers.JUnitMatchers.hasItem;
import static org.openmrs.module.muzimaforms.MuzimaFormBuilder.muzimaform;
import static org.openmrs.module.muzimaforms.MuzimaFormTagBuilder.tag;

@ContextConfiguration(value = "classpath*:muzimaFormtestingApplicationContext.xml", inheritLocations = true)
public class MuzimaFormDAOTest extends BaseModuleContextSensitiveTest {

    private MuzimaFormDAO dao;

    @Before
    public void setUp() throws Exception {
        dao = (MuzimaFormDAO) applicationContext.getBean("muzimaFormDAO");
        executeDataSet("tagTestData.xml");
        executeDataSet("xformTestData.xml");
    }

    @Test
    public void getAll_shouldGetAll() {
        List<MuzimaForm> list = dao.getAll();
        assertThat(list, hasItem(muzimaform().withId(1).withName("Registration Form").withDescription("Form for registration")
                .with(tag().withId(1).withName("Registration"))
                .with(tag().withId(2).withName("Patient"))
                .instance()));
        assertThat(list, hasItem(muzimaform().withId(2).withName("PMTCT Form").withDescription("Form for PMTCT")
                .with(tag().withId(1).withName("Registration"))
                .with(tag().withId(3).withName("Encounter"))
                .with(tag().withId(4).withName("HIV"))
                .instance()));
        assertThat(list, hasItem(muzimaform().withId(3).withName("Ante-Natal Form").withDescription("Form for ante-natal care")
                .instance()));
        assertThat(list.size(), is(3));
    }

    @Test
    public void getXForms_shouldGetXForms() {
        List<MuzimaXForm> all = dao.getXForms();
        assertThat(all.size(), is(3));
    }

    @Test
    public void findById_shouldFindById() {
        MuzimaForm form = dao.findById(1);
        assertThat(form, is(muzimaform().withId(1).withName("Registration Form").withDescription("Form for registration")
                .with(tag().withId(1).withName("Registration"))
                .with(tag().withId(2).withName("Patient"))
                .instance()));
    }

    @Test
    public void findByUUID_shouldFindByUUID() {
        MuzimaForm form = dao.findByUuid("foo");
        assertThat(form, is(muzimaform().withId(1).withUuid("foo").withName("Registration Form").withDescription("Form for registration")
                .with(tag().withId(1).withName("Registration"))
                .with(tag().withId(2).withName("Patient"))
                .instance()));
    }

    @Test
    public void saveForm_shouldSaveForm() {
        dao.saveForm(muzimaform().withId(1).with(tag().withId(1).withName("Registration")).instance());
        List<MuzimaForm> list = dao.getAll();
        assertThat(list, hasItem(muzimaform().withId(1).with(tag().withId(1).withName("Registration")).instance()));
    }

    @Test
    public void saveForm_shouldAssignAnExistingTag() throws IOException {
        dao.saveForm(muzimaform().withId(1).with(tag().withId(1).withName("Registration")).instance());
        List<MuzimaForm> list = dao.getAll();
        assertThat(list, hasItem(muzimaform().withId(1).with(tag().withId(1).withName("Registration")).instance()));
    }

    @Test
    public void saveForm_shouldAssignANewTag() throws IOException {
        MuzimaForm form = muzimaform().withId(1).with(tag().withName("New Tag")).with(tag().withName("Another Tag")).instance();
        dao.saveForm(form);
        Set<MuzimaFormTag> formTags = form.getTags();
        assertThat(formTags.size(), is(2));
        MuzimaFormTag newTag = (MuzimaFormTag) formTags.toArray()[0];
        assertThat(newTag.getId(), notNullValue());
        MuzimaTagService tagService = Context.getService(MuzimaTagService.class);
        List<MuzimaFormTag> tags = tagService.getAll();
        assertThat(tags, hasItem(newTag));
    }
}
