package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/*
길이가 N. 정수, 연산자로 이루어져 있음.
연산자 우선순위는 모두 동일함.
단, 괄호 안의 식은 먼저 계산해야 하고 중첩된 괄호는 사용할 수 없음
괄호를 추가해 만들 수 있는 식의 결과의 최댓값 구하기
 */
public class Main_16637_괄호추가하기 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int N, result;
    static String expression;
    static ArrayList<Character> operators;
    static ArrayList<Integer> numbers;

    public static void main(String[] args) throws IOException{
        init();
        DFS(numbers.get(0), 0);
        /*
        [출력]
        결과의 최댓값
         */
        System.out.println(result);
    }

    static void init() throws IOException {
        /*
        [입력]
        1. N (1 <= N <= 19)
        2. 수식 (항상 올바른 수식이 주어짐)
         */
        N = Integer.parseInt(br.readLine());
        expression = br.readLine();

        operators = new ArrayList<>();
        numbers = new ArrayList<>();

        for(int n = 0; n < N; n++){
            char now = expression.charAt(n);
            //연산자 저장
            if(now == '+' || now == '-' || now == '*'){
                operators.add(now);
            }
            //숫자 저장
            else{
                numbers.add(now - '0');
            }
        }

        result = Integer.MIN_VALUE;
    }

    static void DFS(int cumulative, int operatorIndex){
        if(operatorIndex >= operators.size()){
            //최댓값 갱신
            result = Math.max(result, cumulative);
            return;
        }

        //1. 괄호가 없는 경우
        //누적값에 다음 숫자 계산해주기
        int cal1 = calculate(cumulative, numbers.get(operatorIndex + 1), operators.get(operatorIndex));
        DFS(cal1, operatorIndex + 1);

        //2. 괄호가 있는 경우
        if(operatorIndex + 1 < operators.size()){
            //cumulative operator ('숫자1' '연산자2' '숫자2')
            //괄호 계산 값
            int cal2 = calculate(numbers.get(operatorIndex + 1), numbers.get(operatorIndex + 2), operators.get(operatorIndex + 1));
            //다음 cumulative = 현재 cumulative, operator, cal2 계산
            DFS(calculate(cumulative, cal2, operators.get(operatorIndex)), operatorIndex + 2);
        }
    }

    static int calculate(int num1, int num2, char operator){
        int cal = 0;
        switch(operator){
            case '+':
                cal = num1 + num2;
                break;
            case '-':
                cal = num1 - num2;
                break;
            case '*':
                cal = num1 * num2;
                break;
        }
        return cal;
    }
}
