package org.scoreboard.repository;

import org.scoreboard.mapper.MatchMapper;
import org.scoreboard.model.entity.Match;
import org.scoreboard.util.DBUtil;

import java.util.*;

public class MatchRepository {

    private final MatchMapper mapper;
    private final Map<UUID, Match> tableMatch = DBUtil.getTableMatch();

    public MatchRepository() {
        mapper = new MatchMapper();
    }

    public Match addMatch(Match match) {
        match.setId(UUID.randomUUID());
        match.setVersion(1);
        tableMatch.put(match.getId(), match);
        return match;
    }

    public Match updateMatch(Match match) {
        Match existingMatch = tableMatch.get(match.getId());
        mapper.update(existingMatch, match);
        existingMatch.setVersion(existingMatch.getVersion()+1);
        return existingMatch;
    }

    public void deleteMatch(Match match) {
        tableMatch.remove(match.getId());
    }

    public Match getById(UUID id) {
        return tableMatch.get(id);
    }

    public List<Match> getAll() {
        List<Match> allMatches = new ArrayList<>();
        for (Map.Entry<UUID, Match> entry : tableMatch.entrySet()) {
            allMatches.add(entry.getValue());
        }
        return allMatches;
    }
}
