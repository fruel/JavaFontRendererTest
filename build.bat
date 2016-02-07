@echo off
cd %~dp0
if not exist build mkdir build

javac -d build src\at\fruel\font\*.java
cd build
jar cfm ..\JavaFontRendererTest.jar ..\src\META-INF\MANIFEST.MF at\
cd ..