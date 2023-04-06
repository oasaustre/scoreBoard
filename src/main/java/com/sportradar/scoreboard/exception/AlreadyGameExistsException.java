package com.sportradar.scoreboard.exception;

public class AlreadyGameExistsException extends RuntimeException {
	
	public static final String GAME_EXISTS = "Game already exists";

	private static final long serialVersionUID = 5825619998252197540L;
	
	
	public AlreadyGameExistsException(String message) {
		super(message);
	}

}
