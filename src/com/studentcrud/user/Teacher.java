package com.studentcrud.user;

public class Teacher extends User{

    private Subject subject;
    public Teacher(String name, String id, Subject subject) {
        super.name = name;
        super.id = id;
        super.pw = id;
        this.subject = subject;
    }
    public Teacher() { //오버로딩
    }

    public Subject getSubject() {
        return subject;
    }
}

