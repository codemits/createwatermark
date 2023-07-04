# PDF Watermark Utility

The PDF Watermark Utility is a Java program that adds a watermark to each page of a PDF document. It allows you to specify the input PDF file, the output PDF file with the watermark, and the text for the watermark.

## Features

- Add a custom watermark to each page of a PDF document.
- Customize the watermark text, font, font size, and color.
- Position the watermark at the center of each page.
- Rotate the watermark by a specified angle.

## Prerequisites

- Java Development Kit (JDK) 8 or higher.
- Apache PDFBox library (version 2.0.0 or higher).

## Usage

1. Clone the repository or download the source code.
2. Make sure you have the JDK and Apache PDFBox library installed.
3. Update the configuration in the `config.properties` file:
    - Set the `inputFilePath` property to the path of your input PDF file.
    - Set the `outputFilePath` property to the desired path for the output PDF file.
    - Set the `watermarkText` property to the desired watermark text.
4. Compile the Java source code:
5. Run the utility: 
   ```mvn test```
6. The output PDF file with the watermark will be generated at the specified location.

## Configuration

The `config.properties` file contains the following properties:

- `inputFilePath`: The path of the input PDF file.
- `outputFilePath`: The path for the output PDF file with the watermark.
- `watermarkText`: The text to be used as the watermark.

## Customization

You can customize the utility by modifying the code in the `WatermarkCreater.java` file. Some possible customizations include:

- Changing the font and font size of the watermark.
- Modifying the watermark color and opacity.
- Adjusting the position and rotation of the watermark.