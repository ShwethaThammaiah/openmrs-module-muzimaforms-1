package org.openmrs.module.html5forms.api;

import org.junit.Before;
import org.junit.Test;
import org.openmrs.api.context.Context;
import org.openmrs.module.html5forms.HTML5FormTag;
import org.openmrs.test.BaseModuleContextSensitiveTest;

import java.util.Collection;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertThat;
import static org.openmrs.module.html5forms.HTML5FormTagBuilder.tag;

public class TagServiceTest extends BaseModuleContextSensitiveTest {

    private TagService service;

    @Before
    public void setUp() throws Exception {
        service = Context.getService(TagService.class);
        executeDataSet("tagTestData.xml");
    }

    @Test
    public void getAll_shouldGetAllTags() throws Exception {
        Collection<HTML5FormTag> list = service.getAll();
        assertThat(list.contains(tag().withId(1).withName("Registration").instance()), is(true));
        assertThat(list.contains(tag().withId(2).withName("Patient").instance()), is(true));
        assertThat(list.contains(tag().withId(3).withName("Encounter").instance()), is(true));
    }

    @Test
    public void add_shouldAddTag() throws Exception {
        HTML5FormTag tag = service.add("Observation");
        assertThat(tag.getId(), notNullValue());
        Collection<HTML5FormTag> list = service.getAll();
        assertThat(list.contains(tag().withId(tag.getId()).withName("Observation").instance()), is(true));
    }


}
