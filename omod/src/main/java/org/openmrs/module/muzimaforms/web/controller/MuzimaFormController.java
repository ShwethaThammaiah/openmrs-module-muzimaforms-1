package org.openmrs.module.muzimaforms.web.controller;

import org.dom4j.DocumentException;
import org.openmrs.api.context.Context;
import org.openmrs.module.muzimaforms.MuzimaForm;
import org.openmrs.module.muzimaforms.api.MuzimaFormService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.xpath.XPathExpressionException;
import java.io.IOException;


@Controller
@RequestMapping(value = "module/muzimaforms/form.form")
public class MuzimaFormController {

    //TODO: Use MuzimaFormResource to handle the save
    @RequestMapping(method = RequestMethod.POST, value = "form")
    @ResponseBody
    public void save(final @RequestBody MuzimaForm form) throws Exception {
        MuzimaFormService service = Context.getService(MuzimaFormService.class);
        service.save(form);
    }
}
