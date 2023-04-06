package com.sportradar.scoreboard;

import java.util.List;

import com.sportradar.scoreboard.model.Game;

/**
 * API Live Scoreboard
 * 
 * @author oasaustre
 *
 */

public interface IScoreboard {

	/**
	 * Start a game and add in live scoreboard. If the game exists on the
	 * scoreboard, it throws the AlreadyExistsGameException exception.In case of
	 * team name incorrect ,home or away,it throws the ValidationGameException
	 * exception
	 *
	 * @param homeTeam home team name
	 * @param awayTeam away team name
	 */
	void startGame(String homeTeam, String awayTeam);

	/**
	 * Finish a game and remove in live scoreboard. If the game not exists on the
	 * scoreboard, it throws the GameNotFoundException exception.In case of team
	 * name incorrect ,home or away,it throws the ValidationGameException exception
	 * 
	 * @param homeTeam home team name
	 * @param awayTeam away team name
	 */
	void finishGame(String homeTeam, String awayTeam);

	/**
	 * Update a game existing in live scoreboard.If the game not exists on the
	 * scoreboard, it throws the GameNotFoundException exception In case of score
	 * incorrect (value greater or equal than zero) or team incorrect ,home or
	 * away,it throws the ValidationGameException exception
	 * 
	 * @param homeTeam  home team name
	 * @param awayTeam  away team name
	 * @param scoreHome home team score
	 * @param scoreAway away team score
	 */
	void updateScore(String homeTeam, String awayTeam, int scoreHome, int scoreAway);

	/**
	 * Get a summary of games by total score
	 * 
	 * @return List a summary of games by total score.Those games with the same
	 *         total score will be returned ordered by the most recently added to
	 *         our system.
	 */
	List<Game> getOrdededSummaryOfTotalScore();
}
