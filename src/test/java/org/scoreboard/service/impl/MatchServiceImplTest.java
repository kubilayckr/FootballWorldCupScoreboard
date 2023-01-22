package org.scoreboard.service.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.scoreboard.exception.BusinessException;
import org.scoreboard.model.dto.MatchDTO;
import org.scoreboard.model.entity.Match;
import org.scoreboard.model.enums.MatchStatus;
import org.scoreboard.model.request.AddMatchRequest;
import org.scoreboard.model.request.FinishMatchRequest;
import org.scoreboard.model.request.UpdateMatchStatusRequest;
import org.scoreboard.model.request.UpdateScoreRequest;
import org.scoreboard.repository.MatchRepository;
import org.scoreboard.repository.ScoreboardRepository;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
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
        Match newMatch = getNewMatch();

        when(matchRepository.addMatch(any(Match.class))).thenReturn(newMatch);

        UUID actualId = matchService.addMatch(getAddMatchRequest(false));

        assertEquals(newMatch.getId(), actualId);
    }

    @Test
    void addMatch_whenIncorrectRequest_shouldThrowBusinessException() {
        Match newMatch = getNewMatch();
        AddMatchRequest addMatchRequest = getAddMatchRequest(true);

        when(matchRepository.addMatch(any(Match.class))).thenReturn(newMatch);

        assertThrows(BusinessException.class, () -> matchService.addMatch(addMatchRequest));
    }

    @Test
    void matchStart_shouldAddMatchToScoreboard_returnMatchInfo() {
        Match newMatch = getNewMatch();
        Match startedMatch = getStartedMatch();

        when(matchRepository.getById(any(UUID.class))).thenReturn(newMatch);
        when(matchRepository.updateMatch(any(Match.class))).thenReturn(startedMatch);
        when(scoreboardRepository.addMatch(any(Match.class))).thenReturn(startedMatch);

        MatchDTO actual = matchService.startMatch(getUUID());

        assertEquals(MatchStatus.FIRST_HALF, actual.getMatchStatus());
        assertEquals(startedMatch.getMatchStartDate(), actual.getMatchStartDate());
    }

    @Test
    void matchFinish_shouldUpdateMatchAndDeleteFromScoreboard() {
        Match startedMatch = getStartedMatch();
        Match finishedMatch = getFinishedMatch();
        FinishMatchRequest finishMatchRequest = getFinishMatchRequest(false);

        when(matchRepository.getById(any(UUID.class))).thenReturn(startedMatch);
        when(matchRepository.updateMatch(any(Match.class))).thenReturn(finishedMatch);
        doNothing().when(scoreboardRepository).deleteMatch(any(Match.class));

        matchService.finishMatch(finishMatchRequest);

        verify( scoreboardRepository, times(1)).deleteMatch(startedMatch);
    }

    @Test
    void matchFinish_whenIncorrectRequest_shouldThrowBusinessException() {
        Match startedMatch = getStartedMatch();
        Match finishedMatch = getFinishedMatch();
        FinishMatchRequest finishMatchRequest = getFinishMatchRequest(true);

        when(matchRepository.getById(any(UUID.class))).thenReturn(startedMatch);
        when(matchRepository.updateMatch(any(Match.class))).thenReturn(finishedMatch);
        doNothing().when(scoreboardRepository).deleteMatch(any(Match.class));

        assertThrows(BusinessException.class, () -> matchService.finishMatch(finishMatchRequest));
    }

    @Test
    void updateScore_shouldUpdateTeamsScore() {
        Match startedMatch = getStartedMatch();
        Match match = getMatch(MatchStatus.FIRST_HALF);
        UpdateScoreRequest updateScoreRequest = getUpdateScoreRequest(false);

        when(matchRepository.getById(any(UUID.class))).thenReturn(startedMatch);
        when(matchRepository.updateMatch(any(Match.class))).thenReturn(match);
        when(scoreboardRepository.updateMatch(any(Match.class))).thenReturn(match);

        MatchDTO actual = matchService.updateScore(updateScoreRequest);

        assertEquals(updateScoreRequest.getAwayTeamScore(), actual.getAwayTeamScore());
        assertEquals(updateScoreRequest.getHomeTeamScore(), actual.getHomeTeamScore());
    }

    @Test
    void updateScore_whenIncorrectScore_shouldThrowBusinessException() {
        Match startedMatch = getStartedMatch();
        Match match = getMatch(MatchStatus.SECOND_HALF);
        UpdateScoreRequest incorrectUpdateScoreRequest = getUpdateScoreRequest(true);

        when(matchRepository.getById(any(UUID.class))).thenReturn(startedMatch);
        when(matchRepository.updateMatch(any(Match.class))).thenReturn(match);
        when(scoreboardRepository.updateMatch(any(Match.class))).thenReturn(match);

        Exception exception = assertThrows(BusinessException.class, () -> matchService.updateScore(incorrectUpdateScoreRequest));
        assertEquals("Score cannot be negative!", exception.getMessage());
    }

    @Test
    void updateMatchStatus_shouldUpdateMatchStatus() {
        Match startedMatch = getStartedMatch();
        Match match = getMatch(MatchStatus.DELAYED);
        UpdateMatchStatusRequest updateMatchStatusRequest = getUpdateMatchStatusRequest(false);

        when(matchRepository.getById(any(UUID.class))).thenReturn(startedMatch);
        when(matchRepository.updateMatch(any(Match.class))).thenReturn(match);
        when(scoreboardRepository.updateMatch(any(Match.class))).thenReturn(match);

        MatchDTO actual = matchService.updateMatchStatus(updateMatchStatusRequest);

        assertEquals(updateMatchStatusRequest.getMatchStatus(), actual.getMatchStatus());
    }

    @Test
    void updateMatchStatus_whenIncorrectScore_shouldThrowBusinessException() {
        Match startedMatch = getStartedMatch();
        Match match = getMatch(MatchStatus.DELAYED);
        UpdateMatchStatusRequest updateMatchStatusRequest = getUpdateMatchStatusRequest(true);

        when(matchRepository.getById(any(UUID.class))).thenReturn(startedMatch);
        when(matchRepository.updateMatch(any(Match.class))).thenReturn(match);
        when(scoreboardRepository.updateMatch(any(Match.class))).thenReturn(match);

        assertThrows(BusinessException.class, () -> matchService.updateMatchStatus(updateMatchStatusRequest));
    }

    @Test
    void getAllMatches_shouldReturnAllMatches() {
        List<Match> matches = new ArrayList<>();
        Match match = getMatch(MatchStatus.FINISHED);
        matches.add(match);

        when(matchRepository.getAll()).thenReturn(matches);

        List<MatchDTO> actual = matchService.getAllMatches();

        assertEquals(matches.size(), actual.size());
    }

    @Test
    void getScoreboard_shouldReturnAllMatchesOnScoreboard() {
        List<Match> matches = new ArrayList<>();
        Match match = getMatch(MatchStatus.FINISHED);
        matches.add(match);

        when(scoreboardRepository.getAll()).thenReturn(matches);

        List<MatchDTO> actual = matchService.getScoreboard();

        assertEquals(matches.size(), actual.size());
    }

    private Match getMatch(MatchStatus matchStatus) {
        return new Match(getUUID(), 3, "Argentina", "Portugal", 5, 5, getMatchDate(), getMatchStartDate(), getMatchEndDate(), matchStatus);
    }

    private Match getNewMatch() {
        return new Match(getUUID(), 1, "Argentina", "Portugal", null, null, getMatchDate(), null, null, MatchStatus.NOT_STARTED);
    }

    private Match getStartedMatch() {
        return new Match(getUUID(), 2, "Argentina", "Portugal", 0, 0, getMatchDate(), getMatchStartDate(), null, MatchStatus.FIRST_HALF);
    }

    private Match getFinishedMatch() {
        return new Match(getUUID(), 3, "Argentina", "Portugal", 2, 3, getMatchDate(), getMatchStartDate(), getMatchEndDate(), MatchStatus.FINISHED);
    }

    private AddMatchRequest getAddMatchRequest(Boolean isIncorrect) {
        if (isIncorrect) {
            return new AddMatchRequest("", "", getMatchDate());
        } else {
            return new AddMatchRequest("Argentina", "Portugal", getMatchDate());
        }
    }

    private FinishMatchRequest getFinishMatchRequest(Boolean isIncorrect) {
        if (isIncorrect) {
            return new FinishMatchRequest(getUUID(), null, 5);
        } else {
            return new FinishMatchRequest(getUUID(), 5, 5);
        }
    }

    private UpdateScoreRequest getUpdateScoreRequest(Boolean isIncorrect) {
        if (isIncorrect) {
            return new UpdateScoreRequest(getUUID(), -5, 5);
        } else {
            return new UpdateScoreRequest(getUUID(), 5, 5);
        }
    }

    private UpdateMatchStatusRequest getUpdateMatchStatusRequest(Boolean isIncorrect) {
        if (isIncorrect) {
            return new UpdateMatchStatusRequest(getUUID(), null);
        } else {
            return new UpdateMatchStatusRequest(getUUID(), MatchStatus.DELAYED);
        }

    }

    private UUID getUUID() {
        return UUID.randomUUID();
    }

    private Date getMatchDate() {
        return new Date();
    }

    private Date getMatchStartDate() {
        return new Date();
    }

    private Date getMatchEndDate() {
        LocalDateTime dateTime = LocalDateTime.now().plus(Duration.of(100, ChronoUnit.MINUTES));
        return Date.from(dateTime.atZone(ZoneId.systemDefault()).toInstant());
    }
}