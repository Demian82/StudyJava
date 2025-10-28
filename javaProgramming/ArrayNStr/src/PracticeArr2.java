import java.util.Scanner;

public class PracticeArr2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int[] arr = new int[5];
        int total = 0;


        System.out.println("5명의 점수를 입력하세요: ");
        for (int i=0; i<arr.length; i++) {
            arr[i] = sc.nextInt();
            total += arr[i];
        }

        double avg = total / arr.length;

        System.out.println("평균 = " + avg);
    }
}