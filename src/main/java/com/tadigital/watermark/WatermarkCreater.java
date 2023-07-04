package com.tadigital.watermark;

import java.awt.geom.AffineTransform;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.util.Matrix;


public class WatermarkCreater {

    public static void addWatermarkToPDF() {
        try {
            // Load the configuration from the properties file
            Properties properties = loadProperties();

            // Retrieve the properties
            String inputFile = properties.getProperty("inputFilePath");
            String outputFile = properties.getProperty("outputFilePath");
            String watermarkText = properties.getProperty("watermarkText");

            System.out.println("Input File Path: " + inputFile);
            System.out.println("Output File Path: " + outputFile);
            System.out.println("Watermark Text: " + watermarkText);


            // Load the PDF document
            PDDocument document = PDDocument.load(new File(inputFile));

            // Get the number of pages in the document
            int pageCount = document.getNumberOfPages();

            // Iterate through each page and add the watermark
            for (int i = 0; i < pageCount; i++) {
                PDPage page = document.getPage(i);

                // Create a new content stream for the page
                PDPageContentStream contentStream = new PDPageContentStream(document, page,
                        PDPageContentStream.AppendMode.APPEND, true, true);

                // Define the font and font size for the watermark
                contentStream.setFont(PDType1Font.HELVETICA_BOLD, 48);

                // Define the opacity of the watermark
                contentStream.setNonStrokingColor(255, 0, 0); // Red color with full opacity

                // Get the page dimensions
                PDRectangle pageSize = page.getMediaBox();
                float pageWidth = pageSize.getWidth();
                float pageHeight = pageSize.getHeight();

                // Calculate the position of the watermark (centered)
                float fontSize = 48;
                float textWidth = PDType1Font.HELVETICA_BOLD.getStringWidth(watermarkText) / 1000 * fontSize;
                float x = (pageWidth - textWidth) / 2;
                float y = (pageHeight - fontSize) / 2;

                // Rotate the watermark by 45 degrees
                AffineTransform affineTransform = AffineTransform.getRotateInstance(Math.PI / 4, x, y);
                Matrix matrix = new Matrix();
                matrix.setFromAffineTransform(affineTransform);
                contentStream.transform(matrix);


                // Draw the watermark on the page
                contentStream.beginText();
                contentStream.showText(watermarkText);
                contentStream.endText();
                contentStream.close();
            }

            // Save the modified document with the watermark
            document.save(outputFile);
            document.close();

            System.out.println("Watermark added successfully to the PDF!");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static Properties loadProperties() throws IOException {
        Properties properties = new Properties();
        InputStream inputStream = WatermarkCreater.class.getResourceAsStream("/config.properties");
        properties.load(inputStream);
        return properties;
    }

}

