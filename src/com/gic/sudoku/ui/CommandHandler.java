package com.gic.sudoku.ui;

import com.gic.sudoku.command.Command;
import com.gic.sudoku.domain.Board;
import com.gic.sudoku.service.GameService;

/**
 * CommandHandler class will handles command processing and execution.
 */
public class CommandHandler {
    private final GameService gameService;
    private final BoardDisplay boardDisplay;

    public CommandHandler(GameService gameService, BoardDisplay boardDisplay) {
        this.gameService = gameService;
        this.boardDisplay = boardDisplay;
    }

    /**
     * Processes a command and returns whether the game should continue.
     */
    public boolean processCommand(Command command, Board board) {
        return switch (command.type()) {
            case MOVE -> handleMove(command, board);
            case CLEAR -> handleClear(command, board);
            case HINT -> handleHint(board);
            case CHECK -> handleCheck(board);
            case QUIT -> handleQuit();
            default -> {
                System.out.println("Unknown command type.");
                yield true;
            }
        };
    }

    private boolean handleMove(Command command, Board board) {
        String result = gameService.makeMove(board, command.row(), command.col(), command.value());
        System.out.println("\n" + result);

        // Show validation if the user has filled a cell
        if ("Move accepted.".equals(result)) {
            String validation = gameService.checkBoard(board);
            if (!"No rule violations detected.".equals(validation)) {
                System.out.println(validation);
            }
        }

        System.out.println("\nCurrent grid:");
        boardDisplay.printBoard(board);

        // Check if puzzle is solved
        if (gameService.isPuzzleSolved(board)) {
            System.out.println("\nYou have successfully completed the Sudoku puzzle!");
            return false; // End game
        }

        return true; // Continue game
    }

    private boolean handleClear(Command command, Board board) {
        String result = gameService.clear(board, command.row(), command.col());
        System.out.println("\n" + result);
        System.out.println("\nCurrent grid:");
        boardDisplay.printBoard(board);
        return true;
    }

    private boolean handleHint(Board board) {
        String hint = gameService.getHint(board);
        System.out.println("\n" + hint);
        System.out.println("\nCurrent grid:");
        boardDisplay.printBoard(board);
        return true;
    }

    private boolean handleCheck(Board board) {
        String validation = gameService.checkBoard(board);
        System.out.println("\n" + validation);
        System.out.println("\nCurrent grid:");
        boardDisplay.printBoard(board);
        return true;
    }

    private boolean handleQuit() {
        System.out.println("Game quit. Thanks for playing!");
        return false;
    }
}
