package org.openmrs.module.html5forms.web.controller;

import org.openmrs.api.context.Context;
import org.openmrs.module.html5forms.HTML5FormTag;
import org.openmrs.module.html5forms.api.TagService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;


@Controller
@RequestMapping(value = "module/html5forms/tags.form")

public class TagController {
    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public List<HTML5FormTag> tags() {
        TagService service = Context.getService(TagService.class);
        return service.getAll();
    }

}
