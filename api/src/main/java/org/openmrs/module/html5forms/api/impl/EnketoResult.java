package org.openmrs.module.html5forms.api.impl;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.io.SAXReader;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathFactory;
import java.io.StringReader;

public class EnketoResult {
    private String transform;
    DocumentBuilder documentBuilder;
    XPathFactory xPathFactory;
    private Document document;

    public EnketoResult(String transform) throws ParserConfigurationException, DocumentException {
        this.transform = transform;
        documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        xPathFactory = XPathFactory.newInstance();
        document = new SAXReader().read(new StringReader(transform));
    }

    public String getForm() {
        if (!hasResult()) return "";
        return document.getRootElement().element("form").asXML();
    }

    public String getModel() {
        if (!hasResult()) return "";
        return document.getRootElement().element("model").asXML();
    }


    public String getResult() throws DocumentException {
        return transform;
    }

    public boolean hasResult() {
        return transform != null && !transform.isEmpty();
    }
}
