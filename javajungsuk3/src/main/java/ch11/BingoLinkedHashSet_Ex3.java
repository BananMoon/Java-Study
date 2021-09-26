package ch11;

import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;

public class BingoLinkedHashSet_Ex3 {
    public static void main(String[] args) {
        Set set = new HashSet();        // 자체적인 저장방식에 따라 순서가 결정되기 때문.
//        Set set = new LinkedHashSet();
        int[][] board = new int[5][5];

        for (int i=0; i<25; i++) {
            set.add((int)(Math.random()*50)+1+"");
        }

        Iterator it = set.iterator();

        for (int i=0; i<board.length; i++){
            for (int j=0; j< board.length; j++) {
                if (it.hasNext()) {
                    board[i][j] = Integer.parseInt((String) it.next());  //parseInt하려면 Object-> String이어야함
                    System.out.printf(" %2d", board[i][j]);
                }
            }
            System.out.println();
        }
    }
}