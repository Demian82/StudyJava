import java.util.Scanner;

public class Operator {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String a = "apple";
        String b = new String("apple");

        System.out.println(a.equals(b));
        System.out.println(a==b);

        System.out.print("문자열 입력: ");
        String x = sc.nextLine();
        String y = "java";

        System.out.println("입력값이 java와 같은가? " + x.equals(y));
    }
}
