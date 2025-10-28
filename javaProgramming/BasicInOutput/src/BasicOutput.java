public class BasicOutput {
    public static void main(String[] args) {
        int year = 2025;
        float pi = 3.14F;
        char a = 'A';
        String simple = "Java is simple.";
        boolean t = true;

        System.out.println(year);
        System.out.println(pi);
        System.out.println(a);
        System.out.println(simple);
        System.out.println(t);

        // 형변환
        int score = 93;
        float score_f = (float)score;
        System.out.println(score_f);

        double score_d = 93.4;
        int score_i = (int)score_d;
        System.out.println(score_i);

        String name = "Your name";
        int age = 25;
        double scoref = 95.1234;

        System.out.printf("Name  : %s%n", name);
        System.out.printf("Age   : %d%n", age);
        System.out.printf("Score : %.6f%n", scoref);
    }
}
