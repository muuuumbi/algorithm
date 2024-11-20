package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
정수 4를 1,2,3의 합으로 나타내는 4가지 방법
(수의 순서만 다른 것은 같은 것으로 침)
1. 1+1+1+1
2. 2+1+1
3. 2+2
4. 1+3
n이 주어졌을 때, n을 1,2,3의 합으로 나타내는 방법의 수를 구하기
 */
public class Main_15989_123더하기4 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static int N;
    static int[][] DP;
    public static void main(String[] args) throws IOException{
        int T = Integer.parseInt(br.readLine());
        for(int t = 0; t < T; t++){
            init();
            if(N > 3){
                getDP();
                sb.append(DP[N][1] + DP[N][2] + DP[N][3]).append('\n');
            }
            else{
                switch(N){
                    case 1:
                        sb.append(DP[1][1]).append('\n');
                        break;
                    case 2:
                        sb.append(DP[2][1] + DP[2][2]).append('\n');
                        break;
                    case 3:
                        sb.append(DP[3][1] + DP[3][2] + DP[3][3]).append('\n');
                        break;
                }
            }
        }
        /*
        [출력]
        테스트 케이스마다 n을 1,2,3의 합으로 나타내는 방법의 수 출력
         */
        System.out.println(sb);
    }

    static void init() throws IOException {
        /*
        [입력]
        1. 테스트 케이스 개수 T
        2~T. n (1 <= n <= 10,000)
         */
        N = Integer.parseInt(br.readLine());

        DP = new int[N+1][4];
        switch(N){
            case 1:
                DP[1][1] = 1; //1
                break;
            case 2:
                DP[1][1] = 1; //1
                DP[2][1] = 1; //1+1
                DP[2][2] = 1; //2
                break;
            default:
                DP[1][1] = 1; //1
                DP[2][1] = 1; //1+1
                DP[2][2] = 1; //2
                DP[3][1] = 1; //1+1+1
                DP[3][2] = 1; //1+2
                DP[3][3] = 1; //3
                break;
        }
    }

    static void getDP(){
        for(int n = 4; n <= N; n++){
            DP[n][1] = DP[n-1][1];
            DP[n][2] = DP[n-2][1] + DP[n-2][2];
            DP[n][3] = DP[n-3][1] + DP[n-3][2] + DP[n-3][3];
        }
    }
}
