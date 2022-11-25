package com.bsuir.moyart.archive.server.dao.parser;

import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface XMLParser {
    List<Map<String, String>> parse(File dbFile) throws XMLParserException, ParserConfigurationException, SAXException, IOException;
}
