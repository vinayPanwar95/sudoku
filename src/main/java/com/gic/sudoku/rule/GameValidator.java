package com.gic.sudoku.rule;

import com.gic.sudoku.domain.Board;

import java.util.HashSet;
import java.util.Set;

/**
 * Validates the Sudoku board for rule violations.
 * Checks for duplicate numbers in rows, columns, and 3x3 subgrids.
 */
public class GameValidator {

    /**
     * Validates the entire board for Sudoku rule violations.
     * Checks rows, columns, and 3x3 subgrids for duplicates.
     *
     * @param board the board to validate
     * @return an error message if violations found, or "No rule violations detected." if valid
     */
    public String validate(Board board) {
        // Check rows
        for (int i = 0; i < 9; i++) {
            String error = validateRow(board, i);
            if (error != null) return error;
        }

        // Check columns
        for (int j = 0; j < 9; j++) {
            String error = validateColumn(board, j);
            if (error != null) return error;
        }

        // Check 3x3 subgrids
        for (int r = 0; r < 9; r += 3) {
            for (int c = 0; c < 9; c += 3) {
                String error = validateSubgrid(board, r, c);
                if (error != null) return error;
            }
        }

        return "No rule violations detected.";
    }

    /**
     * Validates a single row for duplicates.
     *
     * @param board the board
     * @param row the row index (0-8)
     * @return error message if violation found, null otherwise
     */
    private String validateRow(Board board, int row) {
        Set<Integer> seen = new HashSet<>();
        for (int j = 0; j < 9; j++) {
            int val = board.get(row, j);
            if (val != 0) {
                if (!seen.add(val)) {
                    return "Number " + val + " already exists in Row " + (char) ('A' + row) + ".";
                }
            }
        }
        return null;
    }

    /**
     * Validates a single column for duplicates.
     *
     * @param board the board
     * @param col the column index (0-8)
     * @return error message if violation found, null otherwise
     */
    private String validateColumn(Board board, int col) {
        Set<Integer> seen = new HashSet<>();
        for (int i = 0; i < 9; i++) {
            int val = board.get(i, col);
            if (val != 0) {
                if (!seen.add(val)) {
                    return "Number " + val + " already exists in Column " + (col + 1) + ".";
                }
            }
        }
        return null;
    }

    /**
     * Validates a 3x3 subgrid for duplicates.
     *
     * @param board the board
     * @param startRow the starting row of the subgrid (0, 3, or 6)
     * @param startCol the starting column of the subgrid (0, 3, or 6)
     * @return error message if violation found, null otherwise
     */
    private String validateSubgrid(Board board, int startRow, int startCol) {
        Set<Integer> seen = new HashSet<>();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                int val = board.get(startRow + i, startCol + j);
                if (val != 0) {
                    if (!seen.add(val)) {
                        return "Number " + val + " already exists in the same 3×3 subgrid.";
                    }
                }
            }
        }
        return null;
    }

    /**
     * Checks if a specific move (placing a number) would violate rules.
     * Useful for real-time validation during gameplay.
     *
     * @param board the board
     * @param row the row (0-8)
     * @param col the column (0-8)
     * @param value the value to check (1-9)
     * @return true if the move is valid, false if it violates rules
     */
    public boolean isValidMove(Board board, int row, int col, int value) {
        if (value < 1 || value > 9) return false;

        // Check row
        for (int j = 0; j < 9; j++) {
            if (j != col && board.get(row, j) == value) return false;
        }

        // Check column
        for (int i = 0; i < 9; i++) {
            if (i != row && board.get(i, col) == value) return false;
        }

        // Check 3x3 subgrid
        int subgridRow = (row / 3) * 3;
        int subgridCol = (col / 3) * 3;
        for (int i = subgridRow; i < subgridRow + 3; i++) {
            for (int j = subgridCol; j < subgridCol + 3; j++) {
                if ((i != row || j != col) && board.get(i, j) == value) {
                    return false;
                }
            }
        }

        return true;
    }
}
