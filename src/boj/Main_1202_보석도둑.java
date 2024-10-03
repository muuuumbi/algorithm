package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/*
보석 N개, 각 무게 M, 가격 V
가방 K개, 담을 수 있는 최대 무게 C, 최대 한 개의 보석만 담을 수 있음.
가져갈 수 있는 보석의 최대 가격 구하기
 */
public class Main_1202_보석도둑 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int N, K;
    static PriorityQueue<Jewel> jewels;
    static PriorityQueue<Integer> bagWeight;

    static class Jewel{
        int weight;
        int value;

        public Jewel(int weight, int value){
            this.weight = weight;
            this.value = value;
        }

        public int getWeight(){
            return this.weight;
        }

        public int getValue(){
            return this.value;
        }
    }
    public static void main(String[] args) throws IOException{
        init();
        /*
        [출력]
        보석 가격 합의 최댓값
         */
        System.out.println(getJewel());
    }

    static void init() throws IOException{
        /*
        [입력]
        1. 보석 N, 가방 K (1 <= N, K <= 300,000)
        2~N. 무게 M, 가격 V (0 <= M, V <= 1,000,000)
        3~K. 가방 최대 무게 C (1 <= C <= 100,000,000)
         */
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        jewels = new PriorityQueue<>(new Comparator<Jewel>() {
            //무게를 오름차순으로 정렬하되, 무게가 같을 경우 가격을 내림차순 정렬
            @Override
            public int compare(Jewel o1, Jewel o2) {
                if(o1.weight == o2.weight){
                    return o2.value - o1.value;
                }
                return o1.weight - o2.weight;
            }
        });
        for(int n = 0; n < N; n++){
            st = new StringTokenizer(br.readLine());
            jewels.add(new Jewel(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
        }

        //오름차순 정렬
        bagWeight = new PriorityQueue<>();
        for(int k = 0; k < K; k++){
            bagWeight.add(Integer.parseInt(br.readLine()));
        }
    }

    static long getJewel(){
        long result = 0;
        // 우선순위 큐는 항상 가격이 내림차순 정렬되도록 설정.
        PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.reverseOrder());

        while(!bagWeight.isEmpty()){
            //현재 가방 무게
            int bag = bagWeight.poll();

            //가방 무게에 맞는 보석 모두 큐에 추가
            while(!jewels.isEmpty() && jewels.peek().getWeight() <= bag){
                //보석 꺼내고
                Jewel jewel = jewels.poll();
                //큐에 보석 가격 넣기
                pq.add(jewel.getValue());
            }

            // 우선순위 큐에 있는 요소를 하나 빼서 가방에 넣음.
            // 이 때, 우선순위 큐는 내림차순 정렬이 되어있으므로
            // 첫 번째 요소는 가장 큰 가격을 의미함.
            if (!pq.isEmpty()) {
                result += pq.poll();
            }
        }

        return result;
    }
}