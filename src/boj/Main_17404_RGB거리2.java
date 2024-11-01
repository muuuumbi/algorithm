package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
집이 N개.
각각의 집을 빨강, 초록, 파랑으로 칠하는 비용이 주어졌을 때,
1. 1번 집의 색은 2번, N번 집의 색과 같지 않아야 한다.
2. N번 집의 색은 N-1번, 1번 집의 색과 같지 않아야 한다.
3. i(2<=i<=N-1)번 집의 색은 i-1, i+1번 집의 색과 같지 않아야 한다.
세 조건을 만족하면서 모든 집을 칠하는 비용의 최솟값 구하기
 */
public class Main_17404_RGB거리2 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static final int INF = 1_000 * 1_000; //최대 비용
    static int N;
    static int[][] houseCost;
    static int[][] DP;

    public static void main(String[] args) throws IOException{
        init();
        /*
        [출력]
        모든 집을 칠하는 비용의 최솟값을 출력
         */
        System.out.println(getMinCost());
    }

    static void init() throws IOException {
        /*
        [입력]
        1. 집의 수 N (2 <= N <= 1,000)
        2~N. 각 집을 빨강,초록,파랑으로 칠하는 비용 (1 <= 비용 <= 1,000)
         */
        N = Integer.parseInt(br.readLine());
        houseCost = new int[N + 1][3]; //0 : R, 1 : G, 2 : B
        DP = new int[N + 1][3];

        for(int n = 1; n <= N; n++){
            st = new StringTokenizer(br.readLine());
            for(int i = 0; i < 3; i++){
                houseCost[n][i] = Integer.parseInt(st.nextToken());
            }
        }
    }

    static int getMinCost(){
        int result = INF;
        // color | 0 : RED, 1 : GREEN, 2 : BLUE로 첫 집을 칠하는 경우이다.
        for(int color = 0; color < 3; color++) {
            //RED, GREEN, BLUE로 칠하는 경우 각 색을 제외한 나머지는 INF로 초기화 해준다.
            for(int i = 0 ; i < 3; i++) {
                if(i == color) DP[1][i] = houseCost[1][i];
                else DP[1][i] = INF;
            }

            // 열의 값인 0 : RED, 1 : GREEN, 2 : BLUE로 칠했을 때의 최소값을 의미한다.
            // 이전 집과 같은 색이면 안되므로
            for (int i = 2; i <= N; i++) {
                DP[i][0] = Math.min(DP[i - 1][1], DP[i - 1][2]) + houseCost[i][0];
                DP[i][1] = Math.min(DP[i - 1][0], DP[i - 1][2]) + houseCost[i][1];
                DP[i][2] = Math.min(DP[i - 1][0], DP[i - 1][1]) + houseCost[i][2];
            }

            // 정답인 최솟값을 구하는 부분
            for(int i = 0 ; i < 3; i++)
                //N번집과 1번집의 색이 다르면
                if(i != color){
                    //최소 비용 갱신
                    result = Math.min(result, DP[N][i]);
                }
        }

        return result;
    }
}
