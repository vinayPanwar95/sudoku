package com.gic.sudoku.service;

import com.gic.sudoku.domain.Board;
import com.gic.sudoku.rule.GameValidator;
import com.gic.sudoku.solver.GameSolver;

/**
 * Core game logic service for Sudoku.
 * Handles moves, clearing cells, hints, and validation.
 */
public class GameService {
    private final GameValidator validator;
    private final GameSolver solver;

    /**
     * Creates a new GameService.
     *
     * @param validator the validator for checking moves
     * @param solver the solver for generating hints
     */
    public GameService(GameValidator validator, GameSolver solver) {
        this.validator = validator;
        this.solver = solver;
    }

    /**
     * Makes a move by placing a number in a cell.
     *
     * @param board the game board
     * @param r the row (0-8)
     * @param c the column (0-8)
     * @param val the value to place (1-9)
     * @return a description of the result (success or error message)
     */
    public String makeMove(Board board, int r, int c, int val) {
        if (board.isFixed(r, c)) {
            return "Invalid move. Cell is pre-filled.";
        }

        if (val < 1 || val > 9) {
            return "Invalid number. Must be 1-9.";
        }

        board.set(r, c, val);
        return "Move accepted.";
    }
    public String clear(Board board, int r, int c) {
        if (board.isFixed(r, c)) {
            return "Cannot clear pre-filled cell.";
        }

        board.clear(r, c);
        return "Cell cleared.";
    }

    /**
     * Checks the board for Sudoku rule violations.
     *
     * @param board the game board
     * @return validation message (empty = valid, otherwise error description)
     */
    public String checkBoard(Board board) {
        return validator.validate(board);
    }

    /**
     * Provides a hint by revealing one empty cell's correct value.
     *
     * @param board the game board
     * @return hint message with cell and value
     */
    public String getHint(Board board) {
        // Create a copy and solve it
        Board solved = solver.findHint(board);
        if (solved == null) {
            return "No hint available.";
        }

        // Find the first cell that's empty in the original but filled in the solution
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board.get(i, j) == 0 && solved.get(i, j) != 0) {
                    return String.format("Hint: Cell %s%d = %d", (char) ('A' + i), j + 1, solved.get(i, j));
                }
            }
        }
        return "No hint available.";
    }

    /**
     * Checks if the puzzle is complete and correctly solved.
     *
     * @param board the game board
     * @return true if board is complete and valid, false otherwise
     */
    public boolean isPuzzleSolved(Board board) {
        if (!board.isComplete()) {
            return false;
        }
        return "No rule violations detected.".equals(validator.validate(board));
    }
}
