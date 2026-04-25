@echo off
REM Build, test, and run the Sudoku application
REM Uses batch scripts and JUnit standalone jar (no Maven required)

echo ==========================================================
echo GISSudoku - Build, Test, and Run
echo ==========================================================
echo.

REM Step 1: Build the application
echo === Step 1: Building application ===
call build.bat
IF ERRORLEVEL 1 (
    echo Build failed. Aborting.
    pause
    EXIT /B 1
)

REM Step 2: Run tests
echo.
echo === Step 2: Running tests ===
call run-test.bat
IF ERRORLEVEL 1 (
    echo Tests failed. Aborting.
    pause
    EXIT /B 1
)

REM Step 3: Run the application
echo.
echo === Step 3: Running application ===
call run.bat

pause

