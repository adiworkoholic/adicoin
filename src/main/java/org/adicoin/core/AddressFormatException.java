package org.adicoin.core;

public class AddressFormatException extends Exception {

	private static final long serialVersionUID = 8184416966851137850L;

	AddressFormatException() {
		super();
	}

	AddressFormatException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	AddressFormatException(String message, Throwable cause) {
		super(message, cause);
	}

	AddressFormatException(String message) {
		super(message);
	}

	AddressFormatException(Throwable cause) {
		super(cause);
	}
	
}
