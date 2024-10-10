package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/*
진실을 아는 사람 / 진실을 듣고 거짓을 들은 사람
이 있을 때는 거짓말하지 않아야 함.
사람 수 N, 진실을 아는 사람, 각 파티에 오는 사람들의 번호가 주어질 때
거짓말 가능한 파티 최대 개수 구하기
 */
public class Main_1043_거짓말 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int N, M, result;
    static List<Integer> knowTrue; //진실을 아는 사람
    static List<Integer>[] party; //각 파티에 참여하는 사람
    static int[] parent; //union-find를 위한 부모 배열

    public static void main(String[] args) throws IOException{
        init();
         /*
         [출력]
         최대 파티 수   
          */
        // 진실을 아는 사람이 없으면 M
        // 있으면 계산해주기
        result = result == M ? result : cntLie();
        System.out.println(result);
    }
    
    static void init() throws IOException {
        /*
        [입력]
        1. 사람 수 N, 파티 수 M (1 <= N,M <= 50)
        2. 진실 아는 사람의 수와 번호 (번호는 1부터 N)
        3~M. 각 파티마다 오는 사람의 수와 번호
         */
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        result = 0;

        // union-find 부모 배열 초기화
        parent = new int[N + 1];
        for(int n = 1; n <= N; n++){
            parent[n] = n;
        }

        //진실 아는 사람 입력
        st = new StringTokenizer(br.readLine());
        int knews = Integer.parseInt(st.nextToken());
        knowTrue = new ArrayList<>();
        //진실을 아는 사람이 없으면
        if(knews == 0){
            //파티 수만큼 거짓말 가능
            result = M;
        }
        else {
            for (int i = 0; i < knews; i++) {
                knowTrue.add(Integer.parseInt(st.nextToken()));
            }
        }

        // 각 파티 정보 입력
        party = new ArrayList[M];
        for(int m = 0; m < M; m++){
            party[m] = new ArrayList<>();
        }
        for(int m = 0; m < M; m++){
            st = new StringTokenizer(br.readLine());
            int attend = Integer.parseInt(st.nextToken());

            //첫 번째 참석자 번호 입력
            int first = Integer.parseInt(st.nextToken());
            party[m].add(first);
            //나머지 참석자 번호 입력하면서 union-find 수행
            for(int i = 1; i < attend; i++){
                int next = Integer.parseInt(st.nextToken());
                //같은 그룹으로 묶어주기
                union(first,next);
                party[m].add(next);
            }
        }
    }

    static int cntLie(){
        int cnt = 0;
        for(int m = 0; m < M; m++){
            boolean canLie = true;
            for(int num : party[m]){
                //진실을 아는 사람의 그룹에 속해있으면
                if(knowTrue.contains(find(parent[num]))){
                    //거짓말 불가
                    canLie = false;
                    //더 확인할 필요 없으므로 넘기기
                    break;
                }
            }
            //거짓말 가능하면
            if(canLie){
                cnt++;
            }
        }

        return cnt;
    }

    //같은 그룹으로 묶어주기
    static void union(int x, int y){
        int rootX = find(x); //x의 root
        int rootY = find(y); //y의 root
        //y의 부모가 진실을 아는 사람이면
        if(knowTrue.contains(rootY)){
            //두 그룹의 부모를 교환
            int temp = rootX;
            rootX = rootY;
            rootY = temp;
        }

        //y의 부모를 x로 설정
        parent[rootY] = rootX;
    }

    // x의 루트 찾기
    static int find(int x){
        // 부모가 자기 자신이 아니면 경로 압축
        if(parent[x] != x){
            return parent[x] = find(parent[x]);
        }
        return parent[x];
    }

}