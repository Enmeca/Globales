REM @author: Luis Villalobos
REM @description: Optimize the process of starting the frontend next to the backend to work 
REM on the frontend, use windows terminal and microsoft edge

REM Run Backend using maven plugin commands
REM Run frontend using Node Package Manager commands

start wt -M new-tab -p "Command Prompt" -d "%cd%" cmd /k "npm run serve" ; split-pane -p "Command Prompt" -d "%cd%" cmd /k "cd ../.. & "mvn exec:java"

REM Open edge explorer in localhost:8080

echo "Start web app in..."
timeout 7
call msedge.exe http://localhost:8080/
exit
