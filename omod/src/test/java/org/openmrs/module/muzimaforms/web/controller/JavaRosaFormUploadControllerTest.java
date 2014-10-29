package org.openmrs.module.muzimaforms.web.controller;

import org.junit.Before;
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

import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.eq;
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

    @Test
    public void shouldConvertJavaRosaFormToHTMLAndSaveIt() throws Exception {
        request.addFile(multipartFile("file", "sampleUploadForm.xml"));

        controller.uploadJavaRosa(request, "name", "form", "description", "discriminator", "1.0");

        verify(service).create(readStream(request.getFile("file").getInputStream()), "name", "form", "description", "discriminator", "1.0");
    }

    @Test
    public void shouldConvertODKFormToHTMLAndSaveIt() throws Exception {
        request.addFile(multipartFile("file", "sampleUploadForm.xml"));

        controller.uploadODK(request, "name", "form", "description", "discriminator", "1.0");

        verify(service).importODK(readStream(request.getFile("file").getInputStream()), "name", "form", "description", "discriminator", "1.0");
    }

    @Test
    public void validate_shouldValidateAJavaRosaXMLUsingTheService() throws Exception {
        request.addFile(multipartFile("file", "sampleUploadForm.xml"));

        controller.validateJavaRosa(request);

        verify(service).validateJavaRosa(readStream(request.getFile("file").getInputStream()));

    }

    @Test
    public void shouldCreateHTMLFormWithGivenNameAndDescription() throws Exception {
        String form = "form";
        String formName = "name";
        String formDescription = "description";
        String formDiscriminator = "discriminator";
        String version = "1.0";
        request.addFile(multipartFile("file", "sampleUploadForm.xml"));
        controller.uploadHTMLForm(request, formName, form, formDescription, formDiscriminator,version);
        verify(service).createHTMLForm(anyString(), eq(formName), eq(form), eq(formDescription), eq(formDiscriminator), eq(version));
    }

    @Test
    public void shouldUpdateHTMLFormWithGivenNameAndDescription() throws Exception {
        String formName = "UpdateForm";
        String formId = "123";
        request.addFile(multipartFile("file", "sampleUploadForm.xml"));
        controller.updateHTMLForm(request, formName,formId);
        verify(service).updateHTMLForm(anyString(), eq(formName), eq(formId));

    }

    private MockMultipartFile multipartFile(String name, String fileName) throws IOException {
        return new MockMultipartFile(name, getClass().getClassLoader().getResourceAsStream(fileName));
    }

    private String readStream(InputStream stream) throws IOException {
        return new Scanner(stream, "UTF-8").useDelimiter("\\A").next();
    }

}
