package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
10,000 이하의 자연수로 이루어진 길이 N짜리 수열.
이 수열에서 연속된 수들의 부분합 중에 그 합이 S 이상이 되는 것 중,
가장 짧은 것의 길이를 구해라.
 */
public class Main_1806_부분합 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int N, S;
    static int result, total = 0;
    static int[] numbers;
    public static void main(String[] args) throws IOException{
        init();

        /*
        [출력]
        최소 길이 / 불가능하다면 0
         */
        if(result == 1){
            System.out.println(1);
        }
        else{
            if(total < S){
                System.out.println(0);
            }
            else{
                result = getMinLength();
                System.out.println(result);
            }
        }
    }

    static void init() throws IOException {
        /*
        [입력]
        1. N (10 <= N < 100,000)과 S(0 < S <= 100,000,000)
        2. 수열 (각 원소는 공백으로 구분, 10,000 이하의 자연수)
         */
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());

        numbers = new int[N+1];
        st = new StringTokenizer(br.readLine());
        for(int n = 0; n < N; n++){
            numbers[n] = Integer.parseInt(st.nextToken());
            //수열의 수들 중 S 이상의 수가 있다면 최소 길이는 1
            if(numbers[n] >= S){
                result = 1;
                break;
            }
            //수열 전체 합 구하기
            total += numbers[n];
        }
    }

    //최소 길이 구하기
    static int getMinLength(){
        int minLen = Integer.MAX_VALUE;
        int len = 0;
        //포인터 인덱스 위치
        int leftIdx = 0;
        int rightIdx = 0;
        int sum = 0;

        while(rightIdx <= N){
            //합이 S 미만이면
            if(sum < S){
                //오른쪽 포인터 값 더해준 후 오른쪽 이동
                sum += numbers[rightIdx++];
            }
            //합이 S 이상이면
            else{
                //왼쪽 포인터 값을 빼준 후 오른쪽 이동
                sum -= numbers[leftIdx++];
                //부분합 길이 구하기
                len = rightIdx - leftIdx + 1;
                //길이 최솟값 구하기
                minLen = Math.min(minLen, len);
            }
        }

        return minLen;
    }
}
