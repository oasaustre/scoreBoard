package com.sportradar.scoreboard.repository;

import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

class ScoreboardRepositoryTest {

	@Test
	void givenGameCorrectNoExistingInScoreBoard_whenSaveGame_thenAddGameInScoreBoard() {
		fail("not implemented!!");
	}

	@Test
	void givenGameIsNull_whenSaveGame_thenReturnValidationGameException() {
		fail("not implemented!!");
	}

	@Test
	void givenGameExistingInScoreBoard_whenSaveGame_thenThrowsAlreadyGameExistsException() {
		fail("not implemented!!");
	}

	@Test
	void givenGameCorrectExistingScoreBoard_whenRemoveGame_thenRemoveGameInScoreBoard() {
		fail("not implemented!!");
	}

	@Test
	void givenGameIsNull_whenRemoveGame_thenThrowsValidationGameException() {
		fail("not implemented!!");
	}

	@Test
	void givenGameCorrectThatNoExistsInScoreBoard_whenRemoveGame_thenThrowsGameNotFoundException() {
		fail("not implemented!!");
	}

	@Test
	void givenGameCorrectThatExistsInScoreBoard_whenUpdateGame_thenUpdateGameInScoreBoard() {
		fail("not implemented!!");
	}

	@Test
	void givenGameIsNull_whenUpdateGame_thenThrowsValidationGameException() {
		fail("not implemented!!");
	}

	@Test
	void givenGameCorrectThatNotExistsInScoreBoard_whenUpdateGame_thenReturnGameNotFoundException() {
		fail("not implemented!!");

	}

	@Test
	void givenGameWithIdentifierExistingInScoreBoard_whenfindGame_thenReturnGameFound() {
		fail("not implemented!!");

	}

	@Test
	void givenGameWithIdentifierNotExistingInScoreBoard_whenfindGame_thenGameNotFoundException() {
		fail("not implemented!!");

	}

	@Test
	void givenInitialScoreBoard_whenGetOrdededSummaryOfTotalScore_thenReturnEmptyScoreBoard() {
		fail("not implemented!!");

	}

	@Test
	void givenScoreBoardWithLiveGame_whenGetOrdededSummaryOfTotalScore_thenReturnScoreBoardOrdered() {
		fail("not implemented!!");

	}
}
