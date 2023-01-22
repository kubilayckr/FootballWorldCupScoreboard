package org.scoreboard.model.request;

import java.util.UUID;

public class UpdateScoreRequest {

    private UUID id;
    private Integer homeTeamScore;
    private Integer awayTeamScore;

    public UpdateScoreRequest() {
    }

    public UpdateScoreRequest(UUID id, Integer homeTeamScore, Integer awayTeamScore) {
        this.id = id;
        this.homeTeamScore = homeTeamScore;
        this.awayTeamScore = awayTeamScore;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Integer getHomeTeamScore() {
        return homeTeamScore;
    }

    public void setHomeTeamScore(Integer homeTeamScore) {
        this.homeTeamScore = homeTeamScore;
    }

    public Integer getAwayTeamScore() {
        return awayTeamScore;
    }

    public void setAwayTeamScore(Integer awayTeamScore) {
        this.awayTeamScore = awayTeamScore;
    }
}
