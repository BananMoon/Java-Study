package com.moonz.javapractice.CrackingCodingInterview.ch7.prob12;

import java.util.ArrayList;
import java.util.List;

public class CustomHashTable<K, V> {
    private final List<LinkedListNode<K, V>> hashTable;
    /*
    생성자
     */
    public CustomHashTable(int capacity) {
        hashTable = new ArrayList<>(capacity);
        for (int i = 0; i < capacity; i++) {
            hashTable.add(i, null); // LinkedListNode가 들어갈 자리
        }
    }

    /*
    삽입
    - 같은 해쉬값을 갖으면 그 해쉬값을 갖는 연결리스트에 추가
    - 같은 key를 갖을 경우 업데이트!
    - 같은 해쉬값이 없는 경우
     */
    public V put(K key, V value) {
        LinkedListNode<K, V> node = getNodeByKey(key);    // 같은 key값인 노드가 반환되거나 마지막 노드가 반환되거나
        if (node != null) { // 같은 key인 노드가 있는 것
            node.update(value);
            return value;
        }
        // 동일 key가 없어서 그냥 해쉬값에 노드 추가하면 되는 경우
        node = new LinkedListNode<>(key, value);
        int hashedIndex = getHashedIndex(key);
        LinkedListNode<K, V> nodes = hashTable.get(hashedIndex);    // 기존 해시값의 노드들
        // 노드 앞뒤 연결
        if (nodes != null) {    // 기존 해시값의 노드들과 연결 (새로 추가되는 노드를 맨 앞에 추가.)
            node.next = nodes;
            node.next.prev = node;
        }
        // 연결된 노드를 해당 idx에 set ★ 세팅 시에 nodes가 아닌 node를 세팅
        hashTable.set(hashedIndex, node);
        return value;
    }

    /*
    제거
     */
    public V remove (K key) {
        LinkedListNode<K, V> node = getNodeByKey(key);
        if (node == null) {
            return null;
        }
        // 다음노드 set
        if (node.next != null) {
            node.next.prev = node.prev;
        }
        // 이전노드 set
        if (node.prev != null) {
            node.prev.next = null;
        } else {    // head인 경우, hash값으로 얻어지는 LinkedListNode를 다음 노드(값이든, null이든)부터 재세팅
            int hashedIndex = getHashedIndex(key);
            hashTable.set(hashedIndex, node.next);
        }
        return node.value;
    }

    /*
    값 얻기
     */
    public V get (K key) {
        LinkedListNode<K, V> node = getNodeByKey(key);
        if (node != null) {
            return node.value;
        }
        return null;
    }
    /*
    key의 해쉬값으로 해당하는 해쉬값의 노드를 조회
    - 해당 해쉬값으로 저장된 노드가 없으면, null 반환
    - 해당 해쉬값으로 저장된 노드랑 key값이 같다면, 해당 노드 반환 (업데이트 예정)
     */
    private LinkedListNode<K, V> getNodeByKey(K k) {
        int idx = getHashedIndex(k);
        LinkedListNode<K, V> current = hashTable.get(idx);  // 같은 해쉬값을 갖는 연결리스트 노드
        // 마지막 노드까지 간다.
        while (current != null) {
            if (current.key == k) {   // 같은 key인 노드 반환?
                return current;
            }
            current = current.next;
        }
        return null;    // 같은 key인 노드가 없을 때.
    }

    /*
    해쉬값 : key를 해쉬화하고 사이즈로 나눈 나머지 의 절댓값
     */
    private int getHashedIndex(K k) {
        return Math.abs(k.hashCode() % hashTable.size());
    }

    public void print() {
        for (int i=0; i<hashTable.size(); i++) {
            String nodeByIdx = hashTable.get(i) == null? "" : hashTable.get(i).printNext();
            System.out.println(i+"." + nodeByIdx);
        }
    }

    /*
    Hash에서 각 노드의 연결리스트 클래스
    양방향 연결 리스트로 구현.
     */
    private static class LinkedListNode<K, V> {
        public LinkedListNode<K, V> prev;
        public LinkedListNode<K, V> next;
        public K key;
        public V value;

        public LinkedListNode(K k, V v) {
            key = k;
            value = v;
        }

        public void update(V v) {
            value = v;
        }

        public String printNext() {
            String data = "(" + key + "," + value + ")";
            if (next != null) {
                return data + " => " + next.printNext();
            } else {
                return data;
            }
        }
    }
}

