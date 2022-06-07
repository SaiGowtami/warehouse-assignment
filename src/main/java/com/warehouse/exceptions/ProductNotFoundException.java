package com.warehouse.exceptions;

public class ProductNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 4499025877878551854L;

	public ProductNotFoundException() {
		super();
	}

	public ProductNotFoundException(String msg) {
		super(msg);
	}

}
