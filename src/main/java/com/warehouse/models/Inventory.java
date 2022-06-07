package com.warehouse.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Inventory {

	@JsonProperty("art_id")
	private String artId;
	private String name;
	private String stock;

	public void setArtId(String artId) {
		this.artId = artId;
	}

	public String getArtId() {
		return artId;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setStock(String stock) {
		this.stock = stock;
	}

	public String getStock() {
		return stock;
	}

}
