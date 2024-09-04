package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
n개의 도시.
한 도시에서 출발하여 다른 도시에 도착하는 m개의 버스.
각 버스는 한 번 사용할 때 필요한 비용이 있음.
모든 도시의 쌍 (A,B)에 대해서 도시 A에서 B로 가는데 필요한 비용의 최솟값을 구하라.
 */
public class Main_11404_플로이드 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;

    static final int INF = 100_000_000;
    static int n, m;
    static int[][] costs;

    public static void main(String[] args) throws IOException{
        init();
        floydWarshall();
        /*
        [출력]
        a에서 b로 가는 최소 비용을 나타내는 그래프 출력.
        갈 수 없으면 0을 출력
         */
        for(int[] row : costs){
            for(int column : row){
                if(column == INF){
                    sb.append(0 + " ");
                }else {
                    sb.append(column + " ");
                }
            }
            sb.append("\n");
        }
        System.out.print(sb);
    }

    static void init() throws IOException {
        /*
        [입력]
        1. 도시의 개수 n (2 <= n <= 100)
        2. 버스의 개수 m (1 <= m <= 100,000)
        3~m+2. 시작 도시 a, 도착 도시 b, 비용 c (a와 b가 같은 경우 없음. 비용은 100,000보다 이하의 자연수)
         */
        n = Integer.parseInt(br.readLine());
        m = Integer.parseInt(br.readLine());
        costs = new int[n][n];

        //그래프 초기화
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                //같으면 0
                if(i == j){
                    costs[i][j] = 0;
                }
                //아니면 INF로 초기화
                else{
                    costs[i][j] = INF;
                }
            }
        }

        for(int i = 0; i < m; i++){
            st = new StringTokenizer(br.readLine());
            int row = Integer.parseInt(st.nextToken()) - 1;
            int column = Integer.parseInt(st.nextToken()) - 1;
            int cost = Integer.parseInt(st.nextToken());

            //더 작은 비용의 값을 저장해줌
            costs[row][column] = Math.min(costs[row][column], cost);
        }
    }

    static void floydWarshall(){
        for(int k = 0; k < n; k++){
            for(int i = 0; i < n; i++){
                for(int j = 0; j < n; j++){
                    //최단 경로 초기화
                    if(costs[i][j] > costs[i][k] + costs[k][j]){
                        costs[i][j] = costs[i][k] + costs[k][j];
                    }
                }
            }
        }
    }
}