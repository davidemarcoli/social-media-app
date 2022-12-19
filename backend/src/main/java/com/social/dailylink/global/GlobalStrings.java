package com.social.dailylink.global;

public class GlobalStrings {
    // Singleton Instance
    private static GlobalStrings INSTANCE;

    // DB relevant strings
    public static final String SCHEMA_NAME = "dailylink";

    // Error Messages
    public static final String ERROR_ROLE_NOT_FOUND = "Error: Role is not found.";

    private GlobalStrings() {
    }
}
