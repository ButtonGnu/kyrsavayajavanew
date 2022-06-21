package com.example.kyrsavayajava;

public enum RequestStatus {
    NEW_REQUEST("Новая заявка"),
    DIAGNOSTICS("Проведена диагностика"),
    DONE("Проведена работа");

    private final String displayName;

    private RequestStatus(String displayName) {
        this.displayName = displayName;
    }

    @Override
    public String toString() {
        return displayName;
    }

    public String getDisplayName() {
        return displayName;
    }

    public static RequestStatus retrieveByDisplayedName(String displayName) {
        switch (displayName) {
            case "Новая заявка":
                return RequestStatus.NEW_REQUEST;
            case "Проведена диагностика":
                return RequestStatus.DIAGNOSTICS;
            case "Проведена работа":
                return RequestStatus.DONE;
            default:
                return null;
        }
    }
}
