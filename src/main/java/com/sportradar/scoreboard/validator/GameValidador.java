package com.sportradar.scoreboard.validator;

import org.apache.commons.lang3.StringUtils;

import com.sportradar.scoreboard.exception.ValidationGameException;

/**
 * Represents a game properties validator
 * 
 * @author oasaustre
 *
 */
public class GameValidador {

	private GameValidador() {

	}

	public static boolean validateNameTeam(String nameTeam) {

		if (!isCorrectTeamName(nameTeam)) {
			throw new ValidationGameException(
					String.format("%s:%s", ValidationGameException.TEAM_NAME_VALID, nameTeam));
		}

		return true;
	}

	public static boolean validateScore(int score) {
		if (score < 0) {
			throw new ValidationGameException(String.format("%s:%d", ValidationGameException.SCORE_VALID, score));
		}

		return true;
	}

	private static boolean isCorrectTeamName(String nameTeam) {
		return !StringUtils.isBlank(nameTeam);
	}
}