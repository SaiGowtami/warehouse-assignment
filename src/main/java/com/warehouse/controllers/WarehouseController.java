package com.warehouse.controllers;

import java.io.IOException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.warehouse.exceptions.NoDataFoundException;
import com.warehouse.models.InventoriesBean;
import com.warehouse.models.ProductBean;
import com.warehouse.models.Products;
import com.warehouse.services.WarehouseService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
public class WarehouseController {

	private static final Logger logger = LoggerFactory.getLogger(WarehouseController.class);

	@Autowired
	private ObjectMapper objectMapper;

	@Autowired
	private WarehouseService warehouseService;

	@Operation(summary = "Load the Inventories/Articles")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "201", description = "Inventories created successfully", content = {
					@Content(mediaType = "multipart/form-data", schema = @Schema(implementation = InventoriesBean.class)) }),
			@ApiResponse(responseCode = "400", description = "Unable to load Inventories", content = @Content) })
	@PostMapping("/loadInventory")
	public ResponseEntity<?> loadInventory(@RequestParam("file") MultipartFile file) {
		try {
			InventoriesBean inventories = objectMapper.readValue(file.getBytes(), InventoriesBean.class);
			logger.info("Recevied inventories data :: "
					+ objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(inventories));
			warehouseService.saveInventories(inventories.getInventory());

		} catch (IOException e) {
			throw new NoDataFoundException("Unable to load Inventories :: " + e.getMessage());
		}
		return new ResponseEntity<>("Inventories created successfully", HttpStatus.CREATED);
	}

	@Operation(summary = "Load the Products")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "201", description = "Products created successfully", content = {
					@Content(mediaType = "multipart/form-data", schema = @Schema(implementation = ProductBean.class)) }),
			@ApiResponse(responseCode = "400", description = "Unable to load Products", content = @Content),
			@ApiResponse(responseCode = "412", description = "Product inventor not found", content = @Content) })
	@PostMapping("/loadProducts")
	public ResponseEntity<?> loadProducts(@RequestParam("file") MultipartFile file) {
		try {
			ProductBean products = objectMapper.readValue(file.getBytes(), ProductBean.class);
			logger.info("Recevied products and mapping data :: "
					+ objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(products));
			warehouseService.saveProductsAndMappings(products.getProducts());
		} catch (IOException e) {
			throw new NoDataFoundException("Unable to load Products :: " + e.getMessage());
		}
		return new ResponseEntity<>("Prodcts created successfully", HttpStatus.CREATED);
	}

	@PutMapping("/sellProduct")
	public ResponseEntity<?> sellProduct(@RequestParam(required = true) String productName,
			@RequestParam(required = true) int quantity) {
		logger.info("Recevied Product name :: " + productName + " and Quantity :: " + quantity);
		warehouseService.sellProduct(productName, quantity);
		return new ResponseEntity<>("Successfully sold Product :: " + productName + " of Quantity :: " + quantity,
				HttpStatus.CREATED);
	}

	@GetMapping("/getProducts")
	public List<Products> getProducts() {
		return warehouseService.getProducts();
	}

}
