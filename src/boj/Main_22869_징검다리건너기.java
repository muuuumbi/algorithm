package boj;

import java.util.*;
import java.lang.*;
import java.io.*;
/*
사용하는 힘은 항상 양수.
오른쪽으로 갈 때 사용해야 하는 힘이 K가 넘지 않을 때 이동 가능
끝까지 도착이 되는지의 유무 계산
*/
public class Main_22869_징검다리건너기 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;
    static int N, K;
    static int[] A;
    static boolean[] DP;

    public static void main(String[] args) throws IOException{
        init();
    /*
    [출력]
    이동 가능하면 YES, 불가능하면 NO 출력
    */
        for(int start = 1; start < N; start++){
            if(DP[start]){
                method(start);
            }
        }
        System.out.println(DP[N] ? "YES" : "NO");
    }

    public static void init() throws IOException{
    /*
    [입력]
    1. 돌의 개수 N, 최대 힘 K (2<=N<=5,000, 1<=K<=1,000)
    2. N개의 돌의 수 A (1<=A<=1,000)
    */
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        A = new int[N+1];
        for(int n = 1; n <= N; n++){
            A[n] = Integer.parseInt(st.nextToken());
        }

        DP = new boolean[N+1];
        DP[1] = true;
    }

    static void method(int start){
        for(int dest = start+1; dest <= N; dest++){
            if(!DP[dest]){
                //이동 가능하면 true 표시
                if(isPossible(start, dest)){
                    DP[dest] = true;
                }
            }
        }
    }

    static boolean isPossible(int start, int dest){
        int power = (dest - start)*(1 + Math.abs(A[start] - A[dest]));
        return power > K ? false : true;
    }
}
