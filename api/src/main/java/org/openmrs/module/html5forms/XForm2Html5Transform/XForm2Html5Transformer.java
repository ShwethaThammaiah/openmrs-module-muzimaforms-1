package org.openmrs.module.html5forms.xForm2Html5Transform;

import org.apache.commons.io.FileUtils;
import org.openmrs.module.html5forms.api.impl.EnketoResult;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

public abstract class XForm2Html5Transformer {
    public abstract EnketoResult transform(String xformXml) throws IOException, TransformerException, ParserConfigurationException;

    protected File createTempFile(String xformXml) throws IOException {
        UUID uuid = UUID.randomUUID();
        File file = new File(uuid + ".xml");
        FileUtils.writeStringToFile(file, xformXml);
        return file;
    }
}
