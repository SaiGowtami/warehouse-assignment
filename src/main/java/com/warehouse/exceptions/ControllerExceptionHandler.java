package com.warehouse.exceptions;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ControllerExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(ProductNoStockException.class)
	public ResponseEntity<ErrorMessage> productNoStockException(Exception ex, WebRequest request) {
		ErrorMessage message = new ErrorMessage(HttpStatus.NOT_FOUND.value(), new Date(), ex.getMessage());

		return new ResponseEntity<ErrorMessage>(message, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(ProductNotFoundException.class)
	public ResponseEntity<ErrorMessage> productNotFoundException(Exception ex, WebRequest request) {
		ErrorMessage message = new ErrorMessage(HttpStatus.INTERNAL_SERVER_ERROR.value(), new Date(), ex.getMessage());

		return new ResponseEntity<ErrorMessage>(message, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(NoDataFoundException.class)
	public ResponseEntity<ErrorMessage> noDataFoundException(Exception ex, WebRequest request) {
		ErrorMessage message = new ErrorMessage(HttpStatus.BAD_REQUEST.value(), new Date(), ex.getMessage());

		return new ResponseEntity<ErrorMessage>(message, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(InventoryNotFoundException.class)
	public ResponseEntity<ErrorMessage> inventoryNotFoundException(Exception ex, WebRequest request) {
		ErrorMessage message = new ErrorMessage(HttpStatus.PRECONDITION_FAILED.value(), new Date(), ex.getMessage());

		return new ResponseEntity<ErrorMessage>(message, HttpStatus.PRECONDITION_FAILED);
	}
}
