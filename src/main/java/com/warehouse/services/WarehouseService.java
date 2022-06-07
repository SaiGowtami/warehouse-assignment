package com.warehouse.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.warehouse.entities.InventoryDetails;
import com.warehouse.entities.ProductDetails;
import com.warehouse.entities.ProductsInventoryDetails;
import com.warehouse.exceptions.InventoryNotFoundException;
import com.warehouse.exceptions.ProductNoStockException;
import com.warehouse.exceptions.ProductNotFoundException;
import com.warehouse.models.ContainArticles;
import com.warehouse.models.Inventory;
import com.warehouse.models.Products;
import com.warehouse.repositories.InventoryRepository;
import com.warehouse.repositories.ProductRepository;
import com.warehouse.repositories.ProductsInventoryRepository;

@Service
public class WarehouseService {

	@Autowired
	private InventoryRepository invRepo;

	@Autowired
	private ProductRepository prdRepo;

	@Autowired
	private ProductsInventoryRepository prdInvRepo;

	private ModelMapper modelMapper = new ModelMapper();

	public void saveInventories(List<Inventory> inventories) {

		List<InventoryDetails> inventoryDetails = new ArrayList<InventoryDetails>();

		for (Inventory inv : inventories) {
			// get the existing InventoryDetails from DB
			Optional<InventoryDetails> existingInvDetails = invRepo.findById(Integer.parseInt(inv.getArtId()));

			if (existingInvDetails.isPresent()) {
				InventoryDetails existingInventory = existingInvDetails.get();
				// Update name and stock
				existingInventory.setName(inv.getName());
				existingInventory.setStock(existingInventory.getStock() + Integer.parseInt(inv.getStock()));
				inventoryDetails.add(existingInventory);
			} else
				// convert Model into entity to be saved in DB
				inventoryDetails.add(modelMapper.map(inv, InventoryDetails.class));
		}
		// Save the InventoryDetails
		invRepo.saveAll(inventoryDetails);
	}

	public void saveProductsAndMappings(List<Products> products) {

		for (Products prd : products) {
			// get the existing ProductDetails from DB
			Optional<ProductDetails> existingPrdDetails = Optional.ofNullable(prdRepo.findByName(prd.getName()));
			ProductDetails prdDetails;
			if (existingPrdDetails.isPresent()) {
				prdDetails = existingPrdDetails.get();
			} else {
				prdDetails = modelMapper.map(prd, ProductDetails.class);
				// save ProductsDetails
				prdDetails = prdRepo.save(prdDetails);
			}

			List<ProductsInventoryDetails> prdInvDetailsList = new ArrayList<ProductsInventoryDetails>();
			ProductsInventoryDetails prdInvDetails;
			InventoryDetails invDetails;
			for (ContainArticles artForPrd : prd.getContainArticles()) {
				try {
					// get the existing InventoryDetails from DB
					invDetails = invRepo.findById(Integer.parseInt(artForPrd.getArtId())).get();
				} catch (Exception ex) {
					throw new InventoryNotFoundException("Product inventor not found :: " + artForPrd.getArtId());
				}

				// get the existing ProductsInventoryDetails from DB
				Optional<ProductsInventoryDetails> prdInvDetailsOp = Optional
						.ofNullable(prdInvRepo.findByInventoryDetailsAndProductDetails(invDetails, prdDetails));

				if (prdInvDetailsOp.isPresent())
					prdInvDetails = prdInvDetailsOp.get();
				else {
					prdInvDetails = new ProductsInventoryDetails();
					prdInvDetails.setProductDetails(prdDetails);
					prdInvDetails.setInventoryDetails(invDetails);
				}
				prdInvDetails.setAmountOf(Integer.parseInt(artForPrd.getAmountOf()));

				prdInvDetailsList.add(prdInvDetails);
			}
			prdDetails.setProductsInventoryDetails(prdInvDetailsList);

			// save ProductsInventoryDetails
			prdInvRepo.saveAll(prdInvDetailsList);
		}
	}

	public List<Products> getProducts() {
		List<ProductDetails> prdDetailsList = prdRepo.findAll();
		List<Products> prdList = new ArrayList<Products>();

		for (ProductDetails prdDetails : prdDetailsList) {
			Products prd = modelMapper.map(prdDetails, Products.class);
			prdList.add(prd);
		}

		return prdList;
	}

	public void sellProduct(String productName, int quantity) {
		Optional<ProductDetails> prdDetailsOp = Optional.ofNullable(prdRepo.findByName(productName));

		// Check product in DB
		if (prdDetailsOp.isPresent()) {
			ProductDetails prdDetails = prdDetailsOp.get();

			List<ProductsInventoryDetails> prdInvDetailsList = prdDetails.getProductsInventoryDetails();
			List<InventoryDetails> invDetailsList = new ArrayList<InventoryDetails>();
			for (ProductsInventoryDetails prdInvDetails : prdInvDetailsList) {
				InventoryDetails invDetails = prdInvDetails.getInventoryDetails();
				int amountOf = prdInvDetails.getAmountOf();
				int stock = invDetails.getStock();

				// find the difference and calculate remaining available stock
				int remaingStock = stock - (amountOf * quantity);

				if (remaingStock >= 0) {
					invDetails.setStock(remaingStock);
					invDetailsList.add(invDetails);
				} else {
					throw new ProductNoStockException("Required Qunatity exceeding the available Stock");
				}
			}

			// Save the updated inventory in DB
			invRepo.saveAll(invDetailsList);
		} else
			throw new ProductNotFoundException("No product found of name :: " + productName);

	}

}
