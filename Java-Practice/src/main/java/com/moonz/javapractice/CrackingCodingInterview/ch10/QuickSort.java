package com.moonz.javapractice.CrackingCodingInterview.ch10;

/** 퀵 소트
 * 1. 파티셔닝 : 피벗보다 작은 값들은 왼쪽에, 큰 값들은 오른쪽에 치중하도록 하는 과정
 * 1-1. 왼idx가 오idx보다 클 때까지 swap 반복하면서 pivot 기준으로 숫자들을 정렬한다.
 * 1-2. 왼idx와 오idx가 엇갈리면 종료한다. (이때 둘중 한 idx를 반환한다.)
 * 2. 왼or오 idx를 기준으로 2개의 부분 리스트로 나눠서 퀵소트 재귀호출한다.
*/
public class QuickSort {
    public static void main(String[] args) {
        int[] arr = {15, 9, 4, 3, 1};
        quickSort(arr, 0, arr.length - 1);
        for (int v : arr) {
            System.out.print(v + " ");
        }
    }
    public static void quickSort(int[] arr, int left, int right) {
        /* 1. 파티셔닝 */
        int partitonedIdx = partition(arr, left, right);  /* 분할 기점 */
        if (left < partitonedIdx - 1)  {
            quickSort(arr, left, partitonedIdx - 1);    /* 왼쪽 정렬 */
        } else if (partitonedIdx < right) {
            quickSort(arr, partitonedIdx, right);    /* 오른쪽 정렬 */
        }
    }

    private static int partition(int[] arr, int left, int right) {
        int piv = arr[(left + right) / 2];
        while (left <= right) {
            /* 왼쪽은 pivot보다 크거나 같은 값 발견 시까지 ++ 이동 */
            while (arr[left] < piv) {
                left++;
            }
            /* 오른쪽은 pivot보다 작거나 같은 값 발견 시까지 -- 이동 */
            while (arr[right] > piv) {
                right--;
            }
            /* left, right가 엇갈리지 않았으면  */
            if (left <= right) {
               swap(arr, left, right);
               left++;
               right--;
            }
        }
        /* left, right가 엇갈리면 한 곳(left or right)을 반환 */
        return left;
    }

    private static void swap(int[] arr, int left, int right) {
        int tmp = arr[left];
        arr[left] = arr[right];
        arr[right] = tmp;
    }
}
