package com.studentcrud.user;

public class Student extends User{ //오직 데이터만을 담고있다.
    private int kScore;
    private int eScore;
    private int mScore;

    public Student(String name, String id, int kScore, int eScore, int mScore) {
        super.name = name;
        super.id = id;
        super.pw = id;
        this.kScore = kScore;
        this. eScore = eScore;
        this.mScore = mScore;
    }
    public Student() { //오버로딩
    }
    public int getkScore() {
        return kScore;
    }

    public int geteScore() {
        return eScore;
    }

    public int getmScore() {
        return mScore;
    }

    public void setkScore(int kScore) {
        this.kScore = kScore;
    }

    public void seteScore(int eScore) {
        this.eScore = eScore;
    }

    public void setmScore(int mScore) {
        this.mScore = mScore;
    }

}