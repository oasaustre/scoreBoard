package com.sportradar.scoreboard.factory;

import com.sportradar.scoreboard.repository.IScoreBoardRepository;

public interface IScoreBoardRepositoryFactory {
	IScoreBoardRepository create();
}
