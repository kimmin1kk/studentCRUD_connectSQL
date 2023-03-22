//package com.studentcrud.service;
//
//import com.studentcrud.user.Student;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Objects;
//
//public class StudentManagerImpl implements StudentManager {
//    private final ArrayList<Student> userList = new ArrayList<>();
//    @Override
//    public boolean findStudentByIdAndPassword(String id, String pw) {
//        Student findedUser = null;
//        for(Student student : userList) {
//            if(Objects.equals(id, student.getId())) { //아이디 체크
//                findedUser = student;
//            }
//        }
//        if(findedUser == null) {
//            return false;
//        }else {
//            return pw.equals(findedUser.getPw());
//        }
//    }
//
//    @Override
//    public void addStudent(Student student) throws IllegalArgumentException {
//        if (isExistenceStudentById(student.getId())) throw new IllegalArgumentException();
//        userList.add(student);
//
//    }
//
//    @Override
//    public Student findById(String id) {
//        for (Student student : userList) {
//            if (student.getId().equals(id))
//                return student;
//        }
//        return null;
//    }
//
//    @Override
//    public List<Student> findAll() {
//        return new ArrayList<>(userList);
//    }
//
//    @Override
//    public boolean isExistenceStudentById(String id) {
//        return findById(id) != null;
//    }
//
//    @Override
//    public void deleteStudentById(String id) {
//        int cnt = 0;
//        for (Student student : userList) {
//            if (id.equals(student.getId())) {
//                userList.remove(cnt);
//                break;
//            }
//            cnt ++;
//        }
//    }
//
//    @Override
//    public void replaceStudentName(String beforeName, String afterName) {
//        findById(beforeName).setName(afterName);
//    }
//
//    @Override
//    public void replaceStudentPassword(String name, String afterPassword) {
//        findById(name).setPw(afterPassword);
//    }
//}
