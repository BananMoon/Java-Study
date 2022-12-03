package com.moonz.javapractice.CrackingCodingInterview.ch10.prob4;

/** 10-4. 크기를 모르는 정렬된 원소 탐색
 * base) i 인덱스에 위치한 원소를 O(1) 시간 만에 반환하는 elementAt(i) 메서드 존재. 배열의 범위 넘어선 경우 -1 반환.
 * 1. 이진탐색을 하기 위한 배열의 길이를 구한다.
 *  - 점점 큰 값을 호출하면서 elementAt(i) 호출하면서 배열의 범위를 알아간다.
 *  - 값은 선형적으로 키우지 않고, 기하급수적으로 (2배씩) 키우는 것이, 원소가 n개일 때 O(logN) 시간 안에 길이를 구할 수 있다.
 *    -> 2의 k승 = n 이 되는 k 값을 구한다.
 *    -> k = log(n)
 * 2. 찾은 길이로 이분 탐색을 진행해서 원소 찾는드.
 */
public class prob4 {
    public static void main(String[] args) {
        prob4 test = new prob4();
        Listy list = new Listy(new int[]{3,5,9,14,30,50});
        System.out.println("14의 인덱스 : " + test.search(list, 14));
    }

    /**
     * 기하급수적으로 값을 채우면서 원소길이를 찾는다.
     * 길이는 무조건 1 이상이라 생각.
     */
    private int search(Listy list, int x) {
        // 1. 길이 대충 찾고
        int idx = 1;
        while(list.elementAt(idx) != -1 && list.elementAt(idx) < x) {   // x보다 커졌을 때 그만 두어도 괜찮. 정확한 길이를 몰라도.
            idx *= 2;
        }
        // 2. 이진 탐색으로 원소 x 찾는다.
        return searchByRecursion(list, 0, idx, x);
        // TODO 왜 left= 0도 되는데 idx/2를 넘겨서 절반을 먼저 찾지???
        // return searchByRecursion(list, idx/2, idx, x);
    }

    /**
     * while문으로 이분 탐색
     */
    private int searchByWhile(Listy list, int left, int right, int x) {
        while (left < right) {
            int mid = (left + right)/2;
            int midNum = list.elementAt(mid);
            if (midNum > x || midNum == -1) {
                right = mid - 1;
            } else if (midNum < x) {
                left = mid + 1;
            } else {
                return mid;
            }
        }
        return -1;
    }

    /**
     * 재귀호출로 이분탐색
     */
    private int searchByRecursion (Listy list, int left, int right, int x) {
        int mid = (left + right)/2;
        int midNum = list.elementAt(mid);
        if (midNum == x) {
            return mid;
        }
        if (left > right) {
            return -1;
        }
        if (midNum > x || midNum == -1) {  // 찾는 원소가 중간보다 왼쪽에 있거나 현재 중간이 배열 범위를 벗어났다면 왼쪽 배열로 이동.
            return searchByRecursion(list, left, mid - 1, x);
        } else {    // 오른쪽
            return searchByRecursion(list, mid + 1, right, x);
        }
    }
}
