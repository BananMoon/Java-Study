package com.moonz.javapractice.CrackingCodingInterview.ch10.prob11;

/** Peaks and Valleys
 * - 풀이
 * 작은 걸 가운데로 몰아.
 * 두칸씩 건너뛰면서, i-1과 i 비교 : i가 더 크면 swap / i와 i+1 비교 : i가 더 크면 swap
 * i가 주위 값보다 크도록 한다.
 * => i를 크게 해도 됨! (ver2)
 */
public class Solve3 {
    public static void main(String[] args) {
        int[] arr = {48, 40, 31, 62, 28, 21, 64, 40, 23, 17};
        Solve3 test = new Solve3();
        System.out.println("가운데가 작은 값으로 만든 ValleyPeak");
        int[] ValleyPeak = test.sortToValleyPeak(arr);
        for (int num : ValleyPeak) {
            System.out.print(num+" ");  /* 48 31 62 28 40 21 64 23 40 17 */
        }

        int[] arr2 = {48, 40, 31, 62, 28, 21, 64, 40, 23, 17};
        System.out.println("\n가운데가 큰 값으로 만든 ValleyPeak");
        int[] ValleyPeakVer2 = test.sortToValleyPeakVer2(arr2);
        for (int num : ValleyPeakVer2) {
            System.out.print(num+" ");  /* 40 48 31 62 28 64 21 40 17 23 */
        }

    }
    public int[] sortToValleyPeak(int[] arr) {
        for (int i=1; i<arr.length; i+=2) {
            if (arr[i] > arr[i-1]) {
                swap(arr, i, i-1);
            }
            if (i+1 < arr.length && arr[i] > arr[i+1]) {
                swap(arr, i, i+1);
            }
        }
        return arr;
    }

    public int[] sortToValleyPeakVer2(int[] arr) {
        for (int i=1; i<arr.length; i+=2) {
            if (arr[i] < arr[i-1]) {
                swap(arr, i, i-1);
            }
            if (i+1 < arr.length && arr[i] < arr[i+1]) {
                swap(arr, i, i+1);
            }
        }
        return arr;
    }

    private void swap(int[] arr, int idx1, int idx2) {
        int tmp = arr[idx1];
        arr[idx1] = arr[idx2];
        arr[idx2] = tmp;
    }
}
