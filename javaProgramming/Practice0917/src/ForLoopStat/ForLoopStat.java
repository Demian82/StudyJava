package ForLoopStat;

import java.util.Scanner;

public class ForLoopStat {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("단 입력 : ");
        int i = sc.nextInt();

        for (int j = 0; j<10; j++) {
            System.out.printf("%d x %d = %d\n", i, j, i*j);
        }
    }
}
