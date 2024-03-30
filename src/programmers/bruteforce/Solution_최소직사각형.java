package programmers.bruteforce;

public class Solution_최소직사각형 {
    public int solution(int[][] sizes) {
        int answer = 0;
        //가장 긴 길이
        int max = Integer.MIN_VALUE;
        //작은 수 중 가장 긴 길이
        int maxOfmin = Integer.MIN_VALUE;

        for(int i = 0; i < sizes.length; i++){
            int first = sizes[i][0] >= sizes[i][1] ? sizes[i][0] : sizes[i][1];
            int second = sizes[i][0] < sizes[i][1] ? sizes[i][0] : sizes[i][1];

            max = first > max ? first : max;
            maxOfmin = second > maxOfmin ? second : maxOfmin;
        }

        return max*maxOfmin;
    }
}
