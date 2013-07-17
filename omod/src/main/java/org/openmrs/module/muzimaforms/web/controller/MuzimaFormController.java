package org.openmrs.module.muzimaforms.web.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dom4j.DocumentException;
import org.openmrs.api.context.Context;
import org.openmrs.module.muzimaforms.MuzimaConstants;
import org.openmrs.module.muzimaforms.MuzimaForm;
import org.openmrs.module.muzimaforms.api.MuzimaFormService;
import org.openmrs.module.webservices.rest.web.RestConstants;
import org.openmrs.module.webservices.rest.web.v1_0.controller.MainResourceController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.xpath.XPathExpressionException;
import java.io.IOException;


@Controller
@RequestMapping(value = "module/muzimaforms/form.form")
public class MuzimaFormController {
    Log logger = LogFactory.getLog(MuzimaFormController.class);

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public MuzimaForm get(@RequestParam(required = true) Integer id) {
        try {
            MuzimaFormService service = Context.getService(MuzimaFormService.class);
            return service.findById(id);
        } catch (Exception e) {
            logger.error(e);
        }
        return null;
    }

    @RequestMapping(method = RequestMethod.POST, value = "form")
    @ResponseBody
    public void create(@RequestBody MuzimaForm form) throws SAXException, DocumentException, TransformerException, IOException, XPathExpressionException, ParserConfigurationException {
        try {
            MuzimaFormService service = Context.getService(MuzimaFormService.class);
            service.saveForm(form);
        } catch (Exception e) {
            logger.error(e);
        }
    }

}
