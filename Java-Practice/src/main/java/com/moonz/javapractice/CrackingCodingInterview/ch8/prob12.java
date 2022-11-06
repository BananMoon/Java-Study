package com.moonz.javapractice.CrackingCodingInterview.ch8;

import java.util.ArrayList;
import java.util.List;

/*
Q. 여덟개의 퀸
8개의 퀸. 8x8 체스판 위에 퀸 8개가 서로 잡아먹히지 않는 모든 가능한 방법을 구하라.
퀸은 같은 행,열,대각선에 놓으면 안된다.

- 문제 이해
8x8에 8개 퀸을 놓는 것이므로, 각 행에 1개의 퀸은 무조건 놓는다.
그러므로, 열을 반복문 돌면서 행은 +1씩 늘려주면 된다. (퀸을 놓는 순서는 상관없으니 고려하지 않아도 된다)
r=0) c=0: (0,0) 놓고, r=1로 재귀호출 ->

r=1) c=0: (1,0) valid X : columns[0] 열 때문
     c=1: (1,1) valid X : columns[0] 대각선 때문
     c=2: (1,2) 놓고, r=2로 재귀 호출 -> ...
     돌아오면
     c=3: (1,3) ...

r=2) c=0: (2,0) valid X : columns[0] 열 때문
     c=1: (2,1) valid X : columns[1] 대각선 때문
     c=2: (2,2) valid X : columns[1] 열 때문
     c=3: (2,3) valid X : columns[1] 대각선 때문
     c=4: (2,4) 놓고, r=3로 재귀 호출 -> ...
     돌아오면
     c=5: (2,5) ...

...
r=8) 8개 퀸 모두 놓은 경우니까 result에 add.
(다시 호출한 부분으로 돌아오면 이어서 c를 증가하며 다른 경우를 구한다.)

- 주요 필드?
1) 2차원 배열 보드판 대신 index를 행, 값을 열(col)로 갖는 1차원 배열 columns
즉, 값이 0 이상이면 퀸이 놓여진 것 (초기값은 -1로 세팅하고, 해당 행 인덱스에 값을 넣으면 퀸이 놓여진 것으로 간주.)
예) columns[7] = 3 : 7행 3열에 퀸을 놓은 것

2) 8개의 퀸이 두어지는 모든 경우의 columns 들를 원소로 갖는 리스트 result

- 문제 풀이 과정
1) 열 (col)을 0부터 7까지 증가하면서..   # 열을 두는 순서는 상관없으므로 순차 접근할 수 있다.
유효하다면? (row,col)(초기 row=0)에 넣고, 다음 행으로 증가(row+=1)하여 재귀 호출한다. => 계속 퀸을 놓고 재귀적으로 호출하면 8행까지 가서 종료한다.
유효하지 않다면? 다음 행으로 넘긴다.

2) 유효성 체크 로직
행이 겹칠 일은 없도록 하므로 해당 행의 열 값과 대각선만 valid 체크하여 퀸을 둔다. (이때 행을 0부터 현재 행 전까지 돌면서 확인한다.)

 */
public class prob12 {
    static final int GRID_SIZE = 8;
    public void placeQueen(int row, Integer[] columns, List<Integer[]> result) {
        // row가 길이 8까지 왔으면 마지막 행까지 둔 것이므로
        if (row == GRID_SIZE) {
            result.add(columns.clone());
        }
        else {
            for (int c = 0; c < GRID_SIZE; c++) {
                if (isValidPosition(columns, row, c)) {
                    columns[row] = c;   // (row, c)에 퀸 놓는다.
                    placeQueen(row+1, columns, result);
                }
            }
        }
    }
    /* (row, column)에 놓기 좋은 자리인가?
    * 행은 항상 +1하여 재귀 호출하므로, 두고자 하는 행에서 이미 같은 열을 사용하고 있는 지 + 대각선에 있는지 체크해야 한다.
    */
    public boolean isValidPosition(Integer[] columns, int placedRow, int placedCol) {
        for (int r=0; r < placedRow; r++) { // 현재 row는 0부터 증가하면서 넣고 있기 때문에 그 앞 row들만 보면 된다.
            int iterCol = columns[r];
            // 열 체크
            if (iterCol == placedCol) {
                return false;
            }

            // 대각선 체크 (행은 0부터 1씩 증가 for문 돌기 때문에 절댓값 필요 없다.)
            int diffCol = Math.abs(placedCol - iterCol);
            int diffRow = placedRow - r;
            if (diffRow == diffCol) {
                return false;
            }
        }
        return true;
    }
    public static void main(String[] args) {
        prob12 t = new prob12();
        Integer[] columns = new Integer[GRID_SIZE];
        List<Integer[]> result = new ArrayList<>();

        t.clear(columns);   /* columns를 모두 -1로 init */
        t.placeQueen(0, columns, result);

        System.out.println("총 가능한 경우의 수: "+ result.size());
        for (Integer[] integers : result) {
            for (int r=0; r<GRID_SIZE; r++) {
                System.out.print("("+ r + ", " + integers[r]+") ");
            }
            System.out.println();
        }
    }
    public  void clear(Integer[] columns) {
        for (int i = 0; i < GRID_SIZE; i++) {
            columns[i] = -1;
        }
    }
}
