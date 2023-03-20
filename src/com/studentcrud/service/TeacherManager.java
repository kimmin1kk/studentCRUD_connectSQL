package com.studentcrud.service;

import com.studentcrud.user.Teacher;

import java.util.List;

public interface TeacherManager {
    boolean findTeacherByIdAndPassword(String id, String pw);

    void addTeacher(Teacher teacher) throws IllegalArgumentException;

    Teacher findById(String id);

    List<Teacher> findAll();

    boolean isExistenceTeacherById(String id);

    void deleteTeacherById(String id);

    void replaceTeacherName(String beforeName, String afterName);

    void replaceTeacherPassword(String name, String afterPassword);
}
