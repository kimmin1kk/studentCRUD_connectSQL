package com.studentcrud.view;

import java.util.InputMismatchException;
import java.util.Scanner;

import static com.studentcrud.config.Configuration.*;

public class TeacherViewer extends UserViewer{
//    @Override
//    protected void checkInstanceValidation(User user) {
//        if (!(user instanceof Teacher)) throw new IllegalArgumentException();
//    }

    public String inputId() { // 아이디를 입력하는 메서드
        String id = null;
        System.out.print("교직원 아이디를 입력하세요 :");
        id = sc.nextLine();
        return id;
    }

    public int teacherMainPage() { // 학생으로 로그인 했을 때 ui 메서드
        int num=0;
        boolean onOff= true;
        while(onOff) {
            System.out.println("---------------------------");
            System.out.println("| 학생 관리 시스템 (교직원용) |");
            System.out.println("------------------------------------------------------------------------------------------");
            System.out.println("| 1. 학생 검색 | 2. 학생 리스트 출력 | 3. 담당과목 점수 수정 | 4. 정보 수정 | 5. 로그아웃 | 6. 종료 |");
            System.out.println("------------------------------------------------------------------------------------------");
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
            }else if (num > INPUT_RANGE_TEACHER_MAIN) {
                sc = new Scanner(System.in);
                System.out.println("잘못된 입력입니다.");
                continue;
            }
            switch(num) {
                case 1:
                    System.out.println("학생 검색을 선택하셨습니다.");break;
                case 2:
                    System.out.println("학생 리스트 출력을 선택하셨습니다.");break;
                case 3:
                    break;
                case 4:
                    System.out.println("정보 수정을 선택하셨습니다.");break;
                case 5:
                    System.out.println("로그아웃합니다.");
                    System.out.println("로그인 페이지로 넘어갑니다.");break;
                case 6:
                    System.out.println("학생 관리 시스템을 종료합니다.");break;
            }
            return num;
        }
        return num;
    }

    public int replaceTeacherInformation() { // 학생으로 로그인해서 수정을 선택했을 때 호출되는 ui 메서드
        int num = 0;
        boolean onOff = true;
        while (onOff) {
            System.out.println("-----------------");
            System.out.println("| 교직원 정보 수정 |");
            System.out.println("-----------------------------------------");
            System.out.println("| 1. 이름 변경 | 2. 비밀번호 변경 | 3. 취소 |");
            System.out.println("-----------------------------------------");
            try {
                System.out.print("값을 입력해주세요 : ");
                num = getInput();
            } catch (InputMismatchException e) {
                sc = new Scanner(System.in);
                System.out.println("정수형만 입력할 수 있습니다.");
                continue;
            }
            if (num < PREVENT_MINUS_N_ZERO) {
                sc = new Scanner(System.in);
                System.out.println("잘못된 입력입니다.");
                continue;
            } else if (num > INPUT_RANGE_MODIFY) {
                sc = new Scanner(System.in);
                System.out.println("잘못된 입력입니다.");
                continue;
            }
            switch(num) {
                case 1:
                    System.out.println("이름 변경을 선택하셨습니다.");break;
                case 2:
                    System.out.println("비밀번호 변경을 선택하셨습니다.");break;
                case 3:
                    System.out.println("정보 수정을 취소합니다.");break;
            }
            return num;
        }
        return num;
    }

    public String replaceTeacherName() { // 이름을 수정할 때 쓰는 메서드
        System.out.print("수정할 이름을 입력해주세요 :");
        return sc.nextLine();
    }


    public String replaceTeacherPassword() { // 비밀번호를 수정할 때 쓰는 메서드
        System.out.print("수정할 비밀번호를 입력해주세요 :");
        return sc.nextLine();
    }

    public int editStudentKoreanScore() {
        System.out.print("수정된 국어 점수를 입력해주세요 :");
        return getScore();
    }
    public int editStudentEnglishScore() {
        System.out.print("수정된 영어 점수를 입력해주세요 :");
        return getScore();
    }
    public int editStudentMathScore() {
        System.out.print("수정된 수학 점수를 입력해주세요 :");
        return getScore();
    }
    public void messageEditKoreanScore() {
        System.out.println("학생의 국어 점수를 수정합니다.");
    }
    public void messageEditEnglishScore() {
        System.out.println("학생의 영어 점수를 수정합니다.");
    }    public void messageEditMathScore() {
        System.out.println("학생의 수학 점수를 수정합니다.");
    }

}
