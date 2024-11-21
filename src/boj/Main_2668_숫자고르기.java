package boj;

import java.util.*;
import java.io.*;
/*
세로 두 줄, 가로로 N개의 표
첫째 줄은 1, 2, 3, ..., N
둘째 줄은 N이하의 정수

첫째 줄에서 숫자를 뽑으면 뽑힌 정수들이 이루는 집합과
뽑힌 정수들의 바로 밑의 둘째 줄에 들어있는 정수들이 이루는 집합이
일치함

정수를 최대한 많이 뽑는 방법을 찾기
 */
public class Main_2668_숫자고르기 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static int N;
    static int[] table;
    static boolean[] visited;
    static ArrayList<Integer> result;

    public static void main(String[] args) throws IOException{
        init();
        for(int n = 1; n <= N; n++){
            visited = new boolean[N+1];
            visited[n] = true;
            DFS(n, n);
        }
        /*
        [출력]
        1. 뽑힌 정수들의 개수
        2~개수. 작은 수부터 순서대로 하나씩 출력
         */
        result.sort(Comparator.naturalOrder());
        sb.append(result.size()).append('\n');
        for(int num : result){
            sb.append(num).append('\n');
        }

        System.out.println(sb);
    }

    static void init() throws IOException {
        /*
        [입력]
        1. N (1 <= N <= 100)
        2~N. 둘째 줄에 들어가는 정수
         */
        N = Integer.parseInt(br.readLine());

        table = new int[N+1];
        for(int n = 1; n <= N; n++){
            table[n] = Integer.parseInt(br.readLine());
        }

        result = new ArrayList<>();
    }

    static void DFS(int start, int end){
        int nextIndex = table[start];

        if(!visited[nextIndex]){
            visited[nextIndex] = true;
            DFS(nextIndex, end);
        }

        //시작 숫자를 만나면 == 사이클을 이루면
        //중복 방지
        if(nextIndex == end && !result.contains(end)){
            result.add(end);
        }
    }
}
