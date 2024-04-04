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
    static int answer_2 = 0;
    static int answer_3 = 0;

    public int[] solution(int[] answers) {
        for(int i = 0; i < answers.length; i++){
            //현재 답과 비교할 반복되는 답의 index 구하기
            int first = i % FIRST;
            int second = i % SECOND;
            int third = i % THIRD;

            if(answers[i] == number_1[first]){
                answer_1++;
            }
            if(answers[i] == number_2[second]){
                answer_2++;
            }
            if(answers[i] == number_3[third]){
                answer_3++;
            }
        }

        int index = 0;
        index = answer_1 != 0 ? index + 1 : index;
        index = answer_2 != 0 ? index + 1 : index;
        index = answer_3 != 0 ? index + 1 : index;

        int[] answer = new int[index];

        return answer;
    }
}
