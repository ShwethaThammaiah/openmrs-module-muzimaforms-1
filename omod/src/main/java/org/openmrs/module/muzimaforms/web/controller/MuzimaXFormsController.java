package org.openmrs.module.muzimaforms.web.controller;

import org.openmrs.api.context.Context;
import org.openmrs.module.muzimaforms.MuzimaXForm;
import org.openmrs.module.muzimaforms.api.MuzimaFormService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;


@Controller
@RequestMapping(value = "module/muzimaforms/xforms.form")

public class MuzimaXFormsController {
    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public List<MuzimaXForm> xForms() {
        MuzimaFormService service = Context.getService(MuzimaFormService.class);
        return service.getXForms();
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public void importXForm(final @RequestParam Integer id,
                            final @RequestParam String name,
                            final @RequestParam String form,
                            final @RequestParam String discriminator,
                            final @RequestParam String description) throws Exception {
        MuzimaFormService service = Context.getService(MuzimaFormService.class);
        service.importExisting(id, name, form, description, discriminator);
    }
}
