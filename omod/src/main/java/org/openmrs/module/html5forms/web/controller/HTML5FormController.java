package org.openmrs.module.html5forms.web.controller;

import org.openmrs.api.context.Context;
import org.openmrs.module.html5forms.HTML5Form;
import org.openmrs.module.html5forms.api.HTML5FormService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping(value = "module/html5forms/form.form")

public class HTML5FormController {

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public HTML5Form get(@RequestParam(required = true) Integer id) {
        HTML5FormService service = Context.getService(HTML5FormService.class);
        return service.findById(id);
    }

    @RequestMapping(method = RequestMethod.POST, value="form")
    @ResponseBody
    public void create(@RequestBody HTML5Form form) {
        HTML5FormService service = Context.getService(HTML5FormService.class);
        service.saveForm(form);
    }

}
