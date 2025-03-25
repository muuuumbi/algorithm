package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
/*
N명의 아이들
1번부터 N번. 옮기는 순서를 최소로 하여 일렬로 정렬
 */
public class Main_2631_줄세우기 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int N;
    static int[] children, DP;
    public static void main(String[] args) throws IOException{
        init();
        /*
        [출력]
        옮겨지는 아이들의 최소 수
         */
        int result = N - LIS();
        System.out.println(result);
    }

    static void init() throws IOException{
        /*
        [입력]
        1. 아이들의 수 N (2<=N<=200)
        2~N. 번호
         */
        N = Integer.parseInt(br.readLine());

        children = new int[N+1];
        for(int n = 1; n <= N; n++){
            children[n] = Integer.parseInt(br.readLine());
        }

        DP = new int[N+1];
        DP[1] = 1;
    }

    static int LIS(){
        int LIS = 1;
        for(int i = 2; i <= N; i++){
            int max = 0;
            for(int j = 1; j < i; j++){
                //이전 번호 중 더 작은 번호가 있으면
                if(children[j] < children[i]) {
                    //해당 번호의 LIS 값(최대)을 구하기
                    max = Math.max(DP[j], max);
                }
            }
            DP[i] = max + 1;
            LIS = Math.max(DP[i], LIS);
        }
        return LIS;
    }
}
