package com.gic.sudoku;

import com.gic.sudoku.command.Command;
import com.gic.sudoku.service.CommandParser;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CommandParserTest {

    private final CommandParser parser = new CommandParser();

    @Test
    void parseMoveCommand() throws Exception {
        Command cmd = parser.parse("A3 5");
        assertNotNull(cmd);
        assertEquals(Command.Type.MOVE, cmd.type());
        assertEquals(0, cmd.row());
        assertEquals(2, cmd.col());
        assertEquals(5, cmd.value());
    }

    @Test
    void parseClearCommand() throws Exception {
        Command cmd = parser.parse("B4 clear");
        assertNotNull(cmd);

        assertEquals(Command.Type.CLEAR, cmd.type());
        assertEquals(1, cmd.row());
        assertEquals(3, cmd.col());
        assertEquals(0, cmd.value());


    }

    @Test
    void parseHintAndCheckAndQuit() throws Exception {
        Command hint = parser.parse("hint");
        assertEquals(Command.Type.HINT, hint.type());

        Command check = parser.parse("check");
        assertEquals(Command.Type.CHECK, check.type());

        Command quit = parser.parse("quit");
        assertEquals(Command.Type.QUIT, quit.type());
    }

    @Test
    void parseInvalidThrows() {
        assertThrows(IllegalArgumentException.class, () -> parser.parse("Z10 5"));
        assertThrows(IllegalArgumentException.class, () -> parser.parse("A1 10"));
        assertThrows(IllegalArgumentException.class, () -> parser.parse(""));
    }
}

