package com.gic.sudoku.domain;

import java.util.Arrays;

/**
 * Represents a Sudoku board with a 9x9 grid.
 * Cells that are pre-filled (fixed) at game start cannot be modified.
 */
public record Board(int[][] grid, boolean[][] fixed) {
//    /**
//     * Creates a new Sudoku board with an empty grid (all zeros) and no fixed cells.
//     */
//    public Board() {
//        this(new int[9][9], new boolean[9][9]);
//    }

    public Board {
    }

    /**
     * Checks if a cell is fixed (pre-filled at game start).
     *
     * @param r the row (0-8)
     * @param c the column (0-8)
     * @return true if the cell is fixed, false otherwise
     */
    public boolean isFixed(int r, int c) {
        return fixed[r][c];
    }

    public int get(int r, int c) {
        return grid[r][c];
    }

    public void set(int r, int c, int val) {
        grid[r][c] = val;
    }

    public void clear(int r, int c) {
        grid[r][c] = 0;
    }

    public void markFixed(int r, int c) {
        fixed[r][c] = true;
    }

    public Board deepCopy() {
        int[][] gridCopy = new int[9][9];
        boolean[][] fixedCopy = new boolean[9][9];

        for (int i = 0; i < 9; i++) {
            gridCopy[i] = Arrays.copyOf(grid[i], 9);
            fixedCopy[i] = Arrays.copyOf(fixed[i], 9);
        }

        return new Board(gridCopy, fixedCopy);
    }

    /**
     * Checks if the board is completely filled.
     *
     * @return true if all cells contain a value (1-9), false otherwise
     */
    public boolean isComplete() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (grid[i][j] == 0) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Returns the fixed cells array. Use with caution as it allows direct modification.
     *
     * @return the 9x9 fixed array
     */
    @Override
    public boolean[][] fixed() {
        return fixed;
    }
}
