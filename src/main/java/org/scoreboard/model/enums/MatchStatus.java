package org.scoreboard.model.enums;

public enum MatchStatus {

    NOT_STARTED("NOT_STARTED"),
    FIRST_HALF("FIRST_HALF"),
    FIRST_HALF_FINISHED("FIRST_HALF_FINISHED"),
    SECOND_HALF("SECOND_HALF"),
    SECOND_HALF_FINISHED("SECOND_HALF_FINISHED"),
    FINISHED("FINISHED"),
    EXTENSIONS("EXTENSIONS"),
    PENALTIES("PENALTIES"),
    DELAYED("DELAYED"),
    CANCELLED("CANCELLED");

    private String value;

    MatchStatus(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
