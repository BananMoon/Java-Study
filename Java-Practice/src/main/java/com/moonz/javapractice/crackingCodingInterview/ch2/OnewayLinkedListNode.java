package com.moonz.javapractice.CrackingCodingInterview.ch2;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.Setter;
// 단방향
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Builder
public class OnewayLinkedListNode {
    int data;
    OnewayLinkedListNode next;
}