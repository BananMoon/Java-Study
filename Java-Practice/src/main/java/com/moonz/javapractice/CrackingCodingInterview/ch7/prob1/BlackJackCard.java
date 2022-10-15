package com.moonz.javapractice.CrackingCodingInterview.ch7.prob1;

public class BlackJackCard extends Card {
    public BlackJackCard(int c, Suit s) {
        super(c, s);
    }
    // card 값 반환 메서드
    @Override
    public int value() {
        if (isAce()) return 1;  // 평소에는 1로 처리.
        else if (faceValue >= 11 && faceValue <=13) return 10;
        else return faceValue;  // 숫자(2~10)
    }

    // 원래 카드는 1이 ACE인데, 블랙잭에서만 1,11로 대체 가능한 것.
    public boolean isAce() {
        return faceValue == 1;
    }

    public int minValue() {
        // Ace있으면 1 리턴
        if (isAce()) return 1;
        else return value();
    }
    public int maxValue() {
        // Ace있으면 11 리턴
        if (isAce()) return 11;
        else return value();
    }

    public boolean isFaceCard() {
        return faceValue >=11 && faceValue <=13;    // BlackJackCard.faceValue로 접근
    }
}
