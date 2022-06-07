package com.warehouse.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "inventory_details")
public class InventoryDetails implements Serializable {

	private static final long serialVersionUID = 3269743987697633760L;

	@Id
	@Column(name = "art_id", unique = true, nullable = false)
	private int artId;

	@Column(nullable = false, length = 50)
	private String name;

	@Column(nullable = false)
	private int stock;

	// bi-directional many-to-one association to ProductsInventoryDetails
	@OneToMany(mappedBy = "inventoryDetails")
	private List<ProductsInventoryDetails> productsInventoryDetails;

	public int getArtId() {
		return this.artId;
	}

	public void setArtId(int artId) {
		this.artId = artId;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getStock() {
		return this.stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public List<ProductsInventoryDetails> getProductsInventoryDetails() {
		return this.productsInventoryDetails;
	}

	public void setProductsInventoryDetails(List<ProductsInventoryDetails> productsInventoryDetails) {
		this.productsInventoryDetails = productsInventoryDetails;
	}

}