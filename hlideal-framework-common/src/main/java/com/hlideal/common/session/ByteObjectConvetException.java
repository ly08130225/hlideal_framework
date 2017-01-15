package com.hlideal.common.session;

public class ByteObjectConvetException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public ByteObjectConvetException(Throwable e, String message) {
		super(message, e);
	}

	public ByteObjectConvetException(Throwable e) {
		super(e);
	}

	public ByteObjectConvetException(String message) {
		super(message);
	}
}