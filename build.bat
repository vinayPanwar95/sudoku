@echo off
echo =====================================
echo Building Sudoku Application...
echo =====================================

REM Step 1: Check if JUnit console launcher exists, download if not
if not exist lib\junit-platform-console-standalone.jar (
    echo JUnit Platform Console Standalone JAR not found in lib\.
    echo Downloading JUnit Platform Console Standalone 1.9.3...
    if not exist lib mkdir lib
    powershell -Command "& {Invoke-WebRequest -Uri 'https://repo1.maven.org/maven2/org/junit/platform/junit-platform-console-standalone/1.9.3/junit-platform-console-standalone-1.9.3.jar' -OutFile 'lib\junit-platform-console-standalone.jar'}"
    if %errorlevel% neq 0 (
        echo Failed to download JUnit JAR. Please check your internet connection.
        pause
        exit /b 1
    )
    echo Download complete.
)

REM Step 2: Clean old build
if exist target rmdir /s /q target
mkdir target

REM Step 3: Delete old JAR
if exist sudoku-app.jar del sudoku-app.jar

REM Step 4: Collect source files
dir /s /b src\*.java > sources.txt

REM Step 5: Compile
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