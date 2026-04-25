package com.gic.sudoku.ui;

import com.gic.sudoku.generator.GameGenerator;
import com.gic.sudoku.rule.GameValidator;
import com.gic.sudoku.service.CommandParser;
import com.gic.sudoku.service.GameService;
import com.gic.sudoku.solver.GameSolver;

/**
 * Factory for creating GameSession instances.
 */
public class GameSessionFactory {
    private final GameGenerator gameGenerator;
    private final CommandParser commandParser;
    private final GameService gameService;
    private final BoardDisplay boardDisplay;

    public GameSessionFactory() {
        // Create dependencies
        GameValidator validator = new GameValidator();
        GameSolver solver = new GameSolver(validator);
        this.gameGenerator = new GameGenerator();
        this.commandParser = new CommandParser();
        this.gameService = new GameService(validator, solver);
        this.boardDisplay = new BoardDisplay();
    }

    /**
     * Creates a new game session with all necessary dependencies.
     */
    public GameSession createSession() {
        CommandHandler commandHandler = new CommandHandler(gameService, boardDisplay);
        InputHandler inputHandler = new InputHandler();

        return new GameSession(gameGenerator, commandParser, commandHandler,
                              inputHandler, boardDisplay);
    }
}
