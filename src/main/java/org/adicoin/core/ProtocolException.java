package org.adicoin.core;

public class ProtocolException extends Exception {

	ProtocolException() {
		super();
	}

	ProtocolException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	ProtocolException(String message, Throwable cause) {
		super(message, cause);
	}

	ProtocolException(String message) {
		super(message);
	}

	ProtocolException(Throwable cause) {
		super(cause);
	}

}
