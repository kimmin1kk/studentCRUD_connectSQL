//package com.studentcrud.service;
//
//import com.studentcrud.user.Teacher;
//import com.studentcrud.user.User;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Objects;
//
//public class TeacherManagerImpl implements TeacherManager{
//    private final ArrayList<Teacher> userList = new ArrayList<>();
//    @Override
//    public boolean findTeacherByIdAndPassword(String id, String pw) {
//        User findedUser = null;
//        for(Teacher teacher : userList) {
//            if(Objects.equals(id, teacher.getId())) { //아이디 체크
//                findedUser = teacher;
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
//    public void addTeacher(Teacher teacher) throws IllegalArgumentException {
//        if (isExistenceTeacherById(teacher.getId())) throw new IllegalArgumentException();
//        userList.add(teacher);
//    }
//
//    @Override
//    public Teacher findById(String id) {
//        for (Teacher teacher : userList) {
//            if (teacher.getId().equals(id))
//                return teacher;
//        }
//        return null;
//    }
//
//    @Override
//    public List<Teacher> findAll() {
//        return new ArrayList<>(userList);
//    }
//
//    @Override
//    public boolean isExistenceTeacherById(String id) {
//        return findById(id) != null;
//    }
//
//    @Override
//    public void deleteTeacherById(String id) {
//        int cnt = 0;
//        for (Teacher teacher : userList) {
//            if (id.equals(teacher.getId())) {
//                userList.remove(cnt);
//                break;
//            }
//            cnt ++;
//        }
//    }
//
//    @Override
//    public void replaceTeacherName(String beforeName, String afterName) {
//        findById(beforeName).setName(afterName);
//    }
//
//    @Override
//    public void replaceTeacherPassword(String name, String afterPassword) {
//        findById(name).setPw(afterPassword);
//    }
//}
