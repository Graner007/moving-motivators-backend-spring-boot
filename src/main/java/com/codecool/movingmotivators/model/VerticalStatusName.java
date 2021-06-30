package com.codecool.movingmotivators.model;

public enum VerticalStatusName {

    NEUTRAL("neutral"),
    POSITIVE("positive"),
    NEGATIVE("negative"),
    DEFAULT_IMAGE("default-image");

    private final String statusName;

    VerticalStatusName(String statusName) { this.statusName = statusName; }

    public String getStatusName() { return statusName; }
}
