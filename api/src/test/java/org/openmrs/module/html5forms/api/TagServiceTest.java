package org.openmrs.module.html5forms.api;

import org.junit.Before;
import org.junit.Test;
import org.openmrs.api.context.Context;
import org.openmrs.module.html5forms.Tag;
import org.openmrs.module.html5forms.TagsAccessor;
import org.openmrs.test.BaseModuleContextSensitiveTest;

import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertThat;
import static org.openmrs.module.html5forms.TagBuilder.tag;

public class TagServiceTest extends BaseModuleContextSensitiveTest {

    private TagService service;

    @Before
    public void setUp() throws Exception {
        service = Context.getService(TagService.class);
        executeDataSet("testData.xml");
    }

    @Test
    public void getAll_shouldGetAllTags() throws Exception {
        List<Tag> list = new TagsAccessor(service.getAll()).getList();
        assertThat(list.contains(tag().withId(1).withName("Registration").instance()), is(true));
        assertThat(list.contains(tag().withId(2).withName("Patient").instance()), is(true));
        assertThat(list.contains(tag().withId(3).withName("Encounter").instance()), is(true));
    }

    @Test
    public void add_shouldAddTag() throws Exception {
        Tag tag = service.add("Observation");
        assertThat(tag.getId(), notNullValue());
        List<Tag> list = new TagsAccessor(service.getAll()).getList();
        assertThat(list.contains(tag().withId(tag.getId()).withName("Observation").instance()), is(true));
    }

}
