package com.example.factory;

/**
 * Test class to verify the Factory Method pattern implementation.
 */
public class FactoryMethodTest {
    public static void main(String[] args) {
        System.out.println("=== Testing Factory Method Pattern ===");

        // 1. Create a Word Document Factory and create/manipulate a Word Document
        System.out.println("\n--- Word Document Scenario ---");
        DocumentFactory wordFactory = new WordDocumentFactory();
        wordFactory.openAndSaveDocument();

        // 2. Create a PDF Document Factory and create/manipulate a PDF Document
        System.out.println("\n--- PDF Document Scenario ---");
        DocumentFactory pdfFactory = new PdfDocumentFactory();
        pdfFactory.openAndSaveDocument();

        // 3. Create an Excel Document Factory and create/manipulate an Excel Document
        System.out.println("\n--- Excel Document Scenario ---");
        DocumentFactory excelFactory = new ExcelDocumentFactory();
        excelFactory.openAndSaveDocument();

        System.out.println("\n======================================");
    }
}
