package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.ArrayList;
/*
연결 요소는 모든 정점이 서로 연결되어 있는 정점의 부분집합이다.

트리는 사이클이 없는 연결 요소이다.
트리는 정점이 n개 간선이 n-1개가 있으며 임의의 두 정점에 대해 경로가 유일하다.

그래프가 주어졌을 때, 트리의 개수를 구해라.
 */
public class Main_4803_트리{
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    static int N = -1, M = -1, tc;
    static ArrayList<Integer>[] graph;
    static boolean[] visited;
    static boolean isCycle;

    public static void main(String[] args) throws IOException {
        while (true) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());

            //입력 종료 조건
            if (N == 0 && M == 0) {
                break;
            }

            init();

            //트리 개수
            int treeCount = 0;

            for (int i = 1; i <= N; i++) {
                //아직 방문 전인 노드이면
                if (!visited[i]) {
                    //사이클이 없는 상태로 탐색 시작
                    isCycle = false;
                    if (dfs(i, 0)) { // 트리 여부 확인
                        treeCount++; // 트리이면 트리 개수 증가
                    }
                }
            }

            //테스트 케이스 번호 증가
            tc++;
            if (treeCount == 0) {
                sb.append("Case ").append(tc).append(": No trees.\n");
            } else if (treeCount == 1) {
                sb.append("Case ").append(tc).append(": There is one tree.\n");
            } else {
                sb.append("Case ").append(tc).append(": A forest of ").append(treeCount).append(" trees.\n");
            }
        }

        /*
        [출력]
        트리가 없으면 "No trees."
        한 개면 "There is one tree."
        T개 (T>1)이면 "A forest of T trees."
        를 테스트 케이스 번호와 함께 출력
         */
        System.out.print(sb);
    }

    static void init() throws IOException {
        /*
        [입력]
        여러 개의 테스트 케이스로 이루어져 있음
        1. 정점의 개수 N, 간선의 개수 M ( N <= 500, M <= n(n-1)/2 )
        2 ~ M. a, b (a, b는 간선을 나타내며 같은 간선은 여러 번 주어지지 않는다.)
        입력의 마지막엔 0 0이 입력됨.
         */
        graph = new ArrayList[N + 1];
        visited = new boolean[N + 1];

        for (int i = 1; i <= N; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            //양방향
            graph[a].add(b);
            graph[b].add(a);
        }
    }

    // DFS로 사이클 존재 여부 확인 (재귀 방식)
    static boolean dfs(int node, int parent) {
        visited[node] = true;

        for (int next : graph[node]) {
            //아직 방문 전인 노드이고
            if (!visited[next]) {
                //사이클이 존재하면 끝내기
                if (!dfs(next, node)) {
                    return false;
                }
            }
            //방문한 노드가 부모 노드가 아니면 사이클
            else if (next != parent) {
                isCycle = true;
            }
        }
        return !isCycle; // 사이클이 있으면 false, 없으면 true
    }
}