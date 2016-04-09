package org.adicoin.core;

public class PeerDiscoveryException extends Exception {

	PeerDiscoveryException() {
		super();
	}

	PeerDiscoveryException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	PeerDiscoveryException(String message, Throwable cause) {
		super(message, cause);
	}

	PeerDiscoveryException(String message) {
		super(message);
	}

	PeerDiscoveryException(Throwable cause) {
		super(cause);
	}

}
