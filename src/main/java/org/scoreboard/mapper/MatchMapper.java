package org.scoreboard.mapper;

import org.scoreboard.model.dto.MatchDTO;
import org.scoreboard.model.entity.Match;
import org.scoreboard.model.request.AddMatchRequest;

public class MatchMapper {

    public Match toModel(MatchDTO dto) {
        Match model = new Match();
        if (dto != null) {
            model.setId(dto.getId());
            model.setHomeTeam(dto.getHomeTeam());
            model.setAwayTeam(dto.getAwayTeam());
            model.setHomeTeamScore(dto.getHomeTeamScore());
            model.setAwayTeamScore(dto.getAwayTeamScore());
            model.setMatchDate(dto.getMatchDate());
            model.setMatchStartDate(dto.getMatchStartDate());
            model.setMatchEndDate(dto.getMatchEndDate());
            model.setMatchStatus(dto.getMatchStatus());
        }
        return model;
    }

    public MatchDTO toDTO(Match model) {
        MatchDTO dto = new MatchDTO();
        if (model != null) {
            dto.setId(model.getId());
            dto.setHomeTeam(model.getHomeTeam());
            dto.setAwayTeam(model.getAwayTeam());
            dto.setHomeTeamScore(model.getHomeTeamScore());
            dto.setAwayTeamScore(model.getAwayTeamScore());
            dto.setMatchDate(model.getMatchDate());
            dto.setMatchStartDate(model.getMatchStartDate());
            dto.setMatchEndDate(model.getMatchEndDate());
            dto.setMatchStatus(model.getMatchStatus());
        }
        return dto;
    }

    public Match update(Match existingModel, Match model) {
        if (model != null) {
            existingModel.setVersion(model.getVersion());
            existingModel.setHomeTeam(model.getHomeTeam());
            existingModel.setAwayTeam(model.getAwayTeam());
            existingModel.setHomeTeamScore(model.getHomeTeamScore());
            existingModel.setAwayTeamScore(model.getAwayTeamScore());
            existingModel.setMatchDate(model.getMatchDate());
            existingModel.setMatchStartDate(model.getMatchStartDate());
            existingModel.setMatchEndDate(model.getMatchEndDate());
            existingModel.setMatchStatus(model.getMatchStatus());
        }
        return model;
    }

    public Match toModel(AddMatchRequest addMatchRequest) {
        Match model = new Match();
        if (addMatchRequest != null) {
            model.setHomeTeam(addMatchRequest.getHomeTeam());
            model.setAwayTeam(addMatchRequest.getAwayTeam());
            model.setMatchDate(addMatchRequest.getMatchDate());
        }
        return model;
    }
}
