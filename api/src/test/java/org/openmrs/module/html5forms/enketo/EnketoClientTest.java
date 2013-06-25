package org.openmrs.module.html5forms.enketo;

import org.apache.commons.io.IOUtils;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

public class EnketoClientTest {
    @Test
    public void transform_shouldCallEnketoServer() throws IOException {
        EnketoClient foo = new EnketoClient("http://10.4.33.189/transform/get_html_form");
        String result = foo.transform(getSampleXForm());
    }


    private String getSampleXForm() throws IOException {
        ApplicationContext context = new ClassPathXmlApplicationContext();
        return IOUtils.toString(context.getResource("/enketoSampleRequest.xml").getInputStream());
    }

    private String getConvertedXForm() throws IOException {
        ApplicationContext context = new ClassPathXmlApplicationContext();
        return IOUtils.toString(context.getResource("/enketoSampleResult.xml").getInputStream());
    }
}
