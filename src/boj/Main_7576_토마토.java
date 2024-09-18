package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/*
M*N 상자
익은 토마토의 인접한 익지 않은 토마토는 하루 뒤 익게 됨
인접하다란 상하좌우 네 방향을 의미
모든 토마토가 익는 최소 일수
 */
public class Main_7576_토마토 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int N, M;
    static int status;
    static int[][] box;
    static boolean[][] visited;
    static Queue<Tomato> queue;
    //상하좌우
    static int[] dir_y = {-1, 1, 0, 0}; //row
    static int[] dir_x = {0, 0, -1, 1}; //column

    static class Tomato {
        int row;
        int column;
        int day;
        public Tomato(int row, int column, int day){
            this.row = row;
            this.column = column;
            this.day = day;
        }

        public int getRow(){
            return this.row;
        }
        public int getColumn(){
            return this.column;
        }
        public int getDay(){
            return this.day;
        }
    }
    public static void main(String[] args) throws IOException{
        init();
        /*
        [출력]
        최소 일수 출력.
        단, 처음부터 모든 토마토가 익어있는 상태이면 0 출력
        모두 익지 못하는 상황이면 -1 출력
         */
        //모두 익어있는 상태이면
        if(status == 1){
            System.out.println(0);
            return;
        }
        int result = bfs();

        System.out.println(result);
    }

    static void init() throws IOException{
        /*
        [입력]
        1. N, M (2 <= M,N <= 1,000)
        2~N. 토마토 정보 (1은 익은 토마토, 0은 익지 않은 토마토, -1은 빈 칸)
         */
        st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken()); //column
        N = Integer.parseInt(st.nextToken()); //row
        box = new int[N][M];
        visited = new boolean[N][M];
        queue = new LinkedList<>();
        status = 1;

        for(int n = 0; n < N; n++){
            st = new StringTokenizer(br.readLine());
            for(int m = 0; m < M; m++){
                box[n][m] = Integer.parseInt(st.nextToken());
                //익은 토마토이면
                if(box[n][m] == 1) {
                    //위치 저장
                    queue.add(new Tomato(n, m, 0));
                    //방문 처리
                    visited[n][m] = true;
                }
                //전체가 익어있는 토마토가 아니면
                if(box[n][m] == 0){
                    //상태값 0로 초기화
                    status = 0;
                }
            }
        }
    }

    static int bfs(){
        int day = 0;

        while(!queue.isEmpty()){
            Tomato now = queue.poll();

            int nowRow = now.getRow();
            int nowColumn = now.getColumn();
            day = now.getDay();

            //인접 위치의
            for(int i = 0; i < 4; i++){
                int nextRow = nowRow + dir_y[i];
                int nextColumn = nowColumn + dir_x[i];

                //범위 넘어가거나 비어있으면 넘기기
                if(nextRow < 0 || nextRow >= N || nextColumn < 0 || nextColumn >= M){
                    continue;
                }
                //빈칸이거나 이미 방문했으면 넘기기
                if(visited[nextRow][nextColumn] || box[nextRow][nextColumn] == -1){
                    continue;
                }

                //익지 않은 토마토이면
                if(box[nextRow][nextColumn] == 0){
                    //익게 하고
                    box[nextRow][nextColumn] = 1;
                    //방문 처리 후
                    visited[nextRow][nextColumn] = true;
                    //리스트에 넣기
                    queue.add(new Tomato(nextRow, nextColumn, day + 1));
                }
            }
        }
        //익지 못 하는 상황이면
        if(!isRipe()){
            return -1;
        }
        else{
            return day;
        }
    }

    //전체 토마토가 익었는지 확인
    static boolean isRipe(){
        for(int n = 0; n < N; n++){
            for(int m = 0; m < M; m++){
                //익지 않은 토마토가 있으면
                if(box[n][m] == 0){
                    return false;
                }
            }
        }
        return true;
    }
}
