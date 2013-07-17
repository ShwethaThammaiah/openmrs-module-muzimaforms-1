package org.openmrs.module.muzimaforms.resource;

import org.dom4j.DocumentException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openmrs.api.context.Context;
import org.openmrs.module.muzimaforms.MuzimaForm;
import org.openmrs.module.muzimaforms.api.MuzimaFormService;
import org.openmrs.module.muzimaforms.web.controller.MuzimaFormMetadata;
import org.openmrs.module.webservices.rest.SimpleObject;
import org.openmrs.module.webservices.rest.web.RequestContext;
import org.openmrs.module.webservices.rest.web.response.ResourceDoesNotSupportOperationException;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.xpath.XPathExpressionException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;
import static org.powermock.api.mockito.PowerMockito.mockStatic;

@RunWith(PowerMockRunner.class)
@PrepareForTest(Context.class)
public class MuzimaFormMetadataResourceTest {
    private MuzimaFormService service;
    MuzimaFormMetadataResource controller;

    @Before
    public void setUp() throws Exception {

        MuzimaForm form = getForm("foo");
        service = mock(MuzimaFormService.class);
        when(service.findByUniqueId("foo")).thenReturn(form);
        controller = new MuzimaFormMetadataResource();
        mockStatic(Context.class);
        PowerMockito.when(Context.getService(MuzimaFormService.class)).thenReturn(service);
    }

    private MuzimaForm getForm(String uuid) {
        MuzimaForm muzimaForm = new MuzimaForm();
        muzimaForm.setId(uuid.hashCode());
        muzimaForm.setUuid(uuid);
        return muzimaForm;
    }


    private MuzimaFormMetadata getFormMetadata(String uuid) {
        return new MuzimaFormMetadata(getForm(uuid));
    }

    @Test(expected = ResourceDoesNotSupportOperationException.class)
    public void getByUniqueId_shouldGetMuzimaFormByUUID() {
        controller.getByUniqueId("foo");
    }

    @Test(expected = ResourceDoesNotSupportOperationException.class)
    public void delete_notSupported() {
        controller.delete(getFormMetadata(""), "", null);
    }

    @Test(expected = ResourceDoesNotSupportOperationException.class)
    public void save_shouldDelegateToService() throws SAXException, DocumentException, TransformerException, IOException, XPathExpressionException, ParserConfigurationException {
        MuzimaFormMetadata form = getFormMetadata("");
        controller.save(form);
    }

    @Test
    public void getAll_shouldGetAllForms() {
        RequestContext context = mock(RequestContext.class);
        when(service.getAll()).thenReturn(getMuzimaForms());
        SimpleObject response = controller.getAll(context);
        assertThat(response.containsKey("forms"), is(true));
        List<MuzimaFormMetadata> forms = (List<MuzimaFormMetadata>) response.get("forms");
        assertThat(forms.get(0).getUuid(), is("foo"));
        assertThat(forms.size(), is(3));
        verify(service, times(1)).getAll();
    }

    private ArrayList<MuzimaForm> getMuzimaForms() {
        ArrayList<MuzimaForm> muzimaForms = new ArrayList<MuzimaForm>();
        muzimaForms.add(getForm("foo"));
        muzimaForms.add(getForm("bar"));
        muzimaForms.add(getForm("baz"));
        return muzimaForms;
    }

}
