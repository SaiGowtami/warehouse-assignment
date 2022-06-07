package com.warehouse.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.warehouse.entities.InventoryDetails;

@Repository
public interface InventoryRepository extends JpaRepository<InventoryDetails, Integer> {

}
