package com.example.factory;

/**
 * Concrete Creator representing a factory for Word Documents.
 */
public class WordDocumentFactory extends DocumentFactory {
    @Override
    public Document createDocument() {
        return new WordDocument();
    }
}
