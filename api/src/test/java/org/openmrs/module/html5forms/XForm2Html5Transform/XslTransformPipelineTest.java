package org.openmrs.module.html5forms.xForm2Html5Transform;

import org.junit.Test;

import java.io.File;
import java.util.Queue;
import java.util.Stack;

import static junit.framework.Assert.assertEquals;
import static org.hamcrest.Matchers.is;
import static org.mockito.Matchers.isNotNull;
import static org.mockito.Matchers.notNull;
import static org.mockito.Mockito.mock;

public class XslTransformPipelineTest {
    @Test
    public void push_shouldAddToTheList(){
        File mock = mock(File.class);
        XslTransformPipeline pipeline = XslTransformPipeline.xslTransformPipeline();
        Stack<File> transforms = pipeline.get();

        assertEquals(0, transforms.size());
        pipeline.push(mock);
        transforms = pipeline.get();
        assertEquals(1, transforms.size());
        assertEquals(transforms.pop(), mock);
        assertEquals(0, transforms.size());

    }
}
