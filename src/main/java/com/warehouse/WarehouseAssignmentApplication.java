package com.warehouse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class WarehouseAssignmentApplication {

	private static final Logger LOGGER = LoggerFactory.getLogger(WarehouseAssignmentApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(WarehouseAssignmentApplication.class, args);
		LOGGER.info("Welcome to Warehouse Application ...,");
	}
}