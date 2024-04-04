package programmers.bruteforce;

public class Solution_모의고사 {
    //수포자 당 반복되는 답 양상
    static int[] number_1 = {1, 2, 3, 4, 5 };
    static int[] number_2 = {2, 1, 2, 3, 2, 4, 2, 5};
    static int[] number_3 = {3, 3, 1, 1, 2, 2, 4, 4, 5, 5};
    //반복되는 답의 개수
    static final int FIRST = 5;
    static final int SECOND = 8;
    static final int THIRD = 10;
    static int answer_1 = 0;
    static

    public int[] solution(int[] answers) {
        int[] answer = {};

        for(int i = 0; i < answers.length; i++){
            //현재 답과 비교할 반복되는 답의 index 구하기
            int first = i % FIRST;
            int second = i % SECOND;
            int third = i % THIRD;

            if(number_1[first] == answers[i]){

            }
        }

        return answer;
    }
}
