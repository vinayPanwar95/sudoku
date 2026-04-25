@echo off
echo =====================================
echo Building Sudoku Application...
echo =====================================

REM Step 1: Clean old build
if exist target rmdir /s /q target
mkdir target

REM Step 2: Delete old JAR
if exist sudoku-app.jar del sudoku-app.jar

REM Step 3: Collect source files
dir /s /b src\*.java > sources.txt

REM Step 4: Compile
@REM javac -d target @sources.txt
javac -cp "lib/*" -d target @sources.txt


if %errorlevel% neq 0 (
echo Compilation failed!
pause
exit /b
)

REM Step 5: Create JAR
jar cfm sudoku-app.jar manifest.txt -C target .
if %errorlevel% neq 0 (
echo JAR creation failed!
pause
exit /b
)

echo Build successful!

pause