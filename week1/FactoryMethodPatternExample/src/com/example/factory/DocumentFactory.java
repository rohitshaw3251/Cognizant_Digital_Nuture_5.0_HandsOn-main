package com.example.factory;

/**
 * Abstract Creator class declaring the factory method.
 * This is the Creator in the Factory Method Pattern.
 */
public abstract class DocumentFactory {

    // The factory method that subclasses will implement to return specific Document types.
    public abstract Document createDocument();

    /**
     * A helper method that demonstrates that the Creator class's primary business logic
     * relies on the product returned by the factory method.
     */
    public void openAndSaveDocument() {
        // Call the factory method to create a Document object.
        Document doc = createDocument();
        // Use the Document object.
        doc.open();
        doc.save();
        doc.close();
    }
}
