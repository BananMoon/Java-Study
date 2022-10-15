package com.moonz.javapractice.CrackingCodingInterview.ch7.prob1;

import java.util.ArrayList;

/*
블랙잭
- 딜러가 자신을 포함한 참가자 전원에게 카드 두 장을 나누어 줌.
- 딜러의 카드 한 장은 상대에게 보이지 않은 채로 둔다.
- 딜러는 16이하면 카드를 더 받아야하고, 17 이상이면 카드를 받지 않아야 한다.
- 플레이어는 자유롭게 선택한다. (20 이하면 카드를 더 받을 수 있지만 21을 초과하면 이를 버스트라 한다.)
- A부터 10,J,Q,K로 이루어진 4쌍의 카드 덱으로 진행한다.
- 이때 A는 1 또는 11로 사용할 수 있어서 상황에 맞게 유리하게 사용할 수 있다. 나머지 영어 카드 J(10),Q(11),K(12)는 10으로 사용된다.
- 처음 두장의 카드의 합이 21(A+10)인 경우, 블랙잭이라고 한다! 배팅 금액의 1.5배 얻는다.
- 카드의 합이 딜러보다 먼저 21이 되거나 딜러보다 21에 가깝게 되면 이기고, 카드를 더 받았는데 21을 초과하면 버스트(Bust)된다.
 */
public class BlackJackHand extends Hand<BlackJackCard> {
    /* 현재 가진 카드값들을 돌면서 21보다 작은 값 중 최댓값 혹은 21보다 큰 값 중 최소값을 반환한다.
    - score가 21보다 값이 크거나 maxUnder21이 21보다 크면,
    => 즉, 21 이하의 최댓값이 없으면 21을 넘는 최솟값을 보낸다.
    => 그런데, 21 이하의 최댓값이 있으면 21 이하의 최댓값을 보낸다.
    - 21이 넘지 않는 가장 최대값 or 21이 넘는 점수 중 가장 최소값 반환
     */
    public int score() {
        ArrayList<Integer> scores = calculatePossibleScores();   // 점수 리스트
        int maxUnder21 = Integer.MIN_VALUE;
        int minOver21 = Integer.MAX_VALUE;
        for (int score : scores) {
            if (score > 21 && 21 < minOver21) { // 21 초과이고, 21이상 최솟값이 21보다 크면, 정정.
                minOver21 = score;
            } else if (score <= 21 && 21 > maxUnder21) {    // 값이 21 이하이고 21이하 최댓값이 현재 21보다 작으면, 정정
                maxUnder21 = score;
            }
        }
        // 21 이하이고 최댓값이 21보다 작은 경우가 없었으면, 21이상 최솟값 반환..
        return maxUnder21 == Integer.MIN_VALUE ? minOver21 : maxUnder21;
    }

    /*
    - 모든 가능한 점수 리스트 반환
    - 에이스는 11로도, 1로도 사용 가능하니까 모든 점수로 될 수 있는 카드 값을 추가한다.
    - ex) ACE이면 1, 11 둘다 추가
     */
    private ArrayList<Integer> calculatePossibleScores() {
        ArrayList<Integer> scores = new ArrayList<>();
        if (cards.size() == 0) {
            return scores;
        }
        for (BlackJackCard card : cards) {
            addCardToScoreList(card, scores);
        }
        return scores;
    }

    // 위에서 빈 리스트 scores 전달해서 0 추가
    // ex) 카드번호가 3이면 scores={3}
    // ex) 카드번호가 1이면 scres={1,11}
    private void addCardToScoreList(BlackJackCard card, ArrayList<Integer> scores) {
        if (scores.size() == 0) {
            scores.add(0);
        }
        int len = scores.size();    // 1개
        for (int i=0; i<len; i++) {
            Integer score = scores.get(i);
            scores.set(i, score + card.minValue());    // minValue로 생성 => 0에서 해당 card값으로 set
            if (card.minValue() != card.maxValue()) {   // 블랙잭에서는, ACE인 경우 1, 11 모두 추가
                scores.add(score + card.maxValue());
            }
        }
    }

    public boolean isBusted() { return score() > 21; }  // 21 이하의 최댓값이 없으면 21을 넘는 최솟값을 보낸다.
    public boolean is21() { return score() == 21; }

    // A + 10 카드 조합이면 블랙잭!
    public boolean isBlackJack() {
        if (cards.size() != 2) return false;
        BlackJackCard first = cards.get(0);
        BlackJackCard second = cards.get(1);
        return (first.isAce() && second.isFaceCard()) || (first.isFaceCard() && second.isAce());
    }
}
