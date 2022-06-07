package com.warehouse.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "product_details")
public class ProductDetails implements Serializable {

	private static final long serialVersionUID = 8435894195245356953L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "prd_id", unique = true, nullable = false)
	private int prdId;

	@Column(name = "equal_amount_of", nullable = false)
	private int equalAmountOf;

	@Column(nullable = false, length = 50)
	private String name;

	@Column(name = "possible_amount_of", nullable = false)
	private int possibleAmountOf;

	@Column
	private String price;

	// bi-directional many-to-one association to ProductsInventoryDetail
	@OneToMany(mappedBy = "productDetails")
	private List<ProductsInventoryDetails> productsInventoryDetails;

	public int getPrdId() {
		return this.prdId;
	}

	public void setPrdId(int prdId) {
		this.prdId = prdId;
	}

	public int getEqualAmountOf() {
		return this.equalAmountOf;
	}

	public void setEqualAmountOf(int equalAmountOf) {
		this.equalAmountOf = equalAmountOf;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public int getPossibleAmountOf() {
		return this.possibleAmountOf;
	}

	public void setPossibleAmountOf(int possibleAmountOf) {
		this.possibleAmountOf = possibleAmountOf;
	}

	public List<ProductsInventoryDetails> getProductsInventoryDetails() {
		return this.productsInventoryDetails;
	}

	public void setProductsInventoryDetails(List<ProductsInventoryDetails> productsInventoryDetails) {
		this.productsInventoryDetails = productsInventoryDetails;
	}

}