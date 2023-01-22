package org.scoreboard.repository;

import org.scoreboard.mapper.MatchMapper;
import org.scoreboard.model.entity.Match;
import org.scoreboard.model.index.ScoreboardIndex;
import org.scoreboard.util.DBUtil;

import java.util.*;

public class ScoreboardRepository {

    private final MatchMapper mapper;
    private final SortedMap<ScoreboardIndex, Match> tableScoreboard = DBUtil.getTableScoreboard();

    public ScoreboardRepository() {
        mapper = new MatchMapper();
    }

    public Match addMatch(Match match) {
        return null;
    }

    public Match updateMatch(Match match) {
        return null;
    }

    public void deleteMatch(Match match) {
    }

    public Match getByIDAndMatchStartDate(UUID id, Date matchStartDate) {
        return null;
    }

    public List<Match> getAll() {
        return null;
    }
}
