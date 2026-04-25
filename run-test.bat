@echo off
echo =====================================
echo Running JUnit Test Cases...
echo =====================================

REM Step 2: Compile test sources (if not already compiled)
if not exist target\test-classes (
    echo Compiling test sources...
    mkdir target\test-classes 2>nul
    dir /s /b src\test\*.java > test-sources.txt
    javac -cp "target;lib\*" -d target\test-classes @test-sources.txt
    if %errorlevel% neq 0 (
        echo Test compilation failed!
        pause
        exit /b 1
    )
    del test-sources.txt 2>nul
)

REM Step 3: Run JUnit Console Launcher
java -jar lib\junit-platform-console-standalone.jar ^
--class-path target;target\test-classes ^
--scan-class-path

echo =====================================
echo Test Execution Completed
echo =====================================
pause
