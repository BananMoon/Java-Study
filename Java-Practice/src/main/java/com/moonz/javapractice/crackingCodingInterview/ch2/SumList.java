package com.moonz.javapractice.crackingCodingInterview.ch2;

// 2.5 리스트의 합
public class SumList {
    OnewayLinkedListNode sumList (OnewayLinkedListNode n1, OnewayLinkedListNode n2, int moveNumber) {
        if (n1 == null && n2 == null) return null;
        OnewayLinkedListNode aNode = new OnewayLinkedListNode();

        if (n1 != null) {
            moveNumber += n1.data;
        }
        if (n2 != null) {
            moveNumber += n2.data;
        }

        aNode.data = moveNumber%10;

        OnewayLinkedListNode nextNode = sumList(n1 == null? null : n1.next,
                n2 == null? null : n2.next,
                moveNumber/10
        );
        aNode.setNext(nextNode);
        return aNode;
    }

    public static void main(String[] args) {
        // 1-2-4-2   4-3-1 ==
        OnewayLinkedListNode tail = OnewayLinkedListNode.builder().data(2).next(null).build();
        OnewayLinkedListNode third = OnewayLinkedListNode.builder().data(4).next(tail).build();
        OnewayLinkedListNode sec = OnewayLinkedListNode.builder().data(8).next(third).build();
        OnewayLinkedListNode header = OnewayLinkedListNode.builder().data(1).next(sec).build();
        // 1을 지우려함. -> 2 4 2

        OnewayLinkedListNode third2 = OnewayLinkedListNode.builder().data(1).next(null).build();
        OnewayLinkedListNode sec2 = OnewayLinkedListNode.builder().data(3).next(third2).build();
        OnewayLinkedListNode header2 = OnewayLinkedListNode.builder().data(9).next(sec2).build();


        SumList t = new SumList();
        OnewayLinkedListNode onewayLinkedListNode = t.sumList(header, header2, 0);
        System.out.println(onewayLinkedListNode.data);
        System.out.println(onewayLinkedListNode.next.data);
        System.out.println(onewayLinkedListNode.next.next.data);
        System.out.println(onewayLinkedListNode.next.next.next.data);
    }
}
