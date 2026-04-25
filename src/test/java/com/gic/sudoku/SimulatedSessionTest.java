package com.gic.sudoku;

import com.gic.sudoku.domain.Board;
import com.gic.sudoku.generator.GameGenerator;
import com.gic.sudoku.rule.GameValidator;
import com.gic.sudoku.service.GameService;
import com.gic.sudoku.solver.GameSolver;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SimulatedSessionTest {

    @Test
    void fullSessionSimulated() {
        GameGenerator generator = new GameGenerator();
        Board board = generator.generate();

        // Check that generator produced ~30 fixed clues
        int fixed = 0;
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board.isFixed(i, j)) fixed++;
            }
        }
        // allow a small tolerance if generator varies
        assertTrue(fixed >= 25 && fixed <= 35, "Expected about 30 clues, got: " + fixed);

        GameValidator validator = new GameValidator();
        GameSolver solver = new GameSolver(validator);
        GameService service = new GameService(validator, solver);

        // find first non-fixed empty cell
        int targetR = -1, targetC = -1;
        for (int i = 0; i < 9 && targetR == -1; i++) {
            for (int j = 0; j < 9; j++) {
                if (!board.isFixed(i, j) && board.get(i, j) == 0) {
                    targetR = i; targetC = j; break;
                }
            }
        }
        assertTrue(targetR != -1, "Expected at least one empty non-fixed cell");

        Board solved = solver.findHint(board);
        assertNotNull(solved, "Solver should provide a solved board");

        int correct = solved.get(targetR, targetC);
        assertTrue(correct >= 1 && correct <= 9);

        String moveResult = service.makeMove(board, targetR, targetC, correct);
        assertNotNull(moveResult);
        assertEquals("Move accepted.", moveResult);

        String validation = service.checkBoard(board);
        assertNotNull(validation);
        assertEquals("No rule violations detected.", validation);

        // test hint returns something useful
        String hint = service.getHint(board);
        assertNotNull(hint);
        assertTrue(hint.contains("=") || hint.toLowerCase().contains("hint"));

        // test clear
        service.clear(board, targetR, targetC);
        assertEquals(0, board.get(targetR, targetC));
    }
}

