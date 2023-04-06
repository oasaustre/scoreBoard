package com.sportradar.scoreboard;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.sportradar.scoreboard.exception.AlreadyGameExistsException;
import com.sportradar.scoreboard.exception.GameNotFoundException;
import com.sportradar.scoreboard.exception.ValidationGameException;
import com.sportradar.scoreboard.factory.ScoreBoardMemoryRepositoryFactory;
import com.sportradar.scoreboard.model.Game;

class FootballWorldCupScoreboardTest {
	
	private static final int AWAY_SCORE = 2;
	private static final int HOME_SCORE = 4;
	private static final int EXPECTED_AWAY_SCORE = 2;
	private static final int EXPECTED_HOME_SCORE = 4;

	private static final String AWAY_TEAM = "Brazil";
	private static final String HOME_TEAM = "Spain";

	private static final String HOME_TEAM_SUMMARY_1 = "Mexico";
	private static final String AWAY_TEAM_SUMMARY_1 = "Canada";
	private static final String HOME_TEAM_SUMMARY_2 = "Spain";
	private static final String AWAY_TEAM_SUMMARY_2 = "Brazil";
	private static final String HOME_TEAM_SUMMARY_3 = "Germany";
	private static final String AWAY_TEAM_SUMMARY_3 = "France";
	private static final String HOME_TEAM_SUMMARY_4 = "Uruguay";
	private static final String AWAY_TEAM_SUMMARY_4 = "Italy";
	private static final String AWAY_TEAM_SUMMARY_5 = "Argentina";
	private static final String HOME_TEAM_SUMMARY_5 = "Australia";

	private static final int HOME_SCORE_SUMMARY_1 = 0;
	private static final int AWAY_SCORE_SUMMARY_1 = 5;
	private static final int HOME_SCORE_SUMMARY_2 = 10;
	private static final int AWAY_SCORE_SUMMARY_2 = 2;
	private static final int HOME_SCORE_SUMMARY_3 = 2;
	private static final int AWAY_SCORE_SUMMARY_3 = 2;
	private static final int HOME_SCORE_SUMMARY_4 = 6;
	private static final int AWAY_SCORE_SUMMARY_4 = 6;
	private static final int HOME_SCORE_SUMMARY_5 = 3;
	private static final int AWAY_SCORE_SUMMARY_5 = 1;

	private IScoreboard scoreBoard;

	@BeforeEach
	void setUp() {

		scoreBoard = new FootballWorldCupScoreBoard(new ScoreBoardMemoryRepositoryFactory());
	}

	@Test
	void givenScoreBoard_whenStartGame_thenAddGameWithInitialScoreinLiveScoreBoard() {
		Game expectedGame = new Game(HOME_TEAM, AWAY_TEAM, 0, 0);

		scoreBoard.startGame(HOME_TEAM, AWAY_TEAM);
		List<Game> summaryTotalScore = scoreBoard.getOrdededSummaryOfTotalScore();

		assertEquals(1, summaryTotalScore.size());
		assertEquals(expectedGame, summaryTotalScore.get(0));
	}

	@Test
	void givenScoreBoard_whenStartGameWithGameExisting_thenThrowsAlreadyGameExistsException() {

		scoreBoard.startGame(HOME_TEAM, AWAY_TEAM);

		Exception ex = assertThrows(AlreadyGameExistsException.class, () -> {
			scoreBoard.startGame(HOME_TEAM, AWAY_TEAM);
		});

		assertEquals(AlreadyGameExistsException.GAME_EXISTS, ex.getMessage());
	}

	@Test
	void givenScoreBoard_whenStartGameWithIncorrectHomeTeam_thenThrowsValidationGameException() {

		Exception ex1 = assertThrows(ValidationGameException.class, () -> {
			scoreBoard.startGame(null, AWAY_TEAM);
		});

		Exception ex2 = assertThrows(ValidationGameException.class, () -> {
			scoreBoard.startGame("", AWAY_TEAM);
		});

		assertEquals(String.format("%s:%s", ValidationGameException.TEAM_NAME_VALID, null), ex1.getMessage());
		assertEquals(String.format("%s:%s", ValidationGameException.TEAM_NAME_VALID, ""), ex2.getMessage());
	}

	@Test
	void givenScoreBoard_whenStartGameWithIncorrectAwayTeam_thenThrowsValidationGameException() {
		Exception ex1 = assertThrows(ValidationGameException.class, () -> {
			scoreBoard.startGame(HOME_TEAM, null);
		});

		Exception ex2 = assertThrows(ValidationGameException.class, () -> {
			scoreBoard.startGame(HOME_TEAM, "");
		});

		assertEquals(String.format("%s:%s", ValidationGameException.TEAM_NAME_VALID, null), ex1.getMessage());
		assertEquals(String.format("%s:%s", ValidationGameException.TEAM_NAME_VALID, ""), ex2.getMessage());
	}

	@Test
	void givenScoreBoard_whenFinishGameWithExistingGame_thenGameIsFinishedAndRemoveFromScoreBoard() {

		scoreBoard.startGame(HOME_TEAM, AWAY_TEAM);

		scoreBoard.finishGame(HOME_TEAM, AWAY_TEAM);

		List<Game> summaryGame = scoreBoard.getOrdededSummaryOfTotalScore();

		assertEquals(0, summaryGame.size());

	}

	@Test
	void givenScoreBoard_whenFinishGameWithNotExistingGame_thenThrowsGameNotFoundException() {

		scoreBoard.startGame(HOME_TEAM, AWAY_TEAM);

		Exception ex1 = assertThrows(GameNotFoundException.class, () -> {
			scoreBoard.finishGame("Mexico", "Canada");
		});

		Exception ex2 = assertThrows(GameNotFoundException.class, () -> {
			scoreBoard.finishGame("Spain", "Mexico");
		});

		Exception ex3 = assertThrows(GameNotFoundException.class, () -> {
			scoreBoard.finishGame("Alemania", "Brazil");
		});

		assertEquals(
				String.format("%s:%s", GameNotFoundException.GAME_NOT_FOUND, new Game("Mexico", "Canada").getKey()),
				ex1.getMessage());
		assertEquals(String.format("%s:%s", GameNotFoundException.GAME_NOT_FOUND, new Game("Spain", "Mexico").getKey()),
				ex2.getMessage());
		assertEquals(
				String.format("%s:%s", GameNotFoundException.GAME_NOT_FOUND, new Game("Alemania", "Brazil").getKey()),
				ex3.getMessage());
	}

	@Test
	void givenScoreBoard_whenFinishGameWithIncorrectHomeTeam_thenThrowsValidationGameException() {

		Exception ex1 = assertThrows(ValidationGameException.class, () -> {
			scoreBoard.finishGame(null, AWAY_TEAM);
		});

		Exception ex2 = assertThrows(ValidationGameException.class, () -> {
			scoreBoard.finishGame("", AWAY_TEAM);
		});

		assertEquals(String.format("%s:%s", ValidationGameException.TEAM_NAME_VALID, null), ex1.getMessage());
		assertEquals(String.format("%s:%s", ValidationGameException.TEAM_NAME_VALID, ""), ex2.getMessage());
	}

	@Test
	void givenScoreBoard_whenFinishGameWithIncorrectAwayTeam_thenThrowsValidationGameException() {
		Exception ex1 = assertThrows(ValidationGameException.class, () -> {
			scoreBoard.finishGame(HOME_TEAM, null);
		});

		Exception ex2 = assertThrows(ValidationGameException.class, () -> {
			scoreBoard.finishGame(HOME_TEAM, "");
		});

		assertEquals(String.format("%s:%s", ValidationGameException.TEAM_NAME_VALID, null), ex1.getMessage());
		assertEquals(String.format("%s:%s", ValidationGameException.TEAM_NAME_VALID, ""), ex2.getMessage());
	}

	@Test
	void givenScoreBoard_whenUpdateScoreWithExistingGame_thenGameChangeScoreInLiveScoreBoard() {
		Game game = new Game(HOME_TEAM, AWAY_TEAM);

		Game expectedGame = new Game(HOME_TEAM, AWAY_TEAM, 5, 2);

		scoreBoard.startGame(HOME_TEAM, AWAY_TEAM);

		game.setHomeScore(5);
		game.setAwayScore(2);

		scoreBoard.updateScore(HOME_TEAM, AWAY_TEAM, 5, 2);

		List<Game> summaryGame = scoreBoard.getOrdededSummaryOfTotalScore();

		assertEquals(1, summaryGame.size());

		assertEquals(summaryGame.get(0), expectedGame);
	}

	@Test
	void givenScoreBoard_whenUpdateScoreWithNotExistingGame_thenGameNotFoundException() {
		scoreBoard.startGame(HOME_TEAM, AWAY_TEAM);

		Exception ex1 = assertThrows(GameNotFoundException.class, () -> {
			scoreBoard.updateScore("Mexico", "Canada", 4, 5);
		});

		Exception ex2 = assertThrows(GameNotFoundException.class, () -> {
			scoreBoard.updateScore("Spain", "Mexico", 2, 1);
		});

		Exception ex3 = assertThrows(GameNotFoundException.class, () -> {
			scoreBoard.updateScore("Alemania", "Brazil", 3, 4);
		});

		assertEquals(
				String.format("%s:%s", GameNotFoundException.GAME_NOT_FOUND, new Game("Mexico", "Canada").getKey()),
				ex1.getMessage());
		assertEquals(String.format("%s:%s", GameNotFoundException.GAME_NOT_FOUND, new Game("Spain", "Mexico").getKey()),
				ex2.getMessage());
		assertEquals(
				String.format("%s:%s", GameNotFoundException.GAME_NOT_FOUND, new Game("Alemania", AWAY_TEAM).getKey()),
				ex3.getMessage());
	}

	@Test
	void givenScoreBoard_whenUpdateScoreWithHomeTeamIncorrect_thenThrowsValidationGameException() {
		Exception ex1 = assertThrows(ValidationGameException.class, () -> {
			scoreBoard.updateScore(null, AWAY_TEAM, 0, 1);
		});

		Exception ex2 = assertThrows(ValidationGameException.class, () -> {
			scoreBoard.updateScore("", AWAY_TEAM, 0, 1);
		});

		assertEquals(String.format("%s:%s", ValidationGameException.TEAM_NAME_VALID, null), ex1.getMessage());
		assertEquals(String.format("%s:%s", ValidationGameException.TEAM_NAME_VALID, ""), ex2.getMessage());
	}

	@Test
	void givenScoreBoard_whenUpdateScoreWithAwayTeamIncorrect_thenThrowsValidationGameException() {
		Exception ex1 = assertThrows(ValidationGameException.class, () -> {
			scoreBoard.updateScore(HOME_TEAM, null, 0, 1);
		});

		Exception ex2 = assertThrows(ValidationGameException.class, () -> {
			scoreBoard.updateScore(HOME_TEAM, "", 0, 1);
		});

		assertEquals(String.format("%s:%s", ValidationGameException.TEAM_NAME_VALID, null), ex1.getMessage());
		assertEquals(String.format("%s:%s", ValidationGameException.TEAM_NAME_VALID, ""), ex2.getMessage());
	}

	@Test
	void givenScoreBoard_whenUpdateScoreWithHomeScoreIncorrect_thenThrowsValidationGameException() {
		Exception ex = assertThrows(ValidationGameException.class, () -> {
			scoreBoard.updateScore(HOME_TEAM, AWAY_TEAM, -2, 1);
		});

		assertEquals(String.format("%s:%d", ValidationGameException.SCORE_VALID, -2), ex.getMessage());
	}

	@Test
	void givenScoreBoard_whenUpdateScoreWithAwayScoreIncorrect_thenThrowsValidationGameException() {
		Exception ex = assertThrows(ValidationGameException.class, () -> {
			scoreBoard.updateScore(HOME_TEAM, AWAY_TEAM, 5, -2);
		});

		assertEquals(String.format("%s:%d", ValidationGameException.SCORE_VALID, -2), ex.getMessage());
	}

	void givenInitializedScoreBoard_whenGetSummaryTotalScore_thenReturnScoreBoardEmpty() {

		List<Game> summaryGame = scoreBoard.getOrdededSummaryOfTotalScore();
		assertEquals(0, summaryGame.size());
	}

	@Test
	void givenScoreBoard_whenGetSummaryTotalScore_thenScoreBoardReturnOrdered() {

		List<Game> expectedGames = Arrays.asList(
				new Game(HOME_TEAM_SUMMARY_4, AWAY_TEAM_SUMMARY_4, HOME_SCORE_SUMMARY_4, AWAY_SCORE_SUMMARY_4),
				new Game(HOME_TEAM_SUMMARY_2, AWAY_TEAM_SUMMARY_2, HOME_SCORE_SUMMARY_2, AWAY_SCORE_SUMMARY_2),
				new Game(HOME_TEAM_SUMMARY_1, AWAY_TEAM_SUMMARY_1, HOME_SCORE_SUMMARY_1, AWAY_SCORE_SUMMARY_1),
				new Game(HOME_TEAM_SUMMARY_5, AWAY_TEAM_SUMMARY_5, HOME_SCORE_SUMMARY_5, AWAY_SCORE_SUMMARY_5),
				new Game(HOME_TEAM_SUMMARY_3, AWAY_TEAM_SUMMARY_3, HOME_SCORE_SUMMARY_3, AWAY_SCORE_SUMMARY_3));


		scoreBoard.startGame(HOME_TEAM_SUMMARY_1, AWAY_TEAM_SUMMARY_1);
		scoreBoard.startGame(HOME_TEAM_SUMMARY_2, AWAY_TEAM_SUMMARY_2);
		scoreBoard.startGame(HOME_TEAM_SUMMARY_3, AWAY_TEAM_SUMMARY_3);
		scoreBoard.startGame(HOME_TEAM_SUMMARY_4, AWAY_TEAM_SUMMARY_4);
		scoreBoard.startGame(HOME_TEAM_SUMMARY_5, AWAY_TEAM_SUMMARY_5);

		scoreBoard.updateScore(HOME_TEAM_SUMMARY_1, AWAY_TEAM_SUMMARY_1, HOME_SCORE_SUMMARY_1, AWAY_SCORE_SUMMARY_1);

		scoreBoard.updateScore(HOME_TEAM_SUMMARY_2, AWAY_TEAM_SUMMARY_2, HOME_SCORE_SUMMARY_2, AWAY_SCORE_SUMMARY_2);

		scoreBoard.updateScore(HOME_TEAM_SUMMARY_3, AWAY_TEAM_SUMMARY_3, HOME_SCORE_SUMMARY_3, AWAY_SCORE_SUMMARY_3);

		scoreBoard.updateScore(HOME_TEAM_SUMMARY_4, AWAY_TEAM_SUMMARY_4, HOME_SCORE_SUMMARY_4, AWAY_SCORE_SUMMARY_4);

		scoreBoard.updateScore(HOME_TEAM_SUMMARY_5, AWAY_TEAM_SUMMARY_5, HOME_SCORE_SUMMARY_5, AWAY_SCORE_SUMMARY_5);

		List<Game> summaryGame = scoreBoard.getOrdededSummaryOfTotalScore();

		assertEquals(expectedGames.size(), summaryGame.size());
		assertTrue(expectedGames.containsAll(summaryGame));
		assertTrue(summaryGame.containsAll(expectedGames));
		assertEquals(expectedGames.size(), summaryGame.size());

		assertIterableEquals(summaryGame, expectedGames);
	}

}
