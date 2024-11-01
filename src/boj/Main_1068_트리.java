package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/*
트리가 주어졌을 때 노드 하나를 지우면
남은 트리에서 리프 노드의 개수를 구하기
리프노드 : 자식의 개수가 0인 노드
 */
public class Main_1068_트리 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int N, deleteNode, rootNode, result;
    static ArrayList<Integer>[] graph;
    static boolean[] visited;
    static int[] parent;

    public static void main(String[] args) throws IOException{
        init();
        /*
        [출력]
        리프노드의 개수
         */
        //만약 루트노드를 삭제하면
        if(deleteNode == rootNode){
            System.out.println(0);
        }
        else{
            DFS(rootNode);
            System.out.println(result);
        }
    }

    static void init() throws IOException {
        /*
        [입력]
        1. 노드의 개수 N (1 <= N <= 50)
        2.각 노드의 부모, 루트면 -1
        3. 지울 노드의 번호
         */
        N = Integer.parseInt(br.readLine());
        graph = new ArrayList[N + 1];
        visited = new boolean[N + 1];
        parent = new int[N + 1];

        for(int n = 0; n < N; n++){
            graph[n] = new ArrayList<>();
        }

        st = new StringTokenizer(br.readLine());
        for(int n = 0; n < N; n++){
            int parentNode = Integer.parseInt(st.nextToken());
            //루트 노드 번호 찾기
            if(parentNode == -1){
                rootNode = n;
            }else{
                graph[n].add(parentNode);
                graph[parentNode].add(n);
            }
        }

        deleteNode = Integer.parseInt(br.readLine());
        result = 0;
    }

    static void DFS(int node){
        visited[node] = true;

        int nodes = 0;
        for(int linkedNode : graph[node]){
            //삭제된 노드가 아니고 아직 탐색 전인 노드이면
            if(linkedNode != deleteNode && !visited[linkedNode]){
                //노드 개수 더해주고
                nodes++;
                DFS(linkedNode);
            }
        }

        //연결된 노드가 하나도 없으면 리프노드
        if(nodes == 0){
            result++;
        }
    }
}
