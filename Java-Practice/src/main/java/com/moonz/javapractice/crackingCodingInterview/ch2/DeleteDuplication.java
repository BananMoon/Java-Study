package com.moonz.javapractice.CrackingCodingInterview.ch2;

import java.util.HashSet;
import java.util.Set;
// 2.1 중복 없애기

// 2 1 4 5 5 1 2
// 1. 링크드 리스트를 정렬한 후에 양 옆 비교
// 2. HashSet에 저장해서 이미 포함되어있으면 제거 후 진행.
// 3. 버퍼없이 진행
public class DeleteDuplication {
    public static void main(String[] args) {
        /*
         단방향
         */
        // 2 1 4 2 => 2 1 4
        OnewayLinkedListNode tail1 = OnewayLinkedListNode.builder().data(2).next(null).build();
        OnewayLinkedListNode third1 = OnewayLinkedListNode.builder().data(4).next(tail1).build();
        OnewayLinkedListNode sec1 = OnewayLinkedListNode.builder().data(1).next(third1).build();
        OnewayLinkedListNode header1 = OnewayLinkedListNode.builder().data(2).next(sec1).build();
        OnewayLinkedListNode onewayLinkedListNode = deleteDuplication(header1);

        System.out.println(onewayLinkedListNode.data);
        System.out.println(onewayLinkedListNode.next.data);
        System.out.println(onewayLinkedListNode.next.next.data);

        /*
         양방향
         */
        LinkedListNode header = LinkedListNode.builder().data(2).previous(null).build();
        LinkedListNode sec = LinkedListNode.builder().data(1).previous(header).build();
        LinkedListNode third = LinkedListNode.builder().data(4).previous(sec).build();
        LinkedListNode tail = LinkedListNode.builder().data(2).previous(third).next(null).build();

        header.setNext(sec);
        sec.setNext(third);
        third.setNext(tail);
        deleteDupsWithoutBuffer(header);    // for문 N^2 시간 소요. 공간은 상수
//        deleteDups1(header);    // 자료구조를 이용하지만훨씬 빠름.
//        2,1,4가 남아야함.
        System.out.println(header.data);
        System.out.println(header.next.data);
        System.out.println(header.next.next.data);
    }
    static void deleteDups1(LinkedListNode node) {
        if (node == null) return;

        Set<LinkedListNode> set = new HashSet<>();
        LinkedListNode previous = null;
        while (node != null) {
            if (set.contains(node.data)) {
                previous.next = node.next;
            } else {
                set.add(node);
                previous = node;
            }
            node = node.next;
        }
    }
    // 두개의 포인터를 사용해서 비교를 하는 식으로 진행
    static void deleteDupsWithoutBuffer(LinkedListNode node) { // 왜 head라 칭하지?  처음 접근시 head를 가리키기 때문.
        if (node == null) return;

        LinkedListNode current = node;
        System.out.println("current출력:"+current.data);  // 무조건 헤드를 가리킴.
        while (current != null) {
            System.out.println("while문실행");
            LinkedListNode runner = current;
            while (runner.next != null) {
                if (runner.next.data == current.data) {
                    runner.next = runner.next.next;
                } else {
                    runner = runner.next;
                }
            }
            current = current.next;
        }
    }
    static OnewayLinkedListNode deleteDuplication (OnewayLinkedListNode node) {
        OnewayLinkedListNode first = node;

        while(first != null) {
            OnewayLinkedListNode second = first;
            while (second.next != null) {
                if (first.data == second.next.data) {
                    second.next = second.next.next;
                } else {
                    second = second.next;
                }
            }  // end while
            first = first.next;
        }  // end while
        return node;
    }
}