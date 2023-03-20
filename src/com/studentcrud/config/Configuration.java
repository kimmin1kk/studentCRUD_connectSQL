package com.studentcrud.config;

public class Configuration {
    public static final int MAX_SCORE = 100;
    public static final int MIN_SCORE = 0;

    public static final int PREVENT_MINUS_N_ZERO = 1;
    public static final int PREVENT_MINUS = 0;

    public static final int INPUT_RANGE_LOGIN = 3; //처음 로그인 선택범위(학생, 교직원, 어드민)

    public static final int INPUT_RANGE_STUDENT_MAIN = 4; //학생 메인화면 선택범위
    public static final int INPUT_RANGE_ADMIN_MAIN = 9; //어드민 메인화면 선택범위
    public static final int INPUT_RANGE_TEACHER_MAIN = 6; // 교직원 메인화면 선택범위
    public static final int INPUT_RANGE_MODIFY = 3; //학생 수정 선택범위
    public static final int INPUT_RANGE_TEACHER_SUBJECT = 3; //선생 과목 선택범위
}

