
public class PracticeString {
    public static void main(String[] args) {
        String name1;
        name1 = "Angela";
        String corp = "Lobotomy";
        String name2 = "Angela";

        System.out.println(name1 == name2); // true; 참조값 같음
        System.out.println(name1.equals(name2)); // true; 내용도 같음

        String name3 = new String("Bina");
        String name4 = new String("Bina");

        System.out.println(name3 == name4); // fasle; 참조값 다름
        System.out.println(name3.equals(name4)); // true; 내용은 같음

        String str = "Limbus company";
        System.out.println(str.charAt(4));
        System.out.println(str.length());

        String oldStr = "Face to sin";
        String newStr = oldStr.replace("sin", "face");
        System.out.println((oldStr));
        System.out.println((newStr));

        int index = str.indexOf("company");
        System.out.println(index);

        String str2 = "번호, 제목, 내용, 글쓰기";
        String[] arr = str2.split(",");
        System.out.println(arr);
    }
}