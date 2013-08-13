package org.openmrs.module.muzimaforms.web.controller;

import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Test;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.mock.web.MockMultipartHttpServletRequest;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class JavaRosaFormUploadControllerTest {
    @Test
    public void validate_shouldValidateAndReturnResultForAnUploadedJavaRosaForm() throws Exception {
        JavaRosaFormUploadController controller = new JavaRosaFormUploadController();
        MockMultipartHttpServletRequest request = new MockMultipartHttpServletRequest();
        request.addFile(new MockMultipartFile("file", getClass().getClassLoader().getResourceAsStream("invalidEmptyJavaRosaForm.xml")));
        assertThat(new ObjectMapper().writeValueAsString(controller.validate(request)), is("{\"list\":[{\"message\":\"Document has no root element!\",\"type\":\"ERROR\"}]}"));
    }
}
