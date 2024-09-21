package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
N*M 맵. 0은 이동 가능 한 곳, 1은 이동할 수 없는 벽이 있는 곳
(1,1)에서 (N,M)의 위치까지 최단 경로로 이동.
시작하는 칸과 끝나는 칸도 포함해서 셈
이동 도중 한 개의 벽을 부수고 이동하는 것이 최단이라면 최대 1개까지 부수는 것이 허용
이동은 상하좌우로 가능
최단 경로를 구해라
 */
public class Main_2206_벽부수고이동하기 {
    static BufferedReader br = new BufferedReader((new InputStreamReader(System.in)));
    static StringTokenizer st;

    static int N, M, result;
    static int[][] map;
    //[row][column][해당 위치에 도달했을 때 벽을 부쉈는지 여부]
    static boolean[][][] isVisted;

    //상하좌우
    static int[] dir_x = {0, 0, -1, 1}; //column
    static int[] dir_y = {-1, 1, 0 ,0}; //row

    static class Node{
        int row;
        int column;
        int length;
        int broken; //0 : 벽을 부수지 않을 상태, 1 : 벽을 부순 상태

        Node(int row, int column, int length, int broken){
            this.row = row;
            this.column = column;
            this.length = length;
            this.broken = broken;
        }

        public int getRow(){
            return this.row;
        }

        public int getColumn(){
            return this.column;
        }

        public int getLength(){
            return this.length;
        }

        public int getBroken(){
            return this.broken;
        }

        public void setBroken(int broken){
            this.broken = broken;
        }


    }

    public static void main(String[] args) throws IOException{
        init();
        /*
        [출력]
        최단 거리 출력. 불가능할 때는 -1을 출력
         */
        BFS();

        if(result == Integer.MAX_VALUE){
            System.out.println(-1);
        }
        else{
            System.out.println(result);
        }
    }

    static void init() throws IOException{
        /*
        [입력]
        1. N(1<=N<=1,000), M(1<=M<=1,000)
        2~N. M개의 숫자로 맵이 주어짐
        (1,1)과 (N,M)은 항상 0임
         */
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        result = Integer.MAX_VALUE;

        isVisted = new boolean[N][M][2];
        map = new int[N][M];
        for(int n = 0; n < N; n++){
            String row = br.readLine();
            for(int m = 0; m < M; m++){
                map[n][m] = row.charAt(m) - '0';
            }
        }
    }

    static void BFS(){
        Queue<Node> queue = new LinkedList<>();
        queue.add(new Node(0,0,1,0));
        isVisted[0][0][0] = true;

        while(!queue.isEmpty()){
            Node nowNode = queue.poll();
            int nowRow = nowNode.getRow();
            int nowColumn = nowNode.getColumn();
            int nowLength = nowNode.getLength();
            int nowBroken = nowNode.getBroken();

            if(nowRow == N-1 && nowColumn == M-1){
                result = Math.min(result, nowLength);
            }

            for(int i = 0; i < 4; i++){
                int nextRow = nowRow + dir_y[i];
                int nextColumn = nowColumn + dir_x[i];

                if(nextRow < 0 || nextRow >= N || nextColumn < 0 || nextColumn >= M){
                    continue;
                }

                //벽이 없고 아직 방문하지 않은 경우
                if(map[nextRow][nextColumn] == 0 && !isVisted[nextRow][nextColumn][nowBroken]){
                    //방문처리
                    isVisted[nextRow][nextColumn][nowBroken] = true;
                    queue.add(new Node(nextRow, nextColumn, nowLength + 1, nowBroken));
                }
                //벽이 있고 벽을 아직 부수지 않은 경우
                if(map[nextRow][nextColumn] == 1 && nowBroken == 0 && !isVisted[nextRow][nextColumn][1]){
                    isVisted[nextRow][nextColumn][1] = true;
                    queue.add(new Node(nextRow, nextColumn, nowLength + 1, 1));
                }
            }
        }
    }
}
