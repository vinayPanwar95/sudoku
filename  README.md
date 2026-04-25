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

## Dependencies

### This project does NOT use Maven or Gradle.

 - All dependencies are manually added in the lib/ folder:

 - JUnit Platform Console Standalone (for testing):
    - `lib/junit-platform-console-standalone-1.9.3.jar`

## 🖥️ Running the Project in IDE (IntelliJ IDEA)

For **IntelliJ IDEA**, the project is already configured via the `GISSUDOKU.iml` file.  
The downloaded JUnit library (`lib/junit-platform-console-standalone.jar`) is already added as a dependency.

### ✅ IntelliJ Setup (Ready to Use)
- Open the project in IntelliJ IDEA
- Ensure `src/main/java` is marked as **Sources Root**
- Ensure `src/test/java` is marked as **Test Sources Root**
- JUnit library is already configured in `lib/` and linked via module settings
- You can directly run test classes from the IDE

---

## ⚠️ If Using a Different IDE (Manual Setup Required)

If you are using any other IDE (Eclipse, VS Code, etc.), you must configure the setup manually:

### 1. Add Source Folders
- `src/main/java` → application code
- `src/test/java` → test code

### 2. Add JUnit Library
Manually add the JUnit jar located at:

Verify your environment:
```powershell
java -version
```
---
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
