package com.gic.sudoku.ui;

import com.gic.sudoku.domain.Board;

/**
 * Handles board display operations.
 * Follows Single Responsibility Principle - only handles board visualization.
 */
public class BoardDisplay {

    /**
     * Prints the current board state to the console.
     */
    public void printBoard(Board board) {
        System.out.println("    1 2 3 4 5 6 7 8 9");
        for (int i = 0; i < 9; i++) {
            System.out.print("  " + (char) ('A' + i) + " ");
            for (int j = 0; j < 9; j++) {
                int val = board.get(i, j);
                System.out.print((val == 0 ? "_" : val) + " ");
            }
            System.out.println();
        }
    }
}
