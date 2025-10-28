public class PracticeStr {
    public static void main(String[] args) {

        String name1 = "Hokma";
        String name2 = "Hokma";

        System.out.println(name1 == name2); // true (참조값 같음)
        System.out.println(name1.equals(name2)); // true (내용도 같음)

        String name3 = new String("Kether");
        String name4 = new String("Kether");

        System.out.println(name3 == name4); // false (참조값 다름)
        System.out.println(name3.equals(name4)); // true (내용은 같음)

        String str = "Java programming";
        // 주어진 인덱스의 문자를 리턴
        System.out.println(str.charAt(3));
        // 문자의 개수(문자열 길이)를 리턴
        System.out.println(str.length());

        // replace()
        // 기준 문자열은 그대로 두고, 대체한 새로운 문자열을 리턴
        String newstr = str.replace("Java", "자바");
        System.out.println(str);
        System.out.println(newstr);

    }
}
