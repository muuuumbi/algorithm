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

    static int N, M, total;
    static boolean[][] isVisited, checked, switched;
    static List<Location>[][] rooms;

    //상, 하, 좌, 우
    static int[] dir_x = {0, 0, -1, 1}; //column
    static int[] dir_y = {-1, 1, 0, 0}; //row

    static class Location {
        int row;
        int column;

        Location(int row, int column){
            this.row = row;
            this.column = column;
        }

        public int getRow(){
            return this.row;
        }

        public int getColumn(){
            return this.column;
        }

        public void makeString(){
            System.out.println("현재 queue");
            System.out.println("row : " + row + " column : " + column);
        }
    }

    public static void main(String[] args) throws IOException{
        init();

        while(true){
            int plus = BFS();
            //더 킨 불이 없으면 break
            if(plus == 0){
                break;
            }
            total += plus;
        }
        /*
        [출력]
        베시가 불을 켤 수 있는 방의 최대 개수
         */
        System.out.println(total);
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
        total = 1;

        isVisited = new boolean[N][N]; //방문확인
        checked = new boolean[N][N]; //스위치 탐색한 방 확인
        switched = new boolean[N][N]; //불 켜진 방 확인ㅌ
        rooms = new List[N][N];
        for(int r = 0; r < N; r++){
            for(int c = 0; c < N; c++){
                rooms[r][c] = new ArrayList<>();
            }
        }

        for(int m = 0; m < M; m++){
            //(1,1)은 켜져있음
            st = new StringTokenizer(br.readLine());
            int room_r = Integer.parseInt(st.nextToken()) - 1;
            int room_c = Integer.parseInt(st.nextToken()) - 1;
            int switch_r = Integer.parseInt(st.nextToken()) - 1;
            int switch_c = Integer.parseInt(st.nextToken()) - 1;

            rooms[room_r][room_c].add(new Location(switch_r, switch_c));
        }
    }

    static int BFS(){
        int result = 0;
        Queue<Location> queue = new LinkedList<>();
        queue.add(new Location(0, 0));

        while(!queue.isEmpty()){
            Location location = queue.poll();
            int row = location.getRow();
            int column = location.getColumn();

            //이미 방문한 곳이면 넘기기
            if(isVisited[row][column]){
                continue;
            }

            //방문처리해주기
            isVisited[row][column] = true;

            //스위치를 확인하지 않은 방이면
            if(!checked[row][column]) {
                //해당 방의 스위치 리스트를 가져옴
                List<Location> switches = rooms[row][column];
                int sizeOfSwitches = switches.size();
                //헤당 방이 스위치를 가지고 있으면 탐색
                if (sizeOfSwitches != 0) {
                    for (int i = 0; i < sizeOfSwitches; i++) {
                        //스위치와 연관된 방 위치 가져오기
                        Location switchRoom = switches.get(i);
                        int switchRow = switchRoom.getRow();
                        int switchColumn = switchRoom.getColumn();
                        //해당 스위치 방의 불이 꺼져있으면 켜주기
                        if (!switched[switchRow][switchColumn]) {
                            switched[switchRow][switchColumn] = true;
                            System.out.println("불 킨 방 : [" + switchRow + "][" + switchColumn + "]");
                            //불 킨 방 값 ++
                            result++;
                        }
                    }
                }
                //스위치 확인 표시
                checked[row][column] = true;
            }

            //사방 탐색 후 불 켜진 곳 있으면 큐에 넣어주기
            for(int i = 0; i < 4; i++){
                int nextRow = row + dir_y[i];
                int nextColumn = column + dir_x[i];

                if(!isPossible(nextRow, nextColumn)){
                    continue;
                }

                //불 켜져 있으면
                if(switched[nextRow][nextColumn]){
                    //큐에 넣어주기
                    queue.add(new Location(nextRow, nextColumn));
                }
            }
        }

        return result;
    }

    //범위 벗어나면 false
    static boolean isPossible(int row, int column){
        if(row < 0 || row >= N || column < 0 || column >= N){
            return false;
        }
        return true;
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