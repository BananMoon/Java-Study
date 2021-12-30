package ch14.Stream;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
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
        IntStream intStream1 = IntStream.of(1, 2, 3);
        IntStream intStream2 = IntStream.of(new int[]{4, 5, 6});
        IntStream intStream3 = Arrays.stream(new int[]{7, 8, 9});
        IntStream intStream4 = Arrays.stream(new int[]{10, 11, 12, 13, 14}, 0, 3);


        // 3. 특정 범위의 정수
        IntStream rangeIntStream = IntStream.range(0, 10);      // end가 포함되지 않음 : 0~9
        LongStream rangeClosedLongStream = LongStream.rangeClosed(0, 10);   // end 포함 : 0~10
        System.out.println("======================END : 특정 범위의 수======================");

        // 4. 임의의 수 (각 타입의 난수들로 이루어진 스트림 반환.  무한 스트림이므로 limit() 이용해서 제한해줘야 한다.
        IntStream randomIntStream = new Random().ints(0,10);
        IntStream random5IntStream = new Random().ints(5);  // 아래와 같은 크기
        randomIntStream.limit(5).forEach(System.out::println);  // 0~9 사이 난소들 중 5개 출력 (9,4,3,7,0)

        LongStream randomLongStream = new Random().longs();
        DoubleStream randomDoubleStream = new Random().doubles(0.0, 1.3);   // 기본은 0.0<= doubles() < 1.0
        System.out.println("======================END : random number======================");

        // 5. 람다식 - iterate(), generate()
        Stream<Integer> evenStream = Stream.iterate(0, n -> n + 2);

    }
}
