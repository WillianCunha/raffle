package com.softexpert.exception;

public class RaffleException extends Exception {

	private static final long serialVersionUID = 4900789506745986852L;

	public RaffleException(String message) {
		super(message);
	}

	public RaffleException(String message, Throwable cause) {
		super(message, cause);
	}
	
}
