package com.sportradar.scoreboard.validator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import com.sportradar.scoreboard.exception.ValidationGameException;

class GameValidatorTest {

	@Test
	void givenGameValidator_whenNameTeamIsCorrect_thenReturnTrue() {
		String nameTeamCorrect1 = "Spain";
		String nameTeamCorrect2 = "Sri Lanka";

		assertTrue(GameValidador.validateNameTeam(nameTeamCorrect1));
		assertTrue(GameValidador.validateNameTeam(nameTeamCorrect2));
	}

	@Test
	void givenGameValidator_whenNameTeamInCorrect_thenReturnValidationException() {
		String nameTeamIncorrect1 = "";
		String nameTeamIncorrect2 = null;

		Exception ex1 = assertThrows(ValidationGameException.class, () -> {
			GameValidador.validateNameTeam(nameTeamIncorrect1);
		});

		Exception ex2 = assertThrows(ValidationGameException.class, () -> {
			GameValidador.validateNameTeam(nameTeamIncorrect2);
		});

		assertEquals(ex1.getMessage(),
				String.format("%s:%s", ValidationGameException.TEAM_NAME_VALID, nameTeamIncorrect1));
		assertEquals(ex2.getMessage(),
				String.format("%s:%s", ValidationGameException.TEAM_NAME_VALID, nameTeamIncorrect2));
	}

	@Test
	void givenGameValidator_whenScoreGreaterEqualThanZero_thenReturnTrue() {
		int score1 = 0;
		int score2 = 5;

		assertTrue(GameValidador.validateScore(score1));
		assertTrue(GameValidador.validateScore(score2));
	}

	@Test
	void givenGameValidator_whenScoreLessThanZero_thenReturnValidationException() {
		int score = -3;

		Exception ex = assertThrows(ValidationGameException.class, () -> {
			GameValidador.validateScore(score);
		});

		assertEquals(ex.getMessage(), String.format("%s:%d", ValidationGameException.SCORE_VALID, score));
	}
}
