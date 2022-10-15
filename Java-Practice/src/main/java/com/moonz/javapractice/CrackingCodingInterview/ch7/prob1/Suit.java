package com.moonz.javapractice.CrackingCodingInterview.ch7.prob1;

public enum Suit {
    Club(0),
    Diamond(1),
    Heart(2),
    Spade(3)
    ;

    private Suit (int value) {
        this.value = value;
    }
    private int value;
    public int getValue() {
        return value;
    }
    public static Suit getSuitFromValue(int value) {
        for (Suit s : Suit.values()) {
            if (s.value == value)
                return s;
        }
        return null;    // 일치하는 value 값이 없는 경우
    }
}
