package com.moonz.javapractice.CrackingCodingInterview.ch8;
import java.util.*;
/*
Q. 중복 있는 순열
문자는 중복될 수 있지만, 나열된 순열은 서로 중복되서는 안된다.

이해 필요!!
 */
public class prob8 {
    public static void main(String[] args) {
        printPerms("abc");
    }
    public static void printPerms(String str) {
        List<String> answers = new ArrayList<>();
        Map<String, Integer> strCnt = buildFreqTable(str);
        getPermutation(answers, "", str, strCnt);
        for (String answer : answers) {
            System.out.println(answer);
        }
    }
    private static void getPermutation(List<String> answers, String curr, String input, Map<String,Integer> map) {
        if (curr.length() == 0) {
            answers.add(curr);
            return;
        }

        for (String key : map.keySet()) {
            int value = map.get(key);
            if (value <= 0) {
                continue;
            }
            map.put(key, value-1);
            getPermutation(answers, curr+key, input, map);
            map.put(key, value);
        }
    }

    public static Map<String, Integer> buildFreqTable (String str) {
        Map<String, Integer> map = new HashMap<>();
        for(int i=0; i<str.length(); i++) {
            String currStr = str.substring(i, i+1);
            map.put(currStr, map.getOrDefault(currStr, 0)+1);
        }
        return map;
    }
}
