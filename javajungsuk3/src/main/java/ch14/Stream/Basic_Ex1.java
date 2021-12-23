package ch14.Stream;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Basic_Ex1 {
    public static void main(String[] args) {
        // 1. 컬렉션
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5);
        Stream<Integer> intStream = list.stream();  // 오토박싱/오토언박싱으로 인한 비효율을 줄이고자?
        intStream.forEach(System.out::println);

        /*
        * 2. 배열 : Stream과 Arrays 둘다에 static 메서드로 정의되어 있음
        * Stream<T> stream = Stream.of(T... values)     // 가변인자
        * Stream<T> stream = Stream.of(T[])
        * Stream<T> stream = Arrays.stream(T[])
        * Stream<T> stream = Arrays.stream(T[] array, int startInclusive, int endExclusive) : index start부터 end-1까지 배열
         */
        Stream<String> strStream = Stream.of("a", "b", "c");
        Stream<String> strStream2 = Stream.of(new String[]{"a", "b", "c"});
        Stream<String> strStream3 = Arrays.stream(new String[]{"a", "b", "c"});
        Stream<String> strStream4 = Arrays.stream(new String[]{"a", "b", "c"}, 0, 3);
        

    }
}
