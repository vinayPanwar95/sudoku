package com.gic.sudoku;

import com.gic.sudoku.domain.Board;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BoardTest {

    @Test
    void testSetGetClearFixedDeepCopyAndComplete() {
        Board board = new Board();

        // set and get
        board.set(0, 0, 5);
        assertEquals(5, board.get(0, 0));

        // clear
        board.clear(0, 0);
        assertEquals(0, board.get(0, 0));

        // fixed
        board.markFixed(1, 1);
        assertTrue(board.isFixed(1, 1));

        // deep copy independence
        board.set(2, 2, 3);
        Board copy = board.deepCopy();
        copy.set(2, 2, 4);
        assertEquals(3, board.get(2, 2), "Original board must not change when copy is modified");

        // isComplete (simple check: all non-zero)
        Board full = new Board();
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                full.set(i, j, 1); // value doesn't matter for isComplete
            }
        }
        assertTrue(full.isComplete());
    }
}

