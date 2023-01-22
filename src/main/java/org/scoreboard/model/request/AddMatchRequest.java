package org.scoreboard.model.request;

import java.util.Date;

public class AddMatchRequest {

    private String homeTeam;
    private String awayTeam;
    private Date matchDate;

    public AddMatchRequest() {
    }

    public AddMatchRequest(String homeTeam, String awayTeam, Date matchDate) {
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
        this.matchDate = matchDate;
    }

    public String getHomeTeam() {
        return homeTeam;
    }

    public void setHomeTeam(String homeTeam) {
        this.homeTeam = homeTeam;
    }

    public String getAwayTeam() {
        return awayTeam;
    }

    public void setAwayTeam(String awayTeam) {
        this.awayTeam = awayTeam;
    }

    public Date getMatchDate() {
        return matchDate;
    }

    public void setMatchDate(Date matchDate) {
        this.matchDate = matchDate;
    }
}
