package org.openmrs.module.muzimaforms.resource;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openmrs.api.context.Context;
import org.openmrs.module.muzimaforms.MuzimaFormTag;
import org.openmrs.module.muzimaforms.api.MuzimaTagService;
import org.openmrs.module.webservices.rest.SimpleObject;
import org.openmrs.module.webservices.rest.web.RequestContext;
import org.openmrs.module.webservices.rest.web.representation.CustomRepresentation;
import org.openmrs.module.webservices.rest.web.representation.RefRepresentation;
import org.openmrs.module.webservices.rest.web.representation.Representation;
import org.openmrs.module.webservices.rest.web.resource.impl.DelegatingResourceDescription;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;
import static org.powermock.api.mockito.PowerMockito.mockStatic;
import static org.powermock.api.mockito.PowerMockito.when;

@RunWith(PowerMockRunner.class)
@PrepareForTest(Context.class)
public class MuzimaTagResourceTest {
    private MuzimaTagService service;
    MuzimaTagResource controller;

    @Before
    public void setUp() throws Exception {
        ArrayList<MuzimaFormTag> tags = getTags("foo", "bar", "baz");
        service = mock(MuzimaTagService.class);
        when(service.getAll()).thenReturn(tags);
        controller = new MuzimaTagResource();
        mockStatic(Context.class);
        PowerMockito.when(Context.getService(MuzimaTagService.class)).thenReturn(service);
    }

    private ArrayList<MuzimaFormTag> getTags(String... tags) {
        ArrayList<MuzimaFormTag> muzimaFormTags = new ArrayList<MuzimaFormTag>();
        for (String tag : tags) {
            muzimaFormTags.add(new MuzimaFormTag(tag));
        }
        return muzimaFormTags;
    }


    @Test
    public void getAll_shouldGetAllTags() {
        Representation representation = mock(CustomRepresentation.class);
        when(representation.getRepresentation()).thenReturn("(uuid:uuid,id:id)");
        RequestContext context = mock(RequestContext.class);
        when(context.getRepresentation()).thenReturn(representation);
        SimpleObject response = controller.getAll(context);
        assertThat(response.containsKey("tags"), is(true));
        List forms = (List) response.get("tags");
        assertThat(forms.size(), is(3));
        verify(service, times(1)).getAll();
    }

    @Test
    public void getRepresentationDescription_shouldAddDefaultProperties() {
        Representation representation = mock(RefRepresentation.class);
        Set<String> keys = controller.getRepresentationDescription(representation).getProperties().keySet();
        assertThat(keys.contains("id"), is(true));
        assertThat(keys.contains("uuid"), is(true));
        assertThat(keys.contains("name"), is(true));
    }
}
