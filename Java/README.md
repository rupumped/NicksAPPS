# NicksAPPS

## Java
To automatically download dependencies and compile classes, run `./install.sh`.

### Dependencies
Some of the files need the following libraries. Download the JAR's of the following API's and place them in the lib folder of the project:
* [JXL](http://jexcelapi.sourceforge.net/)
* [PDFBox](https://pdfbox.apache.org/)
* [JCodec](http://jcodec.org/) (make sure you get both JARs!)

### ArraysPlus
Additional Java methods that work on arrays. Includes multiple comparator sorting, case-agnostic String array operations, and primitive-to-reference array converters.

### MainPlus
Methods I found myself copy-pasting too many times. Includes methods to neatly close and end if an Exception is caught and some debugging tools.

### MathPlus
Additional Java methods that perform mathematical operations such as reciprocal trigonometric functions and linear and geometric sequencing.

### NumberList
Extends ArrayList\<Number\> to enable mathematical operations on vectors of numbers. Includes summation, product, mean, etc.

### RobotPlus
Extends Robot to enable commonly desired functionality when trying to write bots. Includes screen readers, special delays, and special click and type commands. Uses Keyboard.java.

### ScannerPlus
Additional Java methods that work with Scanners to parse text by skipping lines until the desired location in the text is reached.

### ScreenRecorder
A screen recorder that won't produce a watermarked video.

### StringList
Extends ArrayList\<String\> to enable special manipulation on Lists of Strings. Includes concatenation and case-agnostic comparisons.

### StringPlus
Additional Java methods that perform String operations such as HTML cleaning and format checking.