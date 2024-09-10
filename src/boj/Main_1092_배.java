package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/*
화물을 배에 실어야 한다.
모든 화물은 박스 안에 넣어져 있다.
항구에는 크레인이 N대 있고, 1분에 박스를 하나씩 배에 실을 수 있다.
모든 크레인은 동시에 움직이며 무게 제한이 있다.
무게 제한보다 무거운 박스는 크레인으로 움직일 수 없다.
모든 박스를 배로 옮기는데 드는 시간의 최솟값을 구하라.
 */
public class Main_1092_배 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int N, M;
    static Integer[] craneLimit;
    static List<Integer> boxWeight;
    public static void main(String[] args) throws IOException{
        init();
        /*
        [출력]
        모든 박스를 배로 옮기는데 드는 시간의 최솟값
        만약 옮길 수 없으면 -1
         */
        //박스 최대 무게가 크레인 최대 무게 제한보다 크면 불가능
        if(boxWeight.get(0) > craneLimit[0]){
            System.out.println(-1);
            return;
        }

        int result = 0;
        while(!boxWeight.isEmpty()){
            int boxIdx = 0, craneIdx = 0;

            while(craneIdx < N){
                if(boxIdx == boxWeight.size()){
                    break;
                }
                //박스 무게가 무게 제한보다 작거나 같으면
                else if(craneLimit[craneIdx] >= boxWeight.get(boxIdx)){
                    //삭제
                    boxWeight.remove(boxIdx);
                    craneIdx++;
                }
                else{
                    boxIdx++;
                }
            }
            result++;
        }
        System.out.println(result);
    }
    static void init() throws IOException {
        /*
        [입력]
        1. 크레인 개수 N (N <= 50 인 자연수)
        2. 각 크레인의 무게 제한 ( <= 1,000,000)
        3. 박스의 수 M (M <= 10,000 인 자연수)
        4. 각 박스의 무게 ( <= 1,000,000)
         */

        N = Integer.parseInt(br.readLine());
        craneLimit = new Integer[N];

        st = new StringTokenizer(br.readLine());
        for(int n = 0; n < N; n++){
            craneLimit[n] = Integer.parseInt(st.nextToken());
        }

        M = Integer.parseInt(br.readLine());
        boxWeight = new ArrayList<>();

        st = new StringTokenizer(br.readLine());
        for(int m = 0; m < M; m++){
            boxWeight.add(Integer.parseInt(st.nextToken()));
        }


        //내림차순 정렬
        Arrays.sort(craneLimit, Collections.reverseOrder());
        Collections.sort(boxWeight, Collections.reverseOrder());
    }
}
/*
가장 큰 무게 제한보다 가장 무거운 박스의 무게가 크면 -1
크레인의 무게 제한을 큰 순서대로 정렬한 후 박스 무게를 뒤에 넣어줌.
N*M = 500,000
 */