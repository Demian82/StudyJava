public class TimesTableInMatrix {
    public static void main(String[] args) {
        // 9x9 인 matrix객체 생성
        int[][] table = new int[9][9];

        for (int i=0; i<table.length; i++) {
            for (int j=0; j<table[i].length; j++) {
                table[i][j] = (i+1)*(j+1);
            }
        }

        for (int i=0; i<table.length; i++) {
            for (int j=0; j<table[i].length; j++) {
                System.out.printf("%dx%d=%2d", i+1, j+1, table[i][j]);
                if (j<8) {
                    System.out.print("  ");
                }
                else {
                    System.out.print("\n");
                }
            }
        }
    }
}
