package com.moonz.javapractice.CrackingCodingInterview.ch7.prob10;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

public class Board {
    private final int rowLen;
    private final int colLen;
    private Cell[][] cells;
    private Cell[] bombs;   // 지뢰 정보를 갖고 있을 배열
    private final int bombCnt;
    private int unexposedNonBombCnt;
    private static final int[][] ADJ_DIRECTIONS = {
            {-1, -1}, {-1, 0}, {-1, 1},
            {0, -1}, {0, 1},
            {1, -1}, {1, 0}, {1, 1}
    };

    public Board(int r, int c, int b) {
        rowLen = r;
        colLen = c;
        bombCnt = b;
        unexposedNonBombCnt = r * c - bombCnt;
        initBoard();
        shuffleBoard();
        numberingCells();
    }

    /**
     * Board 초기화
     */
    public void initBoard() {
        cells = new Cell[rowLen][colLen];
        bombs = new Cell[bombCnt];
        // 셀 init
        for (int r = 0; r < rowLen; r++) {
            for (int c = 0; c < colLen; c++) {
                cells[r][c] = new Cell(r, c);
            }
        }
        // 지뢰 init : 랜덤 섞기 전, [0번째 열, 값을 계산한 행]에 지뢰 두기
        for (int i = 0; i < bombCnt; i++) {
            int r = i / colLen;
            int c = (i - r * colLen) % colLen;
            bombs[i] = cells[r][c];
            bombs[i].setBomb(true);
        }
    }

    /**
     * 지뢰 랜덤 섞기
     * - 기존 방식이 이해되지 않아서, Random 클래스 이용해서 진행..
     */
    public void shuffleBoard() {
        int cellCnt = rowLen * colLen;
        Random random = new Random();
        for (int i = 0; i < cellCnt; i++) {
            int row1 = random.nextInt(rowLen);
            int col1 = random.nextInt(colLen);

            int row2 = random.nextInt(rowLen);
            int col2 = random.nextInt(colLen);

            if (row1 != row2 || col1 != col2) {
                Cell cell1 = cells[row1][col1];
                Cell cell2 = cells[row2][col2];
                cells[row1][col1] = cell2;
                cells[row2][col2] = cell1;
                cell1.setRowCol(row2, col2);
                cell2.setRowCol(row1, col1);
                System.out.println("cell2의 (r,c): (" + row2 + ", " + col2 + ")");
                System.out.println("cell1의 (r,c): (" + row1 + ", " + col1 + ")");
            }
        }
    }

    /**
     * 인접 지뢰 수만큼 넘버링
     */
    public void numberingCells() {
        // 지뢰 기준으로 순환
        for (Cell curr : bombs) {
            for (int[] adjDirection : ADJ_DIRECTIONS) {
                int adjR = curr.getRow() + adjDirection[0];
                int adjC = curr.getCol() + adjDirection[1];
                if (outOfBounds(adjR, adjC)) continue;
                cells[adjR][adjC].addNumber();
            }
        }
    }

    /**
     * 게임 한 판 진행
     * - 셀 뒤집거나 마킹표시하거나 확장하거나
     */
    public UserPlay.UserPlayResult playOne(UserPlay userPlay) {
        Cell selectedCell = makeCellFrom(userPlay);
        if (selectedCell == null) {
            return new UserPlay.UserPlayResult(false, Game.GameStatus.RUNNING);
        }
        // already exposed
        if (selectedCell.isExposed()) {
            return new UserPlay.UserPlayResult(false, Game.GameStatus.RUNNING);
        }
        // marking
        if (userPlay.isMarkingPlay()) {
            boolean isAlreadyMarked = selectedCell.isMarked();
            if (isAlreadyMarked) {
                return new UserPlay.UserPlayResult(false, Game.GameStatus.RUNNING);
            } else {
                userPlay.markCell(selectedCell);
                return new UserPlay.UserPlayResult(true, Game.GameStatus.RUNNING);
            }
        }
        // flip
        if (selectedCell.isBomb()) {
            return new UserPlay.UserPlayResult(true, Game.GameStatus.LOST);
        }

        // flip Cell
        boolean flipResult = flipCell(selectedCell);

        if (selectedCell.isBlank()) {
            expandExpose(selectedCell);
        }
        if (unexposedNonBombCnt == 0) {
            return new UserPlay.UserPlayResult(true, Game.GameStatus.WON);
        }

        return new UserPlay.UserPlayResult(flipResult, Game.GameStatus.RUNNING);
    }

    /*
    * 셀 뒤집기
    * 노출이 안됐고, 마킹표시 안돼있어야 뒤집기 가능
    */
    private boolean flipCell(Cell cell) {
        if (!cell.isExposed() && !cell.isMarked()) {
            cell.flip();
            unexposedNonBombCnt--;
            return true;
        }
        return false;
    }
    /**
     * 빈 cell인 경우 확장 (bfs 이용)
     * number가 0인 경우 queue에 다시 넣고 뒤집는다.
     * number가 0 이상이거나 bomb인 경우 넣지 않고 뒤집지 않는다.
    */
    private void expandExpose(Cell cell) {
        Queue<Cell> q = new LinkedList<>();
        q.add(cell);
        cell.flip();
        while (!q.isEmpty()) {
            Cell curr = q.poll();
            for (int[] adjDir : ADJ_DIRECTIONS) {
                int adjR = curr.getRow() + adjDir[0];
                int adjC = curr.getCol() + adjDir[1];
                if (outOfBounds(adjR, adjC)) continue;
                Cell adjCell = cells[adjR][adjC];
                if (adjCell.isBlank() && flipCell(adjCell)) {
                    q.add(adjCell);
                }
            }
        }
    }

    private Cell makeCellFrom(UserPlay userPlay) {
        if (outOfBounds(userPlay.getRow(), userPlay.getColumn())) {
            return null;
        }
        return cells[userPlay.getRow()][userPlay.getColumn()];
    }

    private boolean outOfBounds(int row, int column) {
        return row > rowLen || row < 0 || column > colLen || column < 0;
    }
}
