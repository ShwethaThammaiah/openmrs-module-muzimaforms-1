package org.openmrs.module.html5forms.xForm2Html5Transform;

import org.apache.commons.io.IOUtils;
import org.dom4j.DocumentException;
import org.junit.Test;
import org.openmrs.module.html5forms.api.impl.CompositeEnketoResult;
import org.openmrs.module.html5forms.api.impl.EnketoResult;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import java.io.File;
import java.io.IOException;

import static org.openmrs.module.html5forms.xForm2Html5Transform.XslTransformPipeline.xslTransformPipeline;

public class ModelXml2JsonTransformerTest {
    @Test
    public void transform_shouldGetModelAndTransformToJson() throws IOException, DocumentException, TransformerException, ParserConfigurationException {
        String enketoSampleResult = getEnketoSampleResult().getResult();
        ModelXml2JsonTransformer resultXmlToJsonTransformer = getResultXmlToJsonTransformer();
        CompositeEnketoResult result = (CompositeEnketoResult) resultXmlToJsonTransformer.transform(enketoSampleResult);
        System.out.println(result.getModelAsJson());
    }

    @Test
    public void transform_anotherSample_shouldGetModelAndTransformToJson() throws IOException, DocumentException, TransformerException, ParserConfigurationException {

        XslTransformPipeline transformers = xslTransformPipeline()
                .push(getXform2JRTransformer())
                .push(getHtml5Transformer());
        EnketoXslTransformer enketoXslTransformer = new EnketoXslTransformer(TransformerFactory.newInstance(), transformers);
        EnketoResult transform = enketoXslTransformer.transform(getSampleXForm());

        ModelXml2JsonTransformer resultXmlToJsonTransformer = getResultXmlToJsonTransformer();
        CompositeEnketoResult result = (CompositeEnketoResult) resultXmlToJsonTransformer.transform(transform.getResult());
        System.out.println(result.getModelAsJson());
    }



    private File getXform2JRTransformer() throws IOException {
        ApplicationContext context = new ClassPathXmlApplicationContext();
        return context.getResource("/xform2jr.xsl").getFile();
    }

    private File getHtml5Transformer() throws IOException {
        ApplicationContext context = new ClassPathXmlApplicationContext();
        return context.getResource("/jr2html5_php5.xsl").getFile();
    }

    private String getSampleXForm() throws IOException {
        ApplicationContext context = new ClassPathXmlApplicationContext();
        return IOUtils.toString(context.getResource("/test-xform.xml").getInputStream());
    }

    private ModelXml2JsonTransformer getResultXmlToJsonTransformer() throws IOException {
        XslTransformPipeline jsonTransformers = xslTransformPipeline()
                .push(getEnketoResult2ModelTransformer())
                .push(getXml2JsonTransformer());
        return new ModelXml2JsonTransformer(TransformerFactory.newInstance(), jsonTransformers);
    }


    private EnketoResult getEnketoSampleResult() throws IOException, ParserConfigurationException {
        ApplicationContext context = new ClassPathXmlApplicationContext();
        String result = IOUtils.toString(context.getResource("/enketoSampleResult.xml").getInputStream());
        return new EnketoResult(result);
    }

    private File getEnketoResult2ModelTransformer() throws IOException {
        ApplicationContext context = new ClassPathXmlApplicationContext();
        return context.getResource("/enketoResult2model.xsl").getFile();
    }

    private File getXml2JsonTransformer() throws IOException {
        ApplicationContext context = new ClassPathXmlApplicationContext();
        return context.getResource("/xml2json.xsl").getFile();
    }
}
