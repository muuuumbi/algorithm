package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

/*
anta로 시작하고 tica로 끝나는 남극언어.
N개의 단어가 있을 때, 어떤 K개의 글자를 알려줘야 읽을 수 있는 단어가 최대가 되는지 구하라
 */
public class Main_1062_가르침 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int N, K, result;
    static boolean[] learned;
    static String[] subString;

    public static void main(String[] args) throws IOException{
        init();
        /*
        [출력]
        학생들이 읽을 수 있는 단어 개수의 최댓값
         */
        //K가 5 미만이면 읽을 수 있는 단어가 없다
        if(K < 5){
            System.out.println(0);
        }
        //K가 26이면 모든 단어를 읽을 수 있다.
        else if(K == 26){
            System.out.println(N);
        }
        //이외에는 계산해주기
        else{
            backtracking(0, 0);
            System.out.println(result);
        }
    }
    static void init() throws IOException{
        /*
        [입력]
        1. 단어 개수 N, K (1 <= N <= 50, 0 <= K <= 26)
        2~N. 단어 (8 <= 길이 <= 15, 중복 없음)
         */
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        result = Integer.MIN_VALUE;

        learned = new boolean[26];
        learned['a' - 'a'] = true;
        learned['n' - 'a'] = true;
        learned['t' - 'a'] = true;
        learned['i' - 'a'] = true;
        learned['c' - 'a'] = true;

        subString = new String[N];
        for(int n = 0; n < N; n++){
            String word = br.readLine();
            int length = word.length();
            subString[n] = word.substring(4, length-4);
        }
    }

    static void backtracking(int alphabet, int length){
        //다 골랐으면
        if(length == K - 5){
            //뽑은 알파벳으로 읽을 수 있는 단어 개수 세기
            int count = 0;
            for(int n = 0; n < N; n++) {
                boolean read = true;
                String word = subString[n];
                for (int i = 0; i < word.length(); i++) {
                    //못 읽는 단어이면
                    if(!learned[word.charAt(i) - 'a']){
                        read = false;
                        break;
                    }
                }
                if(read){
                    count++;
                }
            }
            //최댓값 갱신
            result = Math.max(result, count);
            return;
        }

        for(int i = alphabet; i < 26; i++){
            //아직 안 배웠으면
            if(!learned[i]){
                learned[i] = true;
                backtracking(i, length+1);
                learned[i] = false;
            }
        }
    }
}