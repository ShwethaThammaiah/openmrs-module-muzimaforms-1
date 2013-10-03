package org.openmrs.module.muzimaforms.web.controller;

import org.dom4j.DocumentException;
import org.javarosa.xform.parse.ValidationMessages;
import org.javarosa.xform.parse.XFormParser;
import org.openmrs.api.context.Context;
import org.openmrs.module.muzimaforms.api.MuzimaFormService;
import org.openmrs.module.muzimaforms.xForm2MuzimaTransform.EnketoXslTransformer;
import org.openmrs.module.muzimaforms.xForm2MuzimaTransform.XForm2JavarosaXslTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.util.Scanner;

@Controller
@RequestMapping(value = "module/muzimaforms")

public class JavaRosaFormUploadController {

    private EnketoXslTransformer transformer;

    @Autowired
    public JavaRosaFormUploadController(XForm2JavarosaXslTransformer transformer) {
        this.transformer = transformer;
    }

    @ResponseBody
    @RequestMapping(value = "/validate.form", method = RequestMethod.POST)
    public ValidationMessages validate(MultipartHttpServletRequest request) throws IOException, DocumentException, TransformerException, ParserConfigurationException {
        return new XFormParser(new StringReader(extractJavarosaXMLFromRequest(request))).validate();
    }

    @ResponseBody
    @RequestMapping(value = "/upload.form", method = RequestMethod.POST)
    public void upload(MultipartHttpServletRequest request, @RequestParam String name, @RequestParam String description) throws IOException, DocumentException, TransformerException, ParserConfigurationException {
        MuzimaFormService service = Context.getService(MuzimaFormService.class);
        service.create(extractJavarosaXMLFromRequest(request), description, name);
    }

    private String extractJavarosaXMLFromRequest(MultipartHttpServletRequest request) throws IOException, DocumentException, TransformerException, ParserConfigurationException {
        MultipartFile file = request.getFile("file");
        String content = readStream(file.getInputStream());

        String[] isODK = (String[]) request.getParameterMap().get("isODK");
        if (isODK != null && "true".equals(isODK[0])) {
            content = transformer.transform(content).getResult();
        }
        return content;
    }

    private String readStream(InputStream stream) throws IOException {
        return new Scanner(stream, "UTF-8").useDelimiter("\\A").next();
    }

}
