package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
현재 고속도로엔 N개의 휴게소가 있음
추가로 M개의 휴게소를 세우려고 함
휴게소가 이미 있는 곳이나 고속도로의 끝에는 세울 수 없음
M개의 휴게소를 지어서 휴게소가 없는 구간의 길이의 최댓값을 최소로 하려고 함
휴게소가 없는 구간의 최댓값의 최솟값을 구하기
 */
public class Main_1477_휴게소세우기 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int N, M, L;
    static int[] storeLocation;
    public static void main(String[] args) throws IOException {
        init();
        /*
        [출력]
        휴게소가 없는 구간의 최댓값의 최솟값
         */
        System.out.println(getResult());
    }

    static void init() throws IOException{
        /*
        [입력]
        1. 현재 휴게소 개수 N, 추가 휴게소의 개수 M, 고속도로의 길이 L
        (0 <= N <= 50, 1 <= M <= 100, 100 <= L <= 1,000, N+M < L)
        2. 현재 휴게소의 위치들 (N = 0이면 빈 줄로 입력됨, 1 <= 휴게소 위치 <= L-1)
         */
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());

        //고속도로 양 끝도 포함해서 초기화
        storeLocation = new int[N + 2];
        storeLocation[0] = 0;
        storeLocation[N + 1] = L;

        st = new StringTokenizer(br.readLine());
        for(int n = 1; n <= N; n++){
            storeLocation[n] = Integer.parseInt(st.nextToken());
        }
        //오름차순 정렬
        Arrays.sort(storeLocation);
    }

    static int getResult(){
        int left = 1;
        int right = L - 1;
        while(left <= right){
            //이 값으로 휴게소 설치했을 때
            int distance = (left + right) / 2;
            //불가능하면
            if(needIncreaseDistance(distance)){
                //더 큰 구간 길이 정하기
                left = distance + 1;
            }
            //가능하면
            else{
                //더 작은 구간 길이 정하기
                right = distance - 1;
            }
        }
        //가장 작은 가능한 값
        return left;
    }

    //주어진 구간 길이로 휴게소를 모두 설치하지 못하면 true 반환
    static boolean needIncreaseDistance(int distance){
        // 필요한 휴게소 개수
        int count = 0;
        //인접한 휴게소 간의 거리에서 각 구간을 분할하여 필요한 휴게소 개수 계산
        for(int n = 1; n <= N + 1; n++){
            //해당 구간에 설치 가능한 휴게소 개수 더해주기
            count += (storeLocation[n] - storeLocation[n - 1] - 1) / distance;
        }
        //필요한 휴게소 개수가 추가 휴게소 개수보다 많으면
        //현재 설정한 구간 길이가 너무 작아서 휴게소를 모두 설치하기 어렵다는 의미
        return count > M;
    }

}
