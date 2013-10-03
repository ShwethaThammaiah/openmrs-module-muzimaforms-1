package org.openmrs.module.muzimaforms.web.controller;

import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openmrs.api.context.Context;
import org.openmrs.module.muzimaforms.api.MuzimaFormService;
import org.openmrs.module.muzimaforms.api.impl.EnketoResult;
import org.openmrs.module.muzimaforms.xForm2MuzimaTransform.EnketoXslTransformer;
import org.openmrs.module.muzimaforms.xForm2MuzimaTransform.XForm2JavarosaXslTransformer;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.mock.web.MockMultipartHttpServletRequest;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Scanner;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;
import static org.powermock.api.mockito.PowerMockito.mockStatic;

@RunWith(PowerMockRunner.class)
@PrepareForTest(Context.class)
public class JavaRosaFormUploadControllerTest {

    private JavaRosaFormUploadController controller;
    private MockMultipartHttpServletRequest request;
    private MuzimaFormService service;
    private XForm2JavarosaXslTransformer transformer;

    @Before
    public void setUp() throws Exception {
        request = new MockMultipartHttpServletRequest();
        transformer = mock(XForm2JavarosaXslTransformer.class);
        controller = new JavaRosaFormUploadController(transformer);

        service = mock(MuzimaFormService.class);
        mockStatic(Context.class);
        PowerMockito.when(Context.getService(MuzimaFormService.class)).thenReturn(service);

    }

    @Test
    public void validate_shouldValidateAndReturnResultForAnUploadedJavaRosaForm() throws Exception {
        request.addFile(multipartFile("file", "invalidEmptyJavaRosaForm.xml"));
        assertThat(new ObjectMapper().writeValueAsString(controller.validate(request)), is("{\"list\":[{\"message\":\"Document has no root element!\",\"type\":\"ERROR\"}]}"));
    }

    @Test
    public void upload_shouldConvertJavaRosaFormToHTMLAndSaveIt() throws Exception {
        request.addFile(multipartFile("file", "sampleJavaRosaXForm.xml"));

        controller.upload(request, "name", "description");

        verify(service).create(readStream(request.getFile("file").getInputStream()), "description", "name");
    }

    @Test
    public void validate_shouldCheckIfTheFileIsAnODKFormAndConvertItToJavaRosaBeforeValidation() throws Exception {
        request.addFile(multipartFile("file", "sampleXForm.xml"));
        request.addParameters(new HashMap() {{
            put("isODK", "true");
        }});

        when(transformer.transform(readFile("sampleXForm.xml"))).thenReturn(new EnketoResult("result"));

        controller.validate(request);

        verify(transformer).transform(readFile("sampleXForm.xml"));
    }

    @Test
    public void validate_shouldCheckIfTheFileIsAnODKFormAndConvertItToJavaRosaBeforeUploading() throws Exception {
        request.addFile(multipartFile("file", "sampleXForm.xml"));
        request.addParameters(new HashMap() {{
            put("isODK", "true");
        }});

        when(transformer.transform(readFile("sampleXForm.xml"))).thenReturn(new EnketoResult("result"));

        controller.upload(request, "name", "description");

        verify(transformer).transform(readFile("sampleXForm.xml"));
        verify(service).create("result", "description", "name");
    }

    @Test
    public void validate_shouldNotConvertToJavaRosaIfItIsNotAnODKForm() throws Exception {
        request.addFile(multipartFile("file", "sampleXForm.xml"));

        controller.validate(request);

        verify(transformer, times(0)).transform(readFile("sampleXForm.xml"));
    }

    private MockMultipartFile multipartFile(String name, String fileName) throws IOException {
        return new MockMultipartFile(name, getClass().getClassLoader().getResourceAsStream(fileName));
    }

    private String readStream(InputStream stream) throws IOException {
        return new Scanner(stream, "UTF-8").useDelimiter("\\A").next();
    }

    private String readFile(String fileName) throws IOException {
        return readStream(getClass().getClassLoader().getResourceAsStream(fileName));
    }
}
