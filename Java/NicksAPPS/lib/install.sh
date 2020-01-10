#!/bin/bash

# Dependencies
sudo apt-get update
sudo apt-get -y install unzip

# JXL
wget https://sourceforge.net/projects/jexcelapi/files/jexcelapi/2.6.12/jexcelapi_2_6_12.zip/download
unzip download
cp jexcelapi/jxl.jar .
rm download
rm -rf jexcelapi/

# PDFBox
wget http://ftp.wayne.edu/apache/pdfbox/2.0.18/pdfbox-app-2.0.18.jar

# JCodec
wget http://central.maven.org/maven2/org/jcodec/jcodec/0.2.3/jcodec-0.2.3.jar
wget http://central.maven.org/maven2/org/jcodec/jcodec-javase/0.2.3/jcodec-javase-0.2.3.jar