package com.sportradar.scoreboard.exception;

public class ValidationGameException extends RuntimeException{
	
    public static final String TEAM_NAME_VALID = " Name team must be a valid name";
    public static final String SCORE_VALID = "Score must be a valid number must greater than or equal to zero";
    public static final String GAME_CANNOT_BE_NULL = "Game cannot be null";

	private static final long serialVersionUID = -2178632332855354731L;

	public ValidationGameException(String message) {
		super(message);
	}
}
