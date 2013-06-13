package org.openmrs.module.html5forms.web.controller;

import org.openmrs.Form;
import org.openmrs.api.FormService;
import org.openmrs.api.context.Context;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller
@RequestMapping(value = "module/html5forms/forms.form")

public class HTML5FormsController {
    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public List<Form> forms() {
        FormService service = Context.getService(FormService.class);
        return service.getAllForms();
    }

}
