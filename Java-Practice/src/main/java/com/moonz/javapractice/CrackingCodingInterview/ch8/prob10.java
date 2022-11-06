package com.moonz.javapractice.CrackingCodingInterview.ch8;

import java.util.Random;

/*
선택한 영역 색 칠하기 문제.
Q. 새로운 색상이 주어졌을 때, 주어진 지점과 색이 같은 주변 영역을 새 색상으로 다시 색칠한다.
- input
화면 (색이 칠해진 이차원 배열)
화면 상의 칠하고자 하는 지점

- 풀이
10*10 칸의 보드판, 칠하고 싶은 색과 칠하고 싶은 (r,c)을 받는다.
다시 내부 helper를 호출하는데, 이때 현재 선택된 보드판의 색도 함께 전달한다.
base case) 영역을 벗어나면 중단.
if) 현재 색과 칠하고자하는 색이 같으면 중단한다. (이미 칠해져있으므로)
else) 자신의 색을 바꾸고, 인접한 네곳을 재귀로 호출한다.
*/
public class prob10 {
    static final int N = 10;
    static final Random random = new Random();

    public static void main (String[] args) {
        // 칸 만들기
        Color[][] board = new Color[N][N];
        // black으로 init
        for (int i=0; i<N; i++) {
            for (int j=0; j<N; j++) {
                board[i][j] = Color.Black;
            }
        }
        // 100번 random으로 초록
        for (int i=0; i<100; i++) {
            board[randomInt()][randomInt()] = Color.Green;
        }
        System.out.println("영역 색칠 전");
        printBoard(board);
        /* 호출하여 특정 color로 영역 채우기 */
        paintColor(board, randomInt(), randomInt(), Color.White);
        System.out.println("영역 색칠 후");
        // 색칠 보여주기
        printBoard(board);
    }

    private static int randomInt() {
        return random.nextInt(N);
    }

    public static void paintColor (Color[][] board, int r, int c, Color filledColor) {
        // 이미 선택한 영역이 해당 색으로 채워져있다면.
        // 주위 같은 색도 이미 칠해져있을 테니
        if (board[r][c] == filledColor) {
            return;
        }
        paintBoardWithColor (board, r, c, board[r][c], filledColor);
    }
    /* 인접 블록이 기존 색(originalColor)과 같은 인접 블록이면 확장 가능 */
    public static void paintBoardWithColor (Color[][] board, int r, int c, Color originalColor, Color wantColor) {
        if (r < 0 || r >= board.length || c < 0 || c >= board[0].length) {
            return;
        }
        if (board[r][c] == originalColor) {
            board[r][c] = wantColor;
            paintBoardWithColor(board, r - 1, c, originalColor, wantColor);
            paintBoardWithColor(board, r, c - 1, originalColor, wantColor);
            paintBoardWithColor(board, r + 1, c, originalColor, wantColor);
            paintBoardWithColor(board, r, c + 1, originalColor, wantColor);

        }
    }

    public static void printBoard(Color[][] board) {
        for (Color[] boards : board) {
            for (Color b : boards) {
                System.out.print(b.name());
            }
            System.out.println();
        }
    }

    enum Color {
        Black,
        White,
        Red,
        Yellow,
        Green,
        ;
    }
}