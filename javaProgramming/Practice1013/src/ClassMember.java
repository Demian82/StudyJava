import java.util.Scanner;

public class ClassMember {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Car red_car = new Car();
        System.out.print("차량 색을 입력하세요: ");
        red_car.color = sc.nextLine();
        System.out.print("차량 번호를 입력하세요: ");
        red_car.number = sc.nextInt();
        System.out.println(red_car.color);
        System.out.println(red_car.number);
        red_car.stop();
        red_car.drive();
    }
}

class Car {
    String color;
    int number;
    void stop() {
        System.out.println("STOP!!");

    }
    void drive() {
        System.out.println(color + "색 " + number + "번 자동차가 달립니다.");
    }
}
