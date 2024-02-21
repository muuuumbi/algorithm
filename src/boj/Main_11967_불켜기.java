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
        List<SwitchRC>  switchRCList;

        State(boolean light, List<SwitchRC> switchRCList){
            this.light = light;
            this.switchRCList = switchRCList;
        }

        List<SwitchRC> getSwitchRCList(){
            return this.switchRCList;
        }
    }

    static class SwitchRC {
        int row;
        int column;

        SwitchRC(int row, int column){
            this.row = row;
            this.column = column;
        }
    }

    public static void main(String[] args) throws IOException{
        init();


        /*
        [출력]
        베시가 불을 켤 수 있는 방의 최대 개수
         */
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
        isVisited[0][0] = true;
        for(int m = 0; m < M; m++){
            //(1,1)은 켜져있음
            st = new StringTokenizer(br.readLine());
            int room_r = Integer.parseInt(st.nextToken()) - 1;
            int room_c = Integer.parseInt(st.nextToken()) - 1;
            int switch_r = Integer.parseInt(st.nextToken()) - 1;
            int switch_c = Integer.parseInt(st.nextToken()) - 1;

            //이미 있으면 list에 추가
            if(rooms[room_r][room_c] != null){
                rooms[room_r][room_c].getSwitchRCList().add(new SwitchRC(switch_r, switch_c));
            }
            //없으면 객체 생성
            else{
                //(1,1)이면
                if(room_r == 0 && room_c == 0){
                    List<SwitchRC> switchRCList = new ArrayList<>();
                    switchRCList.add(new SwitchRC(switch_r, switch_c));
                    rooms[room_r][room_c] = new State(true, switchRCList);
                }
                //그 외
                else{
                    List<SwitchRC> switchRCList = new ArrayList<>();
                    switchRCList.add(new SwitchRC(switch_r, switch_c));
                    rooms[room_r][room_c] = new State(false, switchRCList);
                }
            }
        }
    }

    static void logic(){
        Queue<Integer> queue = new LinkedList<>();
        queue.add()
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