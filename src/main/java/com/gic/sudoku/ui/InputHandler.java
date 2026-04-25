package com.gic.sudoku.ui;

import java.util.Scanner;

/**
 * Handles user input operations.
 */
public class InputHandler {

    private final Scanner scanner;
    public InputHandler() {
        this.scanner = new Scanner(System.in);
    }
    public String readLine() {
        return scanner.nextLine();
    }
    public void close() {
        scanner.close();
    }
}
