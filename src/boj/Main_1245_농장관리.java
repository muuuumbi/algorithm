package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 N*M의 농장. 산봉우리가 총 몇 개인지 세야 한다.
 산봉우리는 같은 높이를 가지는 하나의 격자 혹은 인접한 격자들의 집합으로 이루어져 있다.
 "인접하다"는 X좌표와 Y좌표 차이가 모두 1 이하인 경우.
 산봉우리와 인접한 격자는 모두 산봉우리의 높이보다 작아야 한다.
 */
public class Main_1245_농장관리 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int N, M;
    static boolean isTop;
    static int[][] map;
    static boolean[][] checked;
    //상, 하, 좌, 우, 좌상, 좌하, 우상, 우하
    static int[] dir_x = {0, 0, -1, 1, -1, -1, 1, 1}; //column
    static int[] dir_y = {-1, 1, 0, 0, -1, 1, -1, 1}; //row

    public static void main(String[] args) throws IOException{
        init();
        /*
        [출력]
        산봉우리의 개수
         */
        int result = 0;
        for(int n = 0; n < N; n++){
            for(int m = 0; m < M; m++){
                //아직 탐색 전이면
                if(!checked[n][m]){
                    //산봉우리라는 가정
                    isTop = true;
                    dfs(n, m);
                    //탐색이 끝났을 때도 true이면 산봉우리
                    if(isTop){
                        result++;
                    }
                }
            }
        }
        System.out.println(result);
    }
    static void init() throws IOException{
        /*
        [입력]
        1. N, M (1 < N < 100, 1 < M <= 70)
        2~N. 줄마다의 격자의 높이 (0 < M <= 500)
         */
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        checked = new boolean[N][M];
        for(int n = 0; n < N; n++){
            st = new StringTokenizer(br.readLine());
            for(int m = 0; m < M; m++){
                map[n][m] = Integer.parseInt(st.nextToken());
            }
        }
    }

    static void dfs(int row, int column){
        //탐색 표시
        checked[row][column] = true;
        //인접 격자 탐색
        for(int i = 0; i < 8; i++){
            int nextRow = row + dir_y[i];
            int nextColumn = column + dir_x[i];

            //범위 벗어나면 넘기기
            if(nextRow < 0 || nextRow >= N || nextColumn < 0 || nextColumn >= M){
                continue;
            }

            //인접 격자에 더 높은 격자가 있으면 산봉우리가 아님.
            if(map[nextRow][nextColumn] > map[row][column]){
                isTop = false;
            }

            //무한루프 방지 && 높이가 같은 곳(같은 봉우리로 취급)이 있으면 dfs 탐색.
            if(!checked[nextRow][nextColumn] && map[nextRow][nextColumn] == map[row][column]){
                dfs(nextRow, nextColumn);
            }
        }
    }
}
