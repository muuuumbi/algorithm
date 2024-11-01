package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
M*N*H 크기의 상자
익은 토마토의 인접(위,아래,왼쪽,오른쪽,앞,뒤)한 토마토는 익게 됨.
보관된 토마토가 며칠이 지나면 다 익게 되는지 최소 일수 구하기
 */
public class Main_7569_토마토 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int M, N, H, tomatos;
    static int[][][] box;
    static Queue<Tomato> queue;
    static boolean isAllRipe = true;
    //위,아래,왼쪽,오른쪽,앞,뒤
    static int[] rowDir = {0, 0, 0, 0, -1, 1};
    static int[] columnDir = {0, 0, -1, 1, 0, 0};
    static int[] heightDir = {1, -1, 0, 0, 0, 0};

    static class Tomato {
        int row, column, height;

        public Tomato(int row, int column, int height){
            this.row = row;
            this.column = column;
            this.height = height;
        }
    }

    public static void main(String[] args) throws IOException{
        init();

        System.out.println(ripeTomato());
        /*
        [출력]
        토마토가 모두 익을 때까지 걸리는 최소 일수.
        저장될 때부터 모두 익어있다면 0을 출력, 모두 익지 못하는 상황이면 -1 출력
         */
    }

    static void init() throws IOException {
        /*
        [입력]
        1. 가로 M, 세로 N, 높이 H (2<=M<=100, 2<=N<=100,1<=H<=100)
        2~N *H. 토마토 정보 (1 : 익은 토마토, 0 : 익지 않은 토마토, -1 : 빈 공간)
         */
        st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());
        tomatos = 0;
        queue = new LinkedList<>();

        box = new int[H][M][N];
        for(int h = 0; h < H; h++){
            for(int n = 0; n < N; n++){
                st = new StringTokenizer(br.readLine());
                for(int m = 0; m < M; m++){
                    int state = Integer.parseInt(st.nextToken());
                    box[h][m][n] = state;
                    //익은 토마토이면
                    if(state == 1){
                        //큐에 넣기
                        queue.add(new Tomato(m, n, h));
                    }
                }
            }
        }
    }

    static int ripeTomato(){
        while(!queue.isEmpty()){
            Tomato tomato = queue.poll();
            int nowRow = tomato.row;
            int nowColumn = tomato.column;
            int nowHeight = tomato.height;

            for(int i = 0; i < 6; i++){
                int nextRow = nowRow + rowDir[i];
                int nextColumn = nowColumn + columnDir[i];
                int nextHeight = nowHeight + heightDir[i];

                if(check(nextRow,nextColumn,nextHeight)){
                    //익은 토마토 큐에 넣기
                    queue.add(new Tomato(nextRow,nextColumn,nextHeight));
                    //해당 토마토 익는 데 걸린 시간 갱신
                    box[nextHeight][nextRow][nextColumn] = box[nowHeight][nowRow][nowColumn] + 1;
                }
            }
        }

        int result = Integer.MIN_VALUE;

        for(int h = 0; h < H; h++){
            for(int m = 0; m < M; m++) {
                for (int n = 0; n < N; n++) {
                    //안 익은 토마토가 있으면 -1 반환
                    if(box[h][m][n] == 0){
                        return -1;
                    }
                    //익는 데 걸리는 시간 갱신해주기
                    result = Math.max(result, box[h][m][n]);
                }
            }
        }
        //최대일수가 1이면 처음부터 모두 익어있었단 것
        return result == 1 ? 0 : result - 1;
    }

    static boolean check(int row, int column, int height){
        //범위 벗어나면 false
        if(row < 0 || row >= M || column < 0 || column >= N || height < 0 || height >= H){
            return false;
        }
        //안 익은 토마토이면 true
        return box[height][row][column] == 0;
    }
}
