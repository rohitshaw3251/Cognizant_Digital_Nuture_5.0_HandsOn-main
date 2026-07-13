package com.example.factory;

/**
 * Concrete Creator representing a factory for PDF Documents.
 */
public class PdfDocumentFactory extends DocumentFactory {
    @Override
    public Document createDocument() {
        return new PdfDocument();
    }
}
