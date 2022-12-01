package com.moonz.javapractice.CrackingCodingInterview.ch10.prob2;

import java.util.Arrays;
import java.util.Comparator;

public class AnagramComparator implements Comparator<String> {
    public int compare (String s1, String s2) {
        // 각각 정렬한 문자열이 같으면 Anagram
        return sortChars(s1).compareTo(sortChars(s2));
    }
    private String sortChars(String s) {
        char[] chars = s.toCharArray();
        Arrays.sort(chars);
        return String.copyValueOf(chars);
    }
}