package org.openmrs.module.muzimaforms.xForm2MuzimaTransform;

import org.dom4j.DocumentException;
import org.openmrs.module.muzimaforms.api.impl.CompositeEnketoResult;
import org.openmrs.module.muzimaforms.api.impl.EnketoResult;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.sax.SAXResult;
import javax.xml.transform.sax.SAXTransformerFactory;
import javax.xml.transform.sax.TransformerHandler;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.util.Stack;

public class ModelXml2JsonTransformer extends XForm2Html5Transformer {

    private SAXTransformerFactory transformerFactory;
    private XslTransformPipeline jsonTransformers;

    public ModelXml2JsonTransformer(TransformerFactory transformerFactory, XslTransformPipeline jsonTransformers) {

        this.transformerFactory = (SAXTransformerFactory) transformerFactory;
        this.jsonTransformers = jsonTransformers;
    }

    @Override
    public EnketoResult transform(String xformXml) throws IOException, TransformerException, ParserConfigurationException, DocumentException {

        Stack<File> transforms = jsonTransformers.get();
        if (transforms.isEmpty()) return new EnketoResult("");

        StringWriter writer = new StringWriter();

        Result intermediateResult = new StreamResult(writer);
        while (!transforms.isEmpty()) {
            Templates templates = transformerFactory.newTemplates(new StreamSource(transforms.pop()));
            TransformerHandler transformerHandler = transformerFactory.newTransformerHandler(templates);
            transformerHandler.setResult(intermediateResult);
            intermediateResult = new SAXResult(transformerHandler);
        }

        File inputFile = createTempFile(new EnketoResult(xformXml).getModel());
        Transformer transformer = transformerFactory.newTransformer();
        try {
            transformer.transform(new StreamSource(inputFile), intermediateResult);
        } finally {
            inputFile.delete();
        }
        return new CompositeEnketoResult(xformXml, writer.getBuffer().toString());
    }
}
