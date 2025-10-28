public class VariableParameter {
    public static void main(String[] args) {
        System.out.println(add(3, 5));
        System.out.println(add(1, 2, 3));
        System.out.println(add(3));
    }

    public static int add(int... numbers) {
        int sum = 0;
        for (int n: numbers){
            sum += n;
        }
        return sum;
    }
}

// 접근제어자 리턴타입 메소드명 (자료형... itmes) { }
// 매개변수로 (자료형[] items)를 사용하는 것과의 차이
// -가변길이라는 점에서 비슷해 보이지만, (자료형[] items)의 경우에는 호출 시 배열로 전달해줘야 함
// -(자료형... items)의 경우에는 컴파일러가 알아서 배열로 만들어 전달
//  - 1, 2, 3 과 같이 넘겨도 되고 new int[]{4, 5, 6}과 같이 배열 형태로 넘겨줘도 됨