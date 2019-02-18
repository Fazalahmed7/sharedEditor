cls
echo off
echo *****************************************
call mvn clean spring-boot:run -Drun.arguments=--debug
pause