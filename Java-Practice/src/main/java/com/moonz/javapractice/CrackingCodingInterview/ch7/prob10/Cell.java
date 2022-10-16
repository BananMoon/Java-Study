package com.moonz.javapractice.CrackingCodingInterview.ch7.prob10;

import lombok.Getter;

@Getter
public class Cell {
    private int row;
    private int col;
    private boolean isBomb; // 지뢰여부
    private int number; // 인접 지뢰 갯수
    private boolean isMarked = false;  // 마킹 여부
    private boolean isExposed=  false;  // 노출 여부

    public Cell (int r, int c) {
        row = r;
        col = c;
    }

    public void flip() {
        isExposed = true;
    }
    public void mark() {
        flip();
        isMarked = true;
    }

    public void setRowCol(int changedRow, int changedCol) {
        row = changedRow;
        col = changedCol;
    }
    public void setBomb(boolean isBomb) {
        this.isBomb = isBomb;
    }

    public boolean isBlank() {
        return number == 0;
    }

    public void addNumber() {
        number++;
    }
}
