import java.util.ArrayList;
import java.util.List;

public class PracticeArrayList1 {
    public static void main(String[] args) {
        int[] nums1 = new int[5];
        int size = 0;

        nums1[size++] = 10;
        nums1[size++] = 20;
        nums1[size++] = 30;

        int idx =1;
        for(int i=idx; i<size-1; i++){
            nums1[i] = nums1[i+1];
        }
        size--;
        nums1[size] = 0;

        for (int i=0; i<size; i++){
            System.out.println(nums1[i]);
        }

        List<Integer> nums2 = new ArrayList<>();

        nums2.add(10);
        nums2.add(20);
        nums2.add(30);

        nums2.remove(1);

        for (int n: nums2) {
            System.out.println(n);
        }

    }
}
