package com.moonz.javapractice.CrackingCodingInterview.ch7.prob1;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
// Card를 상속받는 아무 클래스나
// 플레이어가 사용하는 카드 뭉치 Deck
public class Deck <T extends Card> {        // TODO 클래스 + 와일드카드(<>) 의미 알아보기

    private List<T> cards;   // 모든 카드.
    private int dealtIndex = 0;     // 처리되지 않은(dealt) 첫번째 인덱스

    public void setDeckOfCards (ArrayList<T> deckOfCards) {
        this.cards = deckOfCards;
    }
    // 5장=> 0~4 반복. (0,4), (1,3), (2,2), (3,1),(4,0)
    // random(5)+0, random(3)+1, random(1)+2, random(-1)+1, random(-3)+4
    // Math.random(): 0~1사이 * 5,
    // 0.9*(-1) = -0.9+1= 0.1
    // -2.7+4= 1.3
    //
    public void shuffle() {
        Random random = new Random();
        int min = 1;
        int max = cards.size() - 1;
        for (int i=0; i<cards.size(); i++) {
            int swappedIdx = random.nextInt(cards.size() - 1) + 1;
            System.out.println(swappedIdx);
//            int swappedIdx = (int) (Math.random() * (max - min + 1)) + min;   // (int)(랜덤 * (최대-최소+1)) +최소
//            int swappedIdx = (int)(random.nextFloat() * cards.size() * 0.1);    // 랜덤 값을 뽑되, 카드 사이즈로 제한해야함. 0.22 -> 9.9 * 5 = 4.95
            T card1 = cards.get(swappedIdx);
            T card2 = cards.get(i);
            cards.set(swappedIdx, card2);
            cards.set(i, card1);
        }

    }
    public int remainingCardSize() {
        return this.cards.size() - dealtIndex;  // 처리되지 않은 첫 인덱스로 --해야 남은 카드 수
    }

    // number 갯수 만큼 카드 뽑는다.
    public T[] dealHand(int number) {
        // size 확인
        if (remainingCardSize() < number) return null;
        T[] hand = (T[]) new Card[number];
        for (int i=0; i<number; i++) {
//            T card = dealCard();
//            hand[i] = card;
        }
        return hand;
    }

    // 카드 한장 뽑기
    public T takeOneCard() {
        if (remainingCardSize() == 0) return null;
        T card = cards.get(dealtIndex);
        card.markUnavailable();
        dealtIndex+=1;
        return card;
    }
}
