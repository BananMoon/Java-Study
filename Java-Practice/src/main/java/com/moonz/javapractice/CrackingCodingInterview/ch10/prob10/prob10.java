package com.moonz.javapractice.CrackingCodingInterview.ch10.prob10;

/** 스트림에서의 순위
 * - 순위? x보다 작거나 같은 수들의 갯수
 * - 방법
 * 1. 값이 정렬되어있다면 getRankOfNumber()는 효율적이지만, track()은 매 값을 추가할 때마다 원소들을 옮겨야해 비효율적이다.
 * 2(selected). 이진 탐색 트리 : 원소 간 상대적 순서를 유지하면서 새로운 값이 들어와도 효율적으로 배치되는 자료 구조
 *   - 시간복잡도 : 삽입&랭킹 구할 때: 균형잡힌 트리에서는 O(logN), 균형잡히지 않은 트리에서는 모두 탐색하므로 O(N)
 */
public class prob10 {
    static RankNode root = null;
    public static void main(String[] args) {
//        int[] inputStream = {5, 1, 4, 4, 5, 9, 7, 13, 3};
        int[] inputStream = {20, 15, 25, 10, 23, 5, 13, 24};
        for (int s : inputStream) {
            track(s);
        }
        System.out.println(getRankOfNumber(24)); // 6위
        System.out.println(getRankOfNumber(20)); // 4위
        System.out.println(getRankOfNumber(5));  // 0위
    }

    static public int getRankOfNumber(int x)  {
        return root.getRank(x);
    }
    /**
     * 스트림의 수를 한개 읽을 때마다 호출되어 랭크에 추가한다.
     * 원소 간 상대적 순서 유지하면서 새 원소 삽입하기에 효율적인 자료구조 => 이진 탐색 트리
     */
    static void track (int x) {
        if (root == null) {
            root = new RankNode(x);
        } else {
            root.insert(x); // root 기준으로 데이터 검색 후 적절한 곳에 삽입
        }
    }
}
