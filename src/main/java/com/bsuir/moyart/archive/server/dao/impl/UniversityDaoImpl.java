package com.bsuir.moyart.archive.server.dao.impl;

import com.bsuir.moyart.archive.server.bean.Student;
import com.bsuir.moyart.archive.server.dao.DaoException;
import com.bsuir.moyart.archive.server.dao.UniversityDao;
import com.bsuir.moyart.archive.server.dao.parser.XMLParser;
import com.bsuir.moyart.archive.server.dao.parser.XMLParserException;
import com.bsuir.moyart.archive.server.dao.parser.impl.XMLUniversityParser;
import com.bsuir.moyart.archive.server.dao.writer.Writer;
import com.bsuir.moyart.archive.server.dao.writer.XMLWriterException;
import com.bsuir.moyart.archive.server.dao.writer.impl.WriterImpl;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class UniversityDaoImpl implements UniversityDao {
    private final File dbFile = new File(
            Objects.requireNonNull(getClass().getClassLoader().getResource("students.xml")).getFile());
    private final File schemaFile = new File(
            Objects.requireNonNull(getClass().getClassLoader().getResource("students.xsd")).getFile());


    @Override
    public List<Student> getById(String id) throws DaoException {
        List<Student> students = new ArrayList<>();
        try {
            XMLParser parser = new XMLUniversityParser();
            List<Map<String, String>> studentsParams = parser.parse(dbFile);
            for (Map<String, String> params : studentsParams) {
                if (params.get("id").equals(id)) {
                    students.add(new Student(params));
                }
            }
            return students;
        } catch (XMLParserException | ParserConfigurationException | IOException | SAXException | ParseException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public void add(Student appliance) throws DaoException {
        try {
            Writer xmlWriter = new WriterImpl();
            xmlWriter.append(dbFile, schemaFile, appliance);
        } catch (IOException | ParserConfigurationException | SAXException | TransformerException | XMLWriterException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public void edit(Student student) throws DaoException {
        try {
            Writer xmlWriter = new WriterImpl();
            xmlWriter.replace(dbFile, schemaFile, student);
        } catch (IOException | ParserConfigurationException | SAXException | TransformerException | XMLWriterException e) {
            throw new DaoException(e);
        }
    }
}
