package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
1. X가 3으로 나누어 떨어지면, 3으로 나눈다.
2. X가 2로 나누어 떨어지면, 2로 나눈다.
3. 1을 뺀다.
 */
public class Main_1463_1로만들기 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int N;
    static int[] DP;

    public static void main(String[] args) throws IOException {
        init();
        DP(N);
        /*
        [출력]
        연산을 하는 횟수의 최솟값
         */
        System.out.println(DP[1]);
    }

    static void init() throws IOException{
        /*
        [입력]
        1. N (1<=N<=1,000,000)
         */
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        DP = new int[N+1];
    }

    static void DP(int num){
        DP[1] = 0;
        DP[2] = 1;
        DP[3] = 1;

        for(int n = 4; n <= num; n++) {
            DP[n] = 1 + DP[n-1];

            //3으로 나누어지면
            if(n % 3 == 0)
                DP[n] = Math.min(DP[n], 1 + DP[n/3]);

            if(n % 2 == 0)
                DP[n] = Math.min(DP[n], 1 + DP[n/2]);
        }
    }
}
