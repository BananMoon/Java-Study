package com.moonz.javapractice.CrackingCodingInterview.ch7.prob1;

public abstract class Card {
    private boolean available = true;   // isAvaliable()로 값을 리턴.
    // 카드 숫자
    protected int faceValue;    // 2~10:숫자, 11:잭, 12:퀸, 13:킹, 1:에이스
    protected Suit suit;

    public Card(int c, Suit s) {
        faceValue = c;
        suit = s;
    }

    public abstract int value();    // 카드의 값 리턴 메서드
    public Suit suit() { return suit; }

    public boolean isAvaiable() { return available; }    // 카드 돌릴 수 있는가?
    public void markUnavailable() { available = false; }
    public void markAvailable() { available = true; }
}
