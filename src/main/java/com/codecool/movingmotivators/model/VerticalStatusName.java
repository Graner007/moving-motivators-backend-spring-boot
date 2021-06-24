package com.codecool.movingmotivators.model;

public enum VerticalStatusName {

    NATURAL("natural"),
    POSITIVE("positive"),
    NEGATIVE("negative");

    private final String statusName;

    VerticalStatusName(String statusName) { this.statusName = statusName; }

    public String getStatusName() { return statusName; }
}
