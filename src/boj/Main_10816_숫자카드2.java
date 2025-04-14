package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/*
카드 N개
정수 M개가 주어졌을 때 이 수가 적혀있는 카드를 몇 개 가지고 있는지 구하기
 */
public class Main_10816_숫자카드2 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;
    static int N, M;
    static int[] nums;
    static HashMap<Integer, Integer> cards;

    public static void main(String[] args) throws IOException{
        init();
        /*
        [출력]
        M개의 수에 대해 몇 개 가지고 있는지 공백으로 구분해 출력
         */
        for(int m = 0; m < M; m++){
            if(cards.containsKey(nums[m])) {
                sb.append(cards.get(nums[m]));
            }else{
                sb.append(0);
            }
            if(m == M-1){
                break;
            }
            sb.append(" ");
        }
        System.out.println(sb);
    }

    static void init() throws IOException {
        /*
        [입력]
        1. 카드 개수 N (1<=N<=500,000)
        2~N. 카드에 적힌 정수들
        3. M(1<=M<=500,000)
        4. 가지고 있는지 구해야 할 M개의 정수 (-10,000,000~10,000,000)
         */
        N = Integer.parseInt(br.readLine());
        cards = new HashMap<>();

        st = new StringTokenizer(br.readLine());
        for(int n = 0; n < N; n++){
            int key = Integer.parseInt(st.nextToken());
            //있으면
            if(cards.containsKey(key)){
                cards.replace(key, cards.get(key)+1);
            }
            else{
                cards.put(key, 1);
            }
        }

        M = Integer.parseInt(br.readLine());
        nums = new int[M];

        st = new StringTokenizer(br.readLine());
        for(int m = 0; m < M; m++){
            nums[m] = Integer.parseInt(st.nextToken());
        }
    }
}
