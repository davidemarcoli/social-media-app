package com.social.dailylink.global;

public enum ProfilePictureType {
    ROBOT(1),
    MONSTER(2),
    HEAD(3),
    CAT(4);

    private final int value;

    ProfilePictureType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
