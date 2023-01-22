package org.scoreboard.service;

import org.scoreboard.model.dto.MatchDTO;
import org.scoreboard.model.request.AddMatchRequest;
import org.scoreboard.model.request.FinishMatchRequest;
import org.scoreboard.model.request.UpdateMatchStatusRequest;
import org.scoreboard.model.request.UpdateScoreRequest;

import java.util.List;
import java.util.UUID;

public interface MatchService {

    UUID addMatch(AddMatchRequest addMatchRequest);

    MatchDTO startMatch(UUID id);

    void finishMatch(FinishMatchRequest request);

    MatchDTO updateScore(UpdateScoreRequest request);

    MatchDTO updateMatchStatus(UpdateMatchStatusRequest request);

    List<MatchDTO> getAllMatches();

    List<MatchDTO> getScoreboard();
}
