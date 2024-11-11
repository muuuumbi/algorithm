package boj;

import java.io.*;
import java.util.StringTokenizer;

/*
매 초마다 두 개의 나무 중 하나의 나무에서 자두가 떨어짐.
열매가 떨어지는 순간 해당 나무 아래에 서 있으면 열매를 받을 수 있음.
열매는 T초동안 떨어지고
사람은 최대 W번만 움직일 수 있음
매 초마다 열매가 떨어지는 나무에 대한 정보가 주어졌을 때,
받을 수 있는 최대 개수 구하기
 */
public class Main_2240_자두나무 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int T, W;
    static int[] dropTree;
    static int[][][] DP; //[시간][이동 횟수][현재 위치]
    public static void main(String[] args) throws IOException{
        init();
        getFruit();
        /*
        [출력]
        받을 수 있는 열매의 최대 개수
         */
        int result = 0;
        for(int w = 1; w <= W + 1; w++) {
            result = Math.max(result, Math.max(DP[T][w][1], DP[T][w][2]));
        }
        System.out.println(result);
    }

    static void init() throws IOException{
        /*
        [입력]
        1. 열매 떨어지는 시간 T, 가능한 움직임 수 W (1 <= T <= 1,000, 1 <= W <= 30)
        2~T. 각 초마다 열매가 떨어지는 나무 번호가 1, 2로 주어짐
         */
        st = new StringTokenizer(br.readLine());
        T = Integer.parseInt(st.nextToken());
        W = Integer.parseInt(st.nextToken());

        dropTree = new int[T + 1];
        for(int t = 1; t <= T; t++){
            dropTree[t] = Integer.parseInt(br.readLine());
        }

        // 1. 시간 - 1 ~ T초
        // 2. 이동 횟수 - 1 ~ W회
        // 3. 현재 위치 - 1번 나무 / 2번 나무
        DP = new int[T + 1][W + 2][3];
    }

    static void getFruit(){
        for(int t = 1; t <= T; t++){
            for(int w = 1; w <= W + 1; w++){
                //열매가 떨어지는 위치가 1번 나무일 떄
                if(dropTree[t] == 1){
                    //현재 위치가 1번 나무 - 열매 받을 수 있음
                    //1번 나무에서 1번 나무 / 2번 나무에서 1번 나무 (이동 횟수 차감)
                    DP[t][w][1] = Math.max(DP[t - 1][w][1], DP[t - 1][w - 1][2]) + 1;
                    //현재 위치가 2번 나무
                    //1번 나무에서 2번 나무 (이동 횟수 차감) / 2번 나무에서 2번 나무
                    DP[t][w][2] = Math.max(DP[t - 1][w - 1][1], DP[t - 1][w][2]);
                }
                //열매가 떨어지는 위치가 2번 나무일 때
                else{
                    //1번 나무에서 시작해야 하기 때문에 처음 1초에 이동할 수 없음
                    if(t == 1 && w == 1){
                        continue;
                    }
                    //현재 위치가 1번 나무
                    //1번 나무에서 1번 나무 / 2번 나무에서 1번 나무 (이동 횟수 차감)
                    DP[t][w][1] = Math.max(DP[t - 1][w][1], DP[t - 1][w - 1][2]);
                    //현재 위치가 2번 나무 - 열매 받을 수 있음
                    //1번 나무에서 2번 나무 (이동 횟수 차감) / 2번 나무에서 2번 나무
                    DP[t][w][2] = Math.max(DP[t - 1][w - 1][1], DP[t - 1][w][2]) + 1;
                }
            }
        }
    }
}
