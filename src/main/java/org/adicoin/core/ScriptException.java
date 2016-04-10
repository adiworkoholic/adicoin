package org.adicoin.core;

public class ScriptException extends Exception {

	ScriptException() {
		super();
	}

	ScriptException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	ScriptException(String message, Throwable cause) {
		super(message, cause);
	}

	ScriptException(String message) {
		super(message);
	}

	ScriptException(Throwable cause) {
		super(cause);
	}

}
