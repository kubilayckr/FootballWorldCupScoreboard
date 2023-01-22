package org.scoreboard.repository;

import org.scoreboard.mapper.MatchMapper;
import org.scoreboard.model.entity.Match;
import org.scoreboard.util.DBUtil;

import java.util.List;
import java.util.Map;
import java.util.UUID;

public class MatchRepository {

    private final MatchMapper mapper;
    private final Map<UUID, Match> tableMatch = DBUtil.getTableMatch();

    public MatchRepository() {
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

    public Match getById(UUID id) {
        return null;
    }

    public List<Match> getAll() {
        return null;
    }
}
