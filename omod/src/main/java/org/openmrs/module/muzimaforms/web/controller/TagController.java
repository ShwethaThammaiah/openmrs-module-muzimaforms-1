package org.openmrs.module.muzimaforms.web.controller;

import org.openmrs.api.context.Context;
import org.openmrs.module.muzimaforms.MuzimaForm;
import org.openmrs.module.muzimaforms.MuzimaFormTag;
import org.openmrs.module.muzimaforms.api.MuzimaFormService;
import org.openmrs.module.muzimaforms.api.MuzimaTagService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;


@Controller
@RequestMapping(value = "module/muzimaforms/tags.form")

public class TagController {
    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public List<MuzimaFormTag> tags() {

        MuzimaTagService service = Context.getService(MuzimaTagService.class);
        return service.getAll();
    }

//    @RequestMapping(method = RequestMethod.POST)
//    @ResponseBody
//    public void addTag(String tag,final @RequestBody String muzimaFormUUID) throws Exception {
//        MuzimaFormService service = Context.getService(MuzimaFormService.class);
//        MuzimaForm form = service.findById(new Integer(muzimaFormUUID));
//        form.getTags().add(new MuzimaFormTag(tag));
//        service.save(form);
//    }

}
