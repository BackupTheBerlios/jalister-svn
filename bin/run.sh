#!/bin/sh

#java -jar directoryLister.jar
JAVA_PATH=../dist/directoryLister.jar
cd ../lib
pwd
for jar in `ls -f . | grep jar`;
  do JAVA_PATH=$JAVA_PATH:./$jar;
done

echo $JAVA_PATH

java -cp  $JAVA_PATH directorylister.gui.MainWindow
