public class BasicPrint {
    public static void main(String[] args) {
        System.out.println("Hello world!");

        // 행 주석
        /*
        범위 주석
         */
        /**
         * document 주석; javadoc 명령어로 API doc을 생성하는데 사용
         */

        int intNum = 10;
        final int time = 15; // constant

        /*
        기본 자료형
            Stack 영역에 있는 변수에, 실제 데이터 값을 저장
            int, long, float, double, Boolean, char...
        참조 자료형
            변수에, 데이터가 저장된 메모리 주소 값을 저장
            실제 값은 Heap 영역에 있으며 주소값은 Stack 영역에 있는 변수에 저장
            String, 클래스, 인터페이스, 배열....
         */

        // 문자형
        char var1 = 'A';    // 'A' 문자와 매핑되는 숫자 : 65
        char var2 = '가';    // '가' 문자와 매핑되는 숫자 : 44032

        // 실수형
        float varF = 1.0F;
        double varD = 1.0;

        boolean stop = true;
        boolean start = false;

        String str = "Aleph";

        int year = 2025;
        double pi = 3.14159;
        float piF = 3.14F;
        char alpha = 'A';
        String jvIsSim = "Java is simple.";
        boolean tf = true;

        System.out.printf("%d, %f, %f, %c\n%s\n",
                year, pi, piF, alpha, jvIsSim);
        if (tf == true){
            System.out.println(tf);
        }

        // type casting
        double d2I = 9.99;
        int d2I_i = (int)d2I;
        System.out.println(d2I_i);

        System.out.printf("%f%n", pi);
        System.out.printf("%.2f%n", pi);
        System.out.printf("%8.2f%n", pi);

        String name = "Your name";
        int age = 25;
        double score = 95.1234;

        System.out.printf("Name  : %s%n", name);
        System.out.printf("Age   : %d%n", age);
        System.out.printf("Score : %.6f%n", score);
    }
}
