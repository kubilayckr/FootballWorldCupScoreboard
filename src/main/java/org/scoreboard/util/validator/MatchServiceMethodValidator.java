package org.scoreboard.util.validator;

import org.scoreboard.exception.BusinessException;
import org.scoreboard.model.request.AddMatchRequest;
import org.scoreboard.model.request.FinishMatchRequest;
import org.scoreboard.model.request.UpdateMatchStatusRequest;
import org.scoreboard.model.request.UpdateScoreRequest;

import static org.scoreboard.exception.BusinessException.ServiceException.*;
import static org.scoreboard.exception.BusinessException.ServiceException.MATCH_STATUS_CANNOT_BE_NULL;

public class MatchServiceMethodValidator {

    private static final String EMPTY_STRING = "";

    private MatchServiceMethodValidator() {
    }

    public static void validateAddMatchRequest(AddMatchRequest request) {
        if (request.getHomeTeam() == null || EMPTY_STRING.equals(request.getHomeTeam())) {
            throw new BusinessException(HOME_TEAM_NAME_CANNOT_BE_EMPTY.getKey());
        } else if (request.getAwayTeam() == null) {
            throw new BusinessException(AWAY_TEAM_NAME_CANNOT_BE_EMPTY.getKey());
        } else if (request.getMatchDate() == null) {
            throw new BusinessException(MATCH_DATE_CANNOT_BE_NULL.getKey());
        }
    }

    public static void validateFinishMatchRequest(FinishMatchRequest request) {
        if (request.getId() == null) {
            throw new BusinessException(MATCH_ID_CANNOT_BE_NULL.getKey());
        } else if (request.getHomeTeamScore() == null) {
            throw new BusinessException(HOME_TEAM_SCORE_CANNOT_BE_EMPTY.getKey());
        } else if (request.getAwayTeamScore() == null) {
            throw new BusinessException(AWAY_TEAM_SCORE_CANNOT_BE_EMPTY.getKey());
        } else if (request.getHomeTeamScore() < 0 || request.getAwayTeamScore() < 0) {
            throw new BusinessException(SCORE_CANNOT_NEGATIVE.getKey());
        }
    }

    public static void validateUpdateScoreRequest(UpdateScoreRequest request) {
        if (request.getHomeTeamScore() == null) {
            throw new BusinessException(HOME_TEAM_SCORE_CANNOT_BE_EMPTY.getKey());
        } else if (request.getAwayTeamScore() == null) {
            throw new BusinessException(AWAY_TEAM_SCORE_CANNOT_BE_EMPTY.getKey());
        } else if (request.getHomeTeamScore() < 0 || request.getAwayTeamScore() < 0) {
            throw new BusinessException(SCORE_CANNOT_NEGATIVE.getKey());
        }
    }

    public static void validateUpdateMatchStatusRequest(UpdateMatchStatusRequest request) {
        if (request.getId() == null) {
            throw new BusinessException(MATCH_ID_CANNOT_BE_NULL.getKey());
        } else if (request.getMatchStatus() == null) {
            throw new BusinessException(MATCH_STATUS_CANNOT_BE_NULL.getKey());
        }
    }
}
