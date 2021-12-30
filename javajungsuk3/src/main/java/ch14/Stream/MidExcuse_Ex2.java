package ch14.Stream;

import java.util.Comparator;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class MidExcuse_Ex2 {
    public static void main(String[] args) {
        // 1. 스트림의 요소 걸러내기
        // 1) 중복 제거
        IntStream intStream = IntStream.of(1, 2, 2, 3, 3, 3, 4, 5, 5, 6);
        intStream.distinct().forEach(System.out::print);    // 123456
        System.out.println();

        // 2) 주어진 조건에 맞지않는 요소 제거
        IntStream intStream1 = IntStream.rangeClosed(1, 10);
        intStream1.filter(i -> i%2 == 0).forEach(System.out::print);    //246810
        System.out.println("\n                                                                                                                                                                                                                                                                                                                       ================END : 스트림의 요소 걸러내기");
        // 2. 정렬 : 대-소문자&알파벳순
        Stream<String> strStream = Stream.of("hi", "bye", "goodmorning");
        strStream.sorted(Comparator.reverseOrder()).forEach(System.out::print);
    }
}
