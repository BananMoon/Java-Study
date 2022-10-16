package com.moonz.javapractice.CrackingCodingInterview.ch7.prob10;

import lombok.AllArgsConstructor;
import lombok.Getter;
@AllArgsConstructor
@Getter
public class UserPlay {
    private int row;
    private int column;
    private boolean isMarkingPlay;  // 마크 클릭인지 여부

    /**
     * 해당 셀 마킹 표시
     */
    void markCell(Cell cell) {
        cell.mark();
    }

    /**
     * 사용자 입력값을 UserPlay로 변환
     * ex) 1,2 -> 클릭 1열 2행
     */
    public static UserPlay fromInput (String input) {
        if (input.length() <= 0) {
            return null;
        }
        String[] split = input.split(",");
        int row = Integer.parseInt(split[0]);
        int col = Integer.parseInt(split[1]);
        return new UserPlay(row, col, false);
    }

    @Getter
    public static class UserPlayResult {
        private final boolean success;
        private final Game.GameStatus gameResultStatus;

        public UserPlayResult (boolean success, Game.GameStatus gameResultStatus) {
            this.success = success;
            this.gameResultStatus = gameResultStatus;
        }
    }
}
