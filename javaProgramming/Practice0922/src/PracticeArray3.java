import java.util.Scanner;
import java.util.Random;

public class PracticeArray3 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Random rand = new Random();

        int[] score = new int[10];
        int[] grade = new int[5];

        System.out.print("점수 : ");
        for (int i=0; i<score.length; i++) {
            score[i] = rand.nextInt(101);
            System.out.print(score[i]+", ");
        }
        System.out.println();

        for (int i=0; i<score.length; i++) {
            if (score[i] >= 90) {
                grade[0]++;
            } else if (score[i] >= 80) {
                grade[1]++;
            } else if (score[i] >= 70) {
                grade[2]++;
            } else if (score[i] >= 60) {
                grade[3]++;
            } else {
                grade[4]++;
            }
        }

        System.out.printf("A = %d명, B = %d명, C = %d명, BD= %d명, E = %d명\n",
                grade[0], grade[1], grade[2], grade[3], grade[4]);
    }
}
