#! /bin/bash

cd ~/hx

echo ">>>>>>>开始更新代码<<<<<<<"
git pull

echo ">>>>>>>开始打jar包:<<<<<<<"
gradle clean build -x test
echo "<<<<<<<jar包已build完成>>>>>>>"

echo "<<<<<<<开始杀死原进程并启动Java程序>>>>>>>"
cd ~/hx/build/libs
pkill -9 java
nohup java -jar hx-0.0.1-SNAPSHOT.jar &
echo "<<<<<<<执行完成，Java正在启动中，20秒后启动完成>>>>>>>"