package com.bsuir.moyart.archive.server.dao.parser.impl;

import com.bsuir.moyart.archive.server.dao.parser.XMLParser;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class XMLAccountsParser implements XMLParser {
    @Override
    public List<Map<String, String>> parse(File dbFile) throws ParserConfigurationException, SAXException, IOException {
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
        Document document = documentBuilder.parse(dbFile);
        NodeList accounts = document.getElementsByTagName("account");

        List<Map<String, String>> accountsParams = new ArrayList<>();

        for (int i = 0; i < accounts.getLength(); i++) {
            Map<String, String> studentParams = new HashMap<>();
            NodeList student = accounts.item(i).getChildNodes();
            for (int j = 0; j < student.getLength(); j++) {
                studentParams.put(student.item(j).getNodeName(), student.item(j).getTextContent());
            }
            accountsParams.add(studentParams);
        }

        return accountsParams;
    }
}
