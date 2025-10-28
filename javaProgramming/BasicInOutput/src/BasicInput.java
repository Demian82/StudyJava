import java.util.Scanner;

public class BasicInput {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("이름 입력 : ");
        String name = sc.next();

        System.out.print("나이 입력 : ");
        int age = sc.nextInt();

        System.out.print("키 입력 : ");
        double height = sc.nextDouble();

        System.out.printf("이름: %s, 나이: %d, 키: %.1fcm%n", name, age, height);

    }
}
