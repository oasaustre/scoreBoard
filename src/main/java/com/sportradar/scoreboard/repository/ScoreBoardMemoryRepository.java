package com.sportradar.scoreboard.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.sportradar.scoreboard.exception.AlreadyGameExistsException;
import com.sportradar.scoreboard.exception.GameNotFoundException;
import com.sportradar.scoreboard.model.Game;
import com.sportradar.scoreboard.validator.GameValidador;

public class ScoreBoardMemoryRepository implements IScoreBoardRepository {

	private List<Game> liveScoreBoard;

	public ScoreBoardMemoryRepository() {
		liveScoreBoard = new ArrayList<>();
	}

	@Override
	public void save(Game game) {
		GameValidador.validateGameNotNull(game);
		try {
			findGameByIdentifierKey(game.getHomeTeam(), game.getAwayTeam());
			throw new AlreadyGameExistsException(AlreadyGameExistsException.GAME_EXISTS);
		} catch (GameNotFoundException ex) {
			addGame(game);

		}

	}

	private void addGame(Game game) {
		liveScoreBoard.add(game);
	}

	@Override
	public void remove(Game game) {
		GameValidador.validateGameNotNull(game);
		boolean isRemovedGame = liveScoreBoard
				.removeIf(currentGame -> currentGame.getHomeTeam().equalsIgnoreCase(game.getHomeTeam())
						&& currentGame.getAwayTeam().equalsIgnoreCase(game.getAwayTeam()));

		if (!isRemovedGame) {
			throw new GameNotFoundException(String.format("%s:%s", GameNotFoundException.GAME_NOT_FOUND, game.getKey()));
		}

	}

	@Override
	public void update(Game game) {
		GameValidador.validateGameNotNull(game);
		Game foundGame = findGameByIdentifierKey(game.getHomeTeam(), game.getAwayTeam());
		foundGame.setHomeScore(game.getHomeScore());
		foundGame.setAwayScore(game.getAwayScore());
	}

	@Override
	public Game findGameByIdentifierKey(String homeTeam, String awayTeam) {
		return liveScoreBoard.stream()
				.filter(currentGame -> currentGame.getHomeTeam().equalsIgnoreCase(homeTeam)
						&& currentGame.getAwayTeam().equalsIgnoreCase(awayTeam))
				.findFirst().orElseThrow(() -> new GameNotFoundException(String.format("%s:%s", GameNotFoundException.GAME_NOT_FOUND, new Game(homeTeam,awayTeam).getKey())));
	}

	@Override
	public List<Game> getOrdededSummaryOfTotalScore() {
		List<Game> orderedSummary = liveScoreBoard.stream()
				.map(game -> new Game(game.getHomeTeam(), game.getAwayTeam(), game.getHomeScore(), game.getAwayScore()))
				.collect(Collectors.toList());

		orderedSummary.sort((Game game1, Game game2) -> {
			int resultComparingSummary = Integer.compare(game1.getHomeScore() + game1.getAwayScore(),
					game2.getHomeScore() + game2.getAwayScore());
			if (resultComparingSummary == 0) {
				return -Long.compare(game1.getCreationTime(), game2.getCreationTime());
			}
			return -resultComparingSummary;
		});

		return orderedSummary;
	}

}
