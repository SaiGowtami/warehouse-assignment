package com.warehouse.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "products_inventory_details")
public class ProductsInventoryDetails implements Serializable {

	private static final long serialVersionUID = -2523191143328361952L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(unique = true, nullable = false)
	private int id;

	@Column(name = "amount_of", nullable = false)
	private int amountOf;

	// bi-directional many-to-one association to InventoryDetails
	@ManyToOne
	@JoinColumn(name = "art_id")
	private InventoryDetails inventoryDetails;

	// bi-directional many-to-one association to ProductDetails
	@ManyToOne
	@JoinColumn(name = "prd_id")
	private ProductDetails productDetails;

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getAmountOf() {
		return this.amountOf;
	}

	public void setAmountOf(int amountOf) {
		this.amountOf = amountOf;
	}

	public InventoryDetails getInventoryDetails() {
		return this.inventoryDetails;
	}

	public void setInventoryDetails(InventoryDetails inventoryDetails) {
		this.inventoryDetails = inventoryDetails;
	}

	public ProductDetails getProductDetails() {
		return this.productDetails;
	}

	public void setProductDetails(ProductDetails productDetails) {
		this.productDetails = productDetails;
	}

}