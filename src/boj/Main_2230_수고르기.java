package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
N개의 수열 A[1], ..., A[N]
두 수를 골랐을 때 차이가 M 이상이면서 제일 작은 경우 구하기
 */
public class Main_2230_수고르기 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int N;
    static long M, result;
    static long[] A;

    public static void main(String[] args) throws IOException {
        init();
        twoPointer();
        /*
        [출력]
        M 이상이면서 가장 작은 차이
         */
        System.out.println(result);
    }

    static void init() throws IOException{
        /*
        [입력]
        1. N, M (1 <= N <= 100,000, 0 <= M <= 2,000,000,000)
        2. 수열 A[i] (0 <= |A[i]| <= 1,000,000,000)
         */
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Long.parseLong(st.nextToken());

        A = new long[N];
        for(int n = 0; n < N; n++){
            A[n] = Long.parseLong(br.readLine());
        }
        //A 오름차순 정렬
        Arrays.sort(A);

        result = Long.MAX_VALUE;
    }

    static void twoPointer(){
        int left = 0;
        int right = 0;

        while(right < N){
            long diff = A[right] - A[left];

            if(diff >= M){
                result = Math.min(result, diff);
                left++;
            }
            else{
                right++;
            }

            if(left == right){
                right++;
            }
        }
    }
}
