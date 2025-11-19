public class MultipleTypePara {
    public static void main(String[] args) {
        Pair<String, Integer> menu = new Pair<>("아메리카노", 3000);
        String name = menu.getKey();
        Integer price = menu.getValue();
        System.out.println("Menu: " + name + "\nPrice: " + price + "\n");

        Pair<String, String> info = new Pair<>("Today's menu", "Sandwich");
        String title = info.getKey();
        String content = info.getValue();
        System.out.println(title + ": " + content);

    }
}

class Pair<K, V> {
    private K key;
    private V value;

    public Pair(K key, V value) {
        this.key = key;
        this.value = value;
    }

    public K getKey() {
        return key;
    }

    public V getValue() {
        return value;
    }
}
