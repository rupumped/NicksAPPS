#!/bin/bash

# Dependencies
sudo apt-get update
sudo apt-get -y install unzip

# Make dir for jars
mkdir -p lib

# JXL
wget https://sourceforge.net/projects/jexcelapi/files/jexcelapi/2.6.12/jexcelapi_2_6_12.zip/download
unzip download
cp jexcelapi/jxl.jar lib/jxl.jar
rm download
rm -rf jexcelapi/

# PDFBox
wget https://apache.claz.org/pdfbox/2.0.20/pdfbox-app-2.0.20.jar -O lib/pdfbox.jar

# JCodec
wget https://repo.maven.apache.org/maven2/org/jcodec/jcodec/0.2.5/jcodec-0.2.5.jar -O lib/jcodec.jar
wget https://repo.maven.apache.org/maven2/org/jcodec/jcodec-javase/0.2.5/jcodec-javase-0.2.5.jar -O lib/jcodec-javase.jar

mkdir -p src/
mv *.java src/
mv *.form src/

# Compile
javac -cp ".:./lib/jxl.jar:./lib/jcodec.jar:./lib/jcodec-javase.jar:./lib/pdfbox.jar" ./src/*.java -d ./build