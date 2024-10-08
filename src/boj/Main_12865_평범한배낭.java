package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
N개의 물건. 각 무게 W, 가치 V
K만큼의 무게에서 최대 가치 구하기
 */
public class Main_12865_평범한배낭 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int N, K;
    static int[] W, V;
    static Integer[][] DP;
    public static void main(String[] args) throws IOException{
        init();
        /*
        [출력]
        배낭에 넣을 수 있는 물건들의 최대 가치합
         */
        System.out.println(knapsack(N - 1, K));
    }

    static void init() throws IOException {
        /*
        [입력]
        1. 물건 개수 N (1 <= N <= 100), 무게 K (1 <= K <= 100,000)
        2~N. 물건 무게 W (1 <= W <= 100,000), 가치 V (0 <= V <= 1,000)
         */
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        W = new int[N];
        V = new int[N];
        DP = new Integer[N][K+1];

        for(int n = 0; n < N; n++){
            st = new StringTokenizer(br.readLine());
            W[n] = Integer.parseInt(st.nextToken());
            V[n] = Integer.parseInt(st.nextToken());
        }
    }

    //n은 현재 물건 idx, k는 남은 무게
    static int knapsack(int n, int k){
        //n이 범위 밖이 되면
        if(n < 0){
            return 0;
        }

        //탐색하지 않은 위치이면
        if(DP[n][k] == null){
            //현재 물건(n)을 추가로 못 담는 경우
            if(W[n] > k){
                //이전 n 값 탐색
                DP[n][k] = knapsack(n - 1, k);
            }
            //현재 물건 담을 수 있으면
            else{
                //이전 물건까지의 가치와
                //현재 물건을 포함한 가치
                // 둘 중 큰 값 저장
                DP[n][k] = Math.max(knapsack(n - 1, k),
                        knapsack(n - 1, k - W[n]) + V[n]);
            }
        }
        return DP[n][k];
    }
}
