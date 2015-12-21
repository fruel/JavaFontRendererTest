# Java Font Renderer test application

This application is used to test the behaviour of the Java font renderer.
Fore reference see issues #152 and #161 in https://github.com/chrissimpkins/Hack

# Usage
```
Input Data:
	-i [file]	Load text from input file.
	-t [text]	Provide text directly.

	If no options are specified you will be prompted to select a file.

Font:
	-s [font]	Use an installed system font with the given name.
	-f [file]	Load the specified TTF file and use it to render the text.
	-p [pt]		Set font size
	-o [style]	Font style option. A comma separated list of: bold, italic

	If no font options are specified a selection dialog will be shown.

Other Options:
	-h		Show this help page.
```

# Licence
```
The MIT License (MIT)

Copyright (c) 2015 Lukas Frühstück

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
```