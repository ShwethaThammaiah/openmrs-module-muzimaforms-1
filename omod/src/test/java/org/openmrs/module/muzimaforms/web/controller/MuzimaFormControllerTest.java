package org.openmrs.module.muzimaforms.web.controller;

import org.dom4j.DocumentException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openmrs.api.context.Context;
import org.openmrs.module.muzimaforms.MuzimaForm;
import org.openmrs.module.muzimaforms.api.MuzimaFormService;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.xpath.XPathExpressionException;
import java.io.IOException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.*;
import static org.powermock.api.mockito.PowerMockito.mockStatic;

@RunWith(PowerMockRunner.class)
@PrepareForTest(Context.class)
public class MuzimaFormControllerTest {
    private MuzimaFormController html5FormController;
    private MuzimaFormService service;

    @Before
    public void setUp() throws Exception {
        service = mock(MuzimaFormService.class);
        html5FormController = new MuzimaFormController();
        mockStatic(Context.class);
        PowerMockito.when(Context.getService(MuzimaFormService.class)).thenReturn(service);
    }

    @Test
    public void get_shouldReturnFormWithGivenId() throws Exception {
        MuzimaForm html5Form = new MuzimaForm() {{
            setId(1);
        }};

        when(service.findById(1)).thenReturn(html5Form);

        assertThat(html5FormController.get(1), is(html5Form));
    }

    @Test
    public void create_shouldSaveAForm() throws IOException, TransformerException, SAXException, ParserConfigurationException, XPathExpressionException, DocumentException {
        MuzimaForm html5Form = new MuzimaForm() {{
            setId(5);
        }};
        html5FormController.create(html5Form);
        verify(service).saveForm(html5Form);
    }

}
