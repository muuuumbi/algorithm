package programmers.bruteforce;

public class Solution_피로도 {
    static boolean[] check;
    static int[][] dungeon;
    static int K;
    static int numOfDungeon, result;

    public int solution(int k, int[][] dungeons) {
        dungeon = dungeons;

        numOfDungeon = dungeons.length;
        check = new boolean[numOfDungeon];
        result = 0;

        for(int i = 0; i < numOfDungeon; i++){
            int now = calc(dungeon[i][0], dungeon[i][1], k);

            if(now == -1){
                check[i] = true;
            }
            else{
                check[i] = true;
                DFS(1, now);
                check[i] = false;
            }
        }

        return result;
    }

    static void DFS(int depth, int now){
        result = Math.max(result, depth);

        for(int i = 0; i < numOfDungeon; i++){
            if(check[i]){
                continue;
            }

            int next = calc(dungeon[i][0], dungeon[i][1], now);

            if(now != -1){
                check[i] = true;
                DFS(depth + 1, next);
                check[i] = false;
            }
        }
    }

    static int calc(int min, int need, int now){
        if(min >= now){
            return now - need;
        }
        return -1;
    }
}
