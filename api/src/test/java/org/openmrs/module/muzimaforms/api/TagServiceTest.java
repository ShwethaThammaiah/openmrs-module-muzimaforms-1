package org.openmrs.module.muzimaforms.api;

import org.junit.Before;
import org.junit.Test;
import org.openmrs.api.context.Context;
import org.openmrs.module.muzimaforms.MuzimaFormTag;
import org.openmrs.test.BaseModuleContextSensitiveTest;
import org.springframework.test.context.ContextConfiguration;

import java.util.Collection;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertThat;
import static org.openmrs.module.muzimaforms.MuzimaFormTagBuilder.tag;

@ContextConfiguration(value = "classpath*:muzimaFormtestingApplicationContext.xml", inheritLocations = true)
public class TagServiceTest extends BaseModuleContextSensitiveTest {

    private MuzimaTagService service;

    @Before
    public void setUp() throws Exception {
        service = Context.getService(MuzimaTagService.class);
        executeDataSet("tagTestData.xml");
    }

    @Test
    public void getAll_shouldGetAllTags() throws Exception {
        Collection<MuzimaFormTag> list = service.getAll();
        assertThat(list.contains(tag().withId(1).withName("Registration").instance()), is(true));
        assertThat(list.contains(tag().withId(2).withName("Patient").instance()), is(true));
        assertThat(list.contains(tag().withId(3).withName("Encounter").instance()), is(true));
    }

    @Test
    public void add_shouldAddTag() throws Exception {
        MuzimaFormTag tag = service.add("Observation");
        assertThat(tag.getId(), notNullValue());
        Collection<MuzimaFormTag> list = service.getAll();
        assertThat(list.contains(tag().withId(tag.getId()).withName("Observation").instance()), is(true));
    }


}
