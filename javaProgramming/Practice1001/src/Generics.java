public class Generics {
    public static void main(String[] args) {
        System.out.println(add(3, 5));
        System.out.println(add(2.5, 4.3));
    }

    public static <T extends Number> double add (T a, T b) {
        return a.doubleValue() + b.doubleValue();
    }
}

// 접근제어자 제네릭타입 리턴타입 메소드명 (T a, T b) { }
// 제네릭타입에 <T extends Number>: 숫자 타입은 모두 input으로 받겠다 라는 뜻