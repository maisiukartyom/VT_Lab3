package com.bsuir.moyart.archive.server.dao;

import com.bsuir.moyart.archive.server.bean.Student;

import java.util.List;

public interface UniversityDao {
    List<Student> getById(String id) throws DaoException;

    void add(Student appliance) throws DaoException;

    void edit(Student student) throws DaoException;
}
