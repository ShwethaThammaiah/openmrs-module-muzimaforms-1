package org.openmrs.module.muzimaforms.web.controller;

import org.javarosa.xform.parse.ValidationMessages;
import org.javarosa.xform.parse.XFormParser;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.support.DefaultMultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@Controller
@RequestMapping(value = "module/muzimaforms")

public class JavaRosaFormUploadController {
    @ResponseBody
    @RequestMapping(value = "/validate.form", method = RequestMethod.POST)
    public ValidationMessages validate(MultipartHttpServletRequest request) throws IOException {
        MultipartFile file = request.getFile("file");
        return new XFormParser(new InputStreamReader(file.getInputStream())).validate();
    }
}
