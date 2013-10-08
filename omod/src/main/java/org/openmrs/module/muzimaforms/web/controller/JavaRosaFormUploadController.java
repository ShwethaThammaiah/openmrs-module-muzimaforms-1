package org.openmrs.module.muzimaforms.web.controller;

import org.dom4j.DocumentException;
import org.javarosa.xform.parse.ValidationMessages;
import org.openmrs.api.context.Context;
import org.openmrs.module.muzimaforms.api.MuzimaFormService;
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
import java.util.Scanner;

@Controller
@RequestMapping(value = "module/muzimaforms")

public class JavaRosaFormUploadController {


    @ResponseBody
    @RequestMapping(value = "/javarosa/validate.form", method = RequestMethod.POST)
    public ValidationMessages validateJavaRosa(MultipartHttpServletRequest request) throws IOException, DocumentException, TransformerException, ParserConfigurationException {
        MuzimaFormService service = Context.getService(MuzimaFormService.class);
        return service.validateJavaRosa(extractFile(request));
    }

    @ResponseBody
    @RequestMapping(value = "/odk/validate.form", method = RequestMethod.POST)
    public ValidationMessages validateODK(MultipartHttpServletRequest request) throws IOException, DocumentException, TransformerException, ParserConfigurationException {
        MuzimaFormService service = Context.getService(MuzimaFormService.class);
        return service.validateODK(extractFile(request));
    }

    @ResponseBody
    @RequestMapping(value = "/javarosa/upload.form", method = RequestMethod.POST)
    public void uploadJavaRosa(MultipartHttpServletRequest request, @RequestParam String name, @RequestParam String description) throws IOException, DocumentException, TransformerException, ParserConfigurationException {
        MuzimaFormService service = Context.getService(MuzimaFormService.class);
        service.create(extractFile(request), description, name);
    }

    @ResponseBody
    @RequestMapping(value = "/odk/upload.form", method = RequestMethod.POST)
    public void uploadODK(MultipartHttpServletRequest request, @RequestParam String name, @RequestParam String description) throws IOException, DocumentException, TransformerException, ParserConfigurationException {
        MuzimaFormService service = Context.getService(MuzimaFormService.class);
        service.importODK(extractFile(request), description, name);
    }

    private String extractFile(MultipartHttpServletRequest request) throws IOException, DocumentException, TransformerException, ParserConfigurationException {
        MultipartFile file = request.getFile("file");
        return readStream(file.getInputStream());
    }

    private String readStream(InputStream stream) throws IOException {
        return new Scanner(stream, "UTF-8").useDelimiter("\\A").next();
    }

}
