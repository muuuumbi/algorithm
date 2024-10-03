package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.StringTokenizer;

/*
멀티탭 구멍이 N개일 때, 전기용품의 사용 순서를 기반으로
플러그 빼는 횟수 최소화하기
 */
public class Main_1700_멀티탭스케줄링 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int N, K;
    static int[] order;
    static boolean[] use = new boolean[101];

    public static void main(String[] args) throws IOException{
        init();
        /*
        [출력]
        플러그 빼는 최소 횟수
         */
        System.out.println(getResult());
    }

    static void init() throws IOException{
        /*
        [입력]
        1. 멀티탭 구멍 개수 N,전기용품 총 사용횟수 K(1 <= N, K <= 100)
        2. 전기용품 사용 순서 (K 이하의 자연수)
         */
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        order = new int[K];
        st = new StringTokenizer(br.readLine());
        for(int k = 0; k < K; k++){
            order[k] = Integer.parseInt(st.nextToken());
        }
    }

    static int getResult(){
        int put = 0;
        int result = 0;

        for(int k = 0; k < K; k++){
            //현재 사용할 기기
            int now = order[k];

            //멀티탭에 꽂혀있는 상태가 아니면
            if(!use[now]){
                //멀티탭에 공간이 있으면
                if(put < N){
                    use[now] = true;
                    put++;
                }
                //공간이 없으면
                else{
                    ArrayList<Integer> willUse = new ArrayList<>();
                    //뒷순서 기기 탐색해서
                    //현재 꽂혀 있는 게 나중에도 사용되는지 확인하기
                    for(int i = k; i < K; i++){
                        int sort = order[i];
                        //현재 사용되고 나중에도 사용될 기기면 && 중복 제거
                        if(use[sort] && !willUse.contains(sort)) {
                            willUse.add(sort);
                        }
                    }

                    //현재 사용 && 나중에도 사용되는 기기가 구멍의 개수와 다르면
                    if(willUse.size() != N){
                        for(int i = 0; i < use.length; i++){
                            //사용 중인 것 중 나중에 사용되지 않는 것 하나 삭제하기
                            if(use[i] && !willUse.contains(i)){
                                use[i] = false;
                                break;
                            }
                        }
                    }
                    //현재 꽂혀있는 모든 기기가 나중에도 사용되면
                    else{
                        //가장 나중에 사용되는 것 제거하기
                        int remove = willUse.remove(willUse.size() - 1);
                        use[remove] = false;
                    }

                    //현재 기기 꽂아주기
                    use[now] = true;
                    //콘센트 뺀 횟수 추가하기
                    result++;
                }
            }
        }
        return result;
    }
}
