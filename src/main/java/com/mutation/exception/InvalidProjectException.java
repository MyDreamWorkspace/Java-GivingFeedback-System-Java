package com.mutation.exception;


/**
 * Custom exception for invalid assignment project
 *
 */
public class InvalidProjectException extends Exception {

	
	private static final long serialVersionUID = 1L;

	public InvalidProjectException() {
		 super();
	}
	
	public InvalidProjectException(String message) {
        super(message);
    }
	
    public InvalidProjectException(String message, Throwable cause) {
        super(message, cause);
    }
}
