package com.social.dailylink.global;

import java.util.UUID;

/**
 * Global class containing static strings used in various outputs of the application.
 *
 * <strong>IMPORTANT</strong>: Naming convention for ease of reading <br>
 * <ul>
 * <li>Exception must be named <strong>EXCEPTON_reason</strong><li/>
 * </ul>
 */
public class GlobalStrings {
    // DB relevant strings
    public static final String SCHEMA_NAME = "dailylink";
    public static final String MAPPER_COMPONENT_MODEL_NAME = "spring";
    // Default values

//    public static final String DEFAULT_PROFILE_PICTURE_URL =
//            "https://t4.ftcdn.net/jpg/00/64/67/63/360_F_64676383_LdbmhiNM6Ypzb3FM4PPuFP9rHe7ri8Ju.jpg";

    public static String getDefaultProfilePictureUrl(ProfilePictureType type) {
        String baseUrl = "https://robohash.org/";
        UUID uuid = UUID.randomUUID();
        return baseUrl + uuid + "?set=set" + type;
    }

    // Error Messages
    public static final String ERROR_ROLE_NOT_FOUND = "Error: Role is not found.";
    public static final String EXCEPTION_ACCESS_DENIED = "Error: No Access.";
    public static final String EXCEPTION_BAD_REQUEST = "Error: Bad Request.";
    // Singleton Instance
    private static GlobalStrings INSTANCE;

    private GlobalStrings() {
    }
}
