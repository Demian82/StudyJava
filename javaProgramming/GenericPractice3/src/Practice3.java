public class Practice3 {
    public static void main(String[] args) {
        Box<String> b1 = new Box<>("Hello");
        Box<Integer> b2 = new Box<>(100);

        System.out.println("b1 = " + b1.getValue());
        System.out.println("b2 = " + b2.getValue());

        String[] words = {"Java", "Generic", "Practice"};
        Integer[] nums = {1, 2, 3};

        System.out.println("=== words ===");
        ArrayUtil.printAll(words);

        System.out.println("=== nums ===");
        ArrayUtil.printAll(nums);
    }
}

class Box<T> {
    private  T value;

    Box(T value) {
        this.value = value;
    }

    T getValue() {
        return value;
    }
}

class ArrayUtil {
    public static <T> void printAll(T[] array) {
        for (int i=0; i< array.length; i++) {
            System.out.println(array[i]);
        }
    }
}