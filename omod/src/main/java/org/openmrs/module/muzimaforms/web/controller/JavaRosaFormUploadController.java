package org.openmrs.module.muzimaforms.web.controller;

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

import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

@Controller
@RequestMapping(value = "module/muzimaforms")

public class JavaRosaFormUploadController {


    @ResponseBody
    @RequestMapping(value = "/javarosa/validate.form", method = RequestMethod.POST)
    public ValidationMessages validateJavaRosa(final MultipartHttpServletRequest request) throws Exception {
        MuzimaFormService service = Context.getService(MuzimaFormService.class);
        return service.validateJavaRosa(extractFile(request));
    }

    @ResponseBody
    @RequestMapping(value = "/odk/validate.form", method = RequestMethod.POST)
    public ValidationMessages validateODK(final MultipartHttpServletRequest request) throws Exception {
        MuzimaFormService service = Context.getService(MuzimaFormService.class);
        return service.validateODK(extractFile(request));
    }

    @ResponseBody
    @RequestMapping(value = "/javarosa/upload.form", method = RequestMethod.POST)
    public void uploadJavaRosa(final MultipartHttpServletRequest request,
                               final @RequestParam String name,
                               final @RequestParam String discriminator,
                               final @RequestParam String description) throws Exception {
        MuzimaFormService service = Context.getService(MuzimaFormService.class);
        service.create(extractFile(request), name, description, discriminator);
    }

    @ResponseBody
    @RequestMapping(value = "/html/upload.form", method = RequestMethod.POST)
    public void uploadHTMLForm(final MultipartHttpServletRequest request,
                               final @RequestParam String name,
                               final @RequestParam String discriminator,
                               final @RequestParam String description) throws Exception {
        MuzimaFormService service = Context.getService(MuzimaFormService.class);
        service.createHTMLForm(name, description, discriminator, extractFile(request));
    }

    @ResponseBody
    @RequestMapping(value = "/odk/upload.form", method = RequestMethod.POST)
    public void uploadODK(final MultipartHttpServletRequest request,
                          final @RequestParam String name,
                          final @RequestParam String discriminator,
                          final @RequestParam String description) throws Exception {
        MuzimaFormService service = Context.getService(MuzimaFormService.class);
        service.importODK(extractFile(request), name, description, discriminator);
    }

    private String extractFile(final MultipartHttpServletRequest request) throws Exception {
        MultipartFile file = request.getFile("file");
        return readStream(file.getInputStream());
    }

    private String readStream(final InputStream stream) throws IOException {
        return new Scanner(stream, "UTF-8").useDelimiter("\\A").next();
    }

}
