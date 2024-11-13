package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
정수 4를 1,2,3의 합으로 나타내는 7가지 방법
1. 1+1+1+1
2. 1+1+2
3. 1+2+1
4. 2+1+1
5. 2+2
6. 1+3
7. 3+1
정수 n이 주어졌을 때, 1,2,3의 합으로 나타내는 방법의 수 구하기
 */
public class Main_9095_123더하기 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static int[] DP = new int[11];
    public static void main(String[] args) throws IOException {
        getDP();
        /*
        [입력]
        1. 테스트 케이스 개수 T
        2~T. n (1 <= n < 11)
         */
        int T = Integer.parseInt(br.readLine());
        for(int t = 0; t < T; t++){
            sb.append(DP[Integer.parseInt(br.readLine())]).append('\n');
        }
        /*
        [출력]
        테스트 케이스마다 n을 1,2,3의 합으로 나타내는 방법의 수 출력
         */
        System.out.println(sb);
    }

    static void getDP(){
        DP[1] = 1;
        DP[2] = 2;
        DP[3] = 4;
        for(int i = 4; i < 11; i++){
            DP[i] = DP[i - 3] + DP[i - 2] + DP[i - 1];
        }
    }
}