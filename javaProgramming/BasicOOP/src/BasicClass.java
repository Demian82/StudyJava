import java.util.Scanner;

public class BasicClass {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
//        Car car_obj = new Car();
        Car red_car = new Car();
//        Car red_car = new Car("red");
//        car_obj.model = sc.nextLine();
//        car_obj.startup();

        System.out.print("차량명: ");
        String model = sc.nextLine();

        red_car.setModel(model);
        red_car.setSpeed(30);
        red_car.setStarted(true);
        red_car.showInfo();
    }
}

class Car{

    // field
    private int speed;
    private boolean started;
    private String model;

    // constructor
    // 기본 생성자 사용 or 생성자 내부 초기화
//    Car() {
    ////        this.color = "red";
//    } // or 객체 생성(호출) 시 생성자 매개변수로 객체 내부 변수 초기화
    Car() {

    }

    // method
    public String getModel() {
        return model;
    }

    public int getSpeed() {
        return speed;
    }

    public String getStarted() {
        String turnOnOff = null;
        if (started == true) {
            turnOnOff = "ON";
        }
        else {
            turnOnOff = "OFF";
        }
        return turnOnOff;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setSpeed(int speed) {
        if (speed >= 0) {
            this.speed = speed;
        }
        else {
            System.out.println("속도는 음수가 될 수 없습니다.");
        }
    }

    public void setStarted(boolean key) {
        if (key == true) {
            System.out.println("시동을 겁니다.");
            started = true;
        }
        else {
            System.out.println("시동을 끕니다.");
            started = false;
        }
    }

    public void showInfo() {
        System.out.printf("%s / 속도:%d / 시동:%s\n", getModel(), getSpeed(), getStarted());
    }
}