package com.sportradar.scoreboard.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import com.sportradar.scoreboard.exception.ValidationGameException;

/**
 * The following test cases are applied: - Compare two identical games. - Create
 * a new game checking that the properties are correct with the initial score. -
 * Create a new game with the wrong home team name checking that it throws an
 * exception. - Create a new game with the wrong away team name checking that it
 * throws an exception. - Create a new game with the wrong home score checking
 * that it throws an exception. - Create a new game with the wrong away score
 * checking that it throws an exception.
 * 
 * @author oasaustre
 *
 */
class GameTest {

	private static final String HOME_TEAM = "Spain";
	private static final String AWAY_TEAM = "Brazil";
	private static final String OTHER_HOME_TEAM = "Mexico";
	private static final String OTHER_AWAY_TEAM = "Canada";
	private static final int HOME_SCORE = 5;
	private static final int AWAY_SCORE = 1;
	private static final int NEGATIVE_SCORE = -5;

	@Test
	void givenThreeGames_whenCompareGames_thenGamesAreTheSame() {
		Game firstGame = new Game(HOME_TEAM, AWAY_TEAM, HOME_SCORE, AWAY_SCORE);
		Game secondGame = new Game(HOME_TEAM, AWAY_TEAM, HOME_SCORE, AWAY_SCORE);

		Game thirdGame = new Game(HOME_TEAM, AWAY_TEAM);
		thirdGame.setHomeScore(HOME_SCORE);
		thirdGame.setAwayScore(AWAY_SCORE);

		assertEquals(firstGame, secondGame);
		assertEquals(thirdGame, secondGame);
		assertEquals(thirdGame, firstGame);
		assertEquals(firstGame.hashCode(), secondGame.hashCode());
		assertEquals(thirdGame.hashCode(), secondGame.hashCode());
		assertEquals(thirdGame.hashCode(), firstGame.hashCode());
	}

	@Test
	void giveGameWithCorrectData_whenCreateNewGame_thenReturnCorrectPropertiesAndInitialScore() {
		Game game = new Game(HOME_TEAM, AWAY_TEAM);

		assertEquals(HOME_TEAM, game.getHomeTeam());
		assertEquals(AWAY_TEAM, game.getAwayTeam());
		assertEquals(0, game.getHomeScore());
		assertEquals(0, game.getAwayScore());
	}

	@Test
	void giveGameWithIncorrectHomeTeam_whenNewGame_thenReturnValidationGameException() {
		Exception ex = assertThrows(ValidationGameException.class, () -> {
			new Game(null, AWAY_TEAM);
		});
		assertEquals(String.format("%s:%s", ValidationGameException.TEAM_NAME_VALID, null), ex.getMessage());
	}

	@Test
	void giveGameWithIncorrectAwayTeam_whenNewGame_thenReturnValidationGameException() {
		Exception ex = assertThrows(ValidationGameException.class, () -> {
			new Game(HOME_TEAM, null);
		});
		assertEquals(String.format("%s:%s", ValidationGameException.TEAM_NAME_VALID, null), ex.getMessage());
	}

	@Test
	void giveGameWithIncorrectScoreHomeTeam_whenNewGame_thenReturnValidationGameException() {
		Exception ex = assertThrows(ValidationGameException.class, () -> {
			createGame(HOME_TEAM, AWAY_TEAM, NEGATIVE_SCORE, AWAY_SCORE);
		});
		assertEquals(String.format("%s:%d", ValidationGameException.SCORE_VALID, NEGATIVE_SCORE), ex.getMessage());
	}

	@Test
	void giveGameWithIncorrectScoreAwayTeam_whenNewGame_thenReturnValidationGameException() {
		Exception ex = assertThrows(ValidationGameException.class, () -> {
			createGame(HOME_TEAM, AWAY_TEAM, HOME_SCORE, NEGATIVE_SCORE);
		});
		assertEquals(String.format("%s:%d", ValidationGameException.SCORE_VALID, NEGATIVE_SCORE), ex.getMessage());
	}

	@Test
	void giveGameCorrect_whenNewGame_thenCheckGameHaveCreationTime() {
		Game firstGame = new Game(HOME_TEAM, AWAY_TEAM);
		Game secondGame = new Game(OTHER_HOME_TEAM, OTHER_AWAY_TEAM, HOME_SCORE, AWAY_SCORE);

		assertTrue(firstGame.getCreationTime() > 0);
		assertTrue(secondGame.getCreationTime() > 0);
	}

	@Test
	void giveGameCorrect_whenNewGame_thenCheckKeyHaveCorrectFormat() {
		Game firstGame = new Game(HOME_TEAM, AWAY_TEAM);
		Game secondGame = new Game(OTHER_HOME_TEAM, OTHER_AWAY_TEAM, HOME_SCORE, AWAY_SCORE);

		assertEquals(String.format("%s-%s", HOME_TEAM, AWAY_TEAM), firstGame.getKey());
		assertEquals(String.format("%s-%s", OTHER_HOME_TEAM, OTHER_AWAY_TEAM), secondGame.getKey());
	}
	
	@Test
	void giveGameWithNameTeamTooLong_whenNewGame_thenThrowsValidationGameException() {
		fail("Not implements!!");
	}

	private Game createGame(String homeTeam, String awayTeam, int homeScore, int awayScore) {
		Game game = new Game(homeTeam, awayTeam);

		game.setHomeScore(homeScore);
		game.setAwayScore(awayScore);

		return game;
	}
}
