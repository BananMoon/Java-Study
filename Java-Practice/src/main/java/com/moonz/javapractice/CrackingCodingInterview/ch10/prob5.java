package com.moonz.javapractice.CrackingCodingInterview.ch10;

/** 10-5. 드문드문 탐색
 * 빈 문자열이 섞여있는 정렬된 문자열 배열이 있을 때, 특정 문자열의 위치를 찾아라.
 * Q. 빈 문자열을 골랐을 때, 예외를 던지는 방식 or 정상 문자열을 찾을 때까지 조회하는 방식
 * A. 후자 선택!
 * - 최악의 경우 : 찾는 문자열을 제외하고 모두 빈 문자열인 경우, 모든 문자열을 탐색해야 정상 문자열을 찾게 된다. (단계2에서)
 */
public class prob5 {
    public static void main(String[] args) {
        prob5 test = new prob5();
        String[] sortedArr = {"at", "", "", "", "ball", "", "", "car", "", "", "dad", "", ""};
        String findWord = "ball";
        System.out.println(findWord + "의 인덱스 : " + test.search(sortedArr, findWord));
    }

    /**
     * 조회 문자열 체크하는 메서드로 먼저 검증
     */
    public int search(String[] sortedArr, String value) {
        if (sortedArr == null || value == null || value.isEmpty()) {
            return -1;
        }
        return search(sortedArr, 0, sortedArr.length -1, value);
    }
    private int search(String[] sortedArr, int left, int right, String value) {
        /* 1. base case */
        if (left > right) {
            return -1;
        }

        int mid = (left+right) / 2;
        /* 2. 빈 문자열인지 먼저 체크 -> 비어 있으면, 주변 일반 문자열로 이동해서 정상 문자열 찾을 때까지 반복 */
        if (sortedArr[mid].isEmpty()) {
            int movedToL = mid - 1;
            int movedToR = mid + 1;
            while (true) {
                if (movedToL >= left && !sortedArr[movedToL].isEmpty()) {
                    mid = movedToL;
                    break;
                } else if (movedToR <= right && !sortedArr[movedToR].isEmpty()) {
                    mid = movedToR;
                    break;
                }
                movedToL -=1;
                movedToR +=1;
            }
        }
        /* 3. 비지 않은 mid를 찾았으면 value 탐색 시작 */
        if (sortedArr[mid].equals(value)) {
            return mid;
        } else if (sortedArr[mid].compareTo(value) < 0) {   // mid 문자열이 더 작다면
            return search(sortedArr, mid+1, right, value);
        } else {
            return search(sortedArr, left, mid - 1, value);
        }
    }
}
