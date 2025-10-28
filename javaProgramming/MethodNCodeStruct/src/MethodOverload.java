public class MethodOverload {
    public static void main(String[] args) {
        System.out.println(add(3, 5));
        System.out.println(add(2.5, 4.3));
        System.out.println(add(1, 2, 3));
    }

    public static int add(int a, int b) {
        return a+b;
    }

    public static double add(double a, double b) {
        return a+b;
    }
    public static int add(int a, int b, int c) {
        return a + b + c;
    }
}

// 접근제어자 리턴타입 메소드명 (매개변수1, 매개변수2, ...) { }