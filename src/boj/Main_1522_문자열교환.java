package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
a, b로만 이루어진 문자열
a를 모두 연속으로 만들기 위해 필요한 교환의 최소 횟수
문자열은 원형이여서 처음과 끝은 인접해있음
 */
public class Main_1522_문자열교환 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb;
    static int cntA = 0, result = Integer.MAX_VALUE;
    public static void main(String[] args) throws IOException{
        init();
        /*
        [출력]
        최소 교환 횟수
         */
        if(cntA == 1 || cntA == 0){
            System.out.println(0);
        }
        else {
            window(sb);
            System.out.println(result);
        }
    }

    static void init() throws IOException {
        /*
        [입력]
        1. 문자열 (최대 길이 1,000)
         */
        sb = new StringBuilder(br.readLine());
        for(int i = 0; i < sb.length(); i++){
            if(sb.charAt(i) == 'a'){
                cntA++;
            }
        }
    }

    static void window(StringBuilder sb){
        int cntB = 0;
        //첫 구간의 b의 개수
        for(int i = 0; i < cntA; i++){
            if(sb.charAt(i) == 'b'){
                cntB++;
            }
        }
        result = Math.min(result, cntB);

        for(int i = 1; i < sb.length(); i++){
            int lastIdx = (i + cntA - 1) % sb.length();
            if(sb.charAt(i - 1) == 'b'){
                cntB--;
            }

            if(sb.charAt(lastIdx) == 'b'){
                cntB++;
            }
            result = Math.min(result, cntB);
        }
    }
}
