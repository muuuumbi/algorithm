package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
정수 n이 주어졌을 때, n을 1, 2, 3의 합으로 나타내는 방법의 수를 구하는 프로그램을 작성
 */
public class Main_15988_123더하기3 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static int N;
    static long[] DP;
    static final int INF = 1_000_000_009;
    public static void main(String[] args) throws IOException {
        int T = Integer.parseInt(br.readLine());
        for(int t = 0; t < T; t++){
            init();
            if(N > 3) {
                getDP();
                sb.append(DP[N]).append('\n');
            }
            else{
                switch(N) {
                    case 1:
                        sb.append(1).append('\n');
                        break;
                    case 2:
                        sb.append(2).append('\n');
                        break;
                    case 3:
                        sb.append(4).append('\n');
                        break;
                }

            }
        }
        /*
        [출력]
        각 테스트 케이스마다, n을 1, 2, 3의 합으로 나타내는 방법의 수를 1,000,000,009로 나눈 나머지를 출력
         */
        System.out.println(sb);
    }

    static void init() throws IOException {
        /*
        [입력]
        1. 테스트케이스 개수 T
        2. N (1 <= N <= 1_000_000)
         */
        N = Integer.parseInt(br.readLine());
        DP = new long[N+1];
    }

    static void getDP(){
        DP[1] = 1;
        DP[2] = 2;
        DP[3] = 4;

        for(int n = 4; n <= N; n++){
            DP[n] = (DP[n-3] + DP[n-2] + DP[n-1]) % INF;
        }
    }
}
