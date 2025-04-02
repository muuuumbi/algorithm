package boj;

import java.io.*;
import java.util.*;

public class Main_30470_호반우가학교에지각한이유3 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int N;
    static Stack<Long> stack = new Stack<>();
    static long result;

    public static void main(String[] args) throws IOException {
        init();
        if (!stack.isEmpty()) {
            sum();
        }
        System.out.println(result);
    }

    static void init() throws IOException {
        N = Integer.parseInt(br.readLine());
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            long A = Long.parseLong(st.nextToken());
            long B = Long.parseLong(st.nextToken());

            if (A == 1) {
                addTree(B);
            } else {
                if (!stack.isEmpty()) {
                    magic(B);
                }
            }
        }
    }

    static void addTree(long length) {
        stack.push(length);
    }

    static void magic(long power) {
        long K = stack.pop();
        stack.push(Math.max(K - power, 0)); //max 값 push
    }

    static void sum() {
        long top = stack.peek(); //가장 긴 통나무 길이
        while (!stack.isEmpty()) {
            long now = stack.pop();
            if (now > top) { //더 긴 통나무
                result += top;
            } else if (now < top) {//더 짧은 통나무
                top = now; //기준 길이 갱신
                result += now;
            } else {//길이 같음
                result += now;
            }
        }
    }
}
