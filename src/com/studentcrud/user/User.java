package com.studentcrud.user;

public class User {
    protected String id;
    protected String pw;
    protected String name;

    public String getId() {
        return id;
    }

    public String getPw() {
        return pw;
    }

    public void setPw(String pw) {
        this.pw = pw;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
