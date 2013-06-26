package org.openmrs.module.html5forms.api;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.openmrs.api.context.Context;
import org.openmrs.module.html5forms.HTML5Form;
import org.openmrs.module.html5forms.HTML5FormTag;
import org.openmrs.module.html5forms.HTML5XForm;
import org.openmrs.module.html5forms.XFormBuilder;
import org.openmrs.module.html5forms.api.db.hibernate.HTML5FormDAO;
import org.openmrs.module.html5forms.api.impl.HTML5FormServiceImpl;
import org.openmrs.test.BaseModuleContextSensitiveTest;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertThat;
import static org.junit.matchers.JUnitMatchers.hasItem;
import static org.mockito.Mockito.*;
import static org.openmrs.module.html5forms.FormBuilder.form;
import static org.openmrs.module.html5forms.HTML5FormBuilder.html5Form;
import static org.openmrs.module.html5forms.HTML5FormTagBuilder.tag;
import static org.openmrs.module.html5forms.XFormBuilder.xForm;

public class HTML5FormServiceTest extends BaseModuleContextSensitiveTest {

    private HTML5FormService service;
    HTML5FormDAO dao;
    EnketoService enketoService;

    @Before
    public void setUp() throws Exception {
        dao = mock(HTML5FormDAO.class);
        enketoService = mock(EnketoService.class);
        service = new HTML5FormServiceImpl(dao, enketoService);
    }

    void setUpDao() {
        List<HTML5Form> html5Forms = new ArrayList<HTML5Form>();
        html5Forms.add(
                html5Form().withId(1)
                        .with(tag().withId(1).withName("Registration"))
                        .with(tag().withId(2).withName("Patient"))
                        .with(xForm().withId(1))
                        .with(form().withId(1).withName("Registration Form").withDescription("Form for registration"))
                        .instance());
        html5Forms.add(html5Form().withId(2)
                .with(tag().withId(1).withName("Registration"))
                .with(tag().withId(3).withName("Encounter"))
                .with(tag().withId(4).withName("HIV"))
                .with(xForm().withId(2))
                .with(form().withId(2).withName("PMTCT Form").withDescription("Form for PMTCT"))
                .instance());

        html5Forms.add(html5Form().withId(3)
                .with(form().withId(3).withName("Ante-Natal Form").withDescription("Form for ante-natal care"))
                .instance());

        when(dao.getAll()).thenReturn(html5Forms);
    }

    @Test
    public void getAll_shouldGetAllForms() throws Exception {
        setUpDao();
        List<HTML5Form> list = service.getAll();
        assertThat(list.size(), is(3));
        verify(dao, times(1)).getAll();
    }

    @Test
    public void getXform_shouldLoadXForm() throws Exception {
        List<HTML5XForm> xform = service.getXForms();
        verify(dao, times(1)).getXForms();
    }

    @Test
    public void saveForm_shouldSave() throws IOException {
        String xFormXml = "<xml><some/><valid/><xml/></xml>";
        XFormBuilder xFormBuilder = xForm().withId(1).withXFormXml(xFormXml);
        HTML5Form form = html5Form().withId(1).with(tag().withId(1).withName("Registration")).with(xFormBuilder).instance();
        when(dao.findById(1)).thenReturn(form);
        when(dao.getXform(1)).thenReturn(form.getXform());
        service.saveForm(form);
        verify(dao, times(1)).saveForm(any(HTML5Form.class));
        verify(dao, times(1)).getXform(1);
    }

    @Test
    public void saveForm_shouldSetConvertedXform() throws IOException {

        String htmlForm = "<form><ul><li/><li/></ul></form";
        String xFormXml = "<xml><some/><valid/><xml/></xml>";

        XFormBuilder xFormBuilder = xForm().withId(1).withXFormXml(xFormXml);
        HTML5Form form = html5Form().withId(1).with(tag().withName("New Tag")).with(tag().withName("Another Tag")).with(xFormBuilder)
                .instance();
        when(enketoService.transform(xFormXml)).thenReturn(htmlForm);
        when(dao.getXform(1)).thenReturn(form.getXform());
        when(dao.findById(1)).thenReturn(form);
        service.saveForm(form);
        verify(dao, times(1)).saveForm(any(HTML5Form.class));
        verify(enketoService, times(1)).transform(xFormXml);
        verify(dao, times(1)).getXform(1);


    }

    @Test
    public void findById_shouldFindFormById() {
        HTML5Form form = service.findById(1);
        verify(dao, times(1)).findById(1);
    }
}
