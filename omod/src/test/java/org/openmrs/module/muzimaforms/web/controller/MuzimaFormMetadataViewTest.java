package org.openmrs.module.muzimaforms.web.controller;

import org.junit.Before;
import org.junit.Test;
import org.openmrs.Form;
import org.openmrs.module.muzimaforms.MuzimaForm;
import org.openmrs.module.muzimaforms.MuzimaFormTag;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static java.util.Arrays.asList;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class MuzimaFormMetadataViewTest {
    List<MuzimaForm> muzimaForms;

    @Before
    public void setup(){
        final Form form1 = mock(Form.class);
        when(form1.getDescription()).thenReturn("mockedDescription1");
        when(form1.getName()).thenReturn("mockedName1");

        MuzimaForm html5Form1 = new MuzimaForm() {{
            setId(1);
            Set<MuzimaFormTag> tags = new HashSet<MuzimaFormTag>();
            tags.add(new MuzimaFormTag("foo"));
            tags.add(new MuzimaFormTag("bar"));
            setTags(tags);
            setForm(form1);
        }};


        final Form form2 = mock(Form.class);
        when(form2.getDescription()).thenReturn("mockedDescription2");
        when(form2.getName()).thenReturn("mockedName2");

        MuzimaForm html5Form2 = new MuzimaForm() {{
            setId(2);
            setForm(form2);
        }};
        muzimaForms = asList(html5Form1, html5Form2);
    }

    @Test
    public void filter_metadataShouldIncludeIdOfForm(){
        MuzimaFormMetadataView filter = new MuzimaFormMetadataView();
        List<MuzimaFormMetadata> metadata = filter.load(muzimaForms);
        assertThat(metadata.size(), is(2));
        assertThat(metadata.get(0).getId(), is(1));
        assertThat(metadata.get(1).getId(), is(2));
    }

    @Test
    public void filter_metadataShouldIncludeName(){
        MuzimaFormMetadataView filter = new MuzimaFormMetadataView();
        List<MuzimaFormMetadata> metadata = filter.load(muzimaForms);
        assertThat(metadata.size(), is(2));
        assertThat(metadata.get(0).getName(), is("mockedName1"));
        assertThat(metadata.get(1).getName(), is("mockedName2"));
    }

    @Test
    public void filter_metadataShouldIncludeDescription(){
        MuzimaFormMetadataView filter = new MuzimaFormMetadataView();
        List<MuzimaFormMetadata> metadata = filter.load(muzimaForms);
        assertThat(metadata.size(), is(2));
        assertThat(metadata.get(0).getDescription(), is("mockedDescription1"));
        assertThat(metadata.get(1).getDescription(), is("mockedDescription2"));
    }

    @Test
    public void filter_metadataShouldIncludeTags(){
        MuzimaFormMetadataView filter = new MuzimaFormMetadataView();
        List<MuzimaFormMetadata> metadata = filter.load(muzimaForms);
        assertThat(metadata.size(), is(2));
        assertThat(metadata.get(0).getTags().size(), is(2));
        assertThat(metadata.get(1).getTags().size(), is(0));
    }
}
