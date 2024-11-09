package boj;

import java.io.*;
import java.util.StringTokenizer;

/*
각 회원은 다른 회원들과 가까운 정도에 따라 점수를 받게 됨.
다른 모든 회원과 친구이면 1점
다른 모든 회원과 친구 || 친구의 친구이면 2점
다른 모든 회원과 친구 || 친구의 친구 ||친구의 친구의 친구이면 3점
4점, 5점도 같은 방식으로 정해짐
어떤 두 회원이 친구사이이면서 동시에 친구의 친구사이이면 친구사이라고 함.
점수가 가장 작은 사람이 회장
 */
public class Main_2660_회장뽑기 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringBuilder firstLine, secondLine;
    static StringTokenizer st;
    static int N;
    static final int INF = 100;
    static int[][] relationship;
    public static void main(String[] args) throws IOException{
        init();
        floydWarshall();
        getLeader();
        /*
        [출력]
        1. 회장 후보의 점수와 후보의 수 출력
        2. 회장 후보를 오름차순으로 모두 출력
         */
        bw.write(firstLine.toString());
        bw.write(secondLine.toString());
        bw.flush();
    }

    static void init() throws IOException {
        /*
        [입력]
        1. 회원의 수 N (N <= 50)
        2~N. 2개의 회원 번호 (서로 친구임을 나타냄, 마지막 줄은 -1이 두 개)
         */
        N = Integer.parseInt(br.readLine());

        relationship = new int[N+1][N+1];
        for(int r = 1; r <= N; r++){
            for(int c = 1; c <= N; c++){
                if(r != c){
                    relationship[r][c] = INF;
                }
            }
        }

        while(true){
            st = new StringTokenizer(br.readLine());
            int person1 = Integer.parseInt(st.nextToken());
            int person2 = Integer.parseInt(st.nextToken());

            if(person1 == -1 && person2 == -1){
                break;
            }

            //친구 관계 1 입력
            relationship[person1][person2] = relationship[person2][person1] = 1;
        }
    }

    static void floydWarshall(){
        for(int n = 1; n <= N; n++){
            for(int r = 1; r <= N; r++){
                for(int c = 1; c <= N; c++){
                    //더 짧은 경로로 값 갱신
                    if(relationship[r][c] > relationship[r][n] + relationship[n][c]){
                        relationship[r][c] = relationship[r][n] + relationship[n][c];
                    }
                }
            }
        }
    }

    static void getLeader(){
        int leaderScore = INF; //최솟값 구하기
        int[] friendScore = new int[N+1];

        for(int r = 1; r <= N; r++){
            int score = 0;
            for(int c = 1; c <= N; c++){
                //이어진 관계이면
                if(relationship[r][c] != INF){
                    //친구 점수 (최댓값)
                    score = Math.max(score, relationship[r][c]);
                }
            }
            friendScore[r] = score;
            //가장 작은 점수가 회장 점수
            leaderScore = Math.min(leaderScore, score);
        }
        firstLine = new StringBuilder();
        firstLine.append(leaderScore + " ");

        int leaderNum = 0;

        secondLine = new StringBuilder();
        for(int n = 1; n <= N; n++){
            if(leaderScore == friendScore[n]){
                leaderNum++;
                secondLine.append(n + " ");
            }
        }

        firstLine.append(leaderNum + "\n");
    }
}
