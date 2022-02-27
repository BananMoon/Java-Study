package ch7_람다와_스트림.Item42;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class AnonymousClass_Ex1 {

    public static void main(String[] args) {
        ArrayList<String> words = new ArrayList<>();
        words.add("apple");
        words.add("watermelon");
        words.add("banana");

        Collections.sort(words, new Comparator<String>() {   // Comparator 인터페이스에는 compare()라는 추상메서드가 존재하므로 구현해야한다.
            @Override
            public int compare(String s1, String s2) {
                int compare = Integer.compare(s1.length(), s2.length());// 문자열의 길이 비교 : >는 1, <는 -1
                System.out.println(s1+ "과 " + s2  + " 비교 : " +compare);
                return compare;
            }
        });
        // 인터페이스의 인스턴스를 람다식으로 변환 : new 생성자 제거, 타입 제거, return 키워드 제거, 메서드명 제거 등등..
//        Collections.sort(words, (s1, s2) -> Integer.compare(s1.length(), s2.length()));
    }
}
/*
watermelon과 apple 비교 : 1
banana과 watermelon 비교 : -1
banana과 watermelon 비교 : -1
banana과 apple 비교 : 1
 */
