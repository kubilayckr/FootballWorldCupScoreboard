package org.scoreboard.model.index;

import java.util.Date;
import java.util.UUID;

public class ScoreboardIndex {

    private UUID id;
    private Date matchStartDate;

    public ScoreboardIndex() {
    }

    public ScoreboardIndex(UUID id, Date matchStartDate) {
        this.id = id;
        this.matchStartDate = matchStartDate;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Date getMatchStartDate() {
        return matchStartDate;
    }

    public void setMatchStartDate(Date matchStartDate) {
        this.matchStartDate = matchStartDate;
    }
}
