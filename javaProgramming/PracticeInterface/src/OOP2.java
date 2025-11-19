public class OOP2 {
    public static void main(String[] args) {
        Creature c1 = new Tiger("호랑이");
        Creature c2 = new Penguin("펭귄");
        Creature c3 = new Whale("고래");
        Animal otter = new Animal("수달") {
            @Override
            void move() { System.out.println(name + "이 미끄러지듯이 이동합니다."); }
            @Override
            void eat() { System.out.println(name + "이 조개를 먹습니다."); }
        };

        c1.info();;
        c1.breathe();
        c1.move();
        c1.eat();

        c2.move();
        c3.move();

        if (c2 instanceof Swimmable s) {
            s.swim();
        }

        if (c3 instanceof  Swimmable s) {
            s.swim();
        }

        if (c2 instanceof Flyable f) {
            f.fly();
        }

        Swimmable s1 = new Swimmable() {
            @Override
            public void swim() {
                System.out.println("익명: 한 번만 수영을 한다");
            }
        };
        s1.swim();

        new Flyable() {
            @Override
            public void fly() {
                System.out.println("익명: 한 번만 날아갑니다.");
            }
        }.fly();
    }
}

interface Diveable extends Swimmable {
    void deepDive();
}

interface Flyable {
    // const field
    default void fly() {
        System.out.println("하늘을 난다");
    };
}

interface Swimmable {
    // const field

    // method
    public abstract void swim();
}

abstract class Creature {
    // field
    String name;

    // constructor
    Creature(String name)
    {
        this.name = name;
    }

    // method
    abstract void move();
    abstract void eat();
    abstract void breathe();

    void info() {
        System.out.println("Name: "+ name);
    }

}

abstract class Animal extends Creature {
    // field

    // constructor
    // super Animal
    Animal(String name) {
        super(name);
    }

    // method
    @Override
    void breathe() {
        System.out.println(name + "가 코로 숨을 쉽니다.");
    }
}

abstract class Fish extends Creature {
    // field

    // constructor
    // super Animal
    Fish(String name) {
        super(name);
    }

    // method
    @Override
    void breathe() {
        System.out.println(name + "가 아가미로 숨을 쉽니다.");
    }
}

class Tiger extends Animal {
    // field

    // constructor
    Tiger(String name){
        super(name);
    }
    // method
    @Override
    void move() {
        System.out.println("네 발로 달립니다.");
    }

    @Override
    void eat() {
        System.out.println("고기를 먹습니다.");
    }

}

class Penguin extends Animal implements Swimmable, Flyable {
    // field

    // constructor
    Penguin(String name){
        super(name);
    }
    // method
    @Override
    void move() {
        System.out.println("두 발로 걷습니다.");
    }

    @Override
    void eat() {
        System.out.println("생선을 먹습니다.");
    }
    @Override
    public void swim() {
        System.out.println(name + "이 수영을 합니다.");
    }
    @Override
    public void fly() {
        System.out.println(name + "이 하늘을 난다.");
    }

}

class Whale extends Fish implements Diveable{
    // field

    // constructor
    Whale(String name) {
        super(name);
    }

    @Override
    void move() {
        System.out.println("헤엄칩니다.");
    }

    @Override
    void eat() {
        System.out.println("플랑크톤을 먹습니다.");
    }

    @Override
    public void swim() {
        System.out.println(name + "이 수영을 합니다.");
    }

    @Override
    public void deepDive() {
        System.out.println(name+"이 잠수를 합니다.");
    };
}

