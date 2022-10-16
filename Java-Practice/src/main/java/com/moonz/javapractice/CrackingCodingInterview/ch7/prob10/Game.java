package com.moonz.javapractice.CrackingCodingInterview.ch7.prob10;

import java.util.Scanner;

public class Game {
    private GameStatus gameStatus;
    private Board board;

    private void init() {
        if (board == null) {
            board = new Board(7, 7, 3);
        }
    }
    // 사용자가 start() 호출하면서 게임 시작됨.
    private boolean start() {
        if (board == null) {
            init();
        }
        return playGame();
    }
    private boolean playGame() {
        Scanner sc = new Scanner(System.in);
        while (gameStatus == GameStatus.RUNNING) {
            String input = sc.nextLine();
            UserPlay userPlay = UserPlay.fromInput(input);
            if (userPlay == null) {
                continue;
            }
            UserPlay.UserPlayResult userPlayResult = board.playOne(userPlay);
            if (!userPlayResult.isSuccess()) {
                System.out.println("(" + userPlay.getRow() + ", " + userPlay.getColumn() + ")은 선택 불가능한 Cell입니다.");
                continue;
            }
            gameStatus = userPlayResult.getGameResultStatus();
        }
        sc.close();
        return true;
    }

    public enum GameStatus {
        WON,
        LOST,
        RUNNING,
        ;
    }
}
