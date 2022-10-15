package com.moonz.javapractice.CrackingCodingInterview.ch2;

// 2.3 중간 노드 삭제
public class DeleteMidNode {

    public static void main(String[] args) {
        // 2 1 4 2
        OnewayLinkedListNode tail = OnewayLinkedListNode.builder().data(2).next(null).build();
        OnewayLinkedListNode third = OnewayLinkedListNode.builder().data(4).next(tail).build();
        OnewayLinkedListNode sec = OnewayLinkedListNode.builder().data(1).next(third).build();
        OnewayLinkedListNode header = OnewayLinkedListNode.builder().data(2).next(sec).build();
    // 1을 지우려함. -> 2 4 2
        deleteMidNode(header);
        System.out.println(header.data);
        System.out.println(header.next.data);
        System.out.println(header.next.next.data);
    }

    static void deleteMidNode(OnewayLinkedListNode node) {
        if (node == null) return;
        if (node.next.next == null) return;

        OnewayLinkedListNode removedNode = node.next;   // 1
//        node.data = removedNode.data;	// header에 1을 덮은다??
        System.out.println(node.data);
        node.next = removedNode.next;   // ㅇㅈ

    }
}
