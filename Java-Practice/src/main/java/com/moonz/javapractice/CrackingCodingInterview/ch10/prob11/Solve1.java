package com.moonz.javapractice.CrackingCodingInterview.ch10.prob11;

import java.util.Arrays;

/** 10-11. Peaks and Valleys
 * - 풀이
 * 1. 오름차순 정렬
 * 2. (값이 작아졌다 커지는 것을 반복해야 하니까) idx=1부터 2칸씩 건너뛰면서 앞 원소와 바꾼다. '소'<'중'<대 => 중>소<대
 * - 시간복잡도 : 1번:정렬 O(NlogN), 2번: 절반만큼 순회 O(N/2)
 *      => 최종 : 더 큰 O(NlogN)
 *
 * 참고) Arrays.sort는 DualPivotQuicksort.sort() 수행하는데 원시타입의 경우, 배열 크기에 따라 삽입 => 퀵 => 머지정렬(O(NlogN))을 수행한다.
 *       Object 타입의 경우, 팀 소트(최악: O(NlogN))
 */
public class Solve1 {
    public static void main(String[] args) {
        int[] arr = {5,8,6,2,3,4};
        Solve1 test = new Solve1();
        test.sortToValleyPeak(arr);
    }
    public void sortToValleyPeak(int[] arr) {
        Arrays.sort(arr);
        for (int i=1; i<arr.length; i+=2) {
            swap(arr, i, i-1);
        }
        for (int num : arr) {
            System.out.print(num+" ");
        }
    }

    private void swap(int[] arr, int idx1, int idx2) {
        int tmp = arr[idx1];
        arr[idx1] = arr[idx2];
        arr[idx2] = tmp;
    }
}
