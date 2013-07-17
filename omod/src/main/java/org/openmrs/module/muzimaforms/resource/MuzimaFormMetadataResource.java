package org.openmrs.module.muzimaforms.resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openmrs.annotation.Handler;
import org.openmrs.api.context.Context;
import org.openmrs.module.muzimaforms.MuzimaConstants;
import org.openmrs.module.muzimaforms.MuzimaForm;
import org.openmrs.module.muzimaforms.api.MuzimaFormService;
import org.openmrs.module.muzimaforms.web.controller.MuzimaFormMetadata;
import org.openmrs.module.muzimaforms.web.controller.MuzimaFormMetadataView;
import org.openmrs.module.webservices.rest.SimpleObject;
import org.openmrs.module.webservices.rest.web.RequestContext;
import org.openmrs.module.webservices.rest.web.RestConstants;
import org.openmrs.module.webservices.rest.web.annotation.Resource;
import org.openmrs.module.webservices.rest.web.representation.Representation;
import org.openmrs.module.webservices.rest.web.resource.impl.DataDelegatingCrudResource;
import org.openmrs.module.webservices.rest.web.resource.impl.DelegatingResourceDescription;
import org.openmrs.module.webservices.rest.web.response.ResourceDoesNotSupportOperationException;
import org.openmrs.module.webservices.rest.web.response.ResponseException;

import java.util.List;

@Resource(name = RestConstants.VERSION_1 + "/" + MuzimaConstants.MODULE_ID + "/formdata", supportedClass = MuzimaForm.class, supportedOpenmrsVersions = {"1.8.*", "1.9.*"})
@Handler(supports = MuzimaForm.class)
public class MuzimaFormMetadataResource extends DataDelegatingCrudResource<MuzimaFormMetadata> {
    private static final Log log = LogFactory.getLog(MuzimaFormMetadataResource.class);

    @Override
    public SimpleObject getAll(RequestContext context) throws ResponseException {
        SimpleObject response = new SimpleObject();
        List<MuzimaFormMetadata> formsMetadata = new MuzimaFormMetadataView().load(Context.getService(MuzimaFormService.class).getAll());
        response.add("forms", formsMetadata);
        return response;
    }

    @Override
    public MuzimaFormMetadata getByUniqueId(String uuid) {
        throw new ResourceDoesNotSupportOperationException();
    }

    @Override
    protected void delete(MuzimaFormMetadata muzimaFormMetadata, String s, RequestContext requestContext) throws ResponseException {
        throw new ResourceDoesNotSupportOperationException();

    }

    public MuzimaFormMetadata newDelegate() {
        throw new ResourceDoesNotSupportOperationException();
    }

    public MuzimaFormMetadata save(MuzimaFormMetadata muzimaFormMetadata) {
        throw new ResourceDoesNotSupportOperationException();
    }

    @Override
    public void purge(MuzimaFormMetadata muzimaFormMetadata, RequestContext requestContext) throws ResponseException {
        throw new ResourceDoesNotSupportOperationException();
    }


    public DelegatingResourceDescription getRepresentationDescription(Representation representation) {
        return null;
    }

}
