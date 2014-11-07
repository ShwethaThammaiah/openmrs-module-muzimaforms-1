package org.openmrs.module.muzimaforms.api.impl;

import org.dom4j.DocumentException;
import org.javarosa.xform.parse.ValidationMessages;
import org.javarosa.xform.parse.XFormParser;
import org.openmrs.api.context.Context;
import org.openmrs.api.impl.BaseOpenmrsService;
import org.openmrs.module.muzimaforms.MuzimaForm;
import org.openmrs.module.muzimaforms.MuzimaXForm;
import org.openmrs.module.muzimaforms.api.MuzimaFormService;
import org.openmrs.module.muzimaforms.api.db.hibernate.MuzimaFormDAO;
import org.openmrs.module.muzimaforms.xForm2MuzimaTransform.ModelXml2JsonTransformer;
import org.openmrs.module.muzimaforms.xForm2MuzimaTransform.ODK2HTML5Transformer;
import org.openmrs.module.muzimaforms.xForm2MuzimaTransform.ODK2JavarosaTransformer;
import org.openmrs.module.muzimaforms.xForm2MuzimaTransform.XForm2Html5Transformer;
import org.openmrs.module.xforms.Xform;

import java.io.StringReader;
import java.util.Date;
import java.util.List;

public class MuzimaFormServiceImpl extends BaseOpenmrsService implements MuzimaFormService {
    private XForm2Html5Transformer html5Transformer;
    private ModelXml2JsonTransformer modelXml2JsonTransformer;
    private ODK2JavarosaTransformer odk2JavarosaTransformer;
    private ODK2HTML5Transformer odk2HTML5Transformer;
    private MuzimaFormDAO dao;

    public MuzimaFormServiceImpl(MuzimaFormDAO dao, XForm2Html5Transformer html5Transformer,
                                 ModelXml2JsonTransformer modelXml2JsonTransformer,
                                 ODK2JavarosaTransformer odk2JavarosaTransformer, ODK2HTML5Transformer odk2HTML5Transformer) {
        this.dao = dao;
        this.html5Transformer = html5Transformer;
        this.modelXml2JsonTransformer = modelXml2JsonTransformer;
        this.odk2JavarosaTransformer = odk2JavarosaTransformer;
        this.odk2HTML5Transformer = odk2HTML5Transformer;
    }

    public List<MuzimaForm> getAll() {
        return dao.getAll();
    }

    //TODO: Handle records which do not have a form in the forms table.
    public List<MuzimaXForm> getXForms() {
        return dao.getXForms();
    }

    public MuzimaForm importExisting(Integer xFormId, String name, String form, String description, String discriminator, String version) throws Exception {
        Xform xform = dao.getXform(xFormId);
        return create(xform.getXformXml(), name, form, description, discriminator, version);
    }

    public MuzimaForm create(String xformXml, String name, String form, String description, String discriminator, String version) throws Exception {
        if (!isFormDefinitionExists(form)) {
            CompositeEnketoResult result = (CompositeEnketoResult) modelXml2JsonTransformer.
                    transform(html5Transformer.transform(xformXml).getResult());

            return save(new MuzimaForm(form, discriminator, result.getForm(), result.getModel(), result.getModelAsJson(), Context.getFormService().getFormByUuid(form)));
        }
        throw new DocumentException("The file name already Exists !");
    }

    public MuzimaForm update(String xformXml, String name, String form) throws Exception {
        if (isFormDefinitionExists(form)) {
            CompositeEnketoResult result = (CompositeEnketoResult) modelXml2JsonTransformer.
                    transform(html5Transformer.transform(xformXml).getResult());
            MuzimaForm retrievedForm = dao.findByUuid(form);
            if(retrievedForm != null){
                retrievedForm.setForm(result.getForm());
                retrievedForm.setModel(result.getModel());
                retrievedForm.setModelJson(result.getModelAsJson());
            }
            return save(retrievedForm);
        }else{
            throw new DocumentException("Unable to update form with name !" + name);
        }

    }

//    private boolean isFormNameExists(String name) {
//        List<MuzimaForm> formsWithSimilarNames = dao.findByName(name, null);
//        for (MuzimaForm form : formsWithSimilarNames) {
//            if (form.getName().equals(name) && !form.isVoided()) {
//                return true;
//            }
//        }
//        return false;
//    }

    private boolean isFormDefinitionExists(String formUUID) {
        List<MuzimaForm> formsWithUUID = dao.findByForm(formUUID);
        for (MuzimaForm form : formsWithUUID) {
            if (form.getForm().equals(formUUID) && !form.isVoided()) {
                return true;
            }
        }
        return false;
    }

    public MuzimaForm importODK(String xformXml, String name, String form, String description, String discriminator, String version) throws Exception {
        if (!isFormDefinitionExists(form)) {
            CompositeEnketoResult result = (CompositeEnketoResult) modelXml2JsonTransformer.
                    transform(odk2HTML5Transformer.transform(xformXml).getResult());
            return save(new MuzimaForm(form, discriminator, result.getForm(), result.getModel(), result.getModelAsJson(), Context.getFormService().getFormByUuid(form)));
        }
        throw new DocumentException("The file name already Exists !");
    }

    public MuzimaForm createHTMLForm(String html, String name, String form, String description, String discriminator, String version) throws Exception {
        if (!isFormDefinitionExists(form)) {
            return save(new MuzimaForm(form, discriminator, html, null, null, Context.getFormService().getFormByUuid(form)));
        }
        throw new DocumentException("The file name already Exists !");
    }

    public MuzimaForm updateHTMLForm(String html, String name, String formUUID) throws Exception {

            MuzimaForm retrievedForm = dao.findByUuid(formUUID);
            if (retrievedForm != null) {
                retrievedForm.setHtml(html);
                return save(retrievedForm);
            }
        throw  new DocumentException("Unable to update form with name !" + name);
    }

    public MuzimaForm save(MuzimaForm form) throws Exception {
        if(form.getFormDefinition() == null)
            form.setFormDefinition(Context.getFormService().getFormByUuid(form.getForm()));
        dao.saveForm(form);
        return form;
    }

    public ValidationMessages validateJavaRosa(String xml) {
        return new XFormParser(new StringReader(xml)).validate();
    }

    public ValidationMessages validateODK(String xml) throws Exception {
        String result = odk2JavarosaTransformer.transform(xml).getResult();
        return new XFormParser(new StringReader(result)).validate();
    }

    public MuzimaForm findById(Integer id) {
        return dao.findById(id);
    }

    public MuzimaForm findByUniqueId(String uuid) {
        return dao.findByUuid(uuid);  //To change body of implemented methods use File | Settings | File Templates.
    }

    public List<MuzimaForm> findByName(final String name, final Date syncDate) {
        return dao.findByName(name, syncDate);
    }
}
