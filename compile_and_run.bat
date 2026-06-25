@echo off
echo ==============================================
echo Compiling Tic-Tac-Toe Java Swing Application...
echo ==============================================

if not exist bin (
    mkdir bin
)

javac -d bin -cp "lib\mysql-connector-j-8.4.0.jar" src\*.java

if %ERRORLEVEL% neq 0 (
    echo Compilation FAILED!
    pause
    exit /b %ERRORLEVEL%
)

echo Compilation Successful!
echo Running Application...
echo ==============================================

java -cp "bin;lib\mysql-connector-j-8.4.0.jar" Main

pause
