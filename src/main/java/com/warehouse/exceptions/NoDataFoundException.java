package com.warehouse.exceptions;

public class NoDataFoundException extends RuntimeException {

	private static final long serialVersionUID = -2744694812368681908L;

	public NoDataFoundException() {
		super();
	}

	public NoDataFoundException(String msg) {
		super(msg);
	}
}
