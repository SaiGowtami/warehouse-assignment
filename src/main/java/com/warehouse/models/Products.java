package com.warehouse.models;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Products {

	private String name;

	private String price;

	@JsonProperty("contain_articles")
	@JsonInclude(Include.NON_NULL)
	private List<ContainArticles> containArticles;

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public void setContainArticles(List<ContainArticles> containArticles) {
		this.containArticles = containArticles;
	}

	public List<ContainArticles> getContainArticles() {
		return containArticles;
	}

}