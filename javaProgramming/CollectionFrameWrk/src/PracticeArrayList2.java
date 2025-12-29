import java.util.*;


public class PracticeArrayList2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        List<Integer> list = new ArrayList<>();

        while(true) {
            System.out.print("정수를 입력하세요 (0 입력시 종료): ");
            int num = sc.nextInt();
            if (num == 0)
                break;
            list.add(num);
        }


        System.out.println("\n전체 목록 : " + list);
        System.out.println("짝수만 출력 : ");
        for (int x: list) {
            if( x % 2 == 0) {
                System.out.print(x + " ");
            }
        }
        if (!list.isEmpty()) {
            int max = Collections.max(list);
            int min = Collections.max(list);

            double sum = 0;
            for (int i: list) {
                sum += i;
            }
            double avg = sum / list.size();

            System.out.println("최댓값: " + max);
            System.out.println("최솟값: " + min);
            System.out.println("평균: " + avg);
        }

        System.out.print("\n삭제할 값을 입력하세요: ");
        int target = sc.nextInt();

        list.removeIf(x-> x== target);
        System.out.println("삭제 후 목록: " + list);

    }
}
