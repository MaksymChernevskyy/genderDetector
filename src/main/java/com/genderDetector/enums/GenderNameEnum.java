package com.genderDetector.enums;

public enum GenderNameEnum {
    MALE("male"), FEMALE("female"), INCONCLUSIVE("inconclusive");

    private String name;

    GenderNameEnum(String name) {
        this.name = name;
    }
}
