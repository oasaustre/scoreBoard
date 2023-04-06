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
	private static final String TEAM_TOO_LONG = "Lorem ipsum dolor sit amet, consectetuer adipiscing elit. "
			+ "Aenean commodo ligula eget dolor. Aenean massa. Cum sociis natoque penatibus et magnis dis "
			+ "parturient montes, nascetur ridiculus mus.";

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

		assertEquals(String.format("%s:%s", ValidationGameException.TEAM_NAME_VALID, nameTeamIncorrect1),
				ex1.getMessage());
		assertEquals(String.format("%s:%s", ValidationGameException.TEAM_NAME_VALID, nameTeamIncorrect2),
				ex2.getMessage());
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

		assertEquals(String.format("%s:%d", ValidationGameException.SCORE_VALID, SCORE_INVALID), ex.getMessage());
	}

	@Test
	void givenGameValidator_whenNameTeamTooLong_thenReturnValidationException() {

		Exception ex = assertThrows(ValidationGameException.class, () -> {
			GameValidador.validateLengthNameTeam(TEAM_TOO_LONG);
		});

		assertEquals(String.format("%s - Expected: %d Found: %d", ValidationGameException.TEAM_NAME_TOO_LONG, 100,
				TEAM_TOO_LONG.length()), ex.getMessage());
	}
}
