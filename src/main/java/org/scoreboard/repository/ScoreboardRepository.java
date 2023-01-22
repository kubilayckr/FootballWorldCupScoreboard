package org.scoreboard.repository;

import org.scoreboard.exception.BusinessException;
import org.scoreboard.mapper.MatchMapper;
import org.scoreboard.model.entity.Match;
import org.scoreboard.model.index.ScoreboardIndex;
import org.scoreboard.util.DBUtil;

import java.util.*;

import static org.scoreboard.exception.BusinessException.ServiceException.MATCH_NOT_FOUND;

public class ScoreboardRepository {

    private final MatchMapper mapper;
    private final SortedMap<ScoreboardIndex, Match> tableScoreboard = DBUtil.getTableScoreboard();

    public ScoreboardRepository() {
        mapper = new MatchMapper();
    }

    public Match addMatch(Match match) {
        match.setVersion(1);
        ScoreboardIndex scoreboardIndex = new ScoreboardIndex(match.getId(), match.getMatchStartDate());
        tableScoreboard.put(scoreboardIndex, match);
        return match;
    }

    public Match updateMatch(Match match) {
        ScoreboardIndex scoreboardIndex = new ScoreboardIndex(match.getId(), match.getMatchStartDate());
        Match existingMatch = tableScoreboard.get(scoreboardIndex);
        mapper.update(existingMatch, match);
        existingMatch.setVersion(existingMatch.getVersion()+1);
        return existingMatch;
    }

    public void deleteMatch(Match match) {
        ScoreboardIndex scoreboardIndex = new ScoreboardIndex(match.getId(), match.getMatchStartDate());
        tableScoreboard.remove(scoreboardIndex);
    }

    public Match getByIDAndMatchStartDate(UUID id, Date matchStartDate) {
        ScoreboardIndex scoreboardIndex = new ScoreboardIndex(id, matchStartDate);
        Match match = tableScoreboard.get(scoreboardIndex);
        if (match == null) {
            throw new BusinessException(MATCH_NOT_FOUND.getKey());
        }
        return match;
    }

    public List<Match> getAll() {
        List<Match> allMatches = new ArrayList<>();
        for (Map.Entry<ScoreboardIndex, Match> entry : tableScoreboard.entrySet()) {
            allMatches.add(entry.getValue());
        }
        return allMatches;
    }
}
