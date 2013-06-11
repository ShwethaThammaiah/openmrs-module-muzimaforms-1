package org.openmrs.module.html5forms.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;


@Controller
@RequestMapping(value = "module/html5forms/tags.form")

public class TagController {
    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public Map<Integer, Map<String, String>> tags() {
        Map<Integer, Map<String, String>> map = new HashMap<Integer, Map<String, String>>() {{
            put(1, new HashMap<String, String>() {{
                put("name", "Registration");
            }});
            put(2, new HashMap<String, String>() {{
                put("name", "Patient");
            }});
            put(3, new HashMap<String, String>() {{
                put("name", "Encounter");
            }});
            put(4, new HashMap<String, String>() {{
                put("name", "Observation");
            }});
            put(5, new HashMap<String, String>() {{
                put("name", "HIV");
            }});
            put(6, new HashMap<String, String>() {{
                put("name", "PMTCT");
            }});
            put(7, new HashMap<String, String>() {{
                put("name", "Ante-Natal");
            }});
            put(8, new HashMap<String, String>() {{
                put("name", "Pediatric");
            }});
            put(9, new HashMap<String, String>() {{
                put("name", "Locator");
            }});

        }};

        return map;
    }

}
