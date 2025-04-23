package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;
/*
쇠막대기를 겹침.
자신보다 긴 막대기 위에만 놓일 수 있음.
레이저는 어떤 쇠막대기의 양 끝점과도 겹치지 않음

'()' : 레이저
'(',')' : 막대기의 양 끝
 */
public class Main_10799_쇠막대기 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static String env;
    static Stack<Character> stack;
    static Stack<Integer> laser;

    public static void main(String[] args) throws IOException {
        init();
        System.out.print(devide());
    }

    static void init() throws IOException{
        env = br.readLine();
        stack = new Stack<>();
        laser = new Stack<>();
    }

    static int devide(){
        int result = 0;

        for(int i = 0; i < env.length(); i++){
            char now = env.charAt(i);

            if(now == '('){
                stack.push(now);
            }
            else if(now == ')'){
                stack.pop();
                //레이저 끝일 때
                if(env.charAt(i - 1) == '('){
                    result += stack.size();
                }
                //막대기 끝일 때
                else {
                    result++;
                }
            }
        }
        return result;
    }
}
