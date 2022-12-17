package com.moonz.javapractice.CrackingCodingInterview.ch10.prob11;

/** Peaks and Valleys
 * - 풀이
 * 1. idx=1부터 2씩 증가하며 idx-1, idx, idx+1 중 가장 큰 값을 구한다.
 * 2. 그 큰 값의 인덱스가 idx와 같으면 그냥 두고, 다르면 swap한다.
 * - 시간 복잡도 : O(2/N) -> O(N)
 */
public class Solve2 {

    public static void main(String[] args) {
        int[] arr = {48, 40, 31, 62, 28, 21, 64, 40, 23, 17};
        Solve2 test = new Solve2();
        int[] peakAndValleyArr = test.peakAndValley(arr);
        for (int num : peakAndValleyArr) {
            System.out.print(num + " ");        /* 40 48 31 62 28 64 21 40 17 23 */
        }
    }

    public int[] peakAndValley(int[] arr) {
        if (arr.length < 3) {
            System.out.println("It is so short array!");
            return null;
        }
        for (int i=1; i<arr.length; i+=2) {
            int maxIdx = findMaxIdx(arr, i-1, i, i+1);
            if (maxIdx != i) {
                swap (arr, i, maxIdx);
            }
        }
        return arr;
    }

    /**
     * 인덱스가 범위를 넘어서는지 체크하며 최댓값 구한다.
     */
    private int findMaxIdx(int[] arr, int beforeIdx, int midIdx, int nextIdx) {
        int len = arr.length;
        int beforeNum = 0 <= beforeIdx && beforeIdx < len ? arr[beforeIdx] : Integer.MIN_VALUE;
        int midNum = 0 <= midIdx && midIdx < len ? arr[midIdx] : Integer.MIN_VALUE;
        int nextNum = 0 <= nextIdx && nextIdx < len ? arr[nextIdx] : Integer.MIN_VALUE;

        // idx를 넘거야함.
        int maxNum = Math.max(beforeNum, Math.max(midNum, nextNum));
        if (arr[beforeIdx] == maxNum) {
            return beforeIdx;
        } else if (arr[midIdx] == maxNum) {
            return midIdx;
        } else {
            return nextIdx;
        }
    }

    private void swap(int[] arr, int idx1, int idx2) {
        int tmp = arr[idx1];
        arr[idx1] = arr[idx2];
        arr[idx2] = tmp;
    }
}
