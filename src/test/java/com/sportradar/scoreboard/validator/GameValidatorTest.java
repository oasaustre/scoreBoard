package com.sportradar.scoreboard.validator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import com.sportradar.scoreboard.exception.ValidationGameException;

class GameValidatorTest {

	private static final String TEAM_NAME_1 = "Spain";
	private static final String TEAM_NAME_2 = "Sri Lanka";
	private static final int SCORE_VALID_1 = 0;
	private static final int SCORE_VALID_2 = 5;
	private static final int SCORE_INVALID = -3;

	@Test
	void givenGameValidator_whenNameTeamIsCorrect_thenReturnTrue() {

		assertTrue(GameValidador.validateNameTeam(TEAM_NAME_1));
		assertTrue(GameValidador.validateNameTeam(TEAM_NAME_2));
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

		assertTrue(GameValidador.validateScore(SCORE_VALID_1));
		assertTrue(GameValidador.validateScore(SCORE_VALID_2));
	}

	@Test
	void givenGameValidator_whenScoreLessThanZero_thenReturnValidationException() {

		Exception ex = assertThrows(ValidationGameException.class, () -> {
			GameValidador.validateScore(SCORE_INVALID);
		});

		assertEquals(ex.getMessage(), String.format("%s:%d", ValidationGameException.SCORE_VALID, SCORE_INVALID));
	}
}
