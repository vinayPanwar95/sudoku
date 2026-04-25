@echo off
echo =====================================
echo Running JUnit Test Cases...
echo =====================================

REM Run JUnit Console Launcher
java -jar lib\junit-platform-console-standalone.jar execute ^
--class-path target ^
--scan-class-path

echo =====================================
echo Test Execution Completed
echo =====================================
pause
