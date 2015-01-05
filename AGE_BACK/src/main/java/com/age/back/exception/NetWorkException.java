package com.age.back.exception;

public class NetWorkException extends Exception {

	public NetWorkException() {
		super();
	}

	public NetWorkException(String message) {
		super(message);
	}

	public NetWorkException(Throwable cause) {
		super(cause);
	}

	public NetWorkException(String message, Throwable cause) {
		super(message, cause);
	}
}
