package com.social.dailylink.global;

public class GlobalStrings {
    // Singleton Instance
    private static GlobalStrings INSTANCE;

    // DB relevant strings
    public static final String SCHEMA_NAME = "dailylink";
    public static final String MAPPER_COMPONENT_MODEL_NAME = "spring";

    // Default values
    public static final String DEFAULT_PROFILE_PICTURE_URL =
            "https://t4.ftcdn.net/jpg/00/64/67/63/360_F_64676383_LdbmhiNM6Ypzb3FM4PPuFP9rHe7ri8Ju.jpg";

    // Error Messages
    public static final String ERROR_ROLE_NOT_FOUND = "Error: Role is not found.";

    private GlobalStrings() {
    }
}
