import java.util.HashMap;
import java.util.Map;


public class PracticeMapInterface1 {
    static void main(String[] args) {
        Map<String, Integer> scoreMap = new HashMap<>();

        // put: 추가 또는 업데이트
        scoreMap.put("Angela", 90);
        scoreMap.put("Bina", 90);
        scoreMap.put("Carmen", 90);

        // 동일 key 저장 -> value 업데이트
        scoreMap.put("Angela", 92);

        // containsKey: key 존재 여부
        if (scoreMap.containsKey("Angela")) {
            // get: key로 조회
            System.out.println("Angela의 점수: " + scoreMap.get("Angela"));
        }

        // entrySet으로 전체 출력
        System.out.println("===전체 학생 점수===");
        for (Map.Entry<String, Integer> entry : scoreMap.entrySet()) {
            System.out.println(entry.getKey() + " : " + entry.getValue());
        }
    }
}
