package com.sportradar.scoreboard.model;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import com.sportradar.scoreboard.validator.GameValidador;

/**
 * Represents a game in our live scoreBoard
 * 
 * To uniquely identify a game, the pair homeTeam and awayTeam is used as a
 * unique key
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

		GameValidador.validateNameTeam(homeTeam);
		GameValidador.validateNameTeam(awayTeam);
		GameValidador.validateLengthNameTeam(homeTeam);
		GameValidador.validateLengthNameTeam(awayTeam);

		this.homeTeam = homeTeam;
		this.awayTeam = awayTeam;
		this.homeScore = 0;
		this.awayScore = 0;
		this.creationTime = System.nanoTime();
	}

	public Game(String homeTeam, String awayTeam, int homeScore, int awayScore) {

		GameValidador.validateNameTeam(homeTeam);
		GameValidador.validateNameTeam(awayTeam);
		GameValidador.validateLengthNameTeam(homeTeam);
		GameValidador.validateLengthNameTeam(awayTeam);
		GameValidador.validateScore(homeScore);
		GameValidador.validateScore(awayScore);

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
		GameValidador.validateScore(homeScore);
		this.homeScore = homeScore;
	}

	public int getAwayScore() {
		return awayScore;
	}

	public void setAwayScore(int awayScore) {
		GameValidador.validateScore(awayScore);
		this.awayScore = awayScore;
	}

	public long getCreationTime() {
		return creationTime;
	}

	public String getKey() {
		return String.format("%s-%s", this.homeTeam, this.awayTeam);
	}

	@Override
	public int hashCode() {
		return HashCodeBuilder.reflectionHashCode(this, "creationTime");
	}

	@Override
	public boolean equals(Object obj) {
		return EqualsBuilder.reflectionEquals(this, obj, "creationTime");
	}

}
