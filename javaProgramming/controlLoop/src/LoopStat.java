import java.util.Scanner;

public class LoopStat {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("단 입력 : ");
        int i = sc.nextInt();

        for (int j = 0; j<10; j++) {
            System.out.printf("%d x %d = %d\n", i, j, i*j);
        }

        System.out.print("단 입력 : ");
        int a = sc.nextInt();
        int b = 1;
        while (b < 10) {
            System.out.printf("%d x %d = %d\n", a, b, a*b);
            b++;
        }

        do {
            System.out.print("단 입력(0 입력 시 종료) : ");
            int n = sc.nextInt();
            if (n == 0) {
                break;
            }
            for (int m = 0; m<10; m++) {
                System.out.printf("%d x %d = %d\n", n, m, n*m);
            }
        } while (true);
        System.out.println("프로그램을 종료합니다.");
    }
}
