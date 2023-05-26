package com.banking.exception;

public class InvalidDepositAmount extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public InvalidDepositAmount(String msg) {
		super(msg);
	}
}
