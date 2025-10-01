import java.util.Scanner;

public class PracticeArray2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int[] arr = new int[5];

        System.out.println("5명의 점수를 입력하세요: ");
        for (int i=0; i<arr.length; i++) {
            arr[i] = sc.nextInt();
        }

        int total = 0;

        for (int i=0; i<arr.length; i++) {
            total += arr[i];
        }
        float avg = total / arr.length;

        System.out.println("평균 = " + avg);
    }
}
