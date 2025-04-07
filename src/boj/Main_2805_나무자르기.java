package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

/*
나무 M미터가 필요.
1. 높이 H를 지정 -> 한 줄에 연속해있는 나무 모두 절단
2. 잘린 나무 획득
설정할 수 있는 H의 최댓값
 */
public class Main_2805_나무자르기 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int N;
    static long M, maxTree, result;
    static long[] trees;

    public static void main(String[] args) throws IOException {
        init();
        /*
        [출력]
        M미터를 가져가기 위해 설정 가능한 높이의 최댓값
         */
        seletHeight();
        System.out.println(result);
    }

    static void init() throws IOException{
        /*
        [입력]
        1. 나무의 수 N, 나무의 길이 M (1<=N<=1,000,000, 1<=M<=2,000,000,000)
        2. 나무의 높이들 (0<=<=1,000,000,000)
         */
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Long.parseLong(st.nextToken());

        trees = new long[N];
        st = new StringTokenizer(br.readLine());
        for(int n = 0; n < N; n++){
            trees[n] = Long.parseLong(st.nextToken());
            maxTree = Math.max(maxTree, trees[n]);
        }

        Arrays.sort(trees);
    }

    static void seletHeight(){
        long low = 0;
        long high = maxTree;

        while(low <= high){
            long mid = (low + high) / 2;

            if(getTree(mid)){
                result = mid;
                low = mid + 1;
            }else{
                high = mid - 1;
            }
        }
    }

    static boolean getTree(long height){
        long length = 0;
        for(int n = N - 1; n >= 0; n--){
            if(trees[n] <= height){
                break;
            }
            length += (trees[n] - height);
            //필요한 나무를 다 얻었으면 가능
            if(length >= M){
                return true;
            }
        }
        return false;
    }
}
