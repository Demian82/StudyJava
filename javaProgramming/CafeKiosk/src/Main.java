import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // 익명 자식 객체 / 이벤트 메뉴 "빙수"
        Beverage bingsu = new Beverage(sc, "빙수") {
            @Override
            void servingTray() {
                System.out.println("서빙 준비 목록\n: 서빙트레이, 연유 1포, 숟가락,메뉴");
            }

            @Override
            void manufact() {
                System.out.printf("음료 이름: %s\nHOT/ICE: %s\n",getName(), getIceHot());
                System.out.println("(해당 메뉴는 ICE로만 제공됩니다.)");
                System.out.println("제조법 \n: 얼린 우유를 갈고, 그 위에 다양한 과일과 팥을 올려 마무리");
            }

            @Override
            void hotOrIce() {
                this.srvIceHot = "Ice";
            }
        };

        // 메뉴 목록
        Beverage[] bvgList = new Beverage[]{
                new Espresso(sc, "에스프레소"),
                new Mocha(sc, "카페모카"),
                new GreenTea(sc, "녹차"),
                bingsu
        };

        // kiosk 객체 생성
        Kiosk kiosk = new Kiosk(sc, bvgList);

        // 익명 구현 객체 / 이벤트 내용
        Event smEvt = new Event() {
            @Override
            public void noticeEvent() {
                System.out.println("빙수를 주문했다면 빙수 가격 20% 할인\n기간: ~mm/dd");
            }

            @Override
            public  void summerEvent(Kiosk kiosk) {
                for(int i=0; i<kiosk.items.length; i++) {
                    if (kiosk.items[i].getName().equals("빙수") && kiosk.items[i].getCount() >= 1) {
                        System.out.println("빙수 가격 20% 할인");
                    }
                }
            }
        };

        smEvt.noticeEvent();
        kiosk.menu();
        smEvt.summerEvent(kiosk);
    }
}

// 이벤트 구현 인터페이스
interface Event {
    void noticeEvent();
    void summerEvent(Kiosk kiosk);
}

// 휘핑 크림 사용 가능한 음료에 대한 인터페이스
interface Whippable {
    default boolean whipping(int whip) {
        if (whip==1) {
            return true;
        }
        else {
            return false;
        }
    }
}

class Kiosk {
    // field
    Scanner sc;
    Beverage[] items;

    // constructor
    public Kiosk(Scanner sc, Beverage[] items) {
        this.sc = sc;
        this.items = items;
    }

    // method
    public void menu() {
        while (true) {
            System.out.print("메뉴 선택 ");
            for (int i = 0;  i< items.length; i++) {
                System.out.printf("%d번.%s ", i+1, items[i].getName());
                if ((i+1)%4 == 0){
                    System.out.print("\n");
                }
            }
            System.out.printf("%d번.주문종료\n", items.length+1);

            System.out.print("입력: ");
            int menuNum = sc.nextInt();
            if (menuNum > 0 && menuNum <= items.length) {
                items[menuNum-1].addCount();
                items[menuNum-1].hotOrIce();
                if (items[menuNum-1] instanceof Whippable w) {
                    System.out.println("휘핑크림 추가: 1.Yes, 2.No");
                    System.out.print("입력: ");
                    int whip = sc.nextInt();
                    items[menuNum-1].whippingStat = w.whipping(whip);
                }
            }
            else if (menuNum == items.length+1) {
                System.out.println("==========주문종료==========");
                orderSheet();
                break;
            }
            else {
                System.out.println("Menu Error");
            }
        }
    }

    void orderSheet() {
        for (Beverage item : items) {
            if (item.getCount() > 0) {
                System.out.printf("%s: %d잔\n", item.getName(), item.getCount());
                item.manufact();
                System.out.println("========================");
            }
        }
    }
}

abstract class Beverage {
    // field
    Scanner sc;
    private String name;
    private int count = 0; // 주문된 횟수
    String srvIceHot;
    boolean whippingStat = false;

    // constructor
    public Beverage(Scanner sc, String name) {
        this.sc = sc;
        this.name = name;
    }

    // get set
    String getName() {
        return name;
    }
    String getIceHot() {
        return srvIceHot;
    }
    int getCount() {
        return count;
    }

    // method
    abstract void manufact();
    abstract void servingTray();

    void hotOrIce() {
        System.out.println("1. Hot, 2. Ice");
        System.out.print("입력: ");
        int serve = sc.nextInt();
        if (serve == 1) {
            srvIceHot = "Hot";
        }
        else {
            srvIceHot = "Ice";
        }
    }

    void addCount() {
        count++;
    }
}

class Espresso extends Beverage implements Whippable {
    // field

    // constructor
    Espresso(Scanner sc, String name) {
        super(sc, name);
    }
    // method
    @Override
    void servingTray() {
        if (whippingStat) {
            System.out.println("서빙 준비 목록\n: 서빙트레이, 음료");
        }
        else {
            System.out.println("서빙 준비 목록\n: 서빙트레이, 빈 잔, 물 한 잔,\n설탕 1포, 에스프레소 1샷 2잔");
        }
    }

    @Override
    void manufact() {
        System.out.printf("음료 이름: %s\nHOT/ICE: %s\n",getName(), getIceHot());
        if (whippingStat) {
            servingTray();
            System.out.println("제조법 \n: 에스프레소 2샷과 물을 2:1로 희석 한 후\n휘핑크림을 올림");
        }
        else {
            servingTray();
        }
    }
}

class Mocha extends Beverage implements Whippable {
    // field

    // constructor
    Mocha(Scanner sc, String name) {
        super(sc, name);
    }
    // method
    @Override
    void servingTray() {
        System.out.println("서빙 준비 목록\n: 서빙트레이, 음료");
    }

    @Override
    void manufact() {
        System.out.printf("음료 이름: %s\nHOT/ICE: %s\n",getName(), getIceHot());
        if (whippingStat) {
            servingTray();
            System.out.println("제조법 \n: 컵에 초콜릿 시럽을 따른 후 에스프레소 1샷, 우유를 채우고 휘핑크림을 올림");
        }
        else {
            servingTray();
            System.out.println("제조법 \n: 컵에 초콜릿 시럽을 따른 후\n에스프레소 1샷, 우유를 채움");
        }
    }
}

class GreenTea extends Beverage {
    // field

    // constructor
    GreenTea(Scanner sc, String name) {
        super(sc, name);
    }
    // method
    @Override
    void servingTray() {
        System.out.println("서빙 준비 목록\n: 서빙트레이, 음료");
    }

    @Override
    void manufact() {
        System.out.printf("음료 이름: %s\nHOT/ICE: %s\n",getName(), getIceHot());
        System.out.println("제조법 \n: 찻잎을 2~3분간 우려냄.");
    }
}