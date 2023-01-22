package org.scoreboard.service.impl;

import org.scoreboard.mapper.MatchMapper;
import org.scoreboard.model.dto.MatchDTO;
import org.scoreboard.model.entity.Match;
import org.scoreboard.model.enums.MatchStatus;
import org.scoreboard.model.request.AddMatchRequest;
import org.scoreboard.model.request.FinishMatchRequest;
import org.scoreboard.model.request.UpdateMatchStatusRequest;
import org.scoreboard.model.request.UpdateScoreRequest;
import org.scoreboard.repository.MatchRepository;
import org.scoreboard.repository.ScoreboardRepository;
import org.scoreboard.service.MatchService;
import org.scoreboard.util.comparator.MatchCustomComparator;
import org.scoreboard.util.validator.MatchServiceMethodValidator;

import java.util.Date;
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
        MatchServiceMethodValidator.validateAddMatchRequest(request);
        Match match = matchMapper.toModel(request);
        match.setMatchStatus(MatchStatus.NOT_STARTED);
        match = matchRepository.addMatch(match);
        return match.getId();
    }

    public MatchDTO startMatch(UUID id) {
        Match match = matchRepository.getById(id);
        match.setMatchStartDate(new Date());
        match.setHomeTeamScore(0);
        match.setAwayTeamScore(0);
        match.setMatchStatus(MatchStatus.FIRST_HALF);
        matchRepository.updateMatch(match);
        match = scoreBoardRepository.addMatch(match);
        return matchMapper.toDTO(match);
    }

    public void finishMatch(FinishMatchRequest request) {
        MatchServiceMethodValidator.validateFinishMatchRequest(request);
        Match match = matchRepository.getById(request.getId());
        match.setMatchEndDate(new Date());
        match.setHomeTeamScore(request.getHomeTeamScore());
        match.setAwayTeamScore(request.getAwayTeamScore());
        match.setMatchStatus(MatchStatus.FINISHED);
        matchRepository.updateMatch(match);
        scoreBoardRepository.deleteMatch(match);
    }

    public MatchDTO updateScore(UpdateScoreRequest request) {
        MatchServiceMethodValidator.validateUpdateScoreRequest(request);
        Match match = matchRepository.getById(request.getId());
        match.setHomeTeamScore(request.getHomeTeamScore());
        match.setAwayTeamScore(request.getAwayTeamScore());
        matchRepository.updateMatch(match);
        match = scoreBoardRepository.updateMatch(match);
        return matchMapper.toDTO(match);
    }

    public MatchDTO updateMatchStatus(UpdateMatchStatusRequest request) {
        MatchServiceMethodValidator.validateUpdateMatchStatusRequest(request);
        Match match = matchRepository.getById(request.getId());
        match.setMatchStatus(request.getMatchStatus());
        matchRepository.updateMatch(match);
        match = scoreBoardRepository.updateMatch(match);
        return matchMapper.toDTO(match);
    }

    public List<MatchDTO> getAllMatches() {
        List<Match> all = matchRepository.getAll();
        return all.stream().map(matchMapper::toDTO).sorted(new MatchCustomComparator()).toList();
    }

    public List<MatchDTO> getScoreboard() {
        List<Match> all = scoreBoardRepository.getAll();
        return all.stream().map(matchMapper::toDTO).toList();
    }
}
