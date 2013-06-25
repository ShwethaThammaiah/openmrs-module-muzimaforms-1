package org.openmrs.module.html5forms.api;


import org.apache.commons.io.IOUtils;
import org.junit.Before;
import org.junit.Test;
import org.openmrs.api.context.Context;
import org.openmrs.module.html5forms.api.impl.EnketoServiceImpl;
import org.openmrs.module.html5forms.enketo.EnketoClient;
import org.openmrs.test.BaseModuleContextSensitiveTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.Resource;
import sun.net.www.http.HttpClient;

import java.io.IOException;
import java.io.InputStream;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.isNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class EnketoServiceImplTest extends BaseModuleContextSensitiveTest {
    private EnketoService service;
    private EnketoClient enketoClient;

    @Before
    public void setUp() throws Exception {
        enketoClient = mock(EnketoClient.class);
        service = new EnketoServiceImpl(enketoClient);
    }

    @Test
    public void transform_shouldCallEnketoServiceToTransform() throws IOException {
        when(enketoClient.transform(anyString())).thenReturn(getSampleResult());
        String foo = service.transform("foo");
        assertThat(foo, is(getSampleResult()));
    }

    private String getSampleResult() throws IOException {
        ApplicationContext context = new ClassPathXmlApplicationContext();
        return IOUtils.toString(context.getResource("/enketoSampleResult.xml").getInputStream());
    }
}
