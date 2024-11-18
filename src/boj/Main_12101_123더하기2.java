package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

/*
정수 4를 1,2,3의 합으로 나타내는 7가지 방법
1. 1+1+1+1
2. 1+1+2
3. 1+2+1
4. 2+1+1
5. 2+2
6. 1+3
7. 3+1
이를 사전순으로 정렬하면
1. 1+1+1+1
2. 1+1+2
3. 1+2+1
4. 1+3
5. 2+1+1
6. 2+2
7. 3+1
정수 n과 k가 주어졌을 때, n을 1,2,3의 합으로 나타내는 방법 중 k번째로 오는 식을 구하기
 */
public class Main_12101_123더하기2 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;
    static int N, K;
    static ArrayList<String>[] list;
    public static void main(String[] args) throws IOException {
        init();
        getList();
        /*
        [출력]
        n을 1,2,3의 합으로 나타내는 방법의 중 사전 순으로 k번째 오는 것
        없을 경우엔 -1 출력
         */
        if(list[N].size() < K){
            System.out.println(-1);
        }
        else {
            Collections.sort(list[N]);
            System.out.println(list[N].get(K - 1));
        }
    }

    static void init() throws IOException {
        /*
        [입력]
        1. n, k (1 <= n < 11, 1 <= k <= 2^31 - 1)
         */
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        list = new ArrayList[N + 3];
        for(int n = 0; n < N+3; n++){
            list[n] = new ArrayList<>();
        }
        list[1].add("1");
        list[2].add("1+1");
        list[2].add("2");
        list[3].add("1+2");
        list[3].add("1+1+1");
        list[3].add("2+1");
        list[3].add("3");
    }

    static void getList(){
        /*
        ArrayList[n-1]에 있는 수식에 +1,
        ArrayList[n-2]에 있는 수식에 +2,
        ArrayList[n-3]에 있는 수식에 +3
         */
        for(int n = 4; n <= N; n++){
            for(int i = 1; i <= 3; i++){
                for(String str : list[n - i]){
                    list[n].add(str + "+" + i);
                }
            }
        }
    }
}
