import java.util.Scanner;

public class PracticeMethod {
    public static void main(String[] args) {
        hello();
        Scanner sc = new Scanner(System.in);
        int zayin = sc.nextInt();
        evalEvenOrOdd(zayin);

        System.out.println("1부터 10까지의 합 = " + gausSumEx());
        System.out.print("숫자 입력(팩토리얼 연산): ");
        int num = sc.nextInt();
        System.out.println("결과: "+ pactorial(num));

    }

    public static void hello() {
        String helloJava = "Hello Java\n";
        for (int i=0; i<5; i++) {
            System.out.print(helloJava);
        }
    }

    public static void evalEvenOrOdd(int a) {
        if (a % 2 == 0) {
            System.out.println(a+ "은/는 짝수입니다.");
        }
        else {
            System.out.println(a+"은/는 홀수입니다.");
        }
    }

    public static int gausSumEx() {
        int sum = 0;
        for (int i=1; i<=10; i++) {
            sum += i;
        }

        return sum;
    }

    public static int pactorial(int a) {
        int p = 1;
        if (a<0) {
            return -1;
        }

        for (int i = 1; i<=a; i++) {
            p *= i;
        }

        return p;
    }
}