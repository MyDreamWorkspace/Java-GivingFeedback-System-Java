package com.banking.exception;

public class InvalidWithdrawAmount extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public InvalidWithdrawAmount(String msg) {
		super(msg);
	}
}
