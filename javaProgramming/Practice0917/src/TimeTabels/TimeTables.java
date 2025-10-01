package TimeTabels;

import java.util.Scanner;

public class TimeTables {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Time Tables : ");

        for (int i = 2; i < 10; i++) {
            System.out.printf("%d 단 : \n", i);
            for (int j = 1; j < 10; j++) {
                System.out.printf("%d x %d = %d\n", i, j, i*j);
            }
            System.out.println("-------------");
        }

    }
}
