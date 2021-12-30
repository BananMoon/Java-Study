package ch14.Stream;

import java.util.Random;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Lotto_Ex4 {
    public static void main(String[] args) {
        IntStream intStream = new Random().ints(1, 46);
        Stream<String> lottoStream = intStream.distinct().limit(6).sorted()
                .mapToObj(i -> i + ", ");

        lottoStream.forEach(System.out::print);
    }
}
