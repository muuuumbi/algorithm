package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
용액을 혼합하면 두 용액의 특성값의 합이 됨.
혼합해서 0과 가장 가까운 특성값 구하기
 */
public class Main_14921_용액합성하기 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int N;
    static int[] solutions;
    public static void main(String[] args) throws IOException{
        init();
        /*
        [출력]
        0과 가장 가까운 특성값
         */
        System.out.println(twoPointer());
    }

    static void init() throws IOException {
        /*
        [입력]
        1. 용액 수 N (2 <= N <= 100,000)
        2. 용액 특성 값 A ( -100,000,000 <= A <= 100,000,000)
         */
        N = Integer.parseInt(br.readLine());
        solutions = new int[N];
        st = new StringTokenizer(br.readLine());
        for(int n = 0; n < N; n++){
            solutions[n] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(solutions); //오름차순 정렬
    }

    static int twoPointer(){
        int left = 0;
        int right = N - 1;
        int result = Integer.MAX_VALUE;

        while(left < right){
            int sum = solutions[left] + solutions[right];
            //합이 0이면 그만하기
            if(sum == 0){
                return 0;
            }
            //합이 음수이면 왼쪽 포인터 움직이기
             else if(sum < 0){
                 left++;
            }
            //값이 양수이면 오른쪽 포인터 움직이기
             else if(sum > 0){
                 right--;
            }
            //합의 절댓값과 비교해서 최소값 갱신
            if(Math.abs(result) > Math.abs(sum)){
                result = sum;
            }
        }
        return result;
    }
}
