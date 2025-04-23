package boj;
import java.io.*;
import java.util.*;
/*
특정 문자열, 특정 알파벳과 문자열의 구간 [l,r]
특정 알파벳이 몇 번 나타나는지 구하기
 */
public class Main_16139_인간컴퓨터상호작용 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static String str;
    static int q;
    static int[][] alphabetSum;

    public static void main(String[] args) throws IOException {
        init();
        StringBuilder sb = new StringBuilder();

        for(int i = 0; i < q; i++){
            st = new StringTokenizer(br.readLine());
            char c = st.nextToken().charAt(0);
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());

            if(start == 0){
                sb.append(alphabetSum[c - 'a'][end]).append("\n");
            }
            else {
                sb.append(
                        alphabetSum[c - 'a'][end] - alphabetSum[c - 'a'][start - 1]
                ).append("\n");
            }
        }
        System.out.println(sb);
    }

    static void init() throws IOException{
        str = br.readLine();
        q = Integer.parseInt(br.readLine());
        alphabetSum = new int[26][str.length()];

        alphabetSum[str.charAt(0) - 'a'][0] = 1;
        for(int i = 1; i < str.length(); i++){
            char now = str.charAt(i);
            for(int j = 0; j < 26; j++) {
               alphabetSum[j][i] = alphabetSum[j][i-1];
            }
            alphabetSum[now - 'a'][i]++;
        }
    }
}
