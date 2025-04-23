package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/*
칸의 높이는 바다와 인접한 면의 개수만큼 줄어듦.
0이하로는 줄어들지 않음

빙산이 두 덩어리 이상으로 분리되는 최소의 시간(년)을 구하기
 */
public class Main_2573_빙산 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int N, M, numOfIce;
    static boolean meltAll = false;
    static int[][] ocean, minus;
    static boolean[][] visited;
    //상하좌우
    static int[] dir_x = {0, 0, -1, 1}; //column
    static int[] dir_y = {-1, 1, 0, 0}; //row

    static List<IceLocation> iceList;
    static class IceLocation{
        int row, column;

        public IceLocation(int row, int column){
            this.row = row;
            this.column = column;
        }
    }
    public static void main(String[] args) throws IOException{
        init();
        /*
        [출력]
        분리되는 최초의 시간 출력. 녹을 때까지 분리되지 않으면 0 출력
         */
        int year = 0;
        while(true){
            for(int n = 0; n < N; n++){
                Arrays.fill(visited[n], false);
            }
            if(iceList.isEmpty()){
                System.out.println(0);
                return;
            }
            else if(isSeparated()){
                System.out.println(year);
                return;
            }
            meltIce();
            year++;
        }
    }

    static void init() throws IOException{
        /*
        [입력]
        1. 행 N, 열 M (3이상 300이하)
        2~N. 바다와 빙산 값
        3. 배열의 첫 번째, 마지막 행과 열은 항상 0
         */
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        ocean = new int[N][M];
        iceList = new ArrayList<>();
        for(int n = 0; n < N; n++){
            st = new StringTokenizer(br.readLine());
            for(int m = 0; m < M; m++){
                ocean[n][m] = Integer.parseInt(st.nextToken());
                if(ocean[n][m] != 0){
                    iceList.add(new IceLocation(n, m));
                }
            }
        }

        minus = new int[N][M];
        visited = new boolean[N][M];
    }

    //빙산 녹이기
    static void meltIce(){
        ArrayList<IceLocation> nextList = new ArrayList<>();

        //minus 값 채우기
        for (IceLocation iceLocation : iceList) {
            int row = iceLocation.row;
            int column = iceLocation.column;

            minus[row][column] = cntIcewithOcean(row, column);
        }

        //minus 값 빼주기
        for (IceLocation iceLocation : iceList) {
            int row = iceLocation.row;
            int column = iceLocation.column;

            ocean[row][column] = Math.max(0, ocean[row][column] - minus[row][column]);
            //다 녹지 않은 빙하 위치는 재저장
            if(ocean[row][column] > 0){
                nextList.add(new IceLocation(row, column));
            }
        }

        iceList = nextList;
    }

    //바다와 인접한 면 개수 세기
    static int cntIcewithOcean(int row, int column){
        int cnt = 0;
        for(int idx = 0; idx < 4; idx++){
            if(ocean[row+dir_y[idx]][column+dir_x[idx]] == 0){
                cnt++;
            }
        }
        return cnt;
    }

    //빙산이 분리되었는지 확인
    //true면 분리, false면 하나이거나 다 녹았다는 뜻
    static boolean isSeparated(){
        IceLocation startLocation = iceList.get(0);
        int connectedIce = 1;

        Queue<IceLocation> queue = new LinkedList<>();
        queue.add(startLocation);
        visited[startLocation.row][startLocation.column] = true;

        while(!queue.isEmpty()){
            IceLocation iceLocation = queue.poll();

            for(int idx = 0; idx < 4; idx++){
                int nextRow = iceLocation.row + dir_y[idx];
                int nextColumn = iceLocation.column + dir_x[idx];

                if(ocean[nextRow][nextColumn] == 0 || visited[nextRow][nextColumn]){
                    continue;
                }

                visited[nextRow][nextColumn] = true;
                connectedIce++;
                queue.add(new IceLocation(nextRow, nextColumn));
            }
        }

        return connectedIce < iceList.size();
    }
}
