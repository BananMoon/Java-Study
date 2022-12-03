package com.moonz.javapractice.CrackingCodingInterview.ch10.prob4;

public class Listy {
    int[] arr;

    public Listy (int[] arr) {
        this.arr = arr.clone();
    }
    public int elementAt(int idx) {
        if (idx < 0 || arr.length <= idx) {
            return -1;
        }
        return arr[idx];
    }
}
