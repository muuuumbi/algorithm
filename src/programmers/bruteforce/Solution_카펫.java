package programmers.bruteforce;

public class Solution_카펫 {
    public int[] solution(int brown, int yellow) {
        int sumOfHoriVerti = brown / 2 - 2;
        int[] answer = new int[2];
        for(int verti = 1; verti <= sumOfHoriVerti - verti; verti++){
            if(yellow == verti*(sumOfHoriVerti - verti)){
                answer[0] = sumOfHoriVerti - verti + 2;
                answer[1] = verti + 2;
            }
        }
        return answer;
    }
}
