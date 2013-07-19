package org.openmrs.module.muzimaforms.resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openmrs.annotation.Handler;
import org.openmrs.api.context.Context;
import org.openmrs.module.muzimaforms.MuzimaConstants;
import org.openmrs.module.muzimaforms.MuzimaForm;
import org.openmrs.module.muzimaforms.api.MuzimaFormService;
import org.openmrs.module.webservices.rest.web.RequestContext;
import org.openmrs.module.webservices.rest.web.RestConstants;
import org.openmrs.module.webservices.rest.web.annotation.Resource;
import org.openmrs.module.webservices.rest.web.representation.CustomRepresentation;
import org.openmrs.module.webservices.rest.web.representation.DefaultRepresentation;
import org.openmrs.module.webservices.rest.web.representation.RefRepresentation;
import org.openmrs.module.webservices.rest.web.representation.Representation;
import org.openmrs.module.webservices.rest.web.resource.impl.DataDelegatingCrudResource;
import org.openmrs.module.webservices.rest.web.resource.impl.DelegatingResourceDescription;
import org.openmrs.module.webservices.rest.web.resource.impl.NeedsPaging;
import org.openmrs.module.webservices.rest.web.response.ResourceDoesNotSupportOperationException;
import org.openmrs.module.webservices.rest.web.response.ResponseException;

import java.util.List;

@Resource(name = RestConstants.VERSION_1 + "/" + MuzimaConstants.MODULE_ID + "/form", supportedClass = MuzimaForm.class, supportedOpenmrsVersions = {"1.8.*", "1.9.*"})
@Handler(supports = MuzimaForm.class)
public class MuzimaFormResource extends DataDelegatingCrudResource<MuzimaForm> {
    private static final Log log = LogFactory.getLog(MuzimaFormResource.class);

    @Override
    protected NeedsPaging<MuzimaForm> doGetAll(RequestContext context) throws ResponseException {
        MuzimaFormService service = Context.getService(MuzimaFormService.class);
        List<MuzimaForm> all = service.getAll();
        return new NeedsPaging<MuzimaForm>(all, context);
    }

    @Override
    public MuzimaForm getByUniqueId(String uuid) {
        MuzimaFormService service = Context.getService(MuzimaFormService.class);
        return service.findByUniqueId(uuid);
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

    public DelegatingResourceDescription getRepresentationDescription(Representation rep) {
        DelegatingResourceDescription description = null;

        if (rep instanceof DefaultRepresentation || rep instanceof RefRepresentation) {
            DelegatingResourceDescription description1 = new DelegatingResourceDescription();
            description1.addProperty("uuid");
            description1.addProperty("id");
            description1.addProperty("name");
            description1.addProperty("description");
            description1.addProperty("tags", new CustomRepresentation("(id,uuid,name)"));
            description1.addSelfLink();
            description = description1;
        }

        return description;
    }
}
