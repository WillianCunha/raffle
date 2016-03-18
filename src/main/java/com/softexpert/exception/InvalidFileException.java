package com.softexpert.exception;

public class InvalidFileException extends RaffleException {

	private static final long serialVersionUID = -371785027758121951L;

	public InvalidFileException(String message) {
		super(message);
	}
	
	public InvalidFileException(String message, Throwable cause) {
		super(message, cause);
	}
	
}
