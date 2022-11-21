package com.moonz.javapractice.CrackingCodingInterview.ch9.prob5;

import java.util.HashMap;
import java.util.Map;

public class Cache {
    public static int MAX_SIZE = 10;
    public Node head;
    public Node tail;
    public Map<String, Node> table; /* 쿼리를 key로 갖는 Node 테이블 */
    public int size = 0;

    public Cache() {
        this.table = new HashMap<>();
    }

    /* 노드를 앞으로 */
    public void moveToFront(Node node) {
        if (node == head) {
            return; /* 이미 head면 나아갈 필요 X */
        }
        /* 1. List에서 원래 노드 제거 */
        removeFromList(node);
        /* 2. 기존 head를 next로 지정하고 현재 노드를 head로 지정 */
        node.next = head;
        if (head != null) {
            head.previous = node;
        }
        head = node;
        size++;
        /* node가 1개밖에 없었다면 node가 head이자 tail */
        if (tail == null) {
            tail = node;
        }
    }

    /* 리스트에서 기존 노드 제거 */
    private void removeFromList(Node node) {
        if (node == null){
            return;
        }
        Node previous = node.previous;
        Node next = node.next;
        /* 1. 관계가 한개라도 맺어져 있다면 크기-- */
        if (previous != null || next != null) {
            size--;
        }
        /* 1. 노드 간 앞뒤 관계 재설정 */
        if (node.previous != null) {
            previous.next = next;
        }
        if (node.next != null) {
            next.previous = previous;
        }
        /* 2. 캐시의 head, tail 재설정 */
        if (node == head) {
            head = node.next;
        }
        if (node == tail) {
            tail = node.previous;
        }
        /* 3. 데이터 clear */
        node.next = null;
        node.previous = null;
    }

    public void insertQueryResults(String query, String[] queryResults) {
        /* 쿼리가 기존에 존재하면 */
        if (table.containsKey(query)) {
            /* 가장 앞으로 가져온다. */
            Node node = table.get(query);
            node.results = queryResults;
            moveToFront(node);
            return;
        }
        /* 새 쿼리이면 */
        Node newNode = new Node(query, queryResults);
        table.put(query, newNode);  /* 테이블에 query put */
//        size++;   // moveToFront()하면서 기존 노드도 제거하고 다시 추가하기 때문에 size++진행
        moveToFront(newNode);   /* 리스트에서 앞으로 */
        if (size > MAX_SIZE) {
            table.remove(tail.query);   /* 테이블에서 query 제거 */
            removeFromList(tail);   /* 리스트에서 제거 */
        }
    }
    /* 접근 시 리스트 가장 앞으로 감 */
    public String[] getResults(String query) {
        if (table.containsKey(query)) {
            Node node = table.get(query);
            moveToFront(node);
            return node.results;
        }
        return null;
    }
}
