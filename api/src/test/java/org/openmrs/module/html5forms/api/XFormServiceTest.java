package org.openmrs.module.html5forms.api;

import org.junit.Before;
import org.junit.Test;
import org.openmrs.api.context.Context;
import org.openmrs.module.html5forms.XFromsAccessor;
import org.openmrs.module.html5forms.XForm;
import org.openmrs.test.BaseModuleContextSensitiveTest;

import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.openmrs.module.html5forms.XFormBuilder.xForm;

public class XFormServiceTest extends BaseModuleContextSensitiveTest {

    private XFormService service;

    @Before
    public void setUp() throws Exception {
        service = Context.getService(XFormService.class);
        executeDataSet("xformTestData.xml");
    }

    @Test
    public void getAll_shouldGetAllTags() throws Exception {
        List<XForm> list = new XFromsAccessor(service.getAll()).getList();
        assertThat(list.contains(xForm().withId(1).withName("Registration Form").withDescription("Form for registration").instance()), is(true));
        assertThat(list.contains(xForm().withId(2).withName("PMTCT Form").withDescription("Form for PMTCT").instance()), is(true));
        assertThat(list.size(), is(2));
    }
}
