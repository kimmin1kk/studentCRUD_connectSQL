package com.studentcrud.view;

import com.studentcrud.user.Student;
import com.studentcrud.user.Teacher;

import java.util.InputMismatchException;
import java.util.Scanner;

import static com.studentcrud.config.Configuration.*;

public abstract class UserViewer {
    Student student = new Student();
    Teacher teacher = new Teacher();
    Scanner sc = new Scanner(System.in);

    public abstract String inputId();

    public String inputPw() { // 비밀번호를 입력하는 메서드
        Scanner sc = new Scanner(System.in);
        String pw = null;
        System.out.print("비밀번호를 입력하세요 :");
        pw = sc.nextLine();
        return pw;
    }

    public int getScore() { //점수 입력할 때 예외처리해둔 메서드
        int a = 0;
        boolean onOff = true;
        while(onOff) {
            try {
                a = Integer.parseInt(sc.nextLine());
            }
            catch (InputMismatchException | NumberFormatException e) {
                sc = new Scanner(System.in);
                System.out.println("정수형만 입력할 수 있습니다.");
                System.out.print("입력 : ");
                continue;
            }
            if(a < MIN_SCORE) {
                System.out.println("최저 점수는 0점입니다. 다시 입력해주세요");
                System.out.print("입력 : ");
                sc = new Scanner(System.in);
            }else if(a > MAX_SCORE) {
                System.out.println("최대 점수는 100점입니다. 다시 입력해주세요");
                System.out.print("입력 : ");
                sc = new Scanner(System.in);
            }else {
                break;
            }
        }
        return a;
    }

    public int getInput() { //정수형만 입력받을 수 있는 메서드
        int a=0;
        while(true) {
            try {
                a = Integer.parseInt(sc.nextLine());
            }catch(InputMismatchException e){
                System.out.println("숫자만 입력하세요. (InputMismatchException 에러 발생");
                System.out.print("입력 : ");
                sc = new Scanner(System.in);
                continue;
            }catch(NumberFormatException e){
                System.out.println("숫자만 입력하세요. (NumberFormatException 에러 발생)");
                System.out.print("입력 : ");
                sc = new Scanner(System.in);
                continue;
            }
            if (a < PREVENT_MINUS) {
                System.out.println("잘못된 접근입니다.");
                System.out.print("다시 입력 : ");
                sc = new Scanner(System.in);
            }else {
                break;
            }
        }
        return a;
    }
}
