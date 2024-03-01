package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/*
방향그래프가 주어지면 시작점에서 다른 모든 정점으로의 최단 경로를 구하기
모든 간선치의 가중치는 10 이하의 자연수
 */
public class Main_1753_최단경로 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static int V, E, K;
    static List<LinkInfo>[] list;
    static int[] DP;

    public static void main(String[] args) throws IOException{
        init();
        dijkstra();
        /*
        [출력]
        v개의 줄에 걸쳐, i번 정점으로의 최단 경로의 경로값을 출력
        시작점 자신은 0, 경로가 존재하지 않는 경우에는 INF를 출력
         */
        for(int result : DP){
            if(result == Integer.MAX_VALUE){
                sb.append("INF" + "\n");
            }
            else {
                sb.append(result + "\n");
            }
        }
        System.out.println(sb);
    }

    static class LinkInfo implements Comparable<LinkInfo>{
        int node;
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
        public int compareTo(LinkInfo o) {
            return this.edge - o.getEdge() >= 0 ? 1 : -1;
        }
    }
    static class LinkInfoComparator implements Comparator<LinkInfo>{
        @Override
        public int compare(LinkInfo o1, LinkInfo o2){
            return o1.getEdge() - o2.getEdge();
        }
    }

    static void init() throws IOException {
        /*
        [입력]
        1. 정점 수 V, 간선 수 E (1<=V<=20.000, 1<=E<=300,000)
        >>그래프로 하면 20,000 * 20,000 = 400,000,000 안됨.
        인접리스트로 풀기
        2. 시작 정점 번호 K (1<=K<=V)
        3~E. u, v, w (u에서 v로 가는 가중치 w, u와 v는 서로 다름, w는 10이하의 자연수)
         */
         st = new StringTokenizer(br.readLine());
         V = Integer.parseInt(st.nextToken());
         E = Integer.parseInt(st.nextToken());

         K = Integer.parseInt(br.readLine()) - 1; //시작 정점

         list = new ArrayList[V];
         for(int v = 0; v < V; v++){
             list[v] = new ArrayList<>();
         }

         DP = new int[V];
         for(int v = 0; v < V; v++){
             //시작점은 0으로 초기화
             if(v == K){
                 DP[v] = 0;
             }
             else{
                 DP[v] = Integer.MAX_VALUE;
             }
         }

         for(int e = 0; e < E; e++){
             st = new StringTokenizer(br.readLine());
             int u = Integer.parseInt(st.nextToken()) - 1;
             int v = Integer.parseInt(st.nextToken()) - 1;
             int w = Integer.parseInt(st.nextToken());

             list[u].add(new LinkInfo(v, w));
         }
    }

    static void dijkstra(){
<<<<<<< Updated upstream
//        PriorityQueue<Integer> pq = new PriorityQueue<>();
=======
>>>>>>> Stashed changes
        PriorityQueue<LinkInfo> pq = new PriorityQueue<>();
        pq.add(new LinkInfo(K, 0));

        while (!pq.isEmpty()) {
            LinkInfo startLinkInfo = pq.poll();
            //시작 노드
            int startNode = startLinkInfo.getNode();
<<<<<<< Updated upstream
=======
            //가중치 합
            int totalEdge = startLinkInfo.getEdge();

            //저장돼있는 최소가중치보다 크면 탐색할 필요 없음
            if(totalEdge > DP[startNode]){
                continue;
            }
>>>>>>> Stashed changes

            //연결된 간선들 탐색
            for (int i = 0; i < list[startNode].size(); i++) {
                LinkInfo linkInfo = list[startNode].get(i);
                int nextNode = linkInfo.getNode();
                int nextEdge = linkInfo.getEdge();

                //nextNode로 가는 길 가중치 계산
                int value = DP[startNode] + nextEdge;
                //현재 경로의 가중치가 더 작으면
                if (DP[nextNode] > value) {
                    //값 갱신
                    DP[nextNode] = value;
<<<<<<< Updated upstream
                    //queue에 넣어주기
                    pq.add(new LinkInfo(nextNode, nextEdge));
=======
                    //다음 node queue에 넣어주기
                    pq.add(new LinkInfo(nextNode, value));
>>>>>>> Stashed changes
                }
            }
        }
    }
}