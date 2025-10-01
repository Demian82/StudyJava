import java.util.Scanner;
import java.util.HashMap;


public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
//        System.out.print("숫자 입력: ");
//        int num1 = sc.nextInt();
//
//        if(num1 > 10) {
//            System.out.println(num1 + "은/는 10보다 크다");
//        }
//
//        System.out.print("숫자 입력: ");
//        int num2 = sc.nextInt();
//
//        if(num2 % 2 == 0) {
//            System.out.println(num2 + "은/는 짝수");
//        }
//        else {
//            System.out.println(num2 + "은/는 홀수");
//        }
//
//        String ID_df = "angela";
//        int PW_df = 1234;
//
//        String ID_1 = sc.nextLine();
//        if (ID_1.equals(ID_df)) {
//            int PW_1 = sc.nextInt();
//            if (PW_1 == PW_df) {
//                System.out.println("Login success!");
//            }
//            else {
//                System.out.println("Password error!");
//            }
//        }
//        else {
//            System.out.println("ID error!");
//        }

        HashMap<String, String> IDPW = new HashMap<>();
        IDPW.put("angela", "1234");

        while (true) {
            System.out.print("Input Id : ");
            String ID = sc.next();
            if (IDPW.containsKey(ID)) {
                System.out.print("Input PW : ");
                String PW = sc.next();
                if (PW.equals(IDPW.get(ID))) {
                    System.out.println("Login success!");
                    break;
                }
                else {
                    System.out.println("Password error!");
                }
            }
            else {
                System.out.println("ID error!");
            }
        }
    }
}