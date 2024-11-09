package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
산성 용액 특성값 1 ~ 10억
알칼리성 용액 특성값 -10억 ~ -1
두 용액을 혼합하여 0에 가장 가까운 용액 만들기
 */
public class Main_2467_용액 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int N;
    static long[] liquids, result;
    static final long INF = 2_000_000_000;

    public static void main(String[] args) throws IOException{
        init();
        choiceTwoLiquids();
        /*
        [출력]
        0에 가장 가까운 용액을 만드는 두 용액의 특성값을 오름차순으로 출력
         */
        System.out.println(result[0] + " " + result[1]);
    }
    static void init() throws IOException {
        /*
        [입력]
        1. 전체 용액의 수 N (2 <= N <= 100,000)
        2~N. 용액의 특성값 (모두 다름)
         */
        N = Integer.parseInt(br.readLine());
        liquids = new long[N];

        st = new StringTokenizer(br.readLine());
        for(int n = 0; n < N; n++){
            liquids[n] = Long.parseLong(st.nextToken());
        }

        //오름차순 정렬
        Arrays.sort(liquids);

        result = new long[2];
    }

    static void choiceTwoLiquids(){
        int left = 0;
        int right = N - 1;
        long closeToZero = INF;

        while(left < right){
            long liquid1 = liquids[left];
            long liquid2 = liquids[right];

            long sum = liquid1 + liquid2;
            //두 개의 합이 0이면 끝
            if(sum == 0){
                result[0] = liquid1;
                result[1] = liquid2;
                return;
            }
            //합이 0과 더 가까우면
            if(closeToZero > Math.abs(sum)){
                //값 갱신
                closeToZero = Math.abs(sum);
                result[0] = liquid1;
                result[1] = liquid2;
            }
            //합이 0보다 크거나 같으면
            if(sum >= 0){
                right--;
            }
            //작으면
            else{
                left++;
            }
        }
    }
}
