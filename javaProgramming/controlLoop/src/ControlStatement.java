import java.awt.*;
import java.util.Scanner;

public class ControlStatement {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("숫자 입력: ");
        int num1 = sc.nextInt();

        if (num1>10) {
            System.out.printf("%d는 10보다 크다%n", num1);
        }

        System.out.print("정수 입력: ");
        int num2 = sc.nextInt();

        if(num2 % 2 == 0) {
            System.out.println(num2 + "은/는 짝수");
        }
        else {
            System.out.println(num2 + "은/는 홀수");
        }

        String correctId = "admin";
        String correctPw = "1234";

        System.out.print("아이디 입력: ");
        String id = sc.nextLine();

        if (id.equals(correctId)) {
            System.out.print("비밀번호 입력: ");
            String pw = sc.nextLine();
            if (pw.equals(correctPw)) {
                System.out.println("로그인 성공!");
            } else {
                System.out.println("비밀번호가 틀렸습니다.");
            }
        } else {
            System.out.println("존재하지 않는 아이디");
        }

        System.out.print("첫 번째 수 입력: ");
        int a = sc.nextInt();
        System.out.print("연산자 입력 (+, -, *, /): ");
        char op = sc.next().charAt(0);
        System.out.print("두 번째 수 입력: ");
        int b = sc.nextInt();

        switch (op) {
            case '+':
                System.out.println("result: " + (a+b));
                break;
            case '-':
                System.out.println("result: " + (a-b));
                break;
            case '*':
                System.out.println("result: " + (a*b));
                break;
            case '/':
                System.out.println("result: " + (a/b));
                break;
            default:
                System.out.println("지원하지 않는 연산자입니다.");
        }
    }
}
