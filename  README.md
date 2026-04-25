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

- **Java 17 or higher** 

Verify your environment:
```powershell
java -version
```

## Build & Run

This project uses Windows batch scripts for building and testing. **No Maven required!**

### Quickest Way: One-Click Everything
```powershell
.\build-test-run.bat
```

This runs:
1. `build.bat` — Compiles the application
2. `run-test.bat` — Runs tests (auto-downloads JUnit jar if needed)
3. `run.bat` — Runs the Sudoku game

### Individual Scripts
```powershell
# Build the application
.\build.bat

# Run tests (auto-downloads JUnit jar if missing)
.\run-test.bat

# Run the game
.\run.bat
```

**Note:** The first time you run `run-test.bat`, it will automatically download the JUnit Platform Console Standalone jar to `lib\` (internet connection required).

## How to Play (CLI)
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
