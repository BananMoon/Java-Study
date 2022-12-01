package com.moonz.javapractice.CrackingCodingInterview.ch10.prob2;

import java.util.ArrayList;
import java.util.Arrays;

/** Anagram 묶기 - Anagram 끼리 인접하도록 정렬
 방법 2가지
 - 비교 연산자를 이용한다. 이때 compare 메서드 구현은 두가지 가능하다.
 1. 각 단어를 정렬해서 같은지 여부로 아나그램인지 확인 (채택)
 2. 두 단어에서 동일 알파벳에 대해 등장 횟수가 같은지 여부로 아나그램인지 확인

 - 문자열을 완전 정렬하지 않고, 해시테이블을 이용해서 같은 아나그램 그룹들을 저장하고, 순차적으로 배열에 덮어씌운다.
 */
public class prob2 {
    public static void main(String[] args) {
        String[] arr = {"apple", "banana", "carrot", "ele", "duck", "papel", "tarroc", "cudk", "eel", "lee"};
        String[] arr2 = {"apple", "banana", "carrot", "ele", "duck", "papel", "tarroc", "cudk", "eel", "lee"};
        // 1. 비교 연산자 이용 방법
        Arrays.sort(arr, new AnagramComparator());
        printArr(arr);
        // 2. 해시테이블 이용 방법
        sort(arr2);
        printArr(arr2);
    }

    static void sort(String[] arr) {
        HashMapList<String, String> mapList = new HashMapList<>();

        /* 정렬된 key에 대응하는 anagram 그룹 value */
        for (String s : arr) {
            String key = sortChars(s);
            mapList.put(key, s);
        }
        /* HashMapList를 배열로 변환해서 arr에 덮어쓰기 */
        int idx = 0;
        for (String k : mapList.keySet()) {
            ArrayList<String> values = mapList.get(k);
            for (String v : values) {
                arr[idx] = v;
                idx++;
            }
        }
    }

    private static String sortChars(String str) {
        char[] chars = str.toCharArray();
        Arrays.sort(chars);
        return String.valueOf(chars);
    }

    private static void printArr(String[] arr) {
        for (String s : arr) {
            System.out.print(s+" ");
        }
        System.out.println();
    }
}

