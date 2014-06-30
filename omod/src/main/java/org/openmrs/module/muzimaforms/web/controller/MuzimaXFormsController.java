package org.openmrs.module.muzimaforms.web.controller;

import org.dom4j.DocumentException;
import org.openmrs.api.context.Context;
import org.openmrs.module.muzimaforms.MuzimaForm;
import org.openmrs.module.muzimaforms.MuzimaXForm;
import org.openmrs.module.muzimaforms.api.MuzimaFormService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.xpath.XPathExpressionException;
import java.io.IOException;
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
    public void importXForm(@RequestParam Integer id, @RequestParam String name, @RequestParam String discriminator, @RequestParam String description) throws SAXException, DocumentException, TransformerException, IOException, XPathExpressionException, ParserConfigurationException {
        MuzimaFormService service = Context.getService(MuzimaFormService.class);
        service.importExisting(id, name, discriminator, description);
    }
}
