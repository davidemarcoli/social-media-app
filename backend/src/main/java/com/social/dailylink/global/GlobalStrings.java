package com.social.dailylink.global;

public class GlobalStrings {
    // Singleton Instance
    private static GlobalStrings INSTANCE;

    // DB relevant strings
    private final String SCHEMA_NAME = "dailylink";

    // Error Messages
    private final String ERROR_ROLE_NOT_FOUND = "Error: Role is not found.";



    private GlobalStrings() {
    }

    public static GlobalStrings getInstance() {
        if(INSTANCE == null) {
            INSTANCE = new GlobalStrings();
        }
        return INSTANCE;
    }

    public String getSchemaName() {
        return SCHEMA_NAME;
    }

    public String getERROR_ROLE_NOT_FOUND() {
        return ERROR_ROLE_NOT_FOUND;
    }
}
