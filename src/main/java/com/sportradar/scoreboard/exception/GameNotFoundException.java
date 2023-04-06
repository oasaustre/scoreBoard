package com.sportradar.scoreboard.exception;

public class GameNotFoundException extends RuntimeException {
	
	public static final String GAME_NOT_FOUND = "Game not found";

	private static final long serialVersionUID = -1930607781932855454L;
	
	
	public GameNotFoundException(String message) {
		super(message);
	}

}
