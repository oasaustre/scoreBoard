package com.sportradar.scoreboard.repository;

import java.util.List;

import com.sportradar.scoreboard.model.Game;

/**
 * Interface that defines a repository for peform operations in our scoreboard
 * @author oasaustre
 *
 */
public interface IScoreBoardRepository {

	void save(Game game);

	void remove(Game game);

	void update(Game game);

	Game findGameByIdentifierKey(String homeTeam, String awayTeam);

	List<Game> getOrdededSummaryOfTotalScore();

}
