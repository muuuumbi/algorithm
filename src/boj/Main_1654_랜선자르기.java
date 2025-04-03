package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
K개의 랜선 보유 - 길이가 다 다름
N개의 같은 길이의 랜선으로 만들기 위해 K개의 랜선을 자르기
(ex. 300cm : 140cm 두 개를 위해 20cm 버려야 함)
N개보다 많이 만드는 것도 N개를 만드는 것에 포함.
만들 수 있는 최대 랜선의 길이 구하기
 */
public class Main_1654_랜선자르기 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int K, N;
    static long max, result;
    static long[] lines;

    public static void main(String[] args) throws IOException {
        init();
        binarySearch();
        /*
        [출력]
        N개를 만들 수 있는 랜선의 최대 길이
         */
        System.out.println(result);
    }

    static void init() throws IOException{
        /*
        [입력]
        1. 랜선의 개수 K, 필요한 개수 N (1<=K<=10,000, 1<=N<=1,000,000, K<=N)
        2~K. 각 랜선의 길이 (2^32-1)
         */
        st = new StringTokenizer(br.readLine());
        K = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        lines = new long[K];
        for(int k = 0; k < K; k++){
            lines[k] = Long.parseLong(br.readLine());
            max = Math.max(max, lines[k]);
        }
    }

    static void binarySearch(){
        long mid;
        long low = 1;
        long high = max;

        while(low <= high){
            mid = (low + high)/2;
            long cnt = slice(mid);

            if(cnt >= N){
                result = Math.max(result, mid);
                low = mid + 1;
            }
            else {
                high = mid - 1;
            }
        }
    }

    static long slice(long length){
        long cnt = 0;
        for(int k = 0; k < K; k++){
            cnt += (lines[k] / length);
        }
        return cnt;
    }
}
