package com.bsuir.moyart.archive.server.bean;

import java.io.Serializable;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Student implements Serializable {
    private String id;
    private String name;
    private String surname;
    private String university;
    private String speciality;
    private String courseNumber;
    private double averageScore;

    public Student(String id, String name, String surname, String university, String speciality,
                   String courseNumber, double averageScore) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.university = university;
        this.speciality = speciality;
        this.courseNumber = courseNumber;
        this.averageScore = averageScore;
    }

    public Student(Map<String, String> param) throws ParseException {
        this.id = param.get("id");
        this.name = param.get("name");
        this.surname = param.get("surname");
        this.surname = param.get("surname");
        this.university = param.get("university");
        this.speciality = param.get("speciality");
        this.courseNumber = param.get("course_number");
        this.averageScore = Double.parseDouble(param.get("average_score"));
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getUniversity() {
        return university;
    }

    public void setUniversity(String university) {
        this.university = university;
    }

    public String getSpeciality() {
        return speciality;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }

    public String getCourseNumber() {
        return courseNumber;
    }

    public void setCourseNumber(String courseNumber) {
        this.courseNumber = courseNumber;
    }

    public double getAverageScore() {
        return averageScore;
    }

    public void setAverageScore(double averageScore) {
        this.averageScore = averageScore;
    }

    public Map<String, String> getParams() {
        return new HashMap<>() {{
            put("id", String.valueOf(id));
            put("name", name);
            put("surname", surname);
            put("university", university);
            put("speciality", speciality);
            put("course_number", courseNumber);
            put("average_score", String.valueOf(averageScore));
        }};
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return Double.compare(student.averageScore, averageScore) == 0
                && Objects.equals(id, student.id) && Objects.equals(name, student.name)
                && Objects.equals(surname, student.surname) && Objects.equals(university, student.university)
                && Objects.equals(speciality, student.speciality) && Objects.equals(courseNumber, student.courseNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, surname, university, speciality, courseNumber, averageScore);
    }

    @Override
    public String toString() {
        return "Student{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", university='" + university + '\'' +
                ", speciality='" + speciality + '\'' +
                ", courseNumber='" + courseNumber + '\'' +
                ", averageScore=" + averageScore +
                '}';
    }
}
