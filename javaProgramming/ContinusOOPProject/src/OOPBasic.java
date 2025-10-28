
// 동물 클래스
abstract class Animal{
    // field
    String name;
    int age;

    // constructor
    Animal(String name, int age) {
        this.name = name;
        this.age = age;
    }

    // method
    public void showInfo() {
        System.out.printf("%s / Age: %d\n", name, age);
    }

    public void eat() {
        System.out.println("Sound of feeding");
    }
    abstract public void sound();
}

class Dog extends Animal {
    String breed;

    // constructor
    Dog(String name, int age, String breed) {
        super(name, age);
        this.breed = breed;
    }

    // method
    @Override
    public void showInfo() {
        System.out.printf("%s / Age: %d / Breed: %s\n", name, age, this.breed);
    }
    public String getName() {
        return name;
    }
    public  void roll() {
        System.out.println("강아지가 바닥에 뒹굽니다.");
    }

    @Override
    public void sound() {
        System.out.println("멍멍");
    }

    void wagTail() {
        System.out.println("꼬리를 흔든다");
    }
}

class Cat extends Animal {
    // field

    // constructor
    Cat (String name, int age) {
        super(name, age);
    }

    // method
    @Override
    public void showInfo() {
        System.out.printf("%s / Age: %d\n", name, age);
    }
    public String getName() {
        return name;
    }

    public void rub() {
        System.out.println("고양이가 문지릅니다.");
    }

    @Override
    public void sound() {
        System.out.println("야옹");
    }
}

// 메인 메서드
public class OOPBasic {
    public static void main(String[] args) {
        Animal dog = new Dog("dog", 3, "aaaa");
        Cat cat = new Cat("cat", 2);

        dog.showInfo();
        dog.sound();
        dog.eat();
        System.out.println("이 동물은 "+ dog.name+ " 입니다.");

        Dog dog1 = (Dog) dog;
        dog1.wagTail();
    }
}