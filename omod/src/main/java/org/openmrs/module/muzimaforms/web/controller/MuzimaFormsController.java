package org.openmrs.module.muzimaforms.web.controller;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializationConfig;
import org.openmrs.User;
import org.openmrs.api.context.Context;
import org.openmrs.module.muzimaforms.MuzimaForm;
import org.openmrs.module.muzimaforms.MuzimaFormTag;
import org.openmrs.module.muzimaforms.api.MuzimaFormService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.util.List;


@Controller
@RequestMapping(value = "module/muzimaforms/forms.form")

public class MuzimaFormsController {
    @RequestMapping(method = RequestMethod.GET)

    @ResponseBody
    public List<MuzimaForm> forms() throws IOException {

//        ObjectMapper objectMapper = new ObjectMapper();
//        objectMapper.getSerializationConfig().disable(SerializationConfig.Feature.AUTO_DETECT_IS_GETTERS);
//        objectMapper.getSerializationConfig().disable(SerializationConfig.Feature.FAIL_ON_EMPTY_BEANS);

        MuzimaFormService service = Context.getService(MuzimaFormService.class);
//        System.out.println(objectMapper.writeValueAsString(service.getAll()));
//        return objectMapper.writeValueAsString(service.getAll());
        return service.getAll();
    }
}
