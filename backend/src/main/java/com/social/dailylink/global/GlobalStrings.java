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

    // DB relevant strings
    public static final String SCHEMA_NAME = "dailylink";

    // Error Messages
    public static final String ERROR_ROLE_NOT_FOUND = "Error: Role is not found.";
    public static final String EXCEPTION_ACCESS_DENIED = "Error: No Access.";

    private GlobalStrings() {
    }
}
