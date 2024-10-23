package com.example.project.management.enums;

/**
 * Status used for Task Entities.
 */
public enum Status {

    TODO("TODO"),
    IN_PROGRESS("IN_PROGRESS"),
    IN_REVIEW("IN_REVIEW"),
    DONE("DONE");

    /**
     * Attribute represents the name of the status.
     */
    private final String name;

    /**
     * Custom Constructor.
     *
     * @param name
     */
    Status(String name) {
        this.name = name;
    }
}
