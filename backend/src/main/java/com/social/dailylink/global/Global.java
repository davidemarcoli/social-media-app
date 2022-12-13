package com.social.dailylink.global;

public final class Global {

    // Singleton Instance
    private static Global INSTANCE;

    // Global Objects
    public static final GlobalStrings GLOBAL_STRINGS = GlobalStrings.getInstance();

    private Global() {
    }

    public static Global getInstance() {
        if(INSTANCE == null) {
            INSTANCE = new Global();
        }
        return INSTANCE;
    }
}
