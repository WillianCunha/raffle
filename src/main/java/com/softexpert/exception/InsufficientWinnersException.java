package com.softexpert.exception;

public class InsufficientWinnersException extends RaffleException {

	private static final long serialVersionUID = 512408952975392255L;

	public InsufficientWinnersException(String message) {
		super(message);
	}
}