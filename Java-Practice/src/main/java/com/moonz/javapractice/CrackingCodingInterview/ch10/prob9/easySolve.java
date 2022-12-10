package com.moonz.javapractice.CrackingCodingInterview.ch10.prob9;

/**
 * 정렬된 행렬 탐색
 * - 행과 열 모두 정렬된 행렬에서 특정 값 x를 찾는 문제
 * 풀이 방법
 * - 조건
 *  - 행/열의 첫번째원소가 x보다 크면 해당 행/열은 찾을 필요 없다.
 *  - 행/열의 마지막원소가 x보다 작으면 해당 행/열은 찾을 필요 없다.
 * -> 행은 0부터, 열은 last부터 진행해서,
 *   - 그 값이 x보다 크면 그 열 아래에는 없으므로 열을 왼쪽으로 이동(--),
 *   - 그 값이 x보다 작으면 그 열 아래에 있으므로 행을 아래로 이동(++)
 *   - 그 값이 x와 같으면 true 리턴
 * 논의해야할 점
 * - 특정 원소를 찾으면 어떤 값을 반환하도록 해야하는가? -> A: 있는지 없는지 boolean값
 */
public class easySolve {
    public boolean isXContains (int[][] arr, int x) {
        int currColIdx = 0;
        int currRowIdx = arr[0].length - 1;

        while (currColIdx < arr.length && currRowIdx >=0) {
            int currNum = arr[currRowIdx][currRowIdx];
            if (currNum == x) {
                return true;
            } else if (currNum > x) {   // 왼쪽으로 이동 (열 --)
                currColIdx--;
            } else {    // currNum < x -> 아래로 이동 (행 ++)
                currRowIdx++;
            }
        }
        return false;
    }
}
