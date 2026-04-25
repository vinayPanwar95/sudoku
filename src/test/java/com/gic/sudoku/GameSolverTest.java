package com.gic.sudoku;

import com.gic.sudoku.domain.Board;
import com.gic.sudoku.generator.GameGenerator;
import com.gic.sudoku.solver.GameSolver;
import com.gic.sudoku.rule.GameValidator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class GameSolverTest {

    @Test
    void findHintProducesSolvedBoard() {
        GameGenerator generator = new GameGenerator();
        Board puzzle = generator.generate();

        GameValidator validator = new GameValidator();
        GameSolver solver = new GameSolver(validator);

        Board solved = solver.findHint(puzzle);
        assertNotNull(solved, "Solver should return a solved board for a valid puzzle");
        assertTrue(solved.isComplete(), "Solved board should be complete");
    }
}

