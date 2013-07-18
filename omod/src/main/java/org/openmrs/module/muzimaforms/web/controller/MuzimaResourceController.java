package org.openmrs.module.muzimaforms.web.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openmrs.module.muzimaforms.MuzimaConstants;
import org.openmrs.module.webservices.rest.web.v1_0.controller.MainResourceController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(MuzimaConstants.BASE_REQUEST_MAPPING)
public class MuzimaResourceController extends MainResourceController {
    private static final Log log = LogFactory.getLog(MuzimaResourceController.class);

    @Override
    public String getNamespace() {
        return MuzimaConstants.MUZIMA_NAMESPACE;
    }
}
