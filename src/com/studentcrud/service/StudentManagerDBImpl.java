package com.studentcrud.service;

import com.studentcrud.config.DatabaseProp;
import com.studentcrud.user.Student;

import java.util.List;

public class StudentManagerDBImpl implements StudentManager{

    private final DatabaseProp databaseProp = DatabaseProp.getInstance();

    @Override
    public boolean findStudentByIdAndPassword(String id, String pw) {
        return false;
    }

    @Override
    public void addStudent(Student student) throws IllegalArgumentException {

    }

    @Override
    public Student findById(String id) {
        return null;
    }

    @Override
    public List<Student> findAll() {
        return null;
    }

    @Override
    public boolean isExistenceStudentById(String id) {
        return false;
    }

    @Override
    public void deleteStudentById(String id) {

    }

    @Override
    public void replaceStudentName(String beforeName, String afterName) {

    }

    @Override
    public void replaceStudentPassword(String name, String afterPassword) {

    }
}
