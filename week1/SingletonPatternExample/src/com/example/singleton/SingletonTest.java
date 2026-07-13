package com.example.singleton;

/**
 * Test class to verify the Singleton implementation.
 */
public class SingletonTest {
    public static void main(String[] args) {
        System.out.println("=== Testing Singleton Pattern ===");

        // 1. Retrieve the logger instance for the first time
        System.out.println("Retrieving logger1 instance...");
        Logger logger1 = Logger.getInstance();

        // 2. Retrieve the logger instance for the second time
        System.out.println("Retrieving logger2 instance...");
        Logger logger2 = Logger.getInstance();

        // 3. Verify that both references point to the same object
        System.out.println("\nVerifying instance equality:");
        System.out.println("logger1 Reference: " + logger1);
        System.out.println("logger2 Reference: " + logger2);

        if (logger1 == logger2) {
            System.out.println("SUCCESS: logger1 and logger2 are the exact same instance!");
        } else {
            System.out.println("FAILURE: logger1 and logger2 point to different instances.");
        }

        // 4. Use both logger instances to verify they function properly
        System.out.println("\nLogging messages:");
        logger1.log("This is a log message from logger1.");
        logger2.log("This is a log message from logger2.");

        System.out.println("=================================");
    }
}
