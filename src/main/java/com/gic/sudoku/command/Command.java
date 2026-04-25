package com.gic.sudoku.command;

public record Command(Type type,
                      int row,
                      int col,
                      int value) {

    public enum Type {
        MOVE,
        CLEAR,
        HINT,
        CHECK,
        QUIT
    }

    public static Command move(int row, int col, int value) {
        return new Command(Type.MOVE, row, col, value);
    }

    public static Command clear(int row, int col) {
        return new Command(Type.CLEAR, row, col, 0);
    }

    public static Command hint() {
        return new Command(Type.HINT, -1, -1, -1);
    }

    public static Command check() {
        return new Command(Type.CHECK, -1, -1, -1);
    }

    public static Command quit() {
        return new Command(Type.QUIT, -1, -1, -1);
    }
}
