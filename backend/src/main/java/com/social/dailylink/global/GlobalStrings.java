package com.social.dailylink.global;

/**
 * Global class containing static strings used in various outputs of the application.
 *
 * <strong>IMPORTANT</strong>: Naming convention for ease of reading <br>
 * <ul>
 * <li>Exception must be named <strong>EXCEPTON_reason</strong><li/>
 * </ul>
 */
public class GlobalStrings {
    // Singleton Instance
    private static GlobalStrings INSTANCE;

    // Default values
    public static final String DEFAULT_PROFILE_PICTURE_URL =
            "https://t4.ftcdn.net/jpg/00/64/67/63/360_F_64676383_LdbmhiNM6Ypzb3FM4PPuFP9rHe7ri8Ju.jpg";

    // DB relevant strings
    public static final String SCHEMA_NAME = "dailylink";

    // Error Messages
    public static final String ERROR_ROLE_NOT_FOUND = "Error: Role is not found.";
    public static final String EXCEPTION_ACCESS_DENIED = "Error: No Access.";

    private GlobalStrings() {
    }
}
