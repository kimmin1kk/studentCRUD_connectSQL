package com.studentcrud.view;

import com.studentcrud.user.Student;
import com.studentcrud.user.Subject;
import com.studentcrud.user.Teacher;

import java.util.InputMismatchException;
import java.util.Scanner;

import static com.studentcrud.config.Configuration.*;

public class AdminViewer extends UserViewer {

    public int loginPage() { // 처음 화면을 켰을 때 ui 메서드

        int choose = 0, num = 0;
        System.out.println();
        System.out.println("로그인 페이지입니다.");
        System.out.println("1. 학생 로그인 | 2. 교직원 로그인 | 3. 관리자 로그인");
        while(true){
            choose = getInput();
            if (choose < PREVENT_MINUS_N_ZERO) {
                sc = new Scanner(System.in);
                System.out.println("잘못된 입력입니다.");
            }else if (choose > INPUT_RANGE_LOGIN) {
                sc = new Scanner(System.in);
                System.out.println("잘못된 입력입니다.");
            }else {
                break;
            }
        }
        return choose;
    }

    public String inputId() { // 아이디를 입력하는 메서드
        String id = null;
        System.out.print("관리자 아이디를 입력하세요 :");
        id = sc.nextLine();
        return id;
    }


    public int adminLogin() { // 어드민 로그인을 선택했을 때 ui 메서드
        String check = null;int value=0;
        System.out.println("관리자 비밀번호를 입력해주세요. 취소하려면 cancel을 입력해주세요.");
        while(true) {
            System.out.print("입력 : ");
            check = sc.nextLine();
            if(check.equals("admin1234")) {
                System.out.println("관리자로 로그인합니다.");
                value = 1;
                break;
            } else if (check.equals("cancel")) {
                System.out.println("이전 페이지로 넘어갑니다");
                value = 2;
                break;
            } else {
                System.out.println("비밀번호가 틀렸습니다.");
                sc = new Scanner(System.in);
            }
        }
        return value;
    }

    public int adminMainPage() { // 어드민 로그인했을 때 ui 메서드
        boolean onOff=true;
        int num=0;
        while(onOff) {
            System.out.println("---------------------------");
            System.out.println("| 학생 관리 시스템 (관리자용) |");
            System.out.println("----------------------------------------------------------------------------------------------------");
            System.out.println("│학생 | 1. 추가 | 2. 검색 | 3. 출력 | 4. 삭제 │교직원 | 5. 추가 | 6. 출력 | 7. 삭제 │ 8. 로그아웃 | 9. 종료 |");
            System.out.println("----------------------------------------------------------------------------------------------------");
            try {
                System.out.print("값을 입력해주세요 : ");
                num = getInput();
            }
            catch (InputMismatchException e) {
                sc = new Scanner(System.in);
                System.out.println("정수형만 입력할 수 있습니다.");
                continue;
            }
            if (num < PREVENT_MINUS_N_ZERO) {
                sc = new Scanner(System.in);
                System.out.println("잘못된 입력입니다.");
                continue;
            }else if (num > INPUT_RANGE_ADMIN_MAIN) {
                sc = new Scanner(System.in);
                System.out.println("잘못된 입력입니다.");
                continue;
            }
            switch(num) {
                case 1:
                    System.out.println("학생 입력을 선택하셨습니다.");break;
                case 2:
                    System.out.println("학생 검색을 선택하셨습니다.");break;
                case 3:
                    System.out.println("학생 리스트 출력을 선택하셨습니다.");break;
                case 4:
                    System.out.println("학생 삭제를 선택하셨습니다.");break;
                case 5:
                    System.out.println("교직원 추가를 선택하셨습니다.");break;
                case 6:
                    System.out.println("교직원 리스트 출력을 선택하셨습니다.");break;
                case 7:
                    System.out.println("교직원 삭제를 선택하셨습니다.");break;
                case 8:
                    System.out.println("로그아웃합니다.");
                    System.out.println("로그인 페이지로 넘어갑니다.");break;
                case 9:
                    onOff=false;
                    System.out.println("학생 관리 시스템을 종료합니다.");
                    break;
            }
            return num;
        }
        return num;
    }

    public void printStudent(Student student) { //출력하는 메서드.
        if(student != null) {
            System.out.println(makeStudentInfo(student));
        }else {
            System.out.println("찾으시는 정보가 존재하지 않습니다.");
        }

    }

    private String makeStudentInfo(Student student){ //인자로 받은 student 타입의 ArrayList 를 출력형식에 맞게 가공하는 메서드
        return "|이름 :" + student.getName() + " \t\t| 학번 :" + student.getId() + "\t\t| 국어:" + student.getkScore()+ " \t| 영어:" + student.geteScore()
                + "\t| 수학:" + student.getmScore()+ "\t| 총점:" + (student.getkScore() + student.geteScore() + student.getmScore())+ "\t" +
                "| 평균 :" + (student.getkScore() + student.geteScore() + student.getmScore()) / 3;
    }
    public void printTeacher(Teacher teacher) {System.out.println(makeTeacherInfo(teacher));
    }
    private String makeTeacherInfo(Teacher teacher) {
        return "이름 :" + teacher.getName() + "\t| 아이디 :" + teacher.getId() + "\t| 담당과목 :" + teacher.getSubject();
    }

    public Student typeStudent() { // 학생 데이터 입력받는 메소드
        String name;
        int id ,kScore, eScore, mScore;
        System.out.print("이름을 입력하세요 :");
        name = sc.nextLine();
        System.out.print("학번을 입력하세요 :");
        id = getInput();
        System.out.print("국어 점수를 입력하세요 :");
        kScore = getScore();
        System.out.print("영어 점수를 입력하세요 :");
        eScore = getScore();
        System.out.print("수학 점수를 입력하세요 :");
        mScore = getScore();
        student = new Student(name, String.valueOf(id), kScore, eScore, mScore);
        return student;
    };

    public Subject typeTeacherSubject() { // 1~3을 반환
        int choose = 0;

        System.out.println("담당 과목을 입력해주세요. 1.국어 2.영어 3.수학");
        System.out.print("입력 : ");
        while(true) {
            choose = getInput();
            if(choose > INPUT_RANGE_TEACHER_SUBJECT) {
                System.out.println("1.국어 2.영어 3.수학입니다. 다시 입력해주세요");
                System.out.print("입력 :");
                sc = new Scanner(System.in);
            }
            else {
                break;
            }
        }
        if(choose == 1) {
            return Subject.KOREAN;
        }else if(choose == 2) {
            return Subject.ENGLISH;
        }else if(choose == 3) {
            return Subject.MATH;
        }else {
            return null;
        }
    }

    public Teacher typeTeacher() {
        String name, id;
        System.out.print("이름을 입력하세요 :");
        name = sc.nextLine();
        System.out.print("아이디를 입력하세요 :");
        id = sc.nextLine();
        teacher = new Teacher(name, id, typeTeacherSubject());
        return teacher;
    }

    public void printSuccessSignUp() {
        System.out.println("학생이 생성되었습니다. 초기 비밀번호는 학번과 동일합니다.");
    }
    public void printDuplicatedIdException() {
        System.out.println("중복된 학번입니다. 다시 입력해주세요.");
    }
}
