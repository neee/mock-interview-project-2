package com.example.badbank.util;

public class ConsoleLogger {

    private static ConsoleLogger instance;

    private ConsoleLogger() {}

    public static ConsoleLogger getInstance() {
        if (instance == null) {
            instance = new ConsoleLogger();
        }
        return instance;
    }

    public void log(String message) {
        System.out.println("Console log: " + message);
    }
}