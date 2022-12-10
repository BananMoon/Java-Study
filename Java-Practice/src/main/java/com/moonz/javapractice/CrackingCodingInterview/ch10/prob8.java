package com.moonz.javapractice.CrackingCodingInterview.ch10;

import java.util.BitSet;

/**
 * 중복 찾기
 */
public class prob8 {
    void findOverlap(int[] arr) {
        // 1. 크기 32000인 비트 벡터 생성
        BitSet bitSet = new BitSet(32000);
        // 2. arr 순회하며 이미 비트값이 1인 경우, 출력!
        for (int numOfArr : arr) {
            int idxOfBitSet = numOfArr - 1;
            if (bitSet.get(idxOfBitSet)) {
                System.out.println("중복된 값: " + numOfArr);
            } else {
                bitSet.set(idxOfBitSet, true);
            }
        }
    }

    public static void main(String[] args) {
        // 1부터 20까지의 배열. 중복 가능. N=20
        int[] arr = {1,1,5,9,10,2,5,10,20,24};      // 1, 5, 10
        prob8 test = new prob8();
        test.findOverlap(arr);
    }
}
