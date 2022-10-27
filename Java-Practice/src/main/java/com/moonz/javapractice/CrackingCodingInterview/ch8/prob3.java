package com.moonz.javapractice.CrackingCodingInterview.ch8;

public class prob3 {
    public static void main(String[] args) {
        int[] arr = {-40, -20, -1, 1, 2, 3, 5, 7, 9, 12, 13};
        prob3 t = new prob3();
        System.out.println("마술 인덱스: "+ t.findMagicIndex(arr));  // 7

        int[] overlappedArr = {-10, -5, 2, 2, 2, 3, 4, 7, 9, 12, 13};
        System.out.println("중복있는 배열에서 마술 인덱스: " + t.findMagicIndexAtOverlapArr(overlappedArr)); // 2
    }

    public int findMagicIndex (int[] arr) {
        return findMagicIndex(arr, 0, arr.length-1);
    }
    public int findMagicIndexAtOverlapArr (int[] arr) {
        return findMagicIndexAtOverlapArr(arr, 0, arr.length-1);
    }

    private int findMagicIndex(int[] arr, int start, int end) {
        if (end < start) {
            return -1;
        }
        int mid = (start + end ) / 2;
        if (mid == arr[mid]) {
            return mid;
        }
        else if (mid > arr[mid]) {      /* 원소가 index보다 작은 경우 */
            return findMagicIndex(arr, mid+1, end);
        }
        else {  /* 원소가 index보다 큰 경우 */
            return findMagicIndex(arr, start, mid-1);
        }
    }

    private int findMagicIndexAtOverlapArr(int[] arr, int start, int end) {
        if (end < start) {
            return -1;
        }
        int mid = (start + end ) / 2;
        if (mid == arr[mid]) {
            return mid;
        }
        //왼쪽 먼저 재귀 호출 (둘 중 더 작은 값으로)
        int leftIdx = Math.min(mid - 1, arr[mid]);
        int foundIdx = findMagicIndexAtOverlapArr(arr, start, leftIdx);
        if (foundIdx > -1) {
            return foundIdx;
        }
        // 오른쪽 재귀 호출 (둘 중 더 큰값으로)
        int rightIdx = Math.max(mid + 1, arr[mid]);
        return findMagicIndex(arr, rightIdx, end);
    }
}
