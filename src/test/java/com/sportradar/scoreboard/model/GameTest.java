package com.sportradar.scoreboard.model;

import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

/**
 * The following test cases are applied: 
 * - Compare two identical games. 
 * - Create a new game checking that the properties are correct with the initial score. 
 * - Create a new game with the wrong home team name checking that it throws an exception. 
 * - Create a new game with the wrong away team name checking that it throws an exception. 
 * - Create a new game with the wrong home score checking that it throws an exception. 
 * - Create a new game with the wrong away score checking that it throws an exception.
 * 
 * @author oasaustre
 *
 */
public class GameTest {

	@Test
	void givenTwoGames_whenCompareGames_thenGamesAreTheSame() {
		fail("Not implemented!");
	}

	@Test
	void giveGameWithCorrectData_whenCreateNewGame_thenReturnCorrectPropertiesAndInitialScore() {
		fail("Not implemented!");
	}

	@Test
	void giveGameWithIncorrectHomeTeam_whenNewGame_thenReturnValidationGameException() {
		fail("Not implemented!");
	}

	@Test
	void giveGameWithIncorrectAwayTeam_whenNewGame_thenReturnValidationGameException() {
		fail("Not implemented!");
	}

	@Test
	void giveGameWithIncorrectScoreHomeTeam_whenNewGame_thenReturnValidationGameException() {
		fail("Not implemented!");
	}

	@Test
	void giveGameWithIncorrectScoreAwayTeam_whenNewGame_thenReturnValidationGameException() {
		fail("Not implemented!");
	}
}
