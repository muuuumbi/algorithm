package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
크기가 N*N인 집
파이프는 회전 가능하며 2개 칸을 차지.
-, |, \ 방향 가능. 파이프는 →, ↘, ↓ 으로 이동.
빈 칸만 차지하며 벽을 긁으면 안됨.
회전은 45도만 가능
파이프의 한 쪽 끝을 (N,N)으로 이동시키는 방법의 개수 구하기
 */
public class Main_17070_파이프옮기기1 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int N, result;
    static int[][] house;
    public static void main(String[] args) throws IOException{
        init();
        //시작 위치, 가로
        DFS(1, 2, 0);
        /*
        [출력]
        방법의 수 출력. 이동시킬 수 없으면 0 출력
         */
        System.out.println(result);
    }
    static void init() throws IOException {
        /*
        [입력]
        1. 집의 크기 N (3 <= N <= 16)
        2~N. 집의 상태 (0: 빈 칸, 1: 벽)
         */
        N = Integer.parseInt(br.readLine());
        house = new int[N+1][N+1];

        for(int i = 1; i <= N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 1; j <= N; j++){
                house[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        result = 0;
    }

    static void DFS(int row, int column, int direction){
        if(row == N && column == N){
            result++;
            return;
        }

        switch(direction){
            //가로 방향 - 오른쪽, 대각선 가능
            case 0:
                //범위 내 && 이동 가능이면
                if(column + 1 <= N && house[row][column + 1] == 0){
                    DFS(row, column + 1, 0);
                }
                break;
            //세로 방향 - 아래, 대각선 가능
            case 1:
                //범위 내 && 이동 가능이면
                if(row + 1 <= N && house[row + 1][column] == 0){
                    DFS(row + 1, column, 1);
                }
                break;
            //대각선 방향 - 오른쪽, 아래, 대각선 가능
            case 2:
                //오른쪽으로 이동(가로)
                if(column + 1 <= N && house[row][column + 1] == 0){
                    DFS(row, column + 1, 0);
                }
                //아래로 이동(세로)
                if(row + 1 <= N && house[row + 1][column] == 0){
                    DFS(row + 1, column, 1);
                }
                break;
        }

        //대각선 이동
        // 범위 내 && 오른쪽, 아래, 대각선 다 빈 칸이면
        if(column + 1 <= N && row + 1 <= N
                && house[row][column + 1] == 0 && house[row + 1][column] == 0 && house[row + 1][column + 1] == 0){
            DFS(row + 1, column + 1, 2);
        }
    }
}
