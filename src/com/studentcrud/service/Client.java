package com.studentcrud.service;

import com.studentcrud.user.Student;
import com.studentcrud.user.Teacher;
import com.studentcrud.view.AdminViewer;
import com.studentcrud.view.StudentViewer;
import com.studentcrud.view.TeacherViewer;

public class Client {
    AdminViewer adminViewer = new AdminViewer();
    StudentViewer studentViewer = new StudentViewer();
    TeacherViewer teacherViewer = new TeacherViewer();
    StudentManager studentManager = new StudentManagerDBImpl();
    TeacherManager teacherManager = new TeacherManagerDBImpl();
    public void run() {// 학생으로 로그인할지 관리자로 로그인할지 고를 수 있는 초기 페이지.
        String id;
        String pw;
        switch (adminViewer.loginPage()) {
            case 1: // 학생 로그인
                id = studentViewer.inputId();
                pw = studentViewer.inputPw();

                if (studentManager.findStudentByIdAndPassword(id, pw)) { //로그인 성공
                    studentMainPage(studentManager.findById(id));
                    break;
                } else { //로그인 실패
                    System.out.println("로그인 실패");
                    run();
                    break;
                }
            case 2: //교직원 로그인
                id = teacherViewer.inputId();
                pw = teacherViewer.inputPw();

                if(teacherManager.findTeacherByIdAndPassword(id, pw)) {
                    teacherMainPage(teacherManager.findById(id));
                    break;
                }else {
                    System.out.println("로그인 실패");
                    run();
                    break;
                }
            case 3: // 어드민 로그인
                switch (adminViewer.adminLogin()) {
                    case 1: // 로그인 성공
                        adminMainPage();
                        break;
                    case 2: // 취소 했을 때
                        run();
                        break;
                }
                break;
        }
    }

    public void adminMainPage() { //어드민으로 로그인했을때 호출되는 메서드
        boolean onOff = true;
        while (onOff) {
            switch (adminViewer.adminMainPage()) {
                case 1: //학생 입력
                    while (true) {
                        try {
                            studentManager.addStudent(adminViewer.typeStudent());
                            adminViewer.printSuccessSignUp(); //회원가입 성공 메세지
                            break;
                        } catch (IllegalArgumentException ignored) {
                            adminViewer.printDuplicatedIdException(); //회원가입 실패 메세지
                        }
                    }
                    break;
                case 2: // 학생 검색
                    adminViewer.printStudent(studentManager.findById(studentViewer.inputId()));
                    break;
                case 3: // 학생 리스트 출력
                    for(Student std : studentManager.findAll()) {
                    adminViewer.printStudent(std);;
                    }
                    break;
                case 4: // 학생 삭제
                    studentManager.deleteStudentById(studentViewer.inputId());
                    break;
                case 5: //교직원 추가
                    while(true) {
                        try{
                        teacherManager.addTeacher(adminViewer.typeTeacher());
                        adminViewer.printSuccessSignUp(); //회원가입 성공 메세지
                        break;
                        } catch (IllegalArgumentException ignored) {
                        adminViewer.printDuplicatedIdException(); //회원가입 실패 메세지
                            }
                    }
                    break;
                case 6: //교직원 리스트 출력
                    for(Teacher teacher : teacherManager.findAll()){
                        adminViewer.printTeacher(teacher);
                    }
                    break;
                case 7: //교직원 삭제
                    teacherManager.deleteTeacherById(teacherViewer.inputId());
                    break;
                case 8: // 로그아웃
                    run(); //break 안 달은 이유 : 로그아웃할 때 현재 창이 종료가 되면서 새로운 창으로 넘어가는 느낌이라서
                case 9: // 종료
                    onOff = false;
                    break;
            }
        }

    }

    public void studentMainPage(Student student) { //학생으로 로그인 했을때 호출되는 메서드
        boolean onOff = true;
        while (onOff) {
            switch (studentViewer.studentMainPage()) {
                case 1:
                    adminViewer.printStudent(student);
                    break;
                case 2:
                    switch (studentViewer.replaceStudentInformation()) {
                        case 1: //이름 변경
                            studentManager.replaceStudentName(student.getName(), studentViewer.replaceStudentName());
                            break;
                        case 2: //비밀번호 변경
                            studentManager.replaceStudentPassword(student.getPw(), studentViewer.replaceStudentPassword());
                            break;
                        case 3: //취소
                            studentMainPage(student);
                            break;
                    }
                    break;
                case 3:
                    run();
                case 4:
                    onOff = false;
                    break;
            }
        }
    }

    public void teacherMainPage(Teacher teacher) {
        boolean onOff = true;
        while (onOff) {
            switch (teacherViewer.teacherMainPage()) {
                case 1: //학생 검색
                    adminViewer.printStudent(studentManager.findById(studentViewer.inputId()));
                    break;
                case 2: //학생 리스트 출력
                    for(Student std : studentManager.findAll()) {
                        adminViewer.printStudent(std);
                    }
                    break;
                case 3: // 담당과목 학생 점수 수정
                    Student student = studentManager.findById(studentViewer.inputId());
                    switch(teacher.getSubject()) {
                        case KOREAN: //국어 점수만 수정
                            teacherViewer.messageEditKoreanScore();
                            student.setkScore(teacherViewer.editStudentKoreanScore());
                            studentManager.updateStudent(student);
                            break;
                        case ENGLISH: //영어 점수만 수정
                            teacherViewer.messageEditEnglishScore();
                            student.seteScore((teacherViewer.editStudentEnglishScore()));
                            studentManager.updateStudent(student);
                            break;
                        case MATH: //수학 점수만 수정
                            teacherViewer.messageEditMathScore();
                            student.setmScore((teacherViewer.editStudentMathScore()));
                            studentManager.updateStudent(student);
                            break;
                    }
                    break;
                case 4: // 본인의 이름 및 비번 수정
                    switch(teacherViewer.replaceTeacherInformation()) {
                        case 1:
                            teacherManager.replaceTeacherName(teacher.getName(), teacherViewer.replaceTeacherName());
                            break;
                        case 2:
                            teacherManager.replaceTeacherPassword(teacher.getPw(), teacherViewer.replaceTeacherPassword());
                            break;
                        case 3:
                            teacherMainPage(teacher);
                            break;
                    }
                    break;
                case 5: // 로그아웃
                    run();
                case 6: // 종료
                    onOff = false;
                    break;
            }

        }
    }


}
