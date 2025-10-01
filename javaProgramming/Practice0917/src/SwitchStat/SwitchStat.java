package SwitchStat;

import java.util.Scanner;

public class SwitchStat {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Input 1st number : ");
        int num1 = sc.nextInt();

        System.out.print("Input operator (+, -, *, /) : ");
        String operator = sc.next();

        System.out.print("Input 2nd number : ");
        int num2 = sc.nextInt();

        switch (operator) {
            case "+":
                System.out.println("Result : " + (num1 + num2));
                break;
            case "-":
                System.out.println("Result : " + (num1 - num2));
                break;
            case "*":
                System.out.println("Result : " + (num1 * num2));
                break;
            case "/":
                System.out.println("Result : " + (num1 / num2) );
                break;
            default:
                System.out.println("Wrong operator");
        }
    }
}
