package com.moonz.javapractice.CrackingCodingInterview.ch8;

/*
Q. 코인 - 코인 4가지가 있을 때, 특정 n 센트를 표현하는 모든 방법의 수를 계산하자.
중복 가능, 순서 x

- 문제 이해 예시 (토탈 100)
* n=x일 때 (갯수,남은금액)으로 정리
 D: 25      D: 10 (니켈만으로)  D: 5          D: 1
n=0: 0,100 -> n=0: 0,100    -> n=0: 0,100 -> 100,0  (1가지)
                            -> n=1: 1,95  -> 5,0 (1가지)
                            -> n=2: 2,90  -> 1,0 (D:1은 항상 1가지 뿐임을 알 수 있다.)
                            ..
                            -> n=20: 20,0 -> 1가지
           -> n=1: 1,90     -> n=0: 0,90  -> ..
           ..
           -> n=10, 10,0    -> n=0: 0,0   -> 1가지
n=1: 1,75  -> n=0: 0,75     -> n=0: 0,75  -> 1가지
                            -> n=1: 1,70  -> 1가지
                            -> n=2: 2,65  -> 1가지
                            ..
n=2: 2,50  -> n=0, 0,50    -> n=0: 0,50   -> 1가지
                           -> n=1: 1,45   -> ..
           ..
           -> n=5, 5,0     -> 1가지
n=3: 3,25  -> ..
n=4: 4,0   -> 1가지

- 풀이
인자 : 구하고자하는 가격(추가한 amount씩 --, 센트배열, 센트배열의 idx(1씩 ++)
캐싱 : 특정 가격을 만들 수 있는 idx(센트)로 특정 가격을 만들 수 있는 경우의 수를 등록해놓는다.
Ex) memo[0][0] total=0을 만드는 idx=0의 경우의 수; 1개 (5센트 0개 들어가는 경우)

base case : idx가 센트배열 마지막까지 왔으면 그 마지막 1센트는 무조건 1가지므로 1 리턴
for문) cents[idx] 값의 갯수를 한개씩 늘려가며 재귀호출.
*/
public class prob11 {
    public static void main(String[] args) {
        prob11 t = new prob11();
        System.out.println("10센트를 모으는 모든 경우의 수 : " + t.makeChange(10));
    }

    public int makeChange(int total) {
        int[] cents = {25, 10, 5, 1};
        int[][] memo = new int[total + 1][cents.length];
        return makeChangeHelper(total, cents, 0, memo);
    }

    private int makeChangeHelper(int total, int[] cents, int idx, int[][] memo) {
        /* 이미 계산된 값이면 바로 리턴 */
        if (memo[total][idx] > 0) {
            return memo[total][idx];
        }
        int currCoin = cents[idx];
        // 마지막 인덱스까지 다 돌았다면
        if (idx == cents.length - 1) {
            return 1;    // 남은 1센트는 1가지 경우의 수
        } /* 종료 지점 */

        int ways = 0;
        // 코인에 대해 갯수를 total 값 이하까지 늘려나간다.
        for (int amount = 0; amount <= total; amount += currCoin) {
            ways += makeChangeHelper(total - amount, cents, idx + 1, memo);
        }

        // 재귀호출 마무리 후
        memo[total][idx] = ways;    /* 특정 값, 특정 idx에 대한 경우의 수 저장*/
        System.out.println(cents[idx] + "로 토탈 값 = " + total + "을 채우는 경우의 수:" + ways);
        return ways;
    }
}