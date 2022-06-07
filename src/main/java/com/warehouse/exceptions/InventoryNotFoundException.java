package com.warehouse.exceptions;

public class InventoryNotFoundException extends RuntimeException {

	private static final long serialVersionUID = -1382239289421809764L;

	public InventoryNotFoundException() {
		super();
	}

	public InventoryNotFoundException(String msg) {
		super(msg);
	}

}
