public class PracticeArr1 {
    public static void main(String[] args) {
        int[] numbers1 = {1, 3, 4, 7, 9};

        int[] numbers2 = new int[5];
        numbers2[0] = 1;
        numbers2[1] = 3;

        System.out.println(numbers1[2]);

        numbers1[2] = 99;
        System.out.println(numbers1[2]);

        for (int i=0; i<numbers1.length; i++) {
            System.out.print(numbers1[i]);
            if(i<numbers1.length-1) {
                System.out.print(", ");
            }
        }
        System.out.println("");
    }
}
