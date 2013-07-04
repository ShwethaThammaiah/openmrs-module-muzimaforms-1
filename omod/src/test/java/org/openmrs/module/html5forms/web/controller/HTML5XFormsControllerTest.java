package org.openmrs.module.html5forms.web.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openmrs.api.context.Context;
import org.openmrs.module.html5forms.HTML5XForm;
import org.openmrs.module.html5forms.api.HTML5FormService;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import static java.util.Arrays.asList;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.matchers.JUnitMatchers.hasItems;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.powermock.api.mockito.PowerMockito.mockStatic;

@RunWith(PowerMockRunner.class)
@PrepareForTest(Context.class)
public class HTML5XFormsControllerTest {
    private HTML5XFormsController html5XFormsController;
    private HTML5FormService service;

    @Before
    public void setUp() throws Exception {
        service = mock(HTML5FormService.class);
        html5XFormsController = new HTML5XFormsController();
        mockStatic(Context.class);
        PowerMockito.when(Context.getService(HTML5FormService.class)).thenReturn(service);
    }

    @Test
    public void xForms_shouldReturnXForms() throws Exception {
        HTML5XForm xForm1 = new HTML5XForm() {{
            setId(1);
        }};
        HTML5XForm xForm2 = new HTML5XForm() {{
            setId(2);
        }};

        when(service.getXForms()).thenReturn(asList(xForm1, xForm2));

        assertThat(html5XFormsController.xForms().size(), is(2));
        assertThat(html5XFormsController.xForms(), hasItems(xForm1, xForm2));
    }
}
