package org.scoreboard.service.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.scoreboard.repository.MatchRepository;
import org.scoreboard.repository.ScoreboardRepository;

import static org.mockito.Mockito.*;

class MatchServiceImplTest {

    private MatchServiceImpl matchService;
    private MatchRepository matchRepository;
    private ScoreboardRepository scoreboardRepository;

    @BeforeEach
    void init() {
        matchRepository = mock(MatchRepository.class);
        scoreboardRepository = mock(ScoreboardRepository.class);
        matchService = new MatchServiceImpl(matchRepository, scoreboardRepository);
    }

    @Test
    void addMatch_shouldAddMath_returnMatchId() {
    }

    @Test
    void addMatch_whenIncorrectRequest_shouldThrowBusinessException() {
    }

    @Test
    void matchStart_shouldAddMatchToScoreboard_returnMatchInfo() {
    }

    @Test
    void matchFinish_shouldUpdateMatchAndDeleteFromScoreboard() {
    }

    @Test
    void matchFinish_whenIncorrectRequest_shouldThrowBusinessException() {
    }

    @Test
    void updateScore_shouldUpdateTeamsScore() {
    }

    @Test
    void updateScore_whenIncorrectScore_shouldThrowBusinessException() {
    }

    @Test
    void updateMatchStatus_shouldUpdateMatchStatus() {
    }

    @Test
    void updateMatchStatus_whenIncorrectScore_shouldThrowBusinessException() {
    }

    @Test
    void getAllMatches_shouldReturnAllMatches() {
    }

    @Test
    void getScoreboard_shouldReturnAllMatchesOnScoreboard() {
    }
}