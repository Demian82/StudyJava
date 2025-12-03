import java.util.*;

public class PracticeHashset {
    static void main() {
        Scanner sc = new Scanner(System.in);

        System.out.print("입력: ");


        String line = sc.nextLine();
        String[] words = line.split(" ");

        Set<String> wordSet = new HashSet<>();
        for (String w: words) {
            wordSet.add(w);
        }
        System.out.println("중복 제거 후 단어 개수: "+ wordSet.size());
        System.out.println("중복 제거된 단어들: "+ wordSet);
        List<String> list = new ArrayList<>(wordSet);
        Collections.sort(list);
        System.out.println("정렬된 단어들: " + list);
    }
}
