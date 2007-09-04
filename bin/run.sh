#!/bin/sh

#java -jar directoryLister.jar
JAVA_PATH=../dist/directoryLister.jar
cd ../lib
pwd
for jar in `ls -f . | grep jar`;
  do JAVA_PATH=$JAVA_PATH:../lib/$jar;
done

echo $JAVA_PATH

java -Xmx256M -cp $JAVA_PATH directorylister.Main
