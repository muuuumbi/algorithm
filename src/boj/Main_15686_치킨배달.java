package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

/*
N*N 도시, 0 : 빈칸, 1 : 집, 2 : 치킨집
치킨 거리 = 집과 가장 가까운 치킨집 사이의 거리
도시의 치킨 거리 = 모든 집의 치킨 거리의 합
거리 = |r1 - r2| + |c1 - c2|

치킨집 최대 M개 고르기
어떻게 골라야 도시의 치킨 거리가 가장 작게 될지 구하라
 */
public class Main_15686_치킨배달 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int N, M, result;
    static int[][] city;
    static List<Location> house, chicken;

    static class Location{
        int row;
        int column;
        boolean possible;

        public Location(int row, int column){
            this.row = row;
            this.column = column;
        }

        public int getRow(){
            return this.row;
        }

        public int getColumn(){
            return this.column;
        }

        public boolean getPossible(){
            return possible;
        }

        public void setPossible(boolean possible){
            this.possible = possible;
        }
    }
    public static void main(String[] args) throws IOException{
        init();
        deleteChicken(0,0);
        /*
        [출력]
        도시의 치킨 거리의 최솟값
         */
        System.out.println(result);
    }

    static void init() throws IOException {
        /*
        [입력]
        1. N, M (2 <= N <= 50, 1 <= M <= 13)
        2~N. 도시 정보
         */
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        city = new int[N][N];

        house = new ArrayList<>();
        chicken = new LinkedList<>();
        result = Integer.MAX_VALUE;

        for(int r = 0; r < N; r++){
            st = new StringTokenizer(br.readLine());
            for(int c = 0; c < N; c++){
                city[r][c] = Integer.parseInt(st.nextToken());
                //1 : 집
                if(city[r][c] == 1){
                    house.add(new Location(r, c));
                }
                //2 : 치킨집
                else if(city[r][c] == 2){
                    chicken.add(new Location(r, c));
                }
            }
        }
    }

    static void deleteChicken(int start, int depth){
        //M개의 치킨집을 다 골랐으면
        if(depth == M){
            //도시 거리 계산하기
            result = Math.min(result, getCityLength());
            return;
        }
        for(int i = start; i < chicken.size(); i++){
            Location location = chicken.get(i);
            if(!location.getPossible()){
                location.setPossible(true);
                deleteChicken(i + 1, depth + 1);
                location.setPossible(false);
            }
        }
    }

    static int getCityLength(){
        int cityLength = 0;
        for(Location houseLoc : house){
            int chickenLength = Integer.MAX_VALUE;

            for(Location chickenLoc : chicken){
                if(!chickenLoc.getPossible()){
                    continue;
                }
                chickenLength = Math.min(chickenLength, getChickenLength(houseLoc, chickenLoc));
            }

            cityLength += chickenLength;
        }
        return cityLength;
    }

    //치킨거리
    static int getChickenLength(Location house, Location chicken){
        return Math.abs(house.getRow()-chicken.getRow()) + Math.abs(house.getColumn() - chicken.getColumn());
    }
}
