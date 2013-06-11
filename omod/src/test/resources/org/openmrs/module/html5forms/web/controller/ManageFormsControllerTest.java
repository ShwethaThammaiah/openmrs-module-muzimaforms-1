package org.openmrs.module.html5forms.web.controller;

import org.junit.Test;

import static org.springframework.test.web.server.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.server.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.server.setup.MockMvcBuilders.standaloneSetup;

public class ManageFormsControllerTest {

    @Test
    public void tags() throws Exception {
        standaloneSetup(new ManageFormsController()).build().perform(get("/module/html5forms/tags")).andExpect(status().isOk());

    }
}
