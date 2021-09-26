package ch11;

import java.util.TreeSet;

//TreeSet은 1. 정렬 2. 범위검색 에 유리함
// 반면, 데이터 추가/삭제 시 속도 느림. (비교&정렬하면서 저장하므로)
// 문자열의 경우, 정렬순서는 공백 - 숫자 - 대문자 - 소문자 순.
public class TreeSetEx1 {
    public static void main(String[] args) {
        TreeSet set = new TreeSet();
        set.add("abc");
        set.add("alien");
        set.add("bat");
        set.add("car");
        set.add("Car");
        set.add("disc");
        set.add("dance");
        set.add("dZZZZ");
        set.add("dzzzz");
        set.add("elephant");
        set.add("elevator");
        set.add("fan");
        set.add("flower");

        String from = "a";
        String to = "d";
        System.out.println("a부터 d 전까지: "+ set.subSet(from, to));    //"a"부터 "d" 전까지
        System.out.println("a부터 d+zzz 전까지: "+ set.subSet(from, to+"zzz"));  //"a"부터 "dzzz" 전의 d~ 단어들까지 포함!

        TreeSet set2 = new TreeSet();
        int[] score = {80, 95, 50, 35, 45, 65, 10,100};
        for (int i=0; i<score.length; i++) {
            set2.add(new Integer(score[i]));
        }

        System.out.println("50점보다 작은 값: "+ set2.headSet(new Integer(50)));
        System.out.println("50점보다 같거나 큰 값: "+ set2.tailSet(new Integer(50)));
    }
}
