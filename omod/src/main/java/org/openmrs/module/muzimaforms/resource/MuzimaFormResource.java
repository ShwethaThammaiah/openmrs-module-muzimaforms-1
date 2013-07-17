package org.openmrs.module.muzimaforms.resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openmrs.annotation.Handler;
import org.openmrs.api.context.Context;
import org.openmrs.module.muzimaforms.MuzimaConstants;
import org.openmrs.module.muzimaforms.MuzimaForm;
import org.openmrs.module.muzimaforms.api.MuzimaFormService;
import org.openmrs.module.webservices.rest.SimpleObject;
import org.openmrs.module.webservices.rest.web.RequestContext;
import org.openmrs.module.webservices.rest.web.RestConstants;
import org.openmrs.module.webservices.rest.web.annotation.Resource;
import org.openmrs.module.webservices.rest.web.representation.Representation;
import org.openmrs.module.webservices.rest.web.resource.impl.DataDelegatingCrudResource;
import org.openmrs.module.webservices.rest.web.resource.impl.DelegatingResourceDescription;
import org.openmrs.module.webservices.rest.web.response.ResourceDoesNotSupportOperationException;
import org.openmrs.module.webservices.rest.web.response.ResponseException;

import java.util.ArrayList;
import java.util.List;

@Resource(name = RestConstants.VERSION_1 + "/" + MuzimaConstants.MODULE_ID + "/form", supportedClass = MuzimaForm.class, supportedOpenmrsVersions = {"1.8.*", "1.9.*"})
@Handler(supports = MuzimaForm.class)
public class MuzimaFormResource extends DataDelegatingCrudResource<MuzimaForm> {
    private static final Log log = LogFactory.getLog(MuzimaFormResource.class);

    @Override
    public SimpleObject getAll(RequestContext context) throws ResponseException {
        SimpleObject response = new SimpleObject();
        MuzimaFormService service = Context.getService(MuzimaFormService.class);
        List<SimpleObject> forms = new ArrayList<SimpleObject>();
        for (MuzimaForm muzimaForm : service.getAll()) {
            forms.add(asRepresentation(muzimaForm, context.getRepresentation()));
        }
        response.add("forms", forms);
        return response;
    }

    @Override
    public MuzimaForm getByUniqueId(String uuid) {
        return null;
    }

    @Override
    public Object retrieve(String uuid, RequestContext context) throws ResponseException {
        MuzimaFormService service = Context.getService(MuzimaFormService.class);
        return asRepresentation(service.findByUniqueId(uuid), context.getRepresentation());
    }

    @Override
    protected void delete(MuzimaForm muzimaForm, String s, RequestContext requestContext) throws ResponseException {
        throw new ResourceDoesNotSupportOperationException();
    }

    public MuzimaForm newDelegate() {
        return new MuzimaForm();
    }

    public MuzimaForm save(MuzimaForm muzimaForm) {
        MuzimaFormService service = Context.getService(MuzimaFormService.class);
        try {
            return service.saveForm(muzimaForm);
        } catch (Exception e) {
            log.error(e);
        }
        return null;
    }

    @Override
    public void purge(MuzimaForm muzimaForm, RequestContext requestContext) throws ResponseException {
        throw new ResourceDoesNotSupportOperationException();
    }

    public DelegatingResourceDescription getRepresentationDescription(Representation representation) {
        return null;
    }

}
