package org.scoreboard.model.request;

import org.scoreboard.model.enums.MatchStatus;

import java.util.UUID;

public class UpdateMatchStatusRequest {

    private UUID id;
    private MatchStatus matchStatus;

    public UpdateMatchStatusRequest() {
    }

    public UpdateMatchStatusRequest(UUID id, MatchStatus matchStatus) {
        this.id = id;
        this.matchStatus = matchStatus;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public MatchStatus getMatchStatus() {
        return matchStatus;
    }

    public void setMatchStatus(MatchStatus matchStatus) {
        this.matchStatus = matchStatus;
    }
}
