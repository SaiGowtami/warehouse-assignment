package com.warehouse.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.warehouse.entities.InventoryDetails;
import com.warehouse.entities.ProductDetails;
import com.warehouse.entities.ProductsInventoryDetails;

@Repository
public interface ProductsInventoryRepository extends JpaRepository<ProductsInventoryDetails, Integer> {
	public ProductsInventoryDetails findByInventoryDetailsAndProductDetails(InventoryDetails inventoryDetails,
			ProductDetails productDetails);
}
