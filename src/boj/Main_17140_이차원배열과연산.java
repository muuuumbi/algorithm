package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/*
크기가 3X3인 배열 A
1. R연산 : 배열 A의 모든 행에 대해 정렬 수행. 행의 개수 >= 열의 개수인 경우 적용
2. C연산 : 배열 A의 모든 열에 대해 정렬 수행. 행의 개수 < 열의 개수인 경우 적용
1초마다 연산 적용.
한 행 또는 열에 있는 수 정렬 : (수, 횟수)로 정렬.
수의 등장 횟수가 커지는 순서. 여러가지면 수가 커지는 순서

행 또는 열의 크기가 100을 넘어가는 경우에는 100개를 제외한 나머지를 버리기
A[r][c]의 값이 k가 되기 위한 최소 시간 구하기
 */
public class Main_17140_이차원배열과연산 {

    static class Node implements Comparable<Node>{
        int number;
        int count;

        Node(int number, int count){
            this.number = number;
            this.count = count;
        }

        //count가 작은 게 우선순위, 같으면 number가 작은 게 우선순위
        public int compareTo(Node o){
            if(this.count > o.count){
                return 1;
            } else if(this.count == o.count){
                return this.number - o.number;
            } else{
                return -1;
            }
        }
    }

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int r, c, k;
    static int[][] A = new int[101][101];
    static int rowLength, coulmnLength;

    public static void main(String[] args) throws IOException {
        init();
        /*
        [출력]
        A[r][c]의 값이 k가 되기 위한 최소 시간
        100초를 초과하면 -1 출력
         */
        System.out.println(getTime());
    }

    static void init() throws IOException{
        /*
        [입력]
        1. r, c, k (1 <= r,c,k <= 100)
        2~3. 배열 A (요소들 값은 100보다 작거나 같은 자연수)
         */
        st = new StringTokenizer(br.readLine());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        rowLength = 3;
        coulmnLength = 3;

        for(int r = 1; r <= 3; r++){
            st = new StringTokenizer(br.readLine());
            for(int c = 1; c <= 3; c++){
                A[r][c] = Integer.parseInt(st.nextToken());
            }
        }
    }

    static int getTime(){
        for(int time = 0; time <= 100; time++){
            if(A[r][c] == k){
                return time;
            }
            //R/C 연산
            selectMethod();
        }
        return -1;
    }

    static void selectMethod(){
        //행 수 >= 열 수 -> R연산
        if(rowLength >= coulmnLength){
            for(int r = 1; r <= rowLength; r++){
                R(r);
            }
        }
        //행 수 < 열 수 -> C연산
        else{
            for(int c = 1; c <= coulmnLength; c++){
                C(c);
            }
        }
    }


    //R연산 (행 정렬)
    static void R(int row){
        PriorityQueue<Node> queue = new PriorityQueue<>();
        Map<Integer, Integer> map = new HashMap<>();

        //각 숫자 개수 구해서 hashmap에 담기
        for(int c = 1; c <= coulmnLength; c++){
            //0이면 무시
            if(A[row][c] == 0){
                continue;
            }
            //compute 메서드 : 키가 존재하면 수정, 존재하지 않으면 새 값 생성
            //A[row][c] == number, number가 없으면 count = 1, 있으면 ++count 해주기
            map.compute(A[row][c], (number, count) -> count == null ? 1 : count + 1);
        }

        //hashmap에 담긴 요소들 우선순위 queue에 담기
        map.forEach((number, count) -> queue.add(new Node(number, count)));

        int column = 1;
        while(!queue.isEmpty()){
            Node node = queue.poll();
            A[row][column++] = node.number;
            A[row][column++] = node.count;
        }

        coulmnLength = Math.max(coulmnLength, column);

        //남은 공간 0으로 채워주기
        while(column <= 99){
            A[row][column++] = 0;
            A[row][column++] = 0;
        }
    }

    //C연산 (열 정렬)
    static void C(int column){
        PriorityQueue<Node> queue = new PriorityQueue<>();
        Map<Integer, Integer> map = new HashMap<>();

        for(int r = 1; r <= rowLength; r++){
            //0이면 무시
            if(A[r][column] == 0){
                continue;
            }
            map.compute(A[r][column], (number, count) -> count == null ? 1 : count + 1);
        }

        map.forEach((number, count) -> queue.add(new Node(number, count)));

        int row = 1;
        while(!queue.isEmpty()){
            Node node = queue.poll();
            A[row++][column] = node.number;
            A[row++][column] = node.count;
        }

        rowLength = Math.max(rowLength, row);

        while(row <= 99){
            A[row++][column] = 0;
            A[row++][column] = 0;
        }
    }

}
