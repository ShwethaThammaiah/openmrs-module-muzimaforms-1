package org.openmrs.module.muzimaforms.web.controller;

import org.openmrs.api.context.Context;
import org.openmrs.module.muzimaforms.MuzimaConstants;
import org.openmrs.module.muzimaforms.MuzimaForm;
import org.openmrs.module.muzimaforms.api.MuzimaFormService;
import org.openmrs.module.webservices.rest.web.RestConstants;
import org.openmrs.module.webservices.rest.web.v1_0.controller.MainResourceController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;


@Controller
@RequestMapping(value = "module/muzimaforms/forms.form")
public class MuzimaFormsController {

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public List<MuzimaFormMetadata> forms(@RequestParam(value = "tags", required = false)
                                          String tags) throws IOException {
        return new MuzimaFormMetadataView().load(searchForms(tags));
    }

    private List<MuzimaForm> searchForms(String commaSeparatedTags) {
        List<MuzimaForm> forms = Context.getService(MuzimaFormService.class).getAll();

        if (commaSeparatedTags == null || commaSeparatedTags.trim().length() == 0) {
            return forms;
        }

        commaSeparatedTags = commaSeparatedTags.trim();

        List<String> tags = Arrays.asList(commaSeparatedTags.split("\\s*,\\s*"));
        List<MuzimaForm> selected = new ArrayList<MuzimaForm>();
        for (MuzimaForm form : forms) {
            if (!Collections.disjoint(form.getTagNames(), tags))
                selected.add(form);
        }
        return selected;

    }
}
