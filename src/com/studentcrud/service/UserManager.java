package com.studentcrud.service;

import com.studentcrud.user.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class UserManager<T extends User>  {
    private final ArrayList<T> userList = new ArrayList<>();

    public boolean findUserByIdAndPassword(String id, String pw) { //입력 받은 인자로 로그인 하는 메서드
        User findedUser = null;
        for(T user : userList) {
            if(Objects.equals(id, user.getId())) { //아이디 체크
                findedUser = user;
            }
        }
        if(findedUser == null) {
            return false;
        }else {
            return pw.equals(findedUser.getPw());
        }
    }
    public void addUser(T t) throws IllegalArgumentException { // 입력받은 student ArrayList 를 리스트에 추가해주는 메서드
        if (isExistenceUserById(t.getId())) throw new IllegalArgumentException();
        userList.add(t);
    }

    public T findById(String id) { //검색할 때 쓰는 메서드
        for (T user : userList) {
            if (user.getId().equals(id)) return user;
        }
        return null;
    }
    public List<T> findAll() {
        return new ArrayList<>(userList);
    }


    public boolean isExistenceUserById(String id) { //중복 있으면 true 없으면 false 반환 입력할 때 쓰는 메서드
        return findById(id) != null;
    }

    public void deleteUserById(String id) { //삭제
        int cnt = 0;
        for (T user : userList) {
            if (id.equals(user.getId())) {
                userList.remove(cnt);
                break;
            }
            cnt ++;
        }
    }

    public void replaceUserName(String beforeName, String afterName) { // 이름을 수정할 때 쓰는 메서드
        findById(beforeName).setName(afterName);
    }

    public void replaceUserPassword(String name, String afterPassword) {
        findById(name).setPw(afterPassword);
    }
}
