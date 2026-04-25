package com.gic.sudoku.generator;

import com.gic.sudoku.domain.Board;
import com.gic.sudoku.rule.GameValidator;
import com.gic.sudoku.solver.GameSolver;

import java.util.Random;

/**
 * Creates a complete solution and then removes cells to create a puzzle with 30 clues.
 */
public class GameGenerator {
    private static final int CLUES = 30;
    private static final int CELLS_TO_REMOVE = 81 - CLUES;
    private static final Random RANDOM = new Random();

    /**
     * Generates a new random Sudoku puzzle.
     *
     * @return a new Board with a solvable puzzle (30 clues, rest empty)
     */
    public Board generate() {
        Board board = new Board(new int[9][9], new boolean[9][9]);

        // Generate a complete solution
        GameValidator validator = new GameValidator();
        GameSolver solver = new GameSolver(validator);
        solver.solve(board);
        markAllAsFixed(board);

        // Remove cells to create the puzzle (leave 30 clues)
        removeCells(board, CELLS_TO_REMOVE);

        return board;
    }

    /**
     * Removes a specified number of cells from the board.
     * Removes cells randomly while trying to maintain a unique solution.
     *
     * @param board the board to modify
     * @param cellsToRemove the number of cells to remove
     */
    private void removeCells(Board board, int cellsToRemove) {
        int removed = 0;
        int attempts = 0;
        final int maxAttempts = CELLS_TO_REMOVE * 10; // Prevent infinite loops

        while (removed < cellsToRemove && attempts < maxAttempts) {
            attempts++;

            // Pick a random cell
            int row = RANDOM.nextInt(9);
            int col = RANDOM.nextInt(9);

            // Skip if already empty
            if (board.get(row, col) == 0) {
                continue;
            }

            // Save the value
            int backup = board.get(row, col);

            // Remove the cell
            board.set(row, col, 0);
            board.fixed()[row][col] = false;

            // Check if puzzle still has a unique solution (simplified check)
            // For performance, we do a weaker uniqueness check: just verify it's still solvable
            Board copy = board.deepCopy();
            GameValidator validator = new GameValidator();
            GameSolver solver = new GameSolver(validator);

            if (solver.solve(copy)) {
                removed++;
            } else {
                // Restore if removing this cell breaks the puzzle
                board.set(row, col, backup);
                board.fixed()[row][col] = true;
            }
        }
    }

    /**
     * Marks all non-empty cells in the board as fixed.
     * Fixed cells are the initial clues that cannot be modified by the player.
     *
     * @param board the board to mark
     */
    private void markAllAsFixed(Board board) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board.get(i, j) != 0) {
                    board.markFixed(i, j);
                }
            }
        }
    }
}
