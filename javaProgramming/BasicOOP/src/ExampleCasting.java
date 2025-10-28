public class ExampleCasting {
    public static void main(String[] args) {
        Parent p = new Parent();
        Child c = new Child();

        Parent p2 = c;
        Child c2 = (Child)p;
    }
}

class Parent {
    String name;
    int age;
}

class Child extends Parent {
    // String name, int age
    int number;
}