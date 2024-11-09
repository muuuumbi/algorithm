package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
N개의 노드로 이루어진 트리
M개의 두 노드 쌍을 입력받을 때 두 노드 사이의 거리를 출력
 */
public class Main_1240_노드사이거리 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;
    static int N, M;
    static LinkedList<Node>[] tree;

    static class Node{
        int number, distance;

        public Node(int number, int distance){
            this.number = number;
            this.distance = distance;
        }
    }
    public static void main(String[] args) throws IOException{
        init();

        for(int m = 0; m < M; m++){
            st = new StringTokenizer(br.readLine());
            int node1 = Integer.parseInt(st.nextToken());
            int node2 = Integer.parseInt(st.nextToken());

            getDistance(node1, node2);
        }
        /*
        [출력]
        M개의 줄에 차례대로 두 노드 사이의 거리 출력
         */
        System.out.println(sb);
    }

    static void init() throws IOException{
        /*
        [입력]
        1. 노드 개수 N, 거리를 알고 싶은 노드 쌍의 개수 M (2 <= N <= 1,000, 1 <= M <= 1,000)
        2~N-1. 트리 상에 연결된 두 점과 거리 (10,000이하의 자연수)
        3~M. 노드 쌍
         */
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        tree = new LinkedList[N+1];
        for(int n = 1; n <= N; n++){
            tree[n] = new LinkedList<Node>();
        }

        for(int n = 1; n < N; n++){
            st = new StringTokenizer(br.readLine());
            int node_1 = Integer.parseInt(st.nextToken());
            int node_2 = Integer.parseInt(st.nextToken());
            int distance = Integer.parseInt(st.nextToken());

            tree[node_1].add(new Node(node_2, distance));
            tree[node_2].add(new Node(node_1, distance));
        }

    }

    static void getDistance(int start, int end){
        Queue<Integer> queue = new LinkedList<>();
        boolean[] visited = new boolean[N+1];

        visited[start] = true;
        queue.add(start);
        int[] answer = new int[N+1];

        while(!queue.isEmpty()){
            int curNum = queue.poll();

            LinkedList<Node> curList = tree[curNum];
            for(int i = 0; i < curList.size(); i++){
                Node node = curList.get(i);
                int linkedNode = node.number;
                int linkedDistance = node.distance;
                if(!visited[linkedNode]){
                    answer[linkedNode] = answer[curNum] + linkedDistance;
                    //도착이면
                    if(linkedNode == end){
                        sb.append(answer[end] + "\n");
                        return;
                    }
                    queue.add(linkedNode);
                    visited[linkedNode] = true;
                }
            }
        }
    }
}
