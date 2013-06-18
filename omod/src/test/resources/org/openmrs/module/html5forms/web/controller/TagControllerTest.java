package org.openmrs.module.html5forms.web.controller;

import com.jayway.jsonassert.JsonAssert;
import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.openmrs.module.html5forms.HTML5FormTag;
import org.openmrs.test.BaseModuleContextSensitiveTest;

import java.io.IOException;
import java.util.List;

import static org.hamcrest.Matchers.equalTo;

public class TagControllerTest extends BaseModuleContextSensitiveTest {
    @Before
    public void setUp() throws Exception {
        executeDataSet("tagTestData.xml");
    }

    @Test
    public void tags_shouldReturnTags() throws IOException {
        TagController controller = new TagController();
        List<HTML5FormTag> tags = controller.tags();
        ObjectMapper mapper = new ObjectMapper();
        String jsonString = mapper.writeValueAsString(tags);
        JsonAssert.with(jsonString).assertThat("$.[0].id", equalTo(1)).assertThat("$.[0].name", equalTo("Registration"));
        JsonAssert.with(jsonString).assertThat("$.[1].id", equalTo(2)).assertThat("$.[1].name", equalTo("Patient"));
        JsonAssert.with(jsonString).assertThat("$.[2].id", equalTo(3)).assertThat("$.[2].name", equalTo("Encounter"));
        JsonAssert.with(jsonString).assertThat("$.[3].id", equalTo(4)).assertThat("$.[3].name", equalTo("HIV"));
    }
}
