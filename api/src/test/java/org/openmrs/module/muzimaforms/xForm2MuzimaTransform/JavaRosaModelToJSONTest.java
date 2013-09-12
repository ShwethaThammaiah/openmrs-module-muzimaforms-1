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
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;
import static org.openmrs.module.muzimaforms.xForm2MuzimaTransform.XslTransformPipeline.xslTransformPipeline;

public class JavaRosaModelToJSONTest extends ResourceTest {

    private JsonNode form;


    @Before
    public void setUp() throws Exception {
        ModelXml2JsonTransformer jsonTransformer = new ModelXml2JsonTransformer(TransformerFactory.newInstance(), XslTransformPipeline.modelXml2JsonXSLPipeline());

        EnketoResult result = jsonTransformer.transform(getText("test-model-to-json-multiple.xml"));

        JsonNode node = new ObjectMapper().readTree(result.getModelAsJson());
        form = node.get("form");
    }

    @Test
    public void shouldOnlyHaveOneElementInTheFieldSection() throws Exception {
        assertThat(form.get("fields").size(), is(1));
    }

    @Test
    public void shouldOnlyHaveTheNameFieldInTheJSON() throws Exception {
        JsonNode jsonNode = form.get("fields").get(0);
        assertThat(jsonNode.get("name").toString(), is("\"today\""));
    }

    @Test
    public void shouldContainSectionSubFormsInTheJSON() throws Exception {
        assertThat(form.get("sub_forms"), notNullValue());
    }
}
