package org.openmrs.module.muzimaforms.web.controller;

import org.openmrs.api.context.Context;
import org.openmrs.module.muzimaforms.MuzimaForm;
import org.openmrs.module.muzimaforms.api.MuzimaFormService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;


@Controller
@RequestMapping(value = "module/muzimaforms/forms.form")

public class MuzimaFormsController {
    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public List<MuzimaForm> forms() {
        MuzimaFormService service = Context.getService(MuzimaFormService.class);
        return service.getAll();
    }
}
