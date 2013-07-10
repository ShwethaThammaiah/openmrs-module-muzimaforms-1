package org.openmrs.module.muzimaforms.api;

import org.dom4j.DocumentException;
import org.junit.Before;
import org.junit.Test;
import org.openmrs.module.muzimaforms.MuzimaForm;
import org.openmrs.module.muzimaforms.MuzimaXForm;
import org.openmrs.module.muzimaforms.XFormBuilder;
import org.openmrs.module.muzimaforms.api.db.hibernate.MuzimaFormDAO;
import org.openmrs.module.muzimaforms.api.impl.CompositeEnketoResult;
import org.openmrs.module.muzimaforms.api.impl.EnketoResult;
import org.openmrs.module.muzimaforms.api.impl.MuzimaFormServiceImpl;
import org.openmrs.module.muzimaforms.xForm2MuzimaTransform.ModelXml2JsonTransformer;
import org.openmrs.module.muzimaforms.xForm2MuzimaTransform.XForm2Html5Transformer;
import org.openmrs.test.BaseModuleContextSensitiveTest;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.xpath.XPathExpressionException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;
import static org.openmrs.module.muzimaforms.FormBuilder.form;
import static org.openmrs.module.muzimaforms.MuzimaFormBuilder.html5Form;
import static org.openmrs.module.muzimaforms.MuzimaFormTagBuilder.tag;
import static org.openmrs.module.muzimaforms.XFormBuilder.xForm;

public class MuzimaFormServiceTest extends BaseModuleContextSensitiveTest {

    private MuzimaFormService service;
    MuzimaFormDAO dao;
    XForm2Html5Transformer transformer;
    ModelXml2JsonTransformer modelTransformer;

    @Before
    public void setUp() throws Exception {
        dao = mock(MuzimaFormDAO.class);
        transformer = mock(XForm2Html5Transformer.class);
        modelTransformer = mock(ModelXml2JsonTransformer.class);
        service = new MuzimaFormServiceImpl(dao, transformer,modelTransformer);
    }

    void setUpDao() {
        List<MuzimaForm> html5Forms = new ArrayList<MuzimaForm>();
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
        List<MuzimaForm> list = service.getAll();
        assertThat(list.size(), is(3));
        verify(dao, times(1)).getAll();
    }

    @Test
    public void getXform_shouldLoadXForm() throws Exception {
        List<MuzimaXForm> xform = service.getXForms();
        verify(dao, times(1)).getXForms();
    }

    @Test
    public void saveForm_shouldSave() throws IOException, TransformerException, XPathExpressionException, ParserConfigurationException, SAXException, DocumentException {
        String xFormXml = "<xml><some/><valid/></xml>";
        String htmlForm = "<foo><form><ul><li/><li/></ul></form><model/></foo>";
        String modelJson = "{form : [{name:'', bind: ''}]}";
        XFormBuilder xFormBuilder = xForm().withId(1).withXFormXml(xFormXml);
        MuzimaForm form = html5Form().withId(1).with(tag().withId(1).withName("Registration")).with(xFormBuilder).instance();
        when(dao.findById(1)).thenReturn(form);
        when(dao.getXform(1)).thenReturn(form.getXform());
        when(transformer.transform(xFormXml)).thenReturn(new EnketoResult(htmlForm));
        when(modelTransformer.transform(htmlForm)).thenReturn(new CompositeEnketoResult(htmlForm, modelJson));
        service.saveForm(form);
        verify(dao, times(1)).saveForm(any(MuzimaForm.class));
        verify(dao, times(1)).getXform(1);
    }

    @Test
    public void saveForm_shouldSetConvertedXform() throws IOException, TransformerException, XPathExpressionException, ParserConfigurationException, SAXException, DocumentException {

        String htmlForm = "<foo><form><ul><li/><li/></ul></form><model/></foo>";
        String xFormXml = "<foo><some/><valid/></foo>";
        String modelJson = "{form : [{name:'', bind: ''}]}";

        XFormBuilder xFormBuilder = xForm().withId(1).withXFormXml(xFormXml);
        MuzimaForm form = html5Form().withId(1).with(tag().withName("New Tag")).with(tag().withName("Another Tag")).with(xFormBuilder)
                .instance();
        when(transformer.transform(xFormXml)).thenReturn(new EnketoResult(htmlForm));
        when(modelTransformer.transform(htmlForm)).thenReturn(new CompositeEnketoResult(htmlForm, modelJson));
        when(dao.getXform(1)).thenReturn(form.getXform());
        when(dao.findById(1)).thenReturn(form);
        service.saveForm(form);
        verify(dao, times(1)).saveForm(any(MuzimaForm.class));
        verify(transformer, times(1)).transform(xFormXml);
        verify(modelTransformer, times(1)).transform(htmlForm);
        verify(dao, times(1)).getXform(1);


    }

    @Test
    public void findById_shouldFindFormById() {
        MuzimaForm form = service.findById(1);
        verify(dao, times(1)).findById(1);
    }
}
