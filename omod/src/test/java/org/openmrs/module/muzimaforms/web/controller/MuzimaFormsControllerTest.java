package org.openmrs.module.muzimaforms.web.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openmrs.Form;
import org.openmrs.api.context.Context;
import org.openmrs.module.muzimaforms.MuzimaForm;
import org.openmrs.module.muzimaforms.MuzimaFormTag;
import org.openmrs.module.muzimaforms.api.MuzimaFormService;
import org.openmrs.module.xforms.Xform;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static java.util.Arrays.asList;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.matchers.JUnitMatchers.hasItems;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.powermock.api.mockito.PowerMockito.mockStatic;

@RunWith(PowerMockRunner.class)
@PrepareForTest(Context.class)
public class MuzimaFormsControllerTest {
    private MuzimaFormsController html5FormsController;
    private MuzimaFormService service;

    List<MuzimaForm> muzimaForms;

    @Before
    public void setup() {
        setUpServiceResponse();
        service = mock(MuzimaFormService.class);
        html5FormsController = new MuzimaFormsController();
        mockStatic(Context.class);
        PowerMockito.when(Context.getService(MuzimaFormService.class)).thenReturn(service);
    }

    @Test
    public void forms_shouldGetAllForms() throws Exception {
        MuzimaForm html5Form1 = new MuzimaForm() {{
            setId(1);
        }};
        MuzimaForm html5Form2 = new MuzimaForm() {{
            setId(2);
        }};

        when(service.getAll()).thenReturn(muzimaForms);
        List<MuzimaFormMetadata> forms = html5FormsController.forms();

        assertThat(forms.size(), is(2));
        assertThat(forms.get(0).getId(), is(html5Form1.getId()));
        assertThat(forms.get(1).getId(), is(html5Form2.getId()));
    }

    private void setUpServiceResponse() {
        final Xform xform = mock(Xform.class);
        when(xform.getXformXml()).thenReturn("Game of thrones");

        MuzimaForm html5Form1 = new MuzimaForm() {{
            setId(1);
            Set<MuzimaFormTag> tags = new HashSet<MuzimaFormTag>();
            tags.add(new MuzimaFormTag("foo"));
            tags.add(new MuzimaFormTag("bar"));
            setTags(tags);
            setXform(xform);
        }};


        final Xform form2 = mock(Xform.class);
        when(form2.getXformXml()).thenReturn("A clash of kings");

        MuzimaForm html5Form2 = new MuzimaForm() {{
            setId(2);
            setXform(form2);
        }};
        muzimaForms = asList(html5Form1, html5Form2);
    }

}
