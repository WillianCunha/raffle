package com.softexpert.exception;

public class LessThanOneException extends NumberFormatException {
	public void printZeroWinner() {
		System.out.println("Número de ganhadores não pode ser zero.");
	}
	
	public void printNegativeWinner() {
		System.out.println("Número de ganhadores não pode ser negativo.");
	}
}
