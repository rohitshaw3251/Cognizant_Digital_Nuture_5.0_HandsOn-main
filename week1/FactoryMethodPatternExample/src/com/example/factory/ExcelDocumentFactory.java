package com.example.factory;

/**
 * Concrete Creator representing a factory for Excel Documents.
 */
public class ExcelDocumentFactory extends DocumentFactory {
    @Override
    public Document createDocument() {
        return new ExcelDocument();
    }
}
