package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
/*
N개의 회의
회의 시작시간, 끝나는 시간 주어짐
겹치지 않게 하며 할 수 있는 회의실의 최대 개수
 */
public class Main_1931_회의실배정 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int N;
    static PriorityQueue<Session> queue;
    static class Session implements Comparable<Session> {
        int startTime, endTime;
        public Session(int startTime, int endTime){
            this.startTime = startTime;
            this.endTime = endTime;
        }

        @Override
        public int compareTo(Session o) {
            if(this.endTime == o.endTime){
                return this.startTime - o.startTime;
            }
            return this.endTime - o.endTime;
        }
    }
    public static void main(String[] args)throws IOException {
        init();
        /*
        [출력]
        최대 사용할 수 있는 회의의 최대 개수
         */
        System.out.print(getMaxClass());
    }

    static void init() throws IOException{
        /*
        [입력]
        1. 회의의 수 N(1<=N<=100,000)
        2~N. 시작시간 끝나는 시간 (1<=<=2^31-1)
         */
        N = Integer.parseInt(br.readLine());
        queue = new PriorityQueue<>();
        for(int n = 0; n < N; n++){
            st = new StringTokenizer(br.readLine());
            int startTime = Integer.parseInt(st.nextToken());
            int endTime = Integer.parseInt(st.nextToken());
            queue.add(new Session(startTime, endTime));
        }
    }

    static int getMaxClass(){
        int endTime = queue.poll().endTime;
        int result = 1;

        while(!queue.isEmpty()){
            Session nextSession = queue.poll();
            int nextStartTime = nextSession.startTime;
            int nextEndTime = nextSession.endTime;
            if(endTime <= nextStartTime){
                result++;
                endTime = nextEndTime;
            }
        }
        return result;
    }
}
