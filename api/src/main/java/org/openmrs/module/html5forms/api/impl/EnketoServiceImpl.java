package org.openmrs.module.html5forms.api.impl;

import org.openmrs.api.impl.BaseOpenmrsService;
import org.openmrs.module.html5forms.api.EnketoService;
import org.openmrs.module.html5forms.enketo.EnketoClient;

import java.io.IOException;
import java.net.MalformedURLException;

public class EnketoServiceImpl extends BaseOpenmrsService implements EnketoService {
    private EnketoClient enketoClient;

    public EnketoServiceImpl(EnketoClient enketoClient) {
        this.enketoClient = enketoClient;
    }

    public String transform(String xFormXml) throws IOException {
        return enketoClient.transform(xFormXml);
    }
}
