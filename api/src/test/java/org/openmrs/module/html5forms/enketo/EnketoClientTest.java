package org.openmrs.module.html5forms.enketo;

import org.apache.commons.io.IOUtils;
import org.apache.http.ProtocolVersion;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.BasicHttpEntity;
import org.apache.http.message.BasicHttpResponse;
import org.apache.http.message.BasicStatusLine;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;
import java.io.InputStream;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class EnketoClientTest {
    @Test
    public void transform_shouldCallEnketoServer() throws IOException {
        HttpClient httpClient = mock(HttpClient.class);
        when(httpClient.execute(any(HttpPost.class))).thenReturn(getBasicHttpResponse());

        EnketoClient enketoClient = new EnketoClient("http://10.4.33.189/transform/get_html_form", httpClient);
        String result = enketoClient.transform(getSampleXForm());
        assertThat(result, is(IOUtils.toString(getConvertedXForm())));
    }

    private BasicHttpResponse getBasicHttpResponse() throws IOException {
        BasicHttpResponse response = new BasicHttpResponse(
                new BasicStatusLine(new ProtocolVersion("HTTP", 1, 1), 200, "OK"));
        BasicHttpEntity entity = new BasicHttpEntity();
        entity.setContent(getConvertedXForm());
        response.setEntity(entity);
        return response;
    }


    private String getSampleXForm() throws IOException {
        ApplicationContext context = new ClassPathXmlApplicationContext();
        return IOUtils.toString(context.getResource("/enketoSampleRequest.xml").getInputStream());
    }

    private InputStream getConvertedXForm() throws IOException {
        ApplicationContext context = new ClassPathXmlApplicationContext();
        return context.getResource("/enketoSampleResult.xml").getInputStream();
    }
}
