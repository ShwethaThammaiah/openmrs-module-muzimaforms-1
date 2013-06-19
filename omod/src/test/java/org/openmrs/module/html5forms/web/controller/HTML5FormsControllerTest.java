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

import java.util.List;

import static java.util.Arrays.asList;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.matchers.JUnitMatchers.hasItems;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.powermock.api.mockito.PowerMockito.mockStatic;

@RunWith(PowerMockRunner.class)
@PrepareForTest(Context.class)
public class HTML5FormsControllerTest{
    private HTML5FormsController html5FormsController;
    private HTML5FormService service;

    @Before
    public void setUp() throws Exception {
        service = mock(HTML5FormService.class);
        html5FormsController = new HTML5FormsController();
        mockStatic(Context.class);
        PowerMockito.when(Context.getService(HTML5FormService.class)).thenReturn(service);
    }

    @Test
    public void forms_shouldGetAllForms() throws Exception {
        HTML5Form html5Form1 = new HTML5Form(){{setId(1);}};
        HTML5Form html5Form2 = new HTML5Form(){{setId(2);}};

        when(service.getAll()).thenReturn(asList(html5Form1, html5Form2));
        List<HTML5Form> forms = html5FormsController.forms();

        assertThat(forms.size(), is(2));
        assertThat(forms, hasItems(html5Form1,html5Form2));
    }
}
