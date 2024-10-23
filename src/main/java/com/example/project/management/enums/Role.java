package com.example.project.management.enums;

public enum Role {

    ACTIVE("ACTIVE"),
    INACTIVE("INACTIVE");

    /**
     * Attribute represents the name of the status.
     */
    private final String name;

    /**
     * Custom Constructor.
     *
     * @param name
     */
    Role(String name) {
        this.name = name;
    }
}
