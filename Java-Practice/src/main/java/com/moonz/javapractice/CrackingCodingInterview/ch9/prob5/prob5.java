package com.moonz.javapractice.CrackingCodingInterview.ch9.prob5;

/**
 * <단일 시스템에 대한 설계>
 * 0부터 19까지 쿼리 조회 + (쿼리,데이터)가 캐싱될 때
 * 9-8-7-6-5-4-3-2-1-0 이런식으로 최근에 조회된게 가장 앞으로 오는데,
 * 반복문에서 i==9,16,19가 되면 쿼리 2,6,9를 한번씩 조회한다.
 * 즉, 쿼리 2를 조회하면 순서는 아래와 같이 2가 head가 된다.
 * 2-9-8-7-6-5-4-3-1-0 (딱 10개)
 * 결과: 0-19까지 캐싱하지만, 캐시 크기는 10으로 제한되기 때문에 tail을 제거해나가서 남는 캐시값은 9-6-2-19-18-17-16-15-14 이다.
 */
public class prob5 {
    public static void main(String[] args) {
        Cache cache = new Cache();
        for (int i = 0; i < 20; i++) {
            String query = "query " + i;
            cache.insertQueryResults(query, generateQueryResults(i));
            /* 쿼리 2,6,9에 대한 결과 조회 */
            if (i == 9 || i == 16 || i == 19) {
                cache.getResults("query " + 2);
                cache.getResults("query " + 6);
                cache.getResults("query " + 9);
            }
        }
        System.out.println("====쿼리 캐싱된 순 조회====");
        Node node = cache.head;
        while (node.next != null) {
            System.out.print(node.query + " : ");
            for (String s : node.results) {
                System.out.print(s + ", ");
            }
            System.out.println();
            node = node.next;
        }

        /*for (int i = 0; i < 30; i++) {
            String query = "query " + i;
            String[] results = cache.getResults(query);
            System.out.print(query + ": ");
            if (results == null) {
                System.out.print("null");
            } else {
                for (String s : results) {
                    System.out.print(s + ", ");
                }
            }
            System.out.println("");
        }*/
    }

    private static String[] generateQueryResults(int i) {
        String[] results = {"query "+i+"의 resultA", "query "+i+"의 resultB", "query "+i+"의 resultC"};
        return results;
    }
}
