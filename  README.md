# GISSudoku — Command-line Sudoku Game

Lightweight, production-oriented command-line Sudoku game written in Java.

This repository contains:
- A Sudoku engine (board, validator, solver)
- Puzzle generator producing ~30 clues per puzzle
- CLI UI and small, focused service classes
- Unit and integration tests under `src/test/java`

## Quick Links
- Source: `src/main/java/com/gic/sudoku`
- Tests: `src/test/java`

## Requirements
- Java 17 or newer
- Enviornment variable `JAVA_HOME` set to your JDK installation directory
- Run the app on windows using PowerShell or Command Prompt

Verify your environment:
```powershell
java -version
```

## Build (no Maven required)

This project includes simple Windows batch scripts so you can build, test and run the app without Maven or IDE integration. You only need a JDK (Java 17) installed.

Files you can use (project root):
- `build.bat` — compile sources and create `sudoku-app.jar` in the project root
- `run-test.bat` — run test suite using the JUnit Platform Console Standalone jar (requires `lib\junit-platform-console-standalone.jar`)
- `run.bat` — run the packaged application (`sudoku-app.jar`)

Build the project by double-clicking `build.bat` or running it from PowerShell:

```powershell
.\build.bat
```

Notes about compilation:
- `build.bat` compiles sources under `src\` and places compiled classes into `target\` and creates `sudoku-app.jar` in the project root.

## Run tests (no Maven)

The repository includes `run-test.bat` which expects the JUnit Platform Console Standalone JAR in `lib\`.

The JUnit Platform Console Standalone JAR is already present in `lib\` (this project includes it by default).

1. Ensure you have built the project (run `build.bat`), then run tests:

```powershell
.\run-test.bat
```

The batch file launches the console launcher and scans the `target` classpath for tests.

## Run the application


After a successful build, launch the game by double-clicking `run.bat` or running it from PowerShell:

```powershell
.\run.bat
```

Or run the produced jar directly:

```powershell
java -jar sudoku-app.jar
```

## How to play (CLI)
- The grid displays rows A–I and columns 1–9. Empty cells are shown as `_`.
- Commands:
  - Place a number: `A3 5` (place 5 at row A column 3)
  - Clear a cell: `B4 clear`
  - Hint: `hint` (reveals one correct value)
  - Check: `check` (validates current board)
  - Quit: `quit`

Rules enforced by the game:
- Numbers must be 1–9
- Cannot change pre-filled clue cells
- No duplicates in rows, columns, or 3×3 boxes

## Tests added
- `BoardTest` — board operations and deep copy
- `GameValidatorTest` — rule validation for rows/columns/subgrids
- `CommandParserTest` — parsing of CLI commands
- `GameSolverTest` — solver/hint validation
- `SimulatedSessionTest` — simple integration test simulating a user move, hint, check, and clear
