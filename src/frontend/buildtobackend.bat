REM @author: Luis Villalobos
REM @description: Optimize the process of starting the frontend next to the backend to work 
REM on the frontend, use windows terminal and microsoft edge

REM Run Backend using maven plugin commands

start wt -M new-tab -p "Command Prompt" -d "%cd%" cmd /k "cd ../.. & "mvn clean compile exec:java"

REM Open edge explorer in localhost:9191

echo "Start web app in..."
timeout 30
call msedge.exe http://localhost:9191/
exit
