package org.openmrs.module.html5forms.api;

import org.openmrs.api.OpenmrsService;
import org.openmrs.module.html5forms.HTML5Form;
import org.openmrs.module.html5forms.HTML5XForm;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;

public interface EnketoService extends OpenmrsService {
      String transform(String xFormXml) throws IOException;
}
