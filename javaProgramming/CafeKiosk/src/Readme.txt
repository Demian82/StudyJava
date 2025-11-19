import scanner

main {
    Scanner sc = new Scanner(System.in);
    Beverage bingsu = new Beverage(sc, "빙수")

    Beverage[] bvgList = new Beverage[]

    Kiosk kiosk = new Kiosk(sc, bvgList);

    Event smEvt = new Event()
    smEvt.noticeEvent();
    kiosk.menu();smEvt.summerEvent(kiosk);
}

interface Event {
    void noticeEvent();
    void summerEvent(Kiosk kiosk);
}

interface Whippable {
    default boolean whipping(int whip) {
        입력 값에 따라 true와 false 반환
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
            for (int i = 0;  i< items.length; i++) {
                메뉴 목록 순회 및 출력
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
                주문 루프 종료
                주문 내용에 대한 출력
            }
            else {
                System.out.println("Menu Error");
            }
        }
    }

    void orderSheet() {
        for (Beverage item : items) {
            목록의 각 아이템의 주문 받은 내용 출력
        }
    }
}