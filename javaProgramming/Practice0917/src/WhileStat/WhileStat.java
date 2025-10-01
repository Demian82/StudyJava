package WhileStat;

import java.util.Scanner;

public class WhileStat {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("단 입력 : ");
        int i = sc.nextInt();
        int j = 1;
        while (j < 10) {
            System.out.printf("%d x %d = %d\n", i, j, i*j);
            j++;
        }
    }
}
