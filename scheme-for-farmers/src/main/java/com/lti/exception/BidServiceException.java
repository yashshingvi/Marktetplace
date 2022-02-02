package com.lti.exception;

public class BidServiceException extends RuntimeException {

	public BidServiceException() {
		super();
	}

	public BidServiceException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public BidServiceException(String message, Throwable cause) {
		super(message, cause);
	}

	public BidServiceException(String message) {
		super(message);
	}

	public BidServiceException(Throwable cause) {
		super(cause);
	}

	
}
