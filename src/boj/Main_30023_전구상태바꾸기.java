package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/*
N개의 전구가 일렬로 세워져 빨, 초, 파 중 하나의 색으로 빛나고 있음.
연속한 세 전구를 선택하여 상태를 바꿀 수 있음.
빨 -> 초, 초 -> 파, 파 -> 빨
모든 전구가 같은 색으로 빛나게 하려면 이 과정을 최소 몇 번 수행해야 하는지 구해라.
 */
public class Main_30023_전구상태바꾸기 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static int N;
    static int MAX = 1000000;
    static char[] lights;

    public static void main(String[] args) throws IOException{
        init();

        //그대로 진행
        int firstLight = cntChange(N, lights);

        //1번 변경 후 진행
        change(lights, 1);
        //변경 횟수 더해주기
        int secondLight = cntChange(N, lights) + 1;

        //2번 변경 후 진행
        change(lights, 1);
        //변경 횟수 더해주기
        int thirdLight = cntChange(N, lights) + 2;

        //최솟값
        int min = Math.min(firstLight, Math.min(secondLight, thirdLight));

        //MAX 값일 경우 불가능하단 의미이므로 -1로 바꿔주기
        min = min == MAX ? -1 : min;

        System.out.println(min);
    }

    static void init() throws IOException{
        /*
        [입력]
        1. N (3<=N<=100,000)
        2. 길이가 N인 문자열 S (R,G,B로 이루어져 있음)
         */
        N = Integer.parseInt(br.readLine());
        lights = br.readLine().toCharArray();
    }

    //색상 변경 횟수 반환 & 색이 같지 않으면 -1 반환
    static int cntChange(int n, char[] lights){
        char[] lightsCopy = Arrays.copyOf(lights, n);
        int cnt = 0;

        //인덱스 2부터 N-2까지
        for(int i = 2; i < N-1; i++){
            //맨 앞의 전구색과 그 다음의 전구색이 같지 않으면
            while(lightsCopy[0] != lightsCopy[i-1]){
                //전구색 바꾸어줌
                change(lightsCopy, i);
                //변경 횟수 카운트
                cnt++;
            }
        }

        //전체 전구 색이 동일한지 확인
        for(int i = 1; i < n; i++){
            if(lightsCopy[0] != lightsCopy[i]){
                //동일하지 않으면 불가능하단 것으로 MAX_VALUE로 초기화
                cnt = MAX;
                break;
            }
        }

        return cnt;
    }

    //해당 인덱스와 앞, 뒤 (총 3개) 전구들 색 변경
    static void change(char[] lightsCopy, int idx){
        for(int i = idx - 1; i <= idx + 1; i++){
            switch(lightsCopy[i]){
                case 'R':
                    lightsCopy[i] = 'G';
                    break;
                case 'G':
                    lightsCopy[i] = 'B';
                    break;
                case 'B':
                    lightsCopy[i] = 'R';
                    break;
            }
        }
    }
}