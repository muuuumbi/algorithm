package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

/*
N개의 빌딩
             =
 =           =
 =     -     =
 =     =     =        -> 관리인이 보는 방향
 =  -  =  =  =
 =  =  =  =  =  =
10  3  7  4  12 2     -> 빌딩의 높이
[1][2][3][4][5][6]    -> 빌딩의 번호

 i번째 빌딩 관리인이 볼 수 있는 빌딩의 개수의 합 구하기
 */
public class Main_6198_옥상정원꾸미기 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static Stack<Integer> stack = new Stack<>();
    static int N;
    static long answer;
    public static void main(String[] args) throws IOException{
        init();
        /*
        [출력]
        각 관리인들이 볼 수 있는 빌딩 수의 합
         */
        System.out.println(answer);
    }

    static void init() throws IOException {
        /*
        [입력]
        1. 빌딩의 개수 N (1 <= N <= 80,000)
        2~N. 각 빌딩 높이 h (1 <= h <= 1,000,000,
        000)
         */
        N = Integer.parseInt(br.readLine());

        for(int i = 0; i < N; i++){
            int height = Integer.parseInt(br.readLine());

            while(!stack.isEmpty()){
                //i번째 빌딩보다 낮거나 같은 애들은 빼기
                if(stack.peek() <= height){
                    stack.pop();
                }
                else{
                    break;
                }
            }
            //벤치마킹 가능한 개수 더해주기
            answer += stack.size();
            //빌딩 높이 넣어주기
            stack.push(height);
        }
    }
}
