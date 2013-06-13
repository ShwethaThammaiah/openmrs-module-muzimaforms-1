package org.openmrs.module.html5forms.web.controller;

import org.openmrs.api.context.Context;
import org.openmrs.module.xforms.Xform;
import org.openmrs.module.xforms.XformsService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;


@Controller
@RequestMapping(value = "module/html5forms/xforms.form")

public class XFormsController {
    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public List<Xform> xforms() {
        XformsService service = Context.getService(XformsService.class);
        return service.getXforms();
    }

}
