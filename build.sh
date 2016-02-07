#!/bin/bash
cd "$(dirname "$0")"
mkdir -p build

javac -d build src/at/fruel/font/*.java
cd build
jar cfm ../JavaFontRendererTest.jar ../src/META-INF/MANIFEST.MF at/
cd ..