import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Storage : ");
        int storage = sc.nextInt();

        System.out.print("Usage : ");
        int usage = sc.nextInt();

        System.out.println("Storage : " + storage);

        int total_usage = 0;
        int count  = 0;
        System.out.println("Usage in " + count + " month : " + usage);
        while (true) {
            int change = sc.nextInt();
            usage = usage * (100-change) / 100;
            System.out.println("Using in this month : " + usage);
            total_usage += usage;
            if (total_usage > storage){
                System.out.println("Usage in " + count + " month : " + usage);
                count++;
                break;
            }
            else {
                storage -= total_usage;
                System.out.println("남은 저수량 : " + storage);
                System.out.println("Usage in " + count + " month : " + total_usage);
                count++;
            }
        }
    }
}
