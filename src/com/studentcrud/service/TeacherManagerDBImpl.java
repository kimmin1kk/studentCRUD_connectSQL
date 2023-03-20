package com.studentcrud.service;

import com.studentcrud.config.DatabaseProp;
import com.studentcrud.user.Teacher;

import java.util.List;

public class TeacherManagerDBImpl implements TeacherManager{
    private final DatabaseProp databaseProp = DatabaseProp.getInstance();

    @Override
    public boolean findTeacherByIdAndPassword(String id, String pw) {
        return false;
    }

    @Override
    public void addTeacher(Teacher teacher) throws IllegalArgumentException {

    }

    @Override
    public Teacher findById(String id) {
        return null;
    }

    @Override
    public List<Teacher> findAll() {
        return null;
    }

    @Override
    public boolean isExistenceTeacherById(String id) {
        return false;
    }

    @Override
    public void deleteTeacherById(String id) {

    }

    @Override
    public void replaceTeacherName(String beforeName, String afterName) {

    }

    @Override
    public void replaceTeacherPassword(String name, String afterPassword) {

    }
}
