package com.warehouse.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.warehouse.entities.ProductDetails;

@Repository
public interface ProductRepository extends JpaRepository<ProductDetails, Integer> {

	public ProductDetails findByName(String name);
}
