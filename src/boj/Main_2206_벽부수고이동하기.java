package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/*
N*M 맵이 있다. 0은 이동 가능 한 곳, 1은 이동할 수 없는 벽이 있는 곳
(1,1)에서 (N,M)의 위치까지 최단 경로로 이동하려 한다. 이때 시작하는 칸과 끝나는 칸도 포함해서 셈
이동 도중 한 개의 벽을 부수고 이동하는 것이 최단이라면 최대 1개까지 부수는 것이 허용
이동은 상하좌우로 가능
최단 경로를 구해라
 */
public class Main_2206_벽부수고이동하기 {
    static BufferedReader br = new BufferedReader((new InputStreamReader(System.in)));
    static StringTokenizer st;

    static int N, M, result;
    static int[][] map;
    static int[][][] DP;
    static boolean[][] isVisted;

    //상하좌우
    static int[] dir_x = {0, 0, -1, 1}; //column
    static int[] dir_y = {-1, 1, 0 ,0}; //row

    static class Path{
        int row;
        int column;
        int length;
        boolean isBreak;

        public Path(int row, int column, int length, boolean isBreak) {
            this.row = row;
            this.column = column;
            this.length = length;
            this.isBreak = isBreak;
        }

        public int getRow(){
            return row;
        }

        public int getColumn() {
            return column;
        }

        public boolean isBreak() {
            return isBreak;
        }

        public int getLength() {
            return length;
        }
    }

    //3차원배열 -> [][]위치,[2]([0]-벽 안 부쉈을 때 최단 거리,[1]- 벽 부쉈을 때 최단 거리)

    public static void main(String[] args) throws IOException{
        init();
        isVisted[0][0] = true;
        DFS(0,0,false, 1);
        /*
        [출력]
        최단 거리 출력. 불가능할 때는 -1을 출력
         */
        //도달하지 못 했으면 -1
        if(result == Integer.MAX_VALUE){
            result = -1;
        }
        System.out.println(result);
    }

    static void init() throws IOException{
        /*
        [입력]
        1. N(1<=N<=1,000), M(1<=M<=1,000)
        2~N. M개의 숫자로 맵이 주어짐
        (1,1)과 (N,M)은 항상 0임

        그래프 최대 크기 1,000,000 O(n^2) 안됨.
         */
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        result = Integer.MAX_VALUE;

        map = new int[N][M];
        for (int n = 0; n < N; n++) {
            String row = br.readLine();
            for (int m = 0; m < M; m++) {
                map[n][m] = row.charAt(m) - '0';
            }
        }

        isVisted = new boolean[N][M];

        DP = new int[N][M][2];
        for (int n = 0; n < N; n++) {
            for (int m = 0; m < M; m++) {
                for(int i = 0; i < 2; i++){
                    if(n == 0 && m == 0){
                        DP[n][m][i] = 0;
                    }
                    DP[n][m][i] = Integer.MAX_VALUE;
                }
            }
        }
    }
    //BFS
    static void BFS(){
        PriorityQueue<Path> pq = new PriorityQueue<>();
        pq.add(new Path(0, 0,0,false));

        while(!pq.isEmpty()){
            Path path = pq.poll();
            //시작점
            int row = path.getRow();
            int column = path.getColumn();
            //
            int length = path.getLength();

        }
    }

    //DFS
    static void DFS(int row, int column, boolean isBreak, int length) {
        //현재 도착지점이면
        if (row == N - 1 && column == M - 1) {
            //result 갱신해주고 끝내기
            result = result > length ? length : result;
            return;
        }

        for (int i = 0; i < 4; i++) {
            int nextRow = row + dir_y[i];
            int nextColumn = column + dir_x[i];

            //범위 벗어나거나 이미 방문한 곳이면 넘기기
            if (!isPossible(nextRow, nextColumn)) {
                continue;
            }

            //다음으로 넘어갔을 때의 길이
            int plusLength = length + 1;

            //현재의 최소경로보다 크거나 같으면 탐색 필요 없음
            if (plusLength >= result) {
                continue;
            }

            //다음 방이 벽일 때
            if (map[nextRow][nextColumn] == 1) {
                //현재 벽을 부순 적이 없으면 넘어갈 수 있음
                if (!isBreak) {
                    //방문처리
                    isVisted[nextRow][nextColumn] = true;
                    //부수고 넘어가기
                    DFS(nextRow, nextColumn, true, plusLength);
                    //방문처리 풀어주기
                    isVisted[nextRow][nextColumn] = false;
                }
                //벽을 부순 적이 있으면 못 넘어감
                else if(isBreak){
                    continue;
                }
            }
            //빈 방일 때
            else {
                //방문처리
                isVisted[nextRow][nextColumn] = true;
                DFS(nextRow, nextColumn, false, plusLength);
                //방문처리 풀어주기
                isVisted[nextRow][nextColumn] = false;
            }
        }
    }

    static boolean isPossible(int row, int column){
        if(row < 0 || row >= N || column < 0 || column >= M || isVisted[row][column]){
            return false;
        }
        return true;
    }
}
