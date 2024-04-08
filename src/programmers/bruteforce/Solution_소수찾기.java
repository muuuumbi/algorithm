package programmers.bruteforce;

import static java.lang.Math.sqrt;

public class Solution_소수찾기 {
    static int length, answer;
    static int[] arr;
    static boolean[] check, numberArr;
    static StringBuilder nowNum;

    public static int solution(String numbers) {
        length = numbers.length();
        //같은 숫자가 만들어졌을 때 판별하기 위해 생성
        numberArr = new boolean[(int)Math.pow(10, length)];
        answer = 0;
        arr = new int[length];
        check = new boolean[length];

        for(int i = 0; i < length; i++){
            //시작 숫자가 0이 아닐 때
            nowNum = new StringBuilder(numbers.substring(i,i + 1));
            if(nowNum.charAt(0) != '0'){
                //체크해주기
                check[i] = true;
                method(numbers);
                check[i] = false;
            }
        }

        return answer;
    }

    public static void method(String numbers){
        int finalNum = Integer.parseInt(nowNum.toString());
        if(!numberArr[finalNum]) {
            numberArr[finalNum] = true;
            //소수판별
            if (isPrime(finalNum)) {
                System.out.println(finalNum);
                answer++;
            }
        }

        for(int i = 0; i < length; i++){
            if(!check[i]){
                check[i] = true;
                nowNum.append(numbers.charAt(i));
                method(numbers);
                //백트래킹
                check[i] = false;
                nowNum.deleteCharAt(nowNum.length() - 1);
            }
        }
    }

    public static boolean isPrime(int number){
        if(number <= 1){
            return false;
        }

        if(number % 2 == 0){
            return number == 2;
        }

        for(int i = 3; i <= sqrt(number); i += 2){
            if(number % i == 0){
                return false;
            }
        }

        return true;
    }
}
