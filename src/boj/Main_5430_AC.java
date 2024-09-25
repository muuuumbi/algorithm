package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/*
R - 순서를 뒤집는 함수
D - 첫 번째 수를 버리는 함수, 배열이 비어있을 때 사용되면 에러
함수를 조합하여 한 번에 사용
최종 결과 구하기
 */
public class Main_5430_AC {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static int N;
    static boolean isFront;
    static String P;
    static Deque<Integer> nums;

    public static void main(String[] args) throws IOException{
        int T = Integer.parseInt(br.readLine());
        for(int t = 0; t < T; t++){
            init();
            //수행결과 출력
            if(getResult()){
                sb.append("[");
                if(isFront){
                    pollFirst();
                }
                else{
                    pollLast();
                }
                sb.append("]").append("\n");
            }
            //에러 출력
            else{
                sb.append("error").append("\n");
            }
        }
        /*
        [출력]
        각 테스트케이스에 대해서, 결과 출력.
        에러가 발생하면 error 출력
         */
        System.out.println(sb);
    }

    static void init() throws IOException{
        /*
        [입력]
        1. 테스트케이스 T (T <= 100)
        2. 함수 p (1 <= p길이 <= 100,000)
        3. 배열에 들어있는 수의 개수 n (0 <= n <= 100,000)
        4. 배열의 정수가 [x, ...] 형태로 주어짐 (1 <= x <= 100)
        전체 테스트케이스에 주어지는 p의 길이의 합과 n의 합은 70만을 넘지 않음.
         */
        //함수
        P = br.readLine();

        //배열
        N = Integer.parseInt(br.readLine());
        String numStr = br.readLine();
        nums = new ArrayDeque<>();

        if(N > 0) {
            numStr = numStr.substring(1, numStr.length() - 1);
            String[] numArr = numStr.split(",");
            for (String num : numArr) {
                nums.add(Integer.parseInt(num));
            }
        }

        isFront = true;
    }

    static boolean getResult(){
        int cntR = 0;
        //함수 탐색
        for(char command : P.toCharArray()){
            //뒤집기이면
            if(command == 'R'){
                R();
            }
            //삭제이면
            else if(command == 'D'){
                //비어있으면 error
                if(nums.isEmpty()){
                    return false;
                }
                D();
            }
        }
        return true;
    }

    //뒤집기
    static void R(){
        isFront = !isFront;
    }

    static void D(){
        if(isFront){
            nums.removeFirst();
        }
        else{
            nums.removeLast();
        }
    }

    static void pollFirst(){
        int size = nums.size();
        for(int s = 0; s < size; s++){
            if(s == size-1){
                sb.append(nums.pollFirst());
                continue;
            }
            sb.append(nums.pollFirst()).append(",");
        }
    }

    static void pollLast(){
        int size = nums.size();
        for(int s = 0; s < size; s++){
            if(s == size-1){
                sb.append(nums.pollLast());
                continue;
            }
            sb.append(nums.pollLast()).append(",");
        }
    }
}
