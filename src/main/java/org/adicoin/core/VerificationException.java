package org.adicoin.core;

public class VerificationException extends Exception {

	VerificationException() {
		super();
	}

	VerificationException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	VerificationException(String message, Throwable cause) {
		super(message, cause);
	}

	VerificationException(String message) {
		super(message);
	}

	VerificationException(Throwable cause) {
		super(cause);
	}

}
