public class KindOfGeneric {
    public static void main(String[] args) {
        Box<Product> box1 = new Box<>(new Coffee());
        Box<Product> box2 = new Box<>(new Juice());
        Printer<Product> printer = new ProductPrinter();
        System.out.println(box1.getItem());
        System.out.println(box2.getItem());

        Processor<Product, String> toReceipt =
                p-> "Receipt: "+ p.name + " order complete!";
        System.out.println(toReceipt.process((box1.getItem())));
    }
}

class Product {
    String name;
    Product(String name) {
        this.name = name;
    }
}

class Coffee extends Product {
    Coffee() {
        super("Coffee");
    }
}

class Juice extends Product {
    Juice() {
        super("주스");
    }
}

class Box<T> {
    private T item;
    Box(T item) {
        this.item = item;
    }
    public T getItem() {
        return item;
    }
}

@FunctionalInterface
interface Processor<T, R> {
    R process(T item);
}

interface Printer<T> {
    void print(T item);
}

class ProductPrinter<T> implements  Printer<T> {
    @Override
    public void print(T item) {
//        System.out.println("상품 출력: "+item.name);
    }
}

