package boj;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * n + 1개의 집합.
 * 합집합 연산과 두 원소가 같은 집합에 포함되어 있는지를 확인하는 연산 수행.
 * 집합을 표현하는 프로그램 작성
 * 합집합 : 0 a b
 * 두 원소가 같은 집합에 포함되어 있는지를 확인하는 연산 : 1 a b
 *
 */
public class Main_1717_집합의표현{
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;
    static int n, m;
    static int[] parent;

    public static void main(String[] args) throws IOException {
        /**
         * [입력]
         * 1. 집합의 개수 n, 연산의 개수 m
         * 2~m. 연산
         */
        init();
        System.out.println(sb);
    }

    static void init() throws IOException{
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        parent = new int[n+1];
        for(int i = 0; i <= n; i++) {
            parent[i] = i;
        }

        for(int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int calc = Integer.parseInt(st.nextToken());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            if(calc == 0) { //합집합
                union(x, y);
            }else if(calc == 1) { //같은 집합인지 확인
                if(isSameGroup(x, y))
                    sb.append("YES").append('\n');
                else
                    sb.append("NO").append('\n');
            }
        }
    }

    static boolean union(int x, int y) {
        int xRoot = find(x);
        int yRoot = find(y);

        if(xRoot == yRoot) return false; //같은 집합
        //같은 집합 아니면
        parent[yRoot] = xRoot;
        return true;
    }

    static int find(int x) {
        if(parent[x] == x) return x;
        return find(parent[x]);
    }

    static boolean isSameGroup(int x, int y) {
        return parent[find(x)] == parent[find(y)];
    }
}
