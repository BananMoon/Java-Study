package com.moonz.javapractice.CrackingCodingInterview.ch2;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.Setter;
// 양방향
@Builder
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LinkedListNode {
    int data;
    LinkedListNode previous;    // 양방향
    LinkedListNode next;

}