package org.scoreboard.service.impl;

import org.scoreboard.mapper.MatchMapper;
import org.scoreboard.model.dto.MatchDTO;
import org.scoreboard.model.request.AddMatchRequest;
import org.scoreboard.model.request.FinishMatchRequest;
import org.scoreboard.model.request.UpdateMatchStatusRequest;
import org.scoreboard.model.request.UpdateScoreRequest;
import org.scoreboard.repository.MatchRepository;
import org.scoreboard.repository.ScoreboardRepository;
import org.scoreboard.service.MatchService;


import java.util.List;
import java.util.UUID;

public class MatchServiceImpl implements MatchService {

    private final MatchRepository matchRepository;
    private final ScoreboardRepository scoreBoardRepository;
    private final MatchMapper matchMapper = new MatchMapper();

    public MatchServiceImpl(MatchRepository matchRepository, ScoreboardRepository scoreBoardRepository) {
        this.scoreBoardRepository = scoreBoardRepository;
        this.matchRepository = matchRepository;
    }

    public UUID addMatch(AddMatchRequest request) {
        return null;
    }

    public MatchDTO startMatch(UUID id) {
        return null;
    }

    public void finishMatch(FinishMatchRequest request) {
    }

    public MatchDTO updateScore(UpdateScoreRequest request) {
        return null;
    }

    public MatchDTO updateMatchStatus(UpdateMatchStatusRequest request) {
        return null;
    }

    public List<MatchDTO> getAllMatches() {
        return null;
    }

    public List<MatchDTO> getScoreboard() {
        return null;
    }
}
