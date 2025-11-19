//package order;

class Order {
    // field
    private String customer;
    private Item[] items = new Item[5];
    int count = 0;

    static class Item{
        String name;
        int price;

        Item(String name, int price) {
            this.name = name;
            this.price = price;
        }
    }

    // constructor
    Order(String customer) {
        this.customer = customer;
    }

    // method
    void addItem(Item item) {
        if (count>=5) {
            System.out.println("Item List is full");
        }

        items[count] = item;
        count++;
    }
    void printOrder() {
        System.out.println("고객: "+customer);
        for (int i=0; i<count; i++) {
            System.out.printf("- %s : %d원\n",
                    items[i].name, items[i].price);
        }
    }
}

public class Main {
    public static void main(String[] args) {
        Order order1 = new Order("Angela");

        order1.addItem(new Order.Item("Coffee", 3000));
        order1.addItem(new Order.Item("Sandwich", 5500));
        order1.printOrder();
    }
}

