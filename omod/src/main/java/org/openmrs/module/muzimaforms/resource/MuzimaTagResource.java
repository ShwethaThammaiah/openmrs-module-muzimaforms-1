package org.openmrs.module.muzimaforms.resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openmrs.annotation.Handler;
import org.openmrs.api.context.Context;
import org.openmrs.module.muzimaforms.MuzimaConstants;
import org.openmrs.module.muzimaforms.MuzimaFormTag;
import org.openmrs.module.muzimaforms.api.MuzimaTagService;
import org.openmrs.module.webservices.rest.SimpleObject;
import org.openmrs.module.webservices.rest.web.RequestContext;
import org.openmrs.module.webservices.rest.web.RestConstants;
import org.openmrs.module.webservices.rest.web.annotation.Resource;
import org.openmrs.module.webservices.rest.web.representation.DefaultRepresentation;
import org.openmrs.module.webservices.rest.web.representation.RefRepresentation;
import org.openmrs.module.webservices.rest.web.representation.Representation;
import org.openmrs.module.webservices.rest.web.resource.impl.DataDelegatingCrudResource;
import org.openmrs.module.webservices.rest.web.resource.impl.DelegatingResourceDescription;
import org.openmrs.module.webservices.rest.web.response.ResourceDoesNotSupportOperationException;
import org.openmrs.module.webservices.rest.web.response.ResponseException;

import java.util.ArrayList;
import java.util.List;

@Resource(name = RestConstants.VERSION_1 + "/" + MuzimaConstants.MODULE_ID + "/tag", supportedClass = MuzimaFormTag.class, supportedOpenmrsVersions = {"1.8.*", "1.9.*"})
@Handler(supports = MuzimaFormTag.class)
public class MuzimaTagResource extends DataDelegatingCrudResource<MuzimaFormTag> {
    private static final Log log = LogFactory.getLog(MuzimaTagResource.class);

    @Override
    public SimpleObject getAll(RequestContext context) throws ResponseException {
        SimpleObject response = new SimpleObject();
        MuzimaTagService service = Context.getService(MuzimaTagService.class);
        List<SimpleObject> tags = new ArrayList<SimpleObject>();
        for (MuzimaFormTag tag : service.getAll()) {
            tags.add(asRepresentation(tag, context.getRepresentation()));
        }
        response.add("tags", tags);
        return response;
    }

    public DelegatingResourceDescription getRepresentationDescription(Representation rep) {
        DelegatingResourceDescription description = null;
        if (rep instanceof RefRepresentation || rep instanceof DefaultRepresentation) {
            DelegatingResourceDescription description1 = new DelegatingResourceDescription();
            description1.addProperty("uuid");
            description1.addProperty("id");
            description1.addProperty("name");
            description = description1;
        }
        return description;
    }

    @Override
    public MuzimaFormTag getByUniqueId(String s) {
        throw new ResourceDoesNotSupportOperationException();
    }

    @Override
    protected void delete(MuzimaFormTag muzimaFormTag, String s, RequestContext requestContext) throws ResponseException {
        throw new ResourceDoesNotSupportOperationException();
    }

    @Override
    public void purge(MuzimaFormTag muzimaFormTag, RequestContext requestContext) throws ResponseException {
        throw new ResourceDoesNotSupportOperationException();
    }


    public MuzimaFormTag newDelegate() {
        return new MuzimaFormTag();  //To change body of implemented methods use File | Settings | File Templates.
    }

    public MuzimaFormTag save(MuzimaFormTag muzimaFormTag) {
        throw new ResourceDoesNotSupportOperationException();
    }


}
