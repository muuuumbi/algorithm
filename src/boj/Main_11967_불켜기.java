package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/*
N*N개의 방이 있는 헛간
(1,1)부터 (N,N)까지의 번호가 매겨져있다. (2<=N<=100)
최대한 많은 방에 불을 밝히고 싶어한다.

베시는 유일하게 불이 켜져있는 방인 (1,1)에서 출발
어떤 방에는 다른 방의 불을 끄고 켤 수 있는 스위치가 달려있다.
예 ) (1,1)에는 (1,2)의 스위치가 있다

베시는 불이 켜져있는 방으로만 들어갈 수 있고, 각 방에서는 상하좌우에 인접한 방으로 움직일 수 있다.

불을 켤 수 있는 방의 최대 개수를 구하시오
 */
public class Main_11967_불켜기 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;

    static int N, M;
    static State[][] rooms;
    static boolean[][] isVisited;

    //상, 하, 좌, 우
    static int[] dir_x = {0, 0, -1, 1}; //column
    static int[] dir_y = {-1, 1, 0, 0}; //row

    static class State {
        boolean light;
        List<SwitchRC> switchRCList;

        State(boolean light, List<SwitchRC> switchRCList){
            this.light = light;
            this.switchRCList = switchRCList;
        }

        boolean getLight(){
            return this.light;
        }

        List<SwitchRC> getSwitchRCList(){
            return this.switchRCList;
        }

        void setLight(boolean light){
            this.light = light;
        }
    }

    static class SwitchRC {
        int row;
        int column;

        SwitchRC(int row, int column){
            this.row = row;
            this.column = column;
        }

        int getRow(){
            return this.row;
        }

        int getColumn(){
            return this.column;
        }
    }

    static class Location {
        int row;
        int column;

        Location(int row, int column){
            this.row = row;
            this.column = column;
        }
    }

    public static void main(String[] args) throws IOException{
        init();

        BFS();

        /*
        [출력]
        베시가 불을 켤 수 있는 방의 최대 개수
         */
        int result = getResult();
        System.out.println(result);
    }

    static void init() throws IOException {
        /*
        [입력]
        1. N(2<=N<=100), M(1<=M<=20000)
        2~M. x, y, a, b
            (x,y)방에서 (a,b)방의 불을 켜고 끌 수 있음.
            한 방에 여러개의 스위치가 있을 수 있고 하나의 불을 조절하는 스위치 역시 여러개 있을 수 있다.
         */

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        rooms = new State[N][N];
        isVisited = new boolean[N][N];

        for(int m = 0; m < M; m++){
            //(1,1)은 켜져있음
            st = new StringTokenizer(br.readLine());
            int room_r = Integer.parseInt(st.nextToken()) - 1;
            int room_c = Integer.parseInt(st.nextToken()) - 1;
            int switch_r = Integer.parseInt(st.nextToken()) - 1;
            int switch_c = Integer.parseInt(st.nextToken()) - 1;

            //null이 아니면(이미 있으면) list에 추가
            if(rooms[room_r][room_c] != null){
                rooms[room_r][room_c].getSwitchRCList().add(new SwitchRC(switch_r, switch_c));
            }
            //없으면 객체 생성
            else{
                List<SwitchRC> switchRCList = new ArrayList<>();
                switchRCList.add(new SwitchRC(switch_r, switch_c));
                rooms[room_r][room_c] = new State(false, switchRCList);
            }
        }
        //(1,1) 불 켜주기
        rooms[0][0].setLight(true);
    }

    static void BFS(){
        Queue<Location> queue = new LinkedList<>();
        queue.add(new Location(0, 0));

        while(!queue.isEmpty()){
            Location location = queue.poll();
            int row = location.row;
            int column = location.column;

            //이미 방문한 곳이면 넘기기
            if(isVisited[row][column]){
                continue;
            }

            //방문처리해주기
            isVisited[row][column] = true;

            //현재 방의 스위치를 탐색해서 해당 스위치의 방이 불이 꺼져있으면 켜주고 켜져있으면 넘긴다.
            System.out.println("row : " + row + " column : " + column);
            //null이 아니면
            if(rooms[row][column] != null) {
                List<SwitchRC> switchRCList = rooms[row][column].getSwitchRCList();
                for (int i = 0; i < switchRCList.size(); i++) {
                    SwitchRC switchRC = switchRCList.get(i);
                    int switch_r = switchRC.getRow();
                    int switch_c = switchRC.getColumn();
                    //null이면
                    if (rooms[switch_r][switch_c] == null) {
                        //객체 생성해서 넣어주기
                        List<SwitchRC> switchRCListOfswitch = new ArrayList<>();
                        switchRCListOfswitch.add(new SwitchRC(switch_r, switch_c));
                        //켜있는 상태로 넣어주기
                        rooms[switch_r][switch_c] = new State(true, switchRCListOfswitch);
                        //켜준 위치 queue에 넣어주기
                        queue.add(new Location(switch_r, switch_c));
                    }
                    //꺼져있으면 켜주기
                    if (!rooms[switch_r][switch_c].getLight()) {
                        rooms[switch_r][switch_c].setLight(true);
                        //켜준 위치 queue에 넣어주기
                        queue.add(new Location(switch_r, switch_c));
                    }
                }
            }

            //상하좌우 탐색해서 불 켜진 곳 queue에 넣기
            for(int i = 0; i < 4; i++){
                int next_r = row + dir_y[i];
                int next_c = column + dir_x[i];

                //범위 넘어가면 넘기기
                if(next_r < 0 || next_r >= N || next_c < 0 || next_c >= N){
                    continue;
                }

                //이미 탐색한 곳이면 넘기기
                if(isVisited[next_r][next_c]){
                    continue;
                }
                //null이면
                if (rooms[next_r][next_c] == null) {
                    //객체 생성해서 넣어주기
//                    List<SwitchRC> switchRCList = new ArrayList<>();
//                    switchRCList.add(new SwitchRC(next_r, next_c));
//                    rooms[next_r][next_c] = new State(false, switchRCList);
                }
                else {
                    if (rooms[next_r][next_c].getLight()) {
                        queue.add(new Location(next_r, next_c));
                    }
                }
            }
        }
    }

    static int getResult() {
        int result = 0;

        for (int r = 0; r < N; r++) {
            for (int c = 0; c < N; c++) {
                //비어있지 않
                if(rooms[r][c] != null) {
                    if (rooms[r][c].getLight()) {
                        result++;
                    }
                }
            }
        }
        return result;
    }
}

/*
시간제한 2초
복잡도 : N*N = 10,000 * M = 200,000,000

1 0 0
0 0 0
0 0 0

1. 인접한 방 중 불이 켜져있는 곳
2. BFS?
 */