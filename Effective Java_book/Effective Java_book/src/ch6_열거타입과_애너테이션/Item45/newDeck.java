package ch6_열거타입과_애너테이션.Item45;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;
import static java.util.stream.Collectors.toList;

final class Card<Suit, Rank> {
    enum Suit {HEART, CLOVER, DIAMOND};     // 모양(3개)
    enum Rank {ZERO, ONE, TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE};     // 숫자(10개)

    public Card(Suit suit, Rank rank) {
    }

}

// Card : Rank와 Suit를 묶은 불변 값 클래스
// Rank와 Suit : 열거 타입
public class newDeck {
    // for-each 반복문
    private static ArrayList<Card> newDeck_for() {
        ArrayList<Card> result = new ArrayList<>();
        for (Card.Suit suit : Card.Suit.values()) {
            for (Card.Rank rank : Card.Rank.values())
                result.add(new Card(suit, rank));
        }
        return result;
    }

    // stream
    private static List<Card> newDeck_stream() {
        return Stream.of(Card.Suit.values())
                .flatMap(
                        suit ->  Stream.of(Card.Rank.values())
                                .map(rank -> new Card(suit, rank))      // Stream<Card[]> -> Stream<Card>
                )
                .collect(toList());         // Stream<T> => List<T>
    }

    public static void main(String[] args) {
        int i=0;
        ArrayList<Card> resultCard =newDeck.newDeck_for();

        for (Card card : resultCard) {
            i++;
            System.out.println(i + ". " + card);   //  String 출력 어떻게 하지?
        }
    }
}
