package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
NxM 미로
가장 왼쪽 윗 방 (1,1), 가장 오른쪽 아랫 방 (N,M)
각 방에는 사탕이 있음
현재 위치 (1,1)에서 (N,M)으로 이동하려 할 때
(r,c)에 있으면 아래,오른쪽,오른쪽아래대각선으로 이동 가능

가져올 수 있는 사탕 개수의 최댓값
 */
public class Main_11048_이동하기 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int N, M;
    static int[][] maze;
    static int[][] DP;
    //오른쪽, 아래, 대각선
    static int[] dir_x = {1, 0, 1}; //column
    static int[] dir_y = {0, 1, 1}; //row
    public static void main(String[] args)throws IOException{
        init();
        /*
        [출력]
        사탕의 최댓값
         */
        method();
        System.out.print(DP[N][M]);
    }
    static void init() throws IOException {
        /*
        [입력]
        1. 미로의 크기 N,M (1<=N,M<=1,000)
        2~N. 사탕 개수 (0<=<=100)
         */
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        maze = new int[N+1][M+1];
        for(int row = 1; row <= N; row++){
            st = new StringTokenizer(br.readLine());
            for(int column = 1; column <= M; column++){
                maze[row][column] = Integer.parseInt(st.nextToken());
            }
        }

        DP = new int[N+1][M+1];
    }

    static void method(){
        for(int i = 1; i <= N; i++){
            for(int j = 1; j <= M; j++){
                DP[i][j] = Math.max(DP[i][j-1], Math.max(DP[i-1][j], DP[i-1][j-1])) + maze[i][j];
            }
        }
    }
}
