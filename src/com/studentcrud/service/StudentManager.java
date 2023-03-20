package com.studentcrud.service;

import com.studentcrud.user.Student;

import java.util.ArrayList;
import java.util.List;

public interface StudentManager {


    boolean findStudentByIdAndPassword(String id, String pw);

    void addStudent(Student student) throws IllegalArgumentException;

    Student findById(String id);

    List<Student> findAll();

    boolean isExistenceStudentById(String id);

    void deleteStudentById(String id);

    void replaceStudentName(String beforeName, String afterName);

    void replaceStudentPassword(String name, String afterPassword);
}
