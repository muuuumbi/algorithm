package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
2xn 크기의 직사각셩을 1x2,2x1 타일로 채우는 방법의 수 구하기
 */
public class Main_11726_2xn타일링 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int n;
    static int[] DP;
    public static void main(String[] args) throws IOException{
        init();
        /*
        [출력]
        방법의 수를 10,007로 나눈 나머지
        */
        DP[0] = 1;
        DP[1] = 1;
        for(int i = 2; i <= n; i++){
            DP[i] = (DP[i-1] + DP[i-2]) % 10007;
        }

        System.out.println(DP[n]);
    }

    static void init() throws IOException {
        /*
        [입력]
        1. n (1<=n<=1,000)
        */
        n = Integer.parseInt(br.readLine());
        DP = new int[n + 1];
    }
}
