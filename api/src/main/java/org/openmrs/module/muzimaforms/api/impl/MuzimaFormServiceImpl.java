package org.openmrs.module.muzimaforms.api.impl;

import org.dom4j.DocumentException;
import org.javarosa.xform.parse.ValidationMessages;
import org.javarosa.xform.parse.XFormParser;
import org.openmrs.api.impl.BaseOpenmrsService;
import org.openmrs.module.muzimaforms.MuzimaForm;
import org.openmrs.module.muzimaforms.MuzimaXForm;
import org.openmrs.module.muzimaforms.api.MuzimaFormService;
import org.openmrs.module.muzimaforms.api.db.hibernate.MuzimaFormDAO;
import org.openmrs.module.muzimaforms.api.db.hibernate.impl.MuzimaFormDAOImpl;
import org.openmrs.module.muzimaforms.xForm2MuzimaTransform.*;
import org.openmrs.module.xforms.Xform;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.IOException;
import java.io.StringReader;
import java.util.List;

public class MuzimaFormServiceImpl extends BaseOpenmrsService implements MuzimaFormService {
    private XForm2Html5Transformer html5Transformer;
    private ModelXml2JsonTransformer modelXml2JsonTransformer;
    private ODK2JavaRosaTransformer odk2JavaRosaTransformer;
    private ODK2HTML5Transformer odk2HTML5Transformer;
    private MuzimaFormDAO dao;

    public MuzimaFormServiceImpl(MuzimaFormDAO dao, XForm2Html5Transformer html5Transformer,
                                 ModelXml2JsonTransformer modelXml2JsonTransformer,
                                 ODK2JavaRosaTransformer odk2JavaRosaTransformer, ODK2HTML5Transformer odk2HTML5Transformer) {
        this.dao = dao;
        this.html5Transformer = html5Transformer;
        this.modelXml2JsonTransformer = modelXml2JsonTransformer;
        this.odk2JavaRosaTransformer = odk2JavaRosaTransformer;
        this.odk2HTML5Transformer = odk2HTML5Transformer;
    }

    public List<MuzimaForm> getAll() {
        return dao.getAll();
    }

    //TODO: Handle records which do not have a form in the forms table.
    public List<MuzimaXForm> getXForms() {
        return dao.getXForms();
    }

    public MuzimaForm importExisting(Integer xFormId, String name, String description) throws DocumentException, ParserConfigurationException, TransformerException, IOException {
        Xform xform = dao.getXform(xFormId);
        return create(xform.getXformXml(), description, name);
    }

    public MuzimaForm create(String xformXml, String description, String name) throws IOException, TransformerException, ParserConfigurationException, DocumentException {
        CompositeEnketoResult result = (CompositeEnketoResult) modelXml2JsonTransformer.
                transform(html5Transformer.transform(xformXml).getResult());
        return save(new MuzimaForm(name, description, result.getModel(), result.getForm(), result.getModelAsJson()));
    }

    public MuzimaForm importODK(String xformXml, String description, String name) throws IOException, TransformerException, ParserConfigurationException, DocumentException {
        CompositeEnketoResult result = (CompositeEnketoResult) modelXml2JsonTransformer.
                transform(odk2HTML5Transformer.transform(xformXml).getResult());
        return save(new MuzimaForm(name, description, result.getModel(), result.getForm(), result.getModelAsJson()));
    }

    public MuzimaForm save(MuzimaForm form) throws IOException, TransformerException, ParserConfigurationException, DocumentException {
        dao.saveForm(form);
        return form;
    }

    public ValidationMessages validateJavaRosa(String xml) {
        return new XFormParser(new StringReader(xml)).validate();
    }

    public ValidationMessages validateODK(String xml) throws ParserConfigurationException, TransformerException, DocumentException, IOException {
        String result = odk2JavaRosaTransformer.transform(xml).getResult();
        return new XFormParser(new StringReader(result)).validate();
    }

    public MuzimaForm findById(Integer id) {
        return dao.findById(id);
    }

    public MuzimaForm findByUniqueId(String uuid) {
        return dao.findByUuid(uuid);  //To change body of implemented methods use File | Settings | File Templates.
    }

    public List<MuzimaForm> findByName(final String name) {
        return dao.findByName(name);
    }
}
