package com.moonz.javapractice.CrackingCodingInterview.ch10;
/**
 * 문제 이해
 정렬된 배열 A와 B가 있고, A에는 뒤에 B의 갯수만큼이 들어갈 충분한 공간이 있다.
 A와 B를 하나의 정렬된 배열로 병합하는 메서드를 만들어라.
 * 가정
 - 배열 a, b의 원소 갯수 알 수 있다.
*/
public class prob1 {
    public void merge(int[] a, int[] b, int cntA, int cntB) {
        int endIdxA = cntA - 1;
        int endIdxB = cntB - 1;
        int allIdx = cntA + cntB - 1;

        // A 배열 기반으로 정렬하기 때문에, B 배열을 모두 정렬시키고 나면 종료한다.
        // else: a 배열이 모두 탐색하고 나서도 b 배열을 a 배열에 모두 넣도록 한다.
        while (endIdxB>=0) {
            if (endIdxA>=0 && a[endIdxA] > b[endIdxB]) {
                a[allIdx] = a[endIdxA];
                endIdxA--;
            } else {
                a[allIdx] = b[endIdxB];
                endIdxB--;
            }
            allIdx--;  // 전체 idx 이동
        }
    }

    public static void main(String[] args) {
        int[] a = new int[6];   // 3개 외 충분한 여유 공간
        a[0] = 4;
        a[1] = 9;
        a[2] = 14;
        int[] b = {1,2,19};

        prob1 p = new prob1();
        p.merge(a, b, 3, 3);
        for (int value : a) {
            System.out.print(value + " ");
        }
    }
}
