public class GenericError_1 {
    public static void main(String[] args) {
        Coffee[] arrCoffee = {
                new Coffee("Americano"),
                new Coffee("Espreso")
        };

        Juice[] arrJuice = {
                new Juice("Orange"),
                new Juice("Apple"),
        };

        Menu<Coffee> coffeeMenu = new Menu<>(arrCoffee);
        Menu<Juice> juiceMenu = new Menu<>(arrJuice);

        for (int i = 0; i<arrCoffee.length; i++) {
            Coffee cm = coffeeMenu.getMenu(i);
            System.out.printf("%d번째 메뉴: %s\n", i+1, cm.getName());
        }

        for (int j = 0; j<arrJuice.length; j++) {
            Juice jm = juiceMenu.getMenu(j);
            System.out.printf("%d번째 메뉴: %s\n", j+1, jm.getName());
        }
    }
}

class Coffee {
    private String  name;

    public Coffee(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

class Juice {
    private String  name;

    public Juice(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

class Menu<T> {
    private T[] menu;

    public Menu(T[] menu) {
        this.menu = menu;
    }

    public T getMenu(int index) {
        return menu[index];
    }
}