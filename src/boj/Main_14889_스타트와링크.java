package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
/*
축가 - 평일 오후, 필참x
사람 N명 (N은 짝수)
N/2씩 스타트팀, 링크팀으로 나누기
1번부터 N번.
능력치 Sij는 i와 j가 같은 팀에 속했을 때의 능력치 (Sji와 다를 수 있음)
i와 j가 같은 팀이면 Sij + Sji 가 더해짐.

능력치 차이를 최소로 하려 함.
 */
public class Main_14889_스타트와링크 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int N, result;
    static int[][] power;
    static boolean[] selected;
    public static void main(String[] args)throws IOException {
        init();
        /*
        [출력]
        능력치 차이의 최솟값
         */
        selectTeam(1, 0);
        System.out.print(result);
    }

    static void init() throws IOException{
        /*
        [입력]
        1. N (4<=N<=20, 짝수)
        2~N. S[][]입력 (i==j이면 항상 0, 나머지는 1<=<=100)
         */
        N = Integer.parseInt(br.readLine());

        power = new int[N+1][N+1];
        for(int i = 1; i <= N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 1; j <=N; j++){
                power[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        selected = new boolean[N+1];
        result = Integer.MAX_VALUE;
    }

    static void selectTeam(int startIndex, int cntPerson){
        if(cntPerson == N/2){
            result = Math.min(result, sumOfPower());
            return;
        }

        for(int i = startIndex; i <= N; i++){
            if(!selected[i]){
                selected[i] = true;
                selectTeam(i+1,cntPerson+1);
                selected[i] = false;
            }
        }
    }

    static int sumOfPower(){
        int team1 = 0;
        int team2 = 0;
        for(int i = 1; i <= N; i++){
            if(selected[i]){
                for(int j = 1; j <= N; j++){
                    if(selected[j]){
                        team1 += power[i][j];
                    }
                }
            }
            else{
                for(int j = 1; j <= N; j++){
                    if(!selected[j]){
                        team2 += power[i][j];
                    }
                }
            }
        }

        return Math.abs(team1 - team2);
    }
}
