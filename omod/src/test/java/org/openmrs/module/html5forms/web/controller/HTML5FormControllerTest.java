package org.openmrs.module.html5forms.web.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openmrs.api.context.Context;
import org.openmrs.module.html5forms.HTML5Form;
import org.openmrs.module.html5forms.api.HTML5FormService;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.*;
import static org.powermock.api.mockito.PowerMockito.mockStatic;

@RunWith(PowerMockRunner.class)
@PrepareForTest(Context.class)
public class HTML5FormControllerTest {
    private HTML5FormController html5FormController;
    private HTML5FormService service;

    @Before
    public void setUp() throws Exception {
        service = mock(HTML5FormService.class);
        html5FormController = new HTML5FormController();
        mockStatic(Context.class);
        PowerMockito.when(Context.getService(HTML5FormService.class)).thenReturn(service);
    }

    @Test
    public void get_shouldReturnFormWithGivenId() throws Exception {
        HTML5Form html5Form = new HTML5Form(){{setId(1);}};

        when(service.findById(1)).thenReturn(html5Form);

        assertThat(html5FormController.get(1), is(html5Form));
    }

    @Test
    public void create_shouldSaveAForm() {
        HTML5Form html5Form = new HTML5Form(){{setId(5);}};

        html5FormController.create(html5Form);

        verify(service).saveForm(html5Form);
    }
}
