package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
모든 지점에 대해서 목표지점까지의 거리 구하기
가로, 세로만 이동 가능
*/
public class Main_14940_쉬운최단거리 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    static int N, M, destRow, destColumn;
    static int[][] map;
    static int[][] result;
    static boolean[][] visited;
    //상, 하, 좌, 우
    static int[] dir_x = {0, 0, -1, 1};//column
    static int[] dir_y = {-1, 1, 0, 0};//row

    static class Location {
        int row;
        int column;
        int length;

        public Location(int row, int column, int length) {
            this.row = row;
            this.column = column;
            this.length = length;
        }
    }

    public static void main(String[] args) throws IOException {
        init();
        BFS();
        /*
        [출력]
        각 지점에서 목표지점까지의 거리 출력
        갈 수 없는 땅은 0, 갈 수 있는 곳 중 못 가는 곳은 -1
        */
        for (int n = 0; n < N; n++) {
            for (int m = 0; m < M; m++) {
                //도착지점은
                if (map[n][m] == 2) {
                    sb.append(0).append(" ");
                }
                //원래 갈 수 없는 곳
                else if (map[n][m] == 0) {
                    sb.append(0).append(" ");
                }
                //갈 수 있는데 도달 불가면
                else if (map[n][m] != 0 && result[n][m] == 0) {
                    sb.append(-1).append(" ");
                } else {
                    sb.append(result[n][m]).append(" ");
                }
            }
            sb.append("\n");
        }

        System.out.println(sb);
    }

    static void init() throws IOException {
        /*
        [입력]
        1. 지도의 크기 세로n, 가로m (2<=n<=1,000, 2<=m<=1,000)
        2~n.m개의 숫자 (0:갈 수 없는 땅, 1: 갈 수 있는 땅, 2: 목표지점)
        */
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        for (int n = 0; n < N; n++) {
            st = new StringTokenizer(br.readLine());
            for (int m = 0; m < M; m++) {
                map[n][m] = Integer.parseInt(st.nextToken());
                //도착지점 저장
                if(map[n][m] == 2){
                    destRow = n;
                    destColumn = m;
                }
            }
        }

        result = new int[N][M];
        visited = new boolean[N][M];
    }

    static void BFS() {
        Queue<Location> queue = new LinkedList<>();
        Location location = new Location(destRow, destColumn, 0);
        queue.add(location);

        while (!queue.isEmpty()) {
            Location startLoc = queue.poll();
            int nowRow = startLoc.row;
            int nowColumn = startLoc.column;
            int nowLength = startLoc.length;

            //상, 하, 좌, 우 탐색
            for (int i = 0; i < 4; i++) {
                int nextRow = nowRow + dir_y[i];
                int nextColumn = nowColumn + dir_x[i];
                int nextLength = nowLength + 1;

                //범위 벗어나거나 이미 탐색한 곳이면
                if (!isPossible(nextRow, nextColumn)) {
                    continue;
                }

                //갈 수 없는 길이면
                if (map[nextRow][nextColumn] == 0) {
                    visited[nextRow][nextColumn] = true;
                    continue;
                }

                result[nextRow][nextColumn] = nextLength;
                visited[nextRow][nextColumn] = true;
                queue.add(new Location(nextRow, nextColumn, nextLength));
            }
        }
    }

        static boolean isPossible(int row, int column){
            return row >= 0 && row < N && column >= 0 && column < M && !visited[row][column];
        }
    }
