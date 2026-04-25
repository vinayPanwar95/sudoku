package com.gic.sudoku;

import com.gic.sudoku.domain.Board;
import com.gic.sudoku.rule.GameValidator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class GameValidatorTest {

    @Test
    void validateDetectsRowDuplicate() {
        Board board = new Board();
        board.set(0, 0, 5);
        board.set(0, 1, 5);

        GameValidator validator = new GameValidator();
        String result = validator.validate(board);
        assertEquals("Number 5 already exists in Row A.", result);
    }

    @Test
    void validateDetectsColumnDuplicate() {
        Board board = new Board();
        board.set(0, 0, 7);
        board.set(1, 0, 7);

        GameValidator validator = new GameValidator();
        String result = validator.validate(board);
        assertEquals("Number 7 already exists in Column 1.", result);
    }

    @Test
    void validateDetectsSubgridDuplicate() {
        Board board = new Board();
        // place two 9s in top-left 3x3
        board.set(0, 0, 9);
        board.set(1, 1, 9);

        GameValidator validator = new GameValidator();
        String result = validator.validate(board);
        assertEquals("Number 9 already exists in the same 3×3 subgrid.", result);
    }

    @Test
    void isValidMoveChecksRowColumnAndSubgrid() {
        Board board = new Board();
        board.set(0, 0, 1); // row 0
        board.set(1, 2, 2); // subgrid conflict candidate

        GameValidator validator = new GameValidator();

        // placing 1 in same row elsewhere should be invalid
        assertFalse(validator.isValidMove(board, 0, 3, 1));

        // placing 2 in same subgrid (0,0) location - check invalid
        assertFalse(validator.isValidMove(board, 0, 1, 2));

        // valid move
        assertTrue(validator.isValidMove(board, 2, 2, 3));
    }
}

