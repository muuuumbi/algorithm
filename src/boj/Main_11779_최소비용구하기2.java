package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/*
n개의 도시. m개의 버스
A번째 도시에서 B번째 도시까지의 최소비용과 경로 출력.
경로는 항상 존재.
 */
public class Main_11779_최소비용구하기2 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    static ArrayList<LinkInfo>[] list;

    static int N, M, A, B;
    static final int INF = 100_000_000;
    //최소 비용 저장, order[index]에 index번호의 목적지 이전의 번호를 저장
    static int[] DP, order;
    static boolean[] visited;

    private static class LinkInfo implements Comparable<LinkInfo>{
        int node;
        //1. 간선 역할 2. 누적값 역할
        int edge;
        public LinkInfo(int node, int edge){
            this.node = node;
            this.edge = edge;
        }

        public int getNode(){
            return this.node;
        }
         public int getEdge(){
            return this.edge;
         }

         @Override
        public int compareTo(LinkInfo o){
            //오름차순 정렬
            return this.edge - o.getEdge() >= 0 ? 1 : -1;
         }
    }

    public static void main(String[] args) throws IOException{
        init();
        /*
        [출력]
        1. 최소 비용
        2. 경로에 포함돼있는 도시 개수
        3. 경로 속 도시 순서대로
         */
        dijkstra();
        //최소비용
        sb.append(DP[B]).append("\n");
        //도시 순서
        ArrayList<Integer> routes = new ArrayList<>();
        int idx = B;
        while(idx != 0){
            routes.add(idx);
            idx = order[idx];
        }
        //도시 순서
        sb.append(routes.size()).append("\n");
        for(int i = routes.size() - 1; i >= 0; i--){
            sb.append(routes.get(i)).append(" ");
        }
        System.out.println(sb);
    }

    private static void init() throws IOException{
        /*
        [입력]
        1. N (1 <= N <= 1,000)
        2. M (1 <= M <= 100,000)
        3~M. a b c (a: 출발 도시 번호, b: 도착지 도시 번호, c: 버스 비용, 0 <= c <=100,000)
        4. A, B (출발, 도착)
         */
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());

        list = new ArrayList[N+1];
        for(int n = 1; n <= N; n++){
            list[n] = new ArrayList<>();
        }

        for(int m = 0; m < M; m++){
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            list[start].add(new LinkInfo(end, cost));
        }

        st = new StringTokenizer(br.readLine());
        A = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());

        DP = new int[N+1];
        Arrays.fill(DP, INF);

        //순서넣기
        order = new int[N+1];
        //방문체크
        visited = new boolean[N+1];
    }

    private static void dijkstra(){
        PriorityQueue<LinkInfo> pq = new PriorityQueue<>();
        pq.add(new LinkInfo(A, 0));
        //시작점은 A으로 초기화
        DP[A] = 0;
        order[A] = 0;

        while(!pq.isEmpty()){
            LinkInfo curLinkInfo = pq.poll();

            int curNode = curLinkInfo.getNode();
            int curEdge = curLinkInfo.getEdge();

            //방문 전인 곳만 가기
            if(!visited[curNode]){
                visited[curNode] = true;
            }else {
                continue;
            }

            //연결된 간선 탐색
            for(int i = 0; i < list[curNode].size(); i++){
                LinkInfo linkInfo = list[curNode].get(i);
                int nextNode = linkInfo.getNode();
                int nextEdge = linkInfo.getEdge();

                //next로 가는 가중치 계산
                int value = DP[curNode] + nextEdge;

                //현재 경로가 최소비용이면
                if(DP[nextNode] > value){
                    //값 갱신
                    DP[nextNode] = value;
                    //이전 순서(현재) 저장
                    order[nextNode] = curNode;

                    pq.add(new LinkInfo(nextNode, value));
                }
            }
        }
    }
}
