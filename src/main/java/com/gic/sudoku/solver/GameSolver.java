package com.gic.sudoku.solver;

import com.gic.sudoku.domain.Board;
import com.gic.sudoku.rule.GameValidator;

/**
 * Solves a Sudoku puzzle using backtracking algorithm.
 * Used for both puzzle verification and hint generation.
 */
public class GameSolver {
    private final GameValidator validator;

    public GameSolver(GameValidator validator) {
        this.validator = validator;
    }

    /**
     * Solves a Sudoku puzzle using backtracking algorithm.
     * Used for both puzzle verification and hint generation.
     */
    public boolean solve(Board board) {
        // Find next empty cell
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                if (board.get(row, col) == 0) {  // Empty cell found

                    // Try numbers 1-9 in this cell
                    for (int num = 1; num <= 9; num++) {
                        if (validator.isValidMove(board, row, col, num)) {
                            board.set(row, col, num);  // Place number

                            // Recursively solve remaining puzzle
                            if (solve(board)) {
                                return true;  // Solution found!
                            }

                            // No solution with this number, backtrack
                            board.set(row, col, 0);  // Remove number (UNDO)
                        }
                    }

                    // Tried all numbers 1-9, none worked
                    return false;  // Dead end, backtrack to previous cell
                }
            }
        }

        // No empty cells left - puzzle is solved!
        return true;
    }

    /**
     * Finds the first empty cell in the board (reading left-to-right, top-to-bottom).
     * Useful for providing hints.
     *
     * @param board the board
     * @return a solved board with one additional hint filled, or null if board is already complete
     */
    public Board findHint(Board board) {
        Board copy = board.deepCopy();
        if (solve(copy)) {
            return copy;
        }
        return null;
    }
}
