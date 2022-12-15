package com.moonz.javapractice.CrackingCodingInterview.ch10;

/**
 * 방법 고민
 * - 값이 정렬되어있다면 getRankOfNumber()는 효율적이지만, track()은 매 값을 추가할 때마다 원소들을 옮겨야해 비효율적이다.
 * - 이진 탐색 트리 : 원소 간 상대적 순서를 유지하면서 새로운 값이 들어와도 효율적인 자료 구조
 * - to be continue...
 */
public class prob10 {
    static RankNode root = null;
    public static void main(String[] args) {
        int[] inputStream = {5, 1, 4, 4, 5, 9, 7, 13, 3};
        for (int s : inputStream) {
            track(s);
        }
        // x보다 작거나 같은 수들의 갯수 == 랭킹
        System.out.println(getRankOfNumber(1)); // 1: 0위
        System.out.println(getRankOfNumber(3)); // 1-3: 1위
        System.out.println(getRankOfNumber(4)); // 1-3-4: 3위
    }

    static public int getRankOfNumber(int x)  {
        if (root == null) {

        }
        return 1;
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

    /**
     * x의 랭킹 (x보다 같거나 작은 수의 개수, 자신 제외)을 반환한다.
     */
    static int getRank (int x) {
        //

        return 1;
    }

    private static class RankNode {
        RankNode left, right;
        int data;
        int counter = 0;
        public RankNode (int d) {
            data =d;
        }

        public void insert(int d) {

        }

    }
}
