package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/*
정점 N개의 트리. (1번~N번)
루트는 1번 정점. 모든 정점은 하얀색.
하나의 정점에 색칠하면 해당 정점 아래의 모든 정점이 같은 색이 됨.
색은 섞이지 않고 덮어짐. 하얀색으로 색칠할 수는 없음
색 정보는 정수 (0: 하얀색)
모든 정점을 주어진 색으로 칠하고 싶을 때, 최소 몇 번 칠해야 하는지 구하기
 */
public class Main_24230_트리색칠하기 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int N, result;
    static int[] color;
    static boolean[] visited;
    static List<Integer>[] tree;

    public static void main(String[] args) throws IOException{
        init();
        //정점 1을 색칠해야 하면 1로 시작
        result = color[1] == 0 ? 0 : 1;
        visited[1] = true;

        DFS(1);
        /*
        [출력]
        원하는 색으로 칠하기 위한 최소 횟수
         */
        System.out.println(result);
    }

    static void init() throws IOException {
        /*
        [입력]
        1. 정점 개수 N (1 <= N <= 200,000)
        2~N. 색 정보 C (1 <= C <= N)
        3~N-1. 연결된 두 정점
         */
        N = Integer.parseInt(br.readLine());
        color = new int[N+1];
        tree = new List[N+1];
        visited = new boolean[N+1];

        for(int n = 1; n <= N; n++){
            tree[n] = new ArrayList<>();
        }

        st = new StringTokenizer(br.readLine());
        for(int n = 1; n <= N; n++){
            color[n] = Integer.parseInt(st.nextToken());
        }

        for(int i = 1; i < N; i++){
            st = new StringTokenizer(br.readLine());
            int node1 = Integer.parseInt(st.nextToken());
            int node2 = Integer.parseInt(st.nextToken());

            tree[node1].add(node2);
            tree[node2].add(node1);
        }
    }

    static void DFS(int num){
        //부모 정점의 자식 정점들 탐색
        for(int node : tree[num]) {
            //아직 탐색 전인 정점이면
            if (!visited[node]){
                //탐색 표시 후
                visited[node] = true;
                //부모의 색과 다르면
                if(color[num] != color[node]){
                    //색칠 횟수 더해주기
                    result++;
                }
                //자식 정점 탐색
                DFS(node);
            }
        }
    }
}
