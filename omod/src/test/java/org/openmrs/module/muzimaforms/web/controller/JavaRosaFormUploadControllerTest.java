package org.openmrs.module.muzimaforms.web.controller;

import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openmrs.api.context.Context;
import org.openmrs.module.muzimaforms.api.MuzimaFormService;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.mock.web.MockMultipartHttpServletRequest;

import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.powermock.api.mockito.PowerMockito.mockStatic;

@RunWith(PowerMockRunner.class)
@PrepareForTest(Context.class)
public class JavaRosaFormUploadControllerTest {

    private JavaRosaFormUploadController controller;
    private MockMultipartHttpServletRequest request;
    private MuzimaFormService service;

    @Before
    public void setUp() throws Exception {
        request = new MockMultipartHttpServletRequest();
        controller = new JavaRosaFormUploadController();

        service = mock(MuzimaFormService.class);
        mockStatic(Context.class);
        PowerMockito.when(Context.getService(MuzimaFormService.class)).thenReturn(service);

    }

    private MockMultipartFile multipartFile(String name, String fileName) throws IOException {
        return new MockMultipartFile(name, getClass().getClassLoader().getResourceAsStream(fileName));
    }

    @Test
    public void validate_shouldValidateAndReturnResultForAnUploadedJavaRosaForm() throws Exception {
        request.addFile(multipartFile("file", "invalidEmptyJavaRosaForm.xml"));
        assertThat(new ObjectMapper().writeValueAsString(controller.validate(request)), is("{\"list\":[{\"message\":\"Document has no root element!\",\"type\":\"ERROR\"}]}"));
    }

    @Test
    public void upload_shouldConvertJavaRosaFormToHTMLAndSaveIt() throws Exception {
        request.addFile(multipartFile("file", "sampleJavaRosaXForm.xml"));
        request.addFile(multipartFile("data", "sampleJavaRosaData.txt"));

        controller.upload(request);

        verify(service).create("name", "description", readStream(request.getFile("file").getInputStream()));
    }

    private String readStream(InputStream stream) throws IOException {
        return new Scanner(stream, "UTF-8").useDelimiter("\\A").next();
    }
}
