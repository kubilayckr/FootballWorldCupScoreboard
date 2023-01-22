package org.scoreboard.exception;

public class BusinessException extends RuntimeException{

    public BusinessException(String message) {
        super(message);
    }

    public BusinessException(String message, Throwable cause) {
        super(message, cause);
    }

    public enum ServiceException {
        MATCH_NOT_FOUND("Match not found!"),
        MATCH_ID_CANNOT_BE_NULL("Match id cannot be null!"),
        SCORE_CANNOT_NEGATIVE("Score cannot be negative!"),
        HOME_TEAM_SCORE_CANNOT_BE_EMPTY("Home team score cannot be empty!"),
        HOME_TEAM_NAME_CANNOT_BE_EMPTY("Home team name cannot be empty!"),
        AWAY_TEAM_SCORE_CANNOT_BE_EMPTY("Away team score cannot be empty!"),
        AWAY_TEAM_NAME_CANNOT_BE_EMPTY("Away team name cannot be empty!"),
        MATCH_DATE_CANNOT_BE_NULL("Match date cannot be null!"),
        MATCH_STATUS_CANNOT_BE_NULL("Match status cannot be null!");

        private final String key;

        ServiceException(String key) {
            this.key = key;
        }

        public String getKey() {
            return key;
        }
    }
}
