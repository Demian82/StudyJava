public class Solution {
    public int[] solution(String[] cpr) {
        int[] answer = {0, 0, 0, 0, 0};
        String[] basic_order = {"check", "call", "pressure", "respiration", "repeat"};

        for(int i=0; i<6; i++){
            for(int j=0; j<6; j++){
                if(cpr[i].equals(basic_order[j])){
                    answer[i] = j;
                    break;
                }
            }
        }
        return answer;
    }
}
