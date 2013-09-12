package org.openmrs.module.muzimaforms.xForm2MuzimaTransform;

import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.node.ArrayNode;
import org.custommonkey.xmlunit.Diff;
import org.junit.Before;
import org.junit.Test;
import org.openmrs.module.muzimaforms.api.impl.EnketoResult;

import javax.xml.transform.TransformerFactory;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.openmrs.module.muzimaforms.xForm2MuzimaTransform.XslTransformPipeline.xslTransformPipeline;

public class JavaRosaModelToJSONTest extends ResourceTest {

    private ObjectMapper mapper;
    private EnketoResult result;


    @Before
    public void setUp() throws Exception {
        ModelXml2JsonTransformer jsonTransformer = new ModelXml2JsonTransformer(TransformerFactory.newInstance(), XslTransformPipeline.modelXml2JsonXSLPipeline());

        mapper = new ObjectMapper();
        result = jsonTransformer.transform(getText("test-model-to-json-multiple.xml"));
    }

    @Test
    public void shouldOnlyHaveOneElementInTheFieldSection() throws Exception {
        JsonNode node = mapper.readTree(result.getModelAsJson());
        System.out.println(node.toString());
        assertThat(node.get("form").get("fields").size(), is(1));
    }

    @Test
    public void shouldNotHaveElementMarkedAsMultipleInTheFormFieldsSectionOfJSON() throws Exception {

    }

}
