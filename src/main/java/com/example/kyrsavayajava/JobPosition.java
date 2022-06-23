package com.example.kyrsavayajava;

public enum JobPosition {
    MASTER("мастер-приемщик"),
    MOTOR_SPECIALIST("моторист"),
    ELECTRICIAN("электрик"),
    MASTER_GENERAL("мастер общего профиля"),
    BODY("кузовщик")
    ;

    private final String displayName;

    JobPosition(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }

    public static JobPosition retrieveByDisplayedName(String displayName) {
        switch (displayName) {
            case "мастер-приемщик":
                return JobPosition.MASTER;
            case "моторист":
                return JobPosition.MOTOR_SPECIALIST;
            case "электрик":
                return JobPosition.ELECTRICIAN;
            case "мастер общего профиля":
                return JobPosition.MASTER_GENERAL;
            case "кузовщик":
                return JobPosition.BODY;
            default:
                return null;
        }
    }
}
