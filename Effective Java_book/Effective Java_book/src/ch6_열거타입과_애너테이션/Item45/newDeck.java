package ch6_열거타입과_애너테이션.Item45;

import java.util.ArrayList;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

// Card : Rank와 Suit를 묶은 불변 값 클래스
// Rank와 Suit : 열거 타입
public class newDeck {
    // for-each 반복문
    private static List<Card> newDeck_for() {
        List<Card> result = new ArrayList<Card>();
        for (Suit suit : Suit.values()) {
            for (Rank rank : Rank.values())
                result.add(new Card(suit, rank));
            return result;
        }
    }

    // stream
    private static List<Card> newDeck_stream() {
        return Stream.of(Suit.values())
                .flatMap(suit ->                                        // flatMap : 평탄화(flattening). 스트림의 원소 각각을 하나의 스트림으로 매핑,  그 스트림들을 다시 하나의 스트림으로 합침
                        Stream.of(Rank.values()))
                .map(rank -> new Card(suit, rank))
                .collect(toList());
    }
