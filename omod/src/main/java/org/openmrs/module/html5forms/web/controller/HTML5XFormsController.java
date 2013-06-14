package org.openmrs.module.html5forms.web.controller;

import org.openmrs.api.context.Context;
import org.openmrs.module.html5forms.HTML5Forms;
import org.openmrs.module.html5forms.HTML5XForms;
import org.openmrs.module.html5forms.api.HTML5FormService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping(value = "module/html5forms/xforms.form")

public class HTML5XFormsController {


    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public HTML5XForms xForms() {
        HTML5FormService service = Context.getService(HTML5FormService.class);
        return service.getXForms();
    }
}
