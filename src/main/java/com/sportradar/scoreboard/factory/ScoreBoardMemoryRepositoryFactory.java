package com.sportradar.scoreboard.factory;

import com.sportradar.scoreboard.repository.IScoreBoardRepository;
import com.sportradar.scoreboard.repository.ScoreBoardMemoryRepository;

public class ScoreBoardMemoryRepositoryFactory implements IScoreBoardRepositoryFactory {

	@Override
	public IScoreBoardRepository create() {
		return new ScoreBoardMemoryRepository();
	}

}
