package com.sportradar.scoreboard.repository;

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
import com.sportradar.scoreboard.model.Game;

class ScoreboardRepositoryTest {

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

	private IScoreBoardRepository scoreBoardRepository;

	@BeforeEach
	void setUp() {

		scoreBoardRepository = new ScoreBoardMemoryRepository();
	}

	@Test
	void givenGameCorrectNoExistingInScoreBoard_whenSaveGame_thenAddGameInScoreBoard() {
		Game game = new Game(HOME_TEAM, AWAY_TEAM, HOME_SCORE, AWAY_SCORE);

		scoreBoardRepository.save(game);

		Game expectedGame = scoreBoardRepository.findGameByIdentifierKey(game.getHomeTeam(), game.getAwayTeam());

		assertEquals(expectedGame, game);
	}

	@Test
	void givenGameIsNull_whenSaveGame_thenReturnValidationGameException() {
		Game game = null;

		Exception ex = assertThrows(ValidationGameException.class, () -> {
			scoreBoardRepository.save(game);
		});

		assertEquals(ValidationGameException.GAME_CANNOT_BE_NULL, ex.getMessage());
	}

	@Test
	void givenGameExistingInScoreBoard_whenSaveGame_thenThrowsAlreadyGameExistsException() {
		Game game = new Game(HOME_TEAM, AWAY_TEAM, HOME_SCORE, AWAY_SCORE);

		scoreBoardRepository.save(game);

		Game existingGame = scoreBoardRepository.findGameByIdentifierKey(game.getHomeTeam(), game.getAwayTeam());

		Exception ex = assertThrows(AlreadyGameExistsException.class, () -> {
			scoreBoardRepository.save(existingGame);
		});

		assertEquals(AlreadyGameExistsException.GAME_EXISTS, ex.getMessage());
	}

	@Test
	void givenGameCorrectExistingScoreBoard_whenRemoveGame_thenRemoveGameInScoreBoard() {
		Game game = new Game(HOME_TEAM, AWAY_TEAM, HOME_SCORE, AWAY_SCORE);

		scoreBoardRepository.save(game);

		Game existingGame = scoreBoardRepository.findGameByIdentifierKey(game.getHomeTeam(), game.getAwayTeam());

		scoreBoardRepository.remove(existingGame);

		List<Game> summaryGame = scoreBoardRepository.getOrdededSummaryOfTotalScore();

		assertEquals(0, summaryGame.size());
	}

	@Test
	void givenGameIsNull_whenRemoveGame_thenThrowsValidationGameException() {
		Game game = null;

		Exception ex = assertThrows(ValidationGameException.class, () -> {
			scoreBoardRepository.remove(game);
		});

		assertEquals(ValidationGameException.GAME_CANNOT_BE_NULL, ex.getMessage());
	}

	@Test
	void givenGameCorrectThatNoExistsInScoreBoard_whenRemoveGame_thenThrowsGameNotFoundException() {
		Game game = new Game(HOME_TEAM, AWAY_TEAM, HOME_SCORE, AWAY_SCORE);

		Exception ex = assertThrows(GameNotFoundException.class, () -> {
			scoreBoardRepository.remove(game);
		});

		assertEquals(String.format("%s:%s", GameNotFoundException.GAME_NOT_FOUND, game.getKey()), ex.getMessage());
	}

	@Test
	void givenGameCorrectThatExistsInScoreBoard_whenUpdateGame_thenUpdateGameInScoreBoard() {
		Game game = new Game(HOME_TEAM, AWAY_TEAM, HOME_SCORE, AWAY_SCORE);
		Game expectedGame = new Game(HOME_TEAM, AWAY_TEAM, EXPECTED_HOME_SCORE, EXPECTED_AWAY_SCORE);

		scoreBoardRepository.save(game);

		Game existingGame = scoreBoardRepository.findGameByIdentifierKey(game.getHomeTeam(), game.getAwayTeam());
		existingGame.setHomeScore(EXPECTED_HOME_SCORE);
		existingGame.setAwayScore(EXPECTED_AWAY_SCORE);

		scoreBoardRepository.update(existingGame);

		assertEquals(expectedGame, existingGame);
	}

	@Test
	void givenGameIsNull_whenUpdateGame_thenThrowsValidationGameException() {
		Game game = null;

		Exception ex = assertThrows(ValidationGameException.class, () -> {
			scoreBoardRepository.update(game);
		});

		assertEquals(ValidationGameException.GAME_CANNOT_BE_NULL, ex.getMessage());
	}

	@Test
	void givenGameCorrectThatNotExistsInScoreBoard_whenUpdateGame_thenReturnGameNotFoundException() {
		Game game = new Game(HOME_TEAM, AWAY_TEAM, HOME_SCORE, AWAY_SCORE);

		Exception ex = assertThrows(GameNotFoundException.class, () -> {
			scoreBoardRepository.update(game);
		});

		assertEquals(ex.getMessage(), String.format("%s:%s", GameNotFoundException.GAME_NOT_FOUND, game.getKey()));
	}

	@Test
	void givenGameWithIdentifierExistingInScoreBoard_whenfindGame_thenReturnGameFound() {
		Game game = new Game(HOME_TEAM, AWAY_TEAM, HOME_SCORE, AWAY_SCORE);

		String homeTeam = HOME_TEAM;
		String awayTeam = AWAY_TEAM;

		scoreBoardRepository.save(game);

		Game existingGame = scoreBoardRepository.findGameByIdentifierKey(homeTeam, awayTeam);

		assertEquals(existingGame, game);

	}

	@Test
	void givenGameWithIdentifierNotExistingInScoreBoard_whenfindGame_thenGameNotFoundException() {
		String homeTeam = HOME_TEAM;
		String awayTeam = AWAY_TEAM;

		Exception ex = assertThrows(GameNotFoundException.class, () -> {
			scoreBoardRepository.findGameByIdentifierKey(homeTeam, awayTeam);
		});

		assertEquals(ex.getMessage(),
				String.format("%s:%s", GameNotFoundException.GAME_NOT_FOUND, new Game(homeTeam, awayTeam).getKey()));

	}

	@Test
	void givenInitialScoreBoard_whenGetOrdededSummaryOfTotalScore_thenReturnEmptyScoreBoard() {
		List<Game> summaryGame = scoreBoardRepository.getOrdededSummaryOfTotalScore();

		assertEquals(0, summaryGame.size());

	}

	@Test
	void givenScoreBoardWithLiveGame_whenGetOrdededSummaryOfTotalScore_thenReturnScoreBoardOrdered() {
		Game gameStarted1 = new Game(HOME_TEAM_SUMMARY_1, AWAY_TEAM_SUMMARY_1);
		Game gameStarted2 = new Game(HOME_TEAM_SUMMARY_2, AWAY_TEAM_SUMMARY_2);
		Game gameStarted3 = new Game(HOME_TEAM_SUMMARY_3, AWAY_TEAM_SUMMARY_3);
		Game gameStarted4 = new Game(HOME_TEAM_SUMMARY_4, AWAY_TEAM_SUMMARY_4);
		Game gameStarted5 = new Game(HOME_TEAM_SUMMARY_5, AWAY_TEAM_SUMMARY_5);

		List<Game> expectedGames = Arrays.asList(
				new Game(HOME_TEAM_SUMMARY_4, AWAY_TEAM_SUMMARY_4, HOME_SCORE_SUMMARY_4, AWAY_SCORE_SUMMARY_4),
				new Game(HOME_TEAM_SUMMARY_2, AWAY_TEAM_SUMMARY_2, HOME_SCORE_SUMMARY_2, AWAY_SCORE_SUMMARY_2),
				new Game(HOME_TEAM_SUMMARY_1, AWAY_TEAM_SUMMARY_1, HOME_SCORE_SUMMARY_1, AWAY_SCORE_SUMMARY_1),
				new Game(HOME_TEAM_SUMMARY_5, AWAY_TEAM_SUMMARY_5, HOME_SCORE_SUMMARY_5, AWAY_SCORE_SUMMARY_5),
				new Game(HOME_TEAM_SUMMARY_3, AWAY_TEAM_SUMMARY_3, HOME_SCORE_SUMMARY_3, AWAY_SCORE_SUMMARY_3));

		scoreBoardRepository.save(gameStarted1);
		scoreBoardRepository.save(gameStarted2);
		scoreBoardRepository.save(gameStarted3);
		scoreBoardRepository.save(gameStarted4);
		scoreBoardRepository.save(gameStarted5);

		gameStarted1.setHomeScore(HOME_SCORE_SUMMARY_1);
		gameStarted1.setAwayScore(AWAY_SCORE_SUMMARY_1);
		scoreBoardRepository.update(gameStarted1);

		gameStarted2.setHomeScore(HOME_SCORE_SUMMARY_2);
		gameStarted2.setAwayScore(AWAY_SCORE_SUMMARY_2);
		scoreBoardRepository.update(gameStarted2);

		gameStarted3.setHomeScore(HOME_SCORE_SUMMARY_3);
		gameStarted3.setAwayScore(AWAY_SCORE_SUMMARY_3);
		scoreBoardRepository.update(gameStarted3);

		gameStarted4.setHomeScore(HOME_SCORE_SUMMARY_4);
		gameStarted4.setAwayScore(AWAY_SCORE_SUMMARY_4);
		scoreBoardRepository.update(gameStarted4);

		gameStarted5.setHomeScore(HOME_SCORE_SUMMARY_5);
		gameStarted5.setAwayScore(AWAY_SCORE_SUMMARY_5);
		scoreBoardRepository.update(gameStarted5);

		List<Game> summaryGame = scoreBoardRepository.getOrdededSummaryOfTotalScore();

		assertEquals(expectedGames.size(), summaryGame.size());
		assertTrue(expectedGames.containsAll(summaryGame));
		assertTrue(summaryGame.containsAll(expectedGames));
		assertEquals(expectedGames.size(), summaryGame.size());

		assertIterableEquals(expectedGames, summaryGame);

	}
}
