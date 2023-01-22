package org.scoreboard.model.dto;

import org.scoreboard.model.enums.MatchStatus;

import java.util.Date;
import java.util.UUID;

public class MatchDTO {

    private UUID id;
    private String homeTeam;
    private String awayTeam;
    private Integer homeTeamScore;
    private Integer awayTeamScore;
    private Date matchDate;
    private Date matchStartDate;
    private Date MatchEndDate;
    private MatchStatus matchStatus;

    public MatchDTO() {
    }

    public MatchDTO(UUID id, String homeTeam, String awayTeam, Integer homeTeamScore, Integer awayTeamScore, Date matchDate, Date matchStartDate, Date matchEndDate, MatchStatus matchStatus) {
        this.id = id;
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
        this.homeTeamScore = homeTeamScore;
        this.awayTeamScore = awayTeamScore;
        this.matchDate = matchDate;
        this.matchStartDate = matchStartDate;
        this.MatchEndDate = matchEndDate;
        this.matchStatus = matchStatus;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
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

    public Date getMatchDate() {
        return matchDate;
    }

    public void setMatchDate(Date matchDate) {
        this.matchDate = matchDate;
    }

    public Date getMatchStartDate() {
        return matchStartDate;
    }

    public void setMatchStartDate(Date matchStartDate) {
        this.matchStartDate = matchStartDate;
    }

    public Date getMatchEndDate() {
        return MatchEndDate;
    }

    public void setMatchEndDate(Date matchEndDate) {
        MatchEndDate = matchEndDate;
    }

    public MatchStatus getMatchStatus() {
        return matchStatus;
    }

    public void setMatchStatus(MatchStatus matchStatus) {
        this.matchStatus = matchStatus;
    }
}
