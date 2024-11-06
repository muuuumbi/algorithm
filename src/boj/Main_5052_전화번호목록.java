package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

/*
전화번호 목록이 일관성이 있는지 없는지 구하기.
일관성 : 한 번호가 다른 번호의 접두어인 경우가 없어야 함.
 */
public class Main_5052_전화번호목록 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static int N;
    static String[] numbers;
    public static void main(String[] args) throws IOException{
        int T = Integer.parseInt(br.readLine());
        for(int t = 0; t < T; t++){
            init();
            if(!isConsist()){
                sb.append("NO\n");
            }
            else{
                sb.append("YES\n");
            }
        }
        /*
        [출력]
        일관성 있으면 YES, 없으면 NO 출력
         */
        System.out.println(sb);
    }

    static void init() throws IOException{
        /*
        [입력]
        1. 테스트 케이스 개수 t (1 <= t <= 50)
        2. 전화 번호 수 n (1 <= n <= 10,000)
        3~n. 전화번호 정보 (길이 : 최대 10, 같은 경우 없음)
         */
        N = Integer.parseInt(br.readLine());
        numbers = new String[N];

        for(int n = 0; n < N; n++){
            numbers[n] = br.readLine();
        }

        //사전순으로 정렬
        Arrays.sort(numbers);
    }

    static boolean isConsist(){
        for(int n = 0; n < N-1; n++){
            if(numbers[n + 1].startsWith(numbers[n])){
                //일관성 없음
                return false;
            }
        }
        //일관성 있음
        return true;
    }
}
