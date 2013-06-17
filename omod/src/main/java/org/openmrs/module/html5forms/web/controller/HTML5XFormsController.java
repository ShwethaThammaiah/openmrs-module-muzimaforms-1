package org.openmrs.module.html5forms.web.controller;

import org.openmrs.api.context.Context;
import org.openmrs.module.html5forms.HTML5XForm;
import org.openmrs.module.html5forms.api.HTML5FormService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;


@Controller
@RequestMapping(value = "module/html5forms/xforms.form")

public class HTML5XFormsController {


    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public List<HTML5XForm> xForms() {
        HTML5FormService service = Context.getService(HTML5FormService.class);
        return service.getXForms();
    }
}
