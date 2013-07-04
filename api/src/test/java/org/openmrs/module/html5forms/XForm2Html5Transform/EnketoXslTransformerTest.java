package org.openmrs.module.html5forms.xForm2Html5Transform;

import com.sun.org.apache.xalan.internal.xsltc.trax.TemplatesImpl;
import org.apache.commons.io.IOUtils;
import org.dom4j.DocumentException;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Matchers;
import org.openmrs.module.html5forms.api.impl.EnketoResult;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.sax.SAXTransformerFactory;
import javax.xml.transform.sax.TransformerHandler;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;
import static org.openmrs.module.html5forms.xForm2Html5Transform.XslTransformPipeline.xslTransformPipeline;

public class EnketoXslTransformerTest {
    SAXTransformerFactory transformerFactory;
    TransformerHandler transformerHandler;
    Transformer transformer;

    @Before
    public void setUp() throws Exception {
        transformerHandler = mock(TransformerHandler.class);
        transformerFactory = mock(SAXTransformerFactory.class);
        transformer = mock(Transformer.class);
    }

    @Test
    public void transform_shouldTransformXform2JRFirst() throws Exception {
        ArgumentCaptor<Result> result = ArgumentCaptor.forClass(Result.class);

        when(transformerFactory.newTransformerHandler(Matchers.<Templates>anyObject())).thenReturn(transformerHandler);
        when(transformerFactory.newTransformer()).thenReturn(transformer);

        when(transformerFactory.newTemplates(Matchers.<Source>anyObject())).thenReturn(new TemplatesImpl());
        doNothing().when(transformerHandler).setResult(Matchers.<Result>anyObject());

        XslTransformPipeline transformers = xslTransformPipeline().push(getXform2JRTransformer());
        EnketoXslTransformer enketoXslTransformer = new EnketoXslTransformer(transformerFactory, transformers);

        EnketoResult transformed = enketoXslTransformer.transform(getSampleXForm());
        verify(transformerFactory, times(1)).newTransformer();
        verify(transformerHandler, times(1)).setResult(result.capture());
    }


    @Test
    public void transform_shouldPerformSuccessiveTransforms() throws Exception {

        XslTransformPipeline transformers = xslTransformPipeline().push(getXform2JRTransformer())
                .push(getHtml5Transformer());

        ArgumentCaptor<Result> result = ArgumentCaptor.forClass(Result.class);

        when(transformerFactory.newTransformerHandler(Matchers.<Templates>anyObject())).thenReturn(transformerHandler);
        when(transformerFactory.newTransformer()).thenReturn(transformer);

        when(transformerFactory.newTemplates(Matchers.<Source>anyObject())).thenReturn(new TemplatesImpl());
        doNothing().when(transformerHandler).setResult(Matchers.<Result>anyObject());

        EnketoXslTransformer enketoXslTransformer = new EnketoXslTransformer(transformerFactory, transformers);
        EnketoResult transformed = enketoXslTransformer.transform(getSampleXForm());
        verify(transformerFactory, times(1)).newTransformer();
        verify(transformerHandler, times(2)).setResult(result.capture());
    }


    @Test
    public void transform_shouldTransformXFormToModel() throws Exception {

        XslTransformPipeline transformers = xslTransformPipeline().push(getXform2JRTransformer())
                .push(getHtml5Transformer());

        ArgumentCaptor<Result> result = ArgumentCaptor.forClass(Result.class);

        when(transformerFactory.newTransformerHandler(Matchers.<Templates>anyObject())).thenReturn(transformerHandler);
        when(transformerFactory.newTransformer()).thenReturn(transformer);

        when(transformerFactory.newTemplates(Matchers.<Source>anyObject())).thenReturn(new TemplatesImpl());
        doNothing().when(transformerHandler).setResult(Matchers.<Result>anyObject());

        EnketoXslTransformer enketoXslTransformer = new EnketoXslTransformer(transformerFactory, transformers);
        EnketoResult transformed = enketoXslTransformer.transform(getSampleXForm());
        verify(transformerFactory, times(1)).newTransformer();
        verify(transformerHandler, times(2)).setResult(result.capture());
    }

    @Test
    public void transform_shouldReturnEmptyResponseWhenNoTransformsAreAdded() throws Exception {
        ArgumentCaptor<StreamResult> result = ArgumentCaptor.forClass(StreamResult.class);

        when(transformerFactory.newTransformerHandler(Matchers.<Source>anyObject())).thenReturn(transformerHandler);
        doNothing().when(transformerHandler).setResult(Matchers.<Result>anyObject());

        EnketoXslTransformer enketoXslTransformer = new EnketoXslTransformer(transformerFactory, xslTransformPipeline());
        EnketoResult transformed = enketoXslTransformer.transform(getSampleXForm());
        assertThat(transformed.hasResult(), is(false));
        verify(transformerFactory, times(0)).newTransformer(Matchers.<Source>anyObject());
        verify(transformerHandler, times(0)).setResult(result.capture());
    }

    @Test
    public void transform_integrationTest() throws IOException, TransformerException, ParserConfigurationException, DocumentException {

        XslTransformPipeline transformers = xslTransformPipeline()
                .push(getXform2JRTransformer())
                .push(getHtml5Transformer());
        EnketoXslTransformer enketoXslTransformer = new EnketoXslTransformer(TransformerFactory.newInstance(), transformers);
        EnketoResult transform = enketoXslTransformer.transform(getSampleXForm());
        assertThat(transform.hasResult(), is(true));

        transform = enketoXslTransformer.transform(getSampleXForm());
        assertThat(transform.hasResult(), is(true));

        System.out.println(transform.getResult());

    }

    @Test
    public void transform_integrationTest_tojson() throws IOException, TransformerException, ParserConfigurationException, DocumentException {

        XslTransformPipeline transformers = xslTransformPipeline()
                .push(getXform2JRTransformer())
                .push(getHtml5Transformer())
                .push(getXml2JsonTransformer());

        EnketoXslTransformer enketoXslTransformer = new EnketoXslTransformer(TransformerFactory.newInstance(), transformers);
        EnketoResult transform = enketoXslTransformer.transform(getSampleXForm());
        assertThat(transform.hasResult(), is(true));

        transform = enketoXslTransformer.transform(getSampleXForm());
        assertThat(transform.hasResult(), is(true));

//        System.out.println(transform.getResult());

    }

    private String getSampleXForm() throws IOException {
        ApplicationContext context = new ClassPathXmlApplicationContext();
        return IOUtils.toString(context.getResource("/test-xform.xml").getInputStream());
    }

    private File getXform2JRTransformer() throws IOException {
        ApplicationContext context = new ClassPathXmlApplicationContext();
        return context.getResource("/xform2jr.xsl").getFile();
    }

    private File getHtml5Transformer() throws IOException {
        ApplicationContext context = new ClassPathXmlApplicationContext();
        return context.getResource("/jr2html5_php5.xsl").getFile();
    }

    private File getXml2JsonTransformer() throws IOException {
        ApplicationContext context = new ClassPathXmlApplicationContext();
        return context.getResource("/xml2json.xsl").getFile();
    }

}
