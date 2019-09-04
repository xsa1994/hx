#! /bin/bash

cd ~/hx/hx

echo ">>>>>>>您当前位于分支:<<<<<<<"
git status

echo ">>>>>>>开始更新代码<<<<<<<"
git pull

echo ">>>>>>>开始打jar包:<<<<<<<"
gradle clean build -x test
echo "<<<<<<<jar包已build完成>>>>>>>"

echo ">>>>>>>进入到jar包目录<<<<<<<"
cd ~/hx/hx/build/libs

echo "<<<<<<<开始杀死原进程>>>>>>>"
pkill -9 java
echo "<<<<<<<java进程已杀死>>>>>>>"

echo "<<<<<<<开始启动Java程序>>>>>>>"
nohup java -jar hx-0.0.1-SNAPSHOT.jar &
echo "<<<<<<<启动完成>>>>>>>"