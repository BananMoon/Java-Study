package com.moonz.javapractice.CrackingCodingInterview.ch10;

/** 10-3. 회전된 배열에서 검색
 * 가정) 원소 중복 존재
 * 1. 풀이 - 회전점이 어디에 있는지 찾는다.
 *  - 왼쪽 첫번째 값 > 중간 값? 회전점이 왼쪽에 -> 오른쪽 배열에 찾는 값이 있는지 체크 (0 ~ 중간에 있는지)
 *  - 왼쪽 첫번째 값 < 중간값? 회전점이 오른쪽에 -> 정상인 오른쪽 배열에 찾는 값이 있는지 체크 (중간 ~ 오른쪽 끝에 있는지)
 *  - 왼쪽 첫번째 값 == 중간값?
 *   - 오른쪽 끝 값 != 중간값? 오른쪽 배열만 탐색
 *   - 오른쪽 끝 값 == 중간값? 양쪽 배열 모두 탐색 (어디에 값이 있는지 모르므로)
 *     => 탐색 시, 재귀 호출 ?!
 * 2. 시간 복잡도
 * - 중복된 원소가 없다면, 이분탐색 -> O(log N)
 * - 중복된 원소가 있으면, O(N)
 */
public class prob3 {
    public int search(int[] arr, int left, int right, int x) {
        int mid = (left + right) / 2;

        if (arr[mid] == x) {    /* 찾으면 바로 리턴 */
            return mid;
        }
        if (left > right) {
            return -1;
        }
        if (arr[left] < arr[mid]) {       /* 왼쪽이 오름차순 정렬이면 */
            if (arr[left] <= x && x < arr[mid]) {    /* 찾는 값이 왼쪽에 있으면 */
                return search(arr, left, mid-1, x);
            } else {
                return search(arr, mid+1, right, x);
            }
        } else if (arr[mid] <= arr[right]){   /* 왼쪽이 더 작아서 회전된 상태이면: 반대편인 오른쪽이 오름차순 정렬된 경우 */
            if (arr[mid] < x && x <= arr[right]) {   /* 찾는 값이 오른쪽에 있으면 */
                return search(arr, mid+1, right, x);
            } else {
                return search(arr, left, mid-1, x);
            }
        } else if (arr[mid] == arr[left]) {   /* 왼 == 중간: 반복된 경우 */
            if (arr[mid] == arr[right]) {    /* 오른쪽도 같은 수라면 (ex-2,3,2,2,2,2) 양쪽 중 어디에 값이 있을지 탐색 필요 */
                int leftSearchResult = search(arr, left, mid-1, x);
                if (leftSearchResult == -1) {
                    return search(arr, mid+1, right, x);
                } else {
                    return leftSearchResult;
                }
            } else {  /* 오른쪽은 다른 수라면 (ex-2,2,2,3,3,3) 오른쪽만 탐색하면 됨 */
                return search(arr, mid + 1, right, x);
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] arr = {50, 5, 20, 30, 40};
        int[] arr2 = {10, 15, 20, 0, 5};
        prob3 test = new prob3();
        System.out.println("arr에서 5의 인덱스 : " + test.search(arr, 0, arr.length - 1, 5)); // 답: 1
        System.out.println("arr2에서 5의 인덱스 : " + test.search(arr2, 0, arr2.length - 1, 5));  // 답: 5
    }
}
