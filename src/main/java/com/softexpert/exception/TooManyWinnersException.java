package com.softexpert.exception;

public class TooManyWinnersException extends RaffleException {

	private static final long serialVersionUID = 6553754888628091066L;

	public TooManyWinnersException(String message) {
		super(message);
	}

}
