package com.sportradar.scoreboard.model;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import com.sportradar.scoreboard.exception.ValidationGameException;

/**
 * Represents a game in our live scoreBoard
 * 
 * @author oasaustre
 *
 */
public class Game {

	private final String homeTeam;
	private final String awayTeam;
	private int homeScore;
	private int awayScore;
	private final long creationTime;

	public Game(String homeTeam, String awayTeam) {

		validateNameTeam(homeTeam);
		validateNameTeam(awayTeam);

		this.homeTeam = homeTeam;
		this.awayTeam = awayTeam;
		this.homeScore = 0;
		this.awayScore = 0;
		this.creationTime = System.nanoTime();
	}

	public Game(String homeTeam, String awayTeam, int homeScore, int awayScore) {

		validateNameTeam(homeTeam);
		validateNameTeam(awayTeam);
		validateScore(homeScore);
		validateScore(awayScore);

		this.homeTeam = homeTeam;
		this.awayTeam = awayTeam;
		this.homeScore = homeScore;
		this.awayScore = awayScore;
		this.creationTime = System.nanoTime();
	}

	public String getHomeTeam() {
		return homeTeam;
	}

	public String getAwayTeam() {
		return awayTeam;
	}

	public int getHomeScore() {
		return homeScore;
	}

	public void setHomeScore(int homeScore) {
		validateScore(homeScore);
		this.homeScore = homeScore;
	}

	public int getAwayScore() {
		return awayScore;
	}

	public void setAwayScore(int awayScore) {
		validateScore(awayScore);
		this.awayScore = awayScore;
	}

	public long getCreationTime() {
		return creationTime;
	}

	@Override
	public int hashCode() {
		return HashCodeBuilder.reflectionHashCode(this, "creationTime");
	}

	@Override
	public boolean equals(Object obj) {
		return EqualsBuilder.reflectionEquals(this, obj, "creationTime");
	}

	public boolean validateNameTeam(String nameTeam) {

		if (!isCorrectTeamName(nameTeam)) {
			throw new ValidationGameException(
					String.format("%s:%s", ValidationGameException.TEAM_NAME_VALID, nameTeam));
		}

		return true;
	}

	public boolean validateScore(int score) {
		if (score < 0) {
			throw new ValidationGameException(String.format("%s:%d", ValidationGameException.SCORE_VALID, score));
		}

		return true;
	}

	private boolean isCorrectTeamName(String nameTeam) {
		return !StringUtils.isBlank(nameTeam);
	}

}
