package com.example.kyrsavayajava;

public enum ExecutionStage {
    NEW_REQUEST("новая заявка"),
    DIAGNOSTICS("диагностика"),
    REPAIR("ремонт"),
    COMPLETED("выполнена");

    private final String displayName;

    private ExecutionStage(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }

    public static ExecutionStage retrieveByDisplayedName(String displayName) {
        switch (displayName) {
            case "новая заявка":
                return ExecutionStage.NEW_REQUEST;
            case "диагностика":
                return ExecutionStage.DIAGNOSTICS;
            case "ремонт":
                return ExecutionStage.REPAIR;
            case "выполнена":
                return ExecutionStage.COMPLETED;
            default:
                return null;
        }
    }

    @Override
    public String toString() {
        return displayName;
    }
}
