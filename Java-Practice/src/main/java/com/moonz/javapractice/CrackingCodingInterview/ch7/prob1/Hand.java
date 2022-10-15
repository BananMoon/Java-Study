package com.moonz.javapractice.CrackingCodingInterview.ch7.prob1;

import java.util.ArrayList;
// 카드를 뽑거나 점수 계산하는 손 클래스
public class Hand<T extends Card> {
    protected ArrayList<T> cards = new ArrayList<>();   // 카드 뭉치

    public int score() {
        int score = 0;
        for (T card : cards) {
            score += card.value();
        }
        return score;
    }

    public void addCard (T card) {
        cards.add(card);
    }

}
