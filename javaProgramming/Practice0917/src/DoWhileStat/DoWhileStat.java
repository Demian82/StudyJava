package DoWhileStat;

import java.util.Scanner;

public class DoWhileStat {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        do {
            System.out.print("단 입력(0 입력 시 종료) : ");
            int i = sc.nextInt();
            if (i == 0) {
                break;
            }
            for (int j = 0; j<10; j++) {
                System.out.printf("%d x %d = %d\n", i, j, i*j);
            }
        } while (true);
        System.out.println("프로그램을 종료합니다.");
    }
}
