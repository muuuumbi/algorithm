package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
1. 한 위치부터 k개의 접시를 연속해서 먹을 경우 할인
2. 1번 행사에 참가할 경우 쿠폰에 적혀진 종류의 초밥 하나를 무료로 제공
   만약 번호에 적혀진 초밥이 없을 경우, 요리사가 새로 만들어 손님에게 제공
손님이 먹을 수 있는 초밥 가짓수의 최댓값을 구하기
 */
public class Main_15961_회전초밥 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int N, D, K, C, result;
    static int[] sort;
    static int[] sushi;

    public static void main(String[] args) throws IOException{
        init();
        windowSlide();
        /*
        [출력]
        초밥의 최대 가짓수
         */
        System.out.println(result);
    }

    static void init() throws IOException {
        /*
        [입력]
        1. 접시 수 N, 초밥 가짓수 D, 연속 접시 수 K, 쿠폰 번호 C
        (2 <= N <= 3,000,000, 2 <= D <= 3,000, 2 <= K <= 3,000 <= N, 1 <= C <= D
        2~N. 벨트 회전 방향대로 초밥 종류
         */
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        result = Integer.MIN_VALUE;

        sushi = new int[N];
        for(int n = 0; n < N; n++){
            sushi[n] = Integer.parseInt(br.readLine());

        }

        sort = new int[D+1];
    }

    static void windowSlide(){
        int cnt = 0;
        for(int k = 0; k < K; k++){
            //스시 번호
            int idx = sushi[k];
            //아직 먹지 않은 초밥이면
            if(sort[idx] == 0) {
                //개수 세주기
                cnt++;
            }
            sort[idx]++;
        }

        checkCoupon(cnt);

        for(int n = 1; n < N; n++){
            //이전 초밥 종류 빼기
            int left = sushi[n - 1];
            //1번 먹은 초밥이면
            if(sort[left] == 1){
                //먹은 종류 빼주기
                cnt--;
            }
            sort[left]--;

            //이후 초밥 더해주기
            int right = sushi[(n + K - 1) % N];
            //아직 먹지 않은 초밥이면
            if(sort[right] == 0){
                //먹은 종류 더해주기
                cnt++;
            }
            sort[right]++;

            checkCoupon(cnt);
        }
    }

    static void checkCoupon(int cnt){
        //쿠폰 초밥을 아직 안 먹어봤으면
        if(sort[C] == 0) {
            result = Math.max(result, cnt + 1);
        }
        //먹은 상태면
        else{
            result = Math.max(result, cnt);
        }
    }
}
