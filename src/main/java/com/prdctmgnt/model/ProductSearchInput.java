package com.prdctmgnt.model;

import java.math.BigDecimal;
import java.sql.Date;


public class ProductSearchInput {
	
	private String productName;
	private BigDecimal minPrice;
	private BigDecimal maxPrice;
	private Date minPostedDate;
	private Date maxPostedDate;
	
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public BigDecimal getMinPrice() {
		return minPrice;
	}
	public void setMinPrice(BigDecimal minPrice) {
		this.minPrice = minPrice;
	}
	public BigDecimal getMaxPrice() {
		return maxPrice;
	}
	public void setMaxPrice(BigDecimal maxPrice) {
		this.maxPrice = maxPrice;
	}
	public Date getMinPostedDate() {
		return minPostedDate;
	}
	public void setMinPostedDate(Date minPostedDate) {
		this.minPostedDate = minPostedDate;
	}
	public Date getMaxPostedDate() {
		return maxPostedDate;
	}
	public void setMaxPostedDate(Date maxPostedDate) {
		this.maxPostedDate = maxPostedDate;
	}

}
