package com.gic.sudoku.ui;

/**
 * Main game controller that orchestrates the overall game flow.
 * Follows Single Responsibility Principle - only manages game lifecycle.
 */
public class GameController {

    public void startGame() {

        GameSessionFactory sessionFactory = new GameSessionFactory();
        InputHandler inputHandler = new InputHandler();
        System.out.println("Starting Sudoku game...");
        System.out.println("Welcome to Sudoku!");
        System.out.println();

        boolean playAgain = true;
        while (playAgain) {
            GameSession session = sessionFactory.createSession();
            session.play();

            System.out.println("\nPress 'y' to play again, any other key to quit...");
            String input = inputHandler.readLine().toLowerCase();
            playAgain = "y".equals(input);
        }

        System.out.println("Thanks for playing Sudoku!");
        inputHandler.close();
    }
}
