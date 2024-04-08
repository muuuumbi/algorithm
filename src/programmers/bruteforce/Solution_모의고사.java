package programmers.bruteforce;

import java.util.Arrays;
import java.util.stream.IntStream;

public class Solution_모의고사 {
    int[][] patterns = {
            {1, 2, 3, 4, 5},
            {2, 1, 2, 3, 2, 4, 2, 5},
            {3, 3, 1, 1, 2, 2, 4, 4, 5, 5}
    };

    public int[] solution(int[] answers) {
        int[] scores = new int[3];
        for(int i = 0; i < answers.length; i++){
            for(int j = 0; j < 3; j++){
                if(answers[i] == patterns[j][i % patterns[j].length]){
                    scores[j]++;
                }
            }
        }

        int maxScore = Arrays.stream(scores).max().getAsInt();
        return IntStream.range(0, 3).filter(i -> scores[i] == maxScore).map(i -> i+1).toArray();
    }
}
