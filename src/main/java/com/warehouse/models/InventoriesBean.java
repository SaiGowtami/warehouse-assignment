package com.warehouse.models;

import java.util.List;

public class InventoriesBean {

	private List<Inventory> inventory;

	public void setInventory(List<Inventory> inventory) {
		this.inventory = inventory;
	}

	public List<Inventory> getInventory() {
		return inventory;
	}

}