package com.gic.sudoku.ui;

import com.gic.sudoku.command.Command;
import com.gic.sudoku.domain.Board;
import com.gic.sudoku.generator.GameGenerator;
import com.gic.sudoku.service.CommandParser;

/**
 * Manages a single game session.
 * Follows Single Responsibility Principle - manages one game round.
 */
public class GameSession {
    private final GameGenerator gameGenerator;
    private final CommandParser commandParser;
    private final CommandHandler commandHandler;
    private final InputHandler inputHandler;
    private final BoardDisplay boardDisplay;

    public GameSession(GameGenerator gameGenerator, CommandParser commandParser, CommandHandler commandHandler, InputHandler inputHandler, BoardDisplay boardDisplay) {
        this.gameGenerator = gameGenerator;
        this.commandParser = commandParser;
        this.commandHandler = commandHandler;
        this.inputHandler = inputHandler;
        this.boardDisplay = boardDisplay;
    }

    /**
     * Plays a complete game session.
     */
    public void play() {
        // Generate a new puzzle
        Board board = gameGenerator.generate();

        System.out.println("\nHere is your puzzle:");
        boardDisplay.printBoard(board);

        boolean gameRunning = true;
        while (gameRunning) {
            System.out.println("\nEnter command (e.g., A3 4, B5 clear, hint, check, quit):");
            String input = inputHandler.readLine().trim();

            if (input.trim().isEmpty()) {
                System.out.println("Input cannot be empty. Please enter a valid command.");
              continue;
            }
            try {
                Command command = commandParser.parse(input);
                gameRunning = commandHandler.processCommand(command, board);
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid input: " + e.getMessage());
                System.out.println("Valid formats: A3 5, B4 clear, hint, check, quit");
            }
        }
    }
}
