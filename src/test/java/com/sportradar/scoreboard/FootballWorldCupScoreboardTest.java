package com.sportradar.scoreboard;

import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

public class FootballWorldCupScoreboardTest {

	@Test
	void givenScoreBoard_whenStartGame_thenAddGameWithInitialScoreinLiveScoreBoard() {
		fail("not implemented!!");
	}

	@Test
	void givenScoreBoard_whenStartGameWithGameExisting_thenThrowsAlreadyGameExistsException() {
		fail("not implemented!!");
	}

	@Test
	void givenScoreBoard_whenStartGameWithIncorrectHomeTeam_thenThrowsValidationGameException() {
		fail("not implemented!!");
	}

	@Test
	void givenScoreBoard_whenStartGameWithIncorrectAwayTeam_thenThrowsValidationGameException() {
		fail("not implemented!!");
	}

	@Test
	void givenScoreBoard_whenFinishGameWithExistingGame_thenGameIsFinishedAndRemoveFromScoreBoard() {
		fail("not implemented!!");
	}

	@Test
	void givenScoreBoard_whenFinishGameWithNotExistingGame_thenThrowsGameNotFoundException() {
		fail("not implemented!!");
	}

	@Test
	void givenScoreBoard_whenFinishGameWithIncorrectHomeTeam_thenThrowsValidationGameException() {
		fail("not implemented!!");
	}

	@Test
	void givenScoreBoard_whenFinishGameWithIncorrectAwayTeam_thenThrowsValidationGameException() {
		fail("not implemented!!");
	}

	@Test
	void givenScoreBoard_whenUpdateScoreWithExistingGame_thenGameChangeScoreInLiveScoreBoard() {
		fail("not implemented!!");
	}

	@Test
	void givenScoreBoard_whenUpdateScoreWithNotExistingGame_thenGameNotFoundException() {
		fail("not implemented!!");
	}
	
	@Test
	void givenScoreBoard_whenUpdateScoreWithHomeTeamIncorrect_thenThrowsValidationGameException() {
		fail("not implemented!!");
	}

	@Test
	void givenScoreBoard_whenUpdateScoreWithAwayTeamIncorrect_thenThrowsValidationGameException() {
		fail("not implemented!!");
	}

	@Test
	void givenScoreBoard_whenUpdateScoreWithHomeScoreIncorrect_thenThrowsValidationGameException() {
		fail("not implemented!!");
	}

	@Test
	void givenScoreBoard_whenUpdateScoreWithAwayScoreIncorrect_thenThrowsValidationGameException() {
		fail("not implemented!!");
	}

	void givenInitializedScoreBoard_whenGetSummaryTotalScore_thenReturnScoreBoardEmpty() {
		fail("not implemented!!");
	}

	@Test
	void givenScoreBoard_whenGetSummaryTotalScore_thenScoreBoardReturnOrdered() {
		fail("not implemented!!");
	}

}
