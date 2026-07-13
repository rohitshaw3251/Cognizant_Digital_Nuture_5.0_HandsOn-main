package com.example.singleton;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Logger class implementing the Singleton pattern.
 * This class ensures that only one instance of the logger exists in the application
 * and provides a global access point to that instance.
 */
public class Logger {

    // 1. Private static instance of the same class.
    // It is marked as volatile to ensure that changes to the instance are visible across threads.
    private static volatile Logger instance;

    // 2. Private constructor to prevent instantiation from outside the class.
    private Logger() {
        // Optional initialization logic (e.g., opening a file, initializing writer)
        System.out.println("[Logger] Initializing Logger instance...");
    }

    // 3. Public static method to get the single instance of the class.
    // Implements Double-Checked Locking for thread safety and lazy initialization.
    public static Logger getInstance() {
        // First check (no synchronization to improve performance once instance is created)
        if (instance == null) {
            // Synchronize on the class lock to ensure thread safety
            synchronized (Logger.class) {
                // Second check inside synchronized block
                if (instance == null) {
                    instance = new Logger();
                }
            }
        }
        return instance;
    }

    /**
     * A simple log method to print messages with a timestamp.
     * 
     * @param message The message to be logged.
     */
    public void log(String message) {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedTime = now.format(formatter);
        System.out.println("[" + formattedTime + "] " + message);
    }
}
