package com.bsuir.moyart.archive.server.dao.writer.impl;

import com.bsuir.moyart.archive.server.bean.Student;
import com.bsuir.moyart.archive.server.dao.writer.Writer;
import com.bsuir.moyart.archive.server.dao.writer.XMLWriterException;
import org.w3c.dom.*;
import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.validation.SchemaFactory;
import java.io.File;
import java.io.IOException;

public class WriterImpl implements Writer {

    private Document getDocument(File dbFile, File schemaFile) throws ParserConfigurationException, SAXException, IOException {
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
        documentBuilderFactory.setSchema(schemaFactory.newSchema(schemaFile));
        documentBuilderFactory.setValidating(true);
        documentBuilderFactory.setNamespaceAware(true);
        DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
        documentBuilder.setErrorHandler(new XmlErrorHandler());
        return documentBuilder.parse(dbFile);
    }

    private void saveDocument(Document document, File dbFile) throws TransformerException {
        DOMSource source = new DOMSource(document);
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        StreamResult result = new StreamResult(dbFile);
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.transform(source, result);
    }

    public void removeWhitespaces(Element element) {
        NodeList children = element.getChildNodes();
        for (int i = children.getLength() - 1; i >= 0; i--) {
            Node child = children.item(i);
            if (child instanceof Text
                    && ((Text) child).getData().trim().isEmpty()) {
                element.removeChild(child);
            } else if (child instanceof Element) {
                removeWhitespaces((Element) child);
            }
        }
    }

    public void append(File dbFile, File schemaFile, Student student) throws ParserConfigurationException, IOException, SAXException, TransformerException, XMLWriterException {
        Document document = getDocument(dbFile, schemaFile);
        Element students = (Element) document.getElementsByTagName("students").item(0);

        if (document.getElementById(student.getParams().get("id")) != null) {
            throw new XMLWriterException("Student with id = " + student.getParams().get("id") + " already exist");
        }

        removeWhitespaces(document.getDocumentElement());

        // add element
        Element newStudent = document.createElement("student");

        for (String param : student.getParams().keySet()) {
            if (param.equals("id")) {
                newStudent.setAttribute("id", student.getParams().get(param));
            } else {
                Element element = document.createElement(param);
                element.appendChild(document.createTextNode(student.getParams().get(param)));
                newStudent.appendChild(element);
            }
        }

        students.appendChild(newStudent);

        saveDocument(document, dbFile);
    }

    @Override
    public void replace(File dbFile, File schemaFile, Student student) throws ParserConfigurationException, IOException, SAXException, TransformerException, XMLWriterException {
        Document document = getDocument(dbFile, schemaFile);
        Element students = (Element) document.getElementsByTagName("students").item(0);

        // remove whitespaces
        removeWhitespaces(document.getDocumentElement());

        // add element
        Element newStudent = document.createElement("student");
        Element oldStudent = document.getElementById(student.getParams().get("id"));
        if (oldStudent == null) {
            throw new XMLWriterException("No Student with id = " + student.getParams().get("id"));
        }

        for (String param : student.getParams().keySet()) {
            if (param.equals("id")) {
                newStudent.setAttribute("id", student.getParams().get(param));
            } else {
                Element element = document.createElement(param);
                element.appendChild(document.createTextNode(student.getParams().get(param)));
                newStudent.appendChild(element);
            }
        }

        students.replaceChild(newStudent, oldStudent);

        saveDocument(document, dbFile);
    }

    private static class XmlErrorHandler implements ErrorHandler {
        @Override
        public void warning(SAXParseException exception) throws SAXException {
        }

        @Override
        public void error(SAXParseException exception) throws SAXException {
        }

        @Override
        public void fatalError(SAXParseException exception) throws SAXException {
        }
    }
}
