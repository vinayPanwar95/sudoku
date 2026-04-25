package com.gic.sudoku.service;

import com.gic.sudoku.command.Command;

/**
 * Parses user input commands for the Sudoku game.
 * Supports commands like:
 * - Move: "A3 5" (place 5 in row A, column 3)
 * - Clear: "B4 clear" (clear row B, column 4)
 * - Hint: "hint"
 * - Check: "check"
 * - Quit: "quit"
 */
public class CommandParser {
   private final String CLEAR = "clear";
    /**
     * Parses user input string into a Command object.
     *
     * @param input the user input string
     * @return a Command object representing the parsed input
     * @throws IllegalArgumentException if the input format is invalid
     */
    public Command parse(String input) throws IllegalArgumentException {

       String[] parts = input.toLowerCase().split("\\s+");


        // Handle single-word commands
        if (parts.length == 1) {
            String command = parts[0];
            if ("hint".equals(command)) return Command.hint();
            if ("check".equals(command)) return Command.check();
            if ("quit".equals(command)) return Command.quit();
            throw new IllegalArgumentException("Unknown command: " + command);
        }

        // Handle cell-based commands (at least 2 parts)
        if (parts.length >= 2) {
            try {
                String cell = parts[0];
                if (cell.length() < 2) {
                    throw new IllegalArgumentException("Invalid cell format. Use format: A3");
                }

                int row = Character.toUpperCase(cell.charAt(0)) - 'A';
                int col = Character.getNumericValue(cell.charAt(1)) - 1;

                if (row < 0 || row > 8 || col < 0 || col > 8) {
                    throw new IllegalArgumentException("Cell out of bounds. Rows: A-I, Columns: 1-9");
                }

                String action = parts[1];
                if (CLEAR.equalsIgnoreCase(action)) {
                    return Command.clear(row, col);
                }

                // Try to parse as a number
                int value = Integer.parseInt(action);
                if (value < 1 || value > 9) {
                    throw new IllegalArgumentException("Value must be between 1 and 9");
                }
                return Command.move(row, col, value);
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("Invalid move format. Use: A3 5 or A3 clear");
            }
        }

        throw new IllegalArgumentException("Invalid command format");
    }
}
