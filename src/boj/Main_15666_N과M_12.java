package boj;

import java.util.*;
import java.lang.*;
import java.io.*;
/*
N개 중 M개 고르기
중복 가능
오름차순
*/
public class Main_15666_N과M_12{
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;
    static int N, M;
    static int[] A;
    static int[] selected;

    public static void main(String[] args) throws IOException{
        init();
        /*
        [출력]
        한 줄에 하나씩 수열 출력. 중복 수열은 한 번만 출력.
        */
        DFS(0,0);
        System.out.print(sb);
    }

    public static void init() throws IOException{
        /*
        [입력]
        1. N, M (1<=M,N<=8)
        2. N개의 수 (1<=크기<=10,000)
        */
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        A = new int[N];
        st = new StringTokenizer(br.readLine());
        for(int n = 0; n < N; n++){
            A[n] = Integer.parseInt(st.nextToken());
        }

        //오름차순 정렬
        Arrays.sort(A);
        selected = new int[M];
    }

    public static void DFS(int start, int depth){
        if(depth == M){
            for(int m = 0; m < M; m++){
                sb.append(selected[m]).append(" ");
            }
            sb.append('\n');
            return;
        }

        int prevNum = -1;
        for(int i = start; i < N; i++){
            if(prevNum != A[i]){
                selected[depth] = A[i];
                prevNum = A[i];
                DFS(i, depth+1);
            }
        }
    }
}
