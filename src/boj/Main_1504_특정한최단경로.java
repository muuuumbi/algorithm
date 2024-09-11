package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/*
방향성 없는 그래프.
1번 정점에서 N번 정점으로 임의의 두 정점을 통과하면서 최단 거리 이동.
한 번 이동했던 정점, 간선 모두 다시 이동 가능.
 */
public class Main_1504_특정한최단경로 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int N, E, V1, V2;
    static final int INF = 200_000_000;
    static ArrayList<Node>[] list;
    static int[] cost;
    static boolean[] visited;

    static class Node implements Comparable<Node>{
        //dest : 목적지, cost : 거리
        int dest, cost;
        public Node(int dest, int cost){
            this.dest = dest;
            this.cost = cost;
        }
        @Override
        public int compareTo(Node node){
            //cost 기준 오름차순 정렬
            return this.cost - node.cost;
        }
    }

    public static void main(String[] args) throws IOException{
        init();
        /*
        [출력]
        두 개의 정점을 지나는 최단 경로의 길이 출력. 없으면 -1 출력
         */
        int result = minLength();
        if(result >= INF){
            System.out.println(-1);
        }
        else{
            System.out.println(result);
        }
    }

    static void init() throws IOException{
        /*
        [입력]
        1. 정점의 개수 N, 간선의 개수 E (2 <= N <= 800, 0 <= E <=200,000)
        2 ~ E. a, b, c (a부터 b까지 길이가 c인 양방향 길 존재) (1 <= c <= 1,000)
        3. 반드시 거쳐야 하는 서로 다른 정점 번호 v1, v2 (v1 != v2, v1 != N, v2 != 1)
         */
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());

        list = new ArrayList[N+1];
        //초기화 시켜주기
        for(int i = 0; i < N+1; i++){
            list[i] = new ArrayList<>();
        }

        cost = new int[N+1];
        visited = new boolean[N+1];

        for(int e = 0; e < E; e++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken()); //출발지
            int b = Integer.parseInt(st.nextToken()); //도착지
            int c = Integer.parseInt(st.nextToken()); //비용(거리)

            list[a].add(new Node(b, c));
            list[b].add(new Node(a, c));
        }

        st = new StringTokenizer(br.readLine());
        V1 = Integer.parseInt(st.nextToken());
        V2 = Integer.parseInt(st.nextToken());
    }

    //최단 거리 찾기
    static int dijkstra(int start, int end){
        PriorityQueue<Node> pq = new PriorityQueue<>();
        //방문여부 초기화
        Arrays.fill(visited, false);
        //cost 초기화
        Arrays.fill(cost, INF);

        //시작점은 거리 0으로 설정
        cost[start] = 0;
        pq.add(new Node(start, 0)); //현재까지의 최단 거리

        while(!pq.isEmpty()){
            Node now = pq.poll();

            //방문했던 곳이면 넘기기
            if(visited[now.dest]){
                continue;
            }
            //아니면 true로 변환 후
            visited[now.dest] = true;
            //연결된 노드들 탐색
            for(Node next : list[now.dest]){
                int newCost = next.cost + now.cost;
                //도착지까지의 현재 최단 거리보다 더 작으면
                if(cost[next.dest] > newCost){
                    //값 갱신해준 후
                    cost[next.dest] = newCost;
                    // pq에 넣기
                    pq.add(new Node(next.dest, cost[next.dest]));

                }
            }
        }
        return cost[end];
    }

    //두 정점을 지난 최단 거리 찾기
    static int minLength(){
        return Math.min(dijkstra(1, V1) + dijkstra(V1, V2) + dijkstra(V2, N),
                dijkstra(1, V2) + dijkstra(V2, V1) + dijkstra(V1, N));
    }
}
/*
- 다익스트라 문제

- 방향성이 없는 그래프 = 무방향(=양방향) 인접리스트 구성 필요

- cost 초기값인 INF 는 오버플로우 나지 않도록 200,000(E의 최대값) * 1,000(c의 최대값) 값으로 설정

- 목적지와, 목적지의 비용(거리)를 담는 Node class 생성하여 사용

- 경로는 크게 두 가지 : 1 → v1 → v2 → N와 1 → v2 → v1 → N

- 두 가지 경로 중 더 작은 값을 출력

- 이 때 두 경로 모두 INF와 같거나 INF보다 큰 값이 나오면 경로가 없다는 의미이기 때문에 -1 출력
 */