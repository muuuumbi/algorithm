package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/*
상근이네(출발), 맥주 한 박스(20개), 50미터 당 한 병씩, 페스티벌(도착)
맥주를 더 구매할 수도 있지만 소지할 수 있는 맥주는 최대 20병
 */
public class Main_9205_맥주마시면서걸어가기 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    static int T, N;
    static Loc home, festival;
    static ArrayList<Loc> stores;

    static class Loc{
        int x;
        int y;
        public Loc(int x, int y){
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) throws IOException{
        /*
        [출력]
        맥주 있이 도착하면 happy, 없으면 sad 출력
         */
        T = Integer.parseInt(br.readLine());
        for(int t = 0; t < T; t++){
            init();
            sb.append(bfs(0, 0) ? "happy" : "sad").append("\n");
        }
        System.out.println(sb);
    }

    static void init() throws IOException{
        /*
        [입력]
        1. 테스트케이스 개수 t (t <= 50)
        -t-
        1. 편의점 개수 N (0 <= N <= 100)
        2~n. 상근이네, 편의점, 페스티벌 좌표 (-32768 <= x, y <= 32767)
        좌표 사이의 거리는 x좌표의 차이 + y좌표의 차이
         */
        N = Integer.parseInt(br.readLine());

        stores = new ArrayList<>();
        //상근이네
        st = new StringTokenizer(br.readLine());
        home = new Loc(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        //편의점
        for(int n = 0; n < N; n++){
            st = new StringTokenizer(br.readLine());
            stores.add(new Loc(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
        }
        //페스티벌
        st = new StringTokenizer(br.readLine());
        festival = new Loc(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
    }

    //거리 계산
    static int getLen(Loc startLoc, Loc endLoc){
        return Math.abs(startLoc.x - endLoc.x) + Math.abs(startLoc.y - endLoc.y);
    }

    static boolean bfs(int x, int y){
        Queue<Loc> queue = new LinkedList<>();
        //편의점 방문 여부 체크
        boolean[] visited = new boolean[N + 1];
        queue.add(home);

        while(!queue.isEmpty()){
            Loc curLoc = queue.poll();

            //페스티벌에 바로 갈 수 있으면 바로 끝내기
            if(getLen(curLoc, festival) <= 1000){
                return true;
            }

            //편의점 탐색
            for(int n = 0; n < N ; n++){
                //방문 가능한 편의점이면
                if(!visited[n] && getLen(curLoc, stores.get(n)) <= 1000){
                    //큐에 넣기
                    visited[n] = true;
                    queue.add(stores.get(n));
                }
            }
        }
        return false;
    }
}