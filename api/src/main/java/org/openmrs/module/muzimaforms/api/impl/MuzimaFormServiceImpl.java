package org.openmrs.module.muzimaforms.api.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openmrs.api.impl.BaseOpenmrsService;
import org.openmrs.module.muzimaforms.MuzimaForm;
import org.openmrs.module.muzimaforms.MuzimaXForm;
import org.openmrs.module.muzimaforms.api.MuzimaFormService;
import org.openmrs.module.muzimaforms.api.db.hibernate.MuzimaFormDAO;
import org.openmrs.module.muzimaforms.xForm2MuzimaTransform.ModelXml2JsonTransformer;
import org.openmrs.module.muzimaforms.xForm2MuzimaTransform.XForm2Html5Transformer;
import org.openmrs.module.xforms.Xform;

import java.util.List;

public class MuzimaFormServiceImpl extends BaseOpenmrsService implements MuzimaFormService {
    private XForm2Html5Transformer html5Transformer;
    private ModelXml2JsonTransformer modelXml2JsonTransformer;
    private MuzimaFormDAO dao;
    private static final Log log = LogFactory.getLog(MuzimaFormServiceImpl.class);

    public MuzimaFormServiceImpl(MuzimaFormDAO dao, XForm2Html5Transformer html5Transformer, ModelXml2JsonTransformer modelXml2JsonTransformer) {
        this.dao = dao;
        this.html5Transformer = html5Transformer;
        this.modelXml2JsonTransformer = modelXml2JsonTransformer;
    }

    public List<MuzimaForm> getAll() {
        return dao.getAll();
    }

    //TODO: Handle records which do not have a form in the forms table.
    public List<MuzimaXForm> getXForms() {
        return dao.getXForms();
    }

    public MuzimaForm saveForm(MuzimaForm form) {
        Xform xform = dao.getXform(form.getId());
        String xformXml = xform.getXformXml();
        try {
            log.info("xform --> ");
            log.info(xformXml);
            CompositeEnketoResult result = (CompositeEnketoResult) modelXml2JsonTransformer.
                    transform(html5Transformer.transform(xformXml).getResult());
            log.info("converted -->");
            log.info(result.getResult());
            form.setHtml(result.getForm());
            form.setModel(result.getModel());
            form.setModelJson(result.getModelAsJson());
            dao.saveForm(form);
            return form;
        } catch (Exception e) {
            log.error(e);
            log.error("Possible XForm to HTML5 transformation failure.", e);
        }
        return null;
    }

    public MuzimaForm findById(Integer id) {
        return dao.findById(id);
    }

    public MuzimaForm findByUniqueId(String uuid) {
        return dao.findByUuid(uuid);  //To change body of implemented methods use File | Settings | File Templates.
    }

}
