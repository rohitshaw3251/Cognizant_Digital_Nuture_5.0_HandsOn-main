package com.example.factory;

/**
 * Interface representing a Document.
 * This is the Product in the Factory Method Pattern.
 */
public interface Document {
    void open();
    void save();
    void close();
}
