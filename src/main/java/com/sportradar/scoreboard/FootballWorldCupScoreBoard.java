package com.sportradar.scoreboard;

import java.util.List;

import com.sportradar.scoreboard.factory.IScoreBoardRepositoryFactory;
import com.sportradar.scoreboard.model.Game;
import com.sportradar.scoreboard.repository.IScoreBoardRepository;

public class FootballWorldCupScoreBoard implements IScoreboard {

	private IScoreBoardRepository scoreBoardPersistente;

	public FootballWorldCupScoreBoard(IScoreBoardRepositoryFactory scoreBoardPersistenceFactory) {
		scoreBoardPersistente = scoreBoardPersistenceFactory.create();
	}

	@Override
	public void startGame(String homeTeam, String awayTeam) {
		scoreBoardPersistente.save(new Game(homeTeam, awayTeam));

	}

	@Override
	public void finishGame(String homeTeam, String awayTeam) {
		scoreBoardPersistente.remove(new Game(homeTeam, awayTeam));

	}

	@Override
	public void updateScore(String homeTeam, String awayTeam, int scoreHome, int scoreAway) {
		scoreBoardPersistente.update(new Game(homeTeam, awayTeam, scoreHome, scoreAway));

	}

	@Override
	public List<Game> getOrdededSummaryOfTotalScore() {
		return scoreBoardPersistente.getOrdededSummaryOfTotalScore();
	}

}
