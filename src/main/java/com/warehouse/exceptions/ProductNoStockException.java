package com.warehouse.exceptions;

public class ProductNoStockException extends RuntimeException {

	private static final long serialVersionUID = 2368906213068857832L;

	public ProductNoStockException() {
		super();
	}

	public ProductNoStockException(String msg) {
		super(msg);
	}

}
