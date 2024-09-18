package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
회문이 아니지만 한 문자를 삭제하여 회문으로 만들 수 있는 문자열인 유사회문
회문인지 유사회문인지 일반 문자열인지를 판단해라
 */
public class Main_17609_회문 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static int T;

    public static void main(String[] args) throws IOException {
        init();
        /*
        [출력]
        회문이면 0, 유사회문이면 1, 둘 다 아니면 2
         */
        System.out.println(sb);
    }

    static void init() throws IOException{
        /*
        [입력]
        1. 문자열 개수 T (1 <= T <= 30)
        2~T.문자열 (3 <= 길이 <= 100,000)
         */
        T = Integer.parseInt(br.readLine());
        for(int t = 0; t < T; t++){
            StringBuilder str = new StringBuilder(br.readLine());
            int result = isPalindrome(str);
            sb.append(result).append("\n");
        }
    }

    static int isPalindrome(StringBuilder str){
        int length = str.length();
        for(int i = 0; i < length/2; i++){
            //대칭이 아니면
            if(str.charAt(i) != str.charAt(length-1-i)){
                char temp = str.charAt(i);
                boolean left = deleteAndCheck(str.deleteCharAt(i));
                str.insert(i, temp);
                boolean right = deleteAndCheck(str.deleteCharAt(length-1-i));

                //둘 중 하나라도 회문이면
                if(left || right){
                    //유사회문
                    return 1;
                }
                //둘 다 아니면
                else{
                    return 2;
                }
            }
        }
        //회문
        return 0;
    }

    static boolean deleteAndCheck(StringBuilder str){
        int length = str.length();
        for(int i = 0; i < length/2; i++){
            //대칭이 아니면
            if(str.charAt(i) != str.charAt(length-1-i)){
                return false;
            }
        }
        return true;
    }
}
