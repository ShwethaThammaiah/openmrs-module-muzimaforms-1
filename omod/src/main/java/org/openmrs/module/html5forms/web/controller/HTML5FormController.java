package org.openmrs.module.html5forms.web.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dom4j.DocumentException;
import org.openmrs.api.context.Context;
import org.openmrs.module.html5forms.HTML5Form;
import org.openmrs.module.html5forms.api.HTML5FormService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.xpath.XPathExpressionException;
import java.io.IOException;


@Controller
@RequestMapping(value = "module/html5forms/form.form")

public class HTML5FormController {
    Log logger = LogFactory.getLog("org.openmrs.module.html5forms");


    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public HTML5Form get(@RequestParam(required = true) Integer id) {
        try {
            HTML5FormService service = Context.getService(HTML5FormService.class);
            return service.findById(id);
        } catch (Exception e) {
            logger.error(e);
        }
        return null;
    }

    @RequestMapping(method = RequestMethod.POST, value = "form")
    @ResponseBody
    public void create(@RequestBody HTML5Form form) throws SAXException, DocumentException, TransformerException, IOException, XPathExpressionException, ParserConfigurationException {
        try {
            HTML5FormService service = Context.getService(HTML5FormService.class);
            service.saveForm(form);
        } catch (Exception e) {
            logger.error(e);
        }
    }

}
