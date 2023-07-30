package com.prdctmgnt.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;


@Entity
@Table(name="product")
public class Product implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6540989466666053027L;

	@Column(name="product_id")
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "product_seq")
	@SequenceGenerator(name="product_seq", initialValue = 1, allocationSize = 1,sequenceName = "product_seq" )
	private Long productId;
	
	@Column(name="skuId")
	private String skuId;
	
	@Column(name="prdct_name")
	private String prdctName;
	
	@Column(name="prdct_desc")
	private String prdctDesc;
	
	@Column(name="prdct_color")
	private String prdctColor;
	
	@Column(name="prdct_price")
	private BigDecimal prdctPrice;
	
	@Column(name="prdct_sts")
	private String prdctSts;
	
	@Column(name="prdct_post_dt")
	private Date prdctPostDt;
	
	@Column(name="prdct_apprvd_dt")
	private Date prdctApprvdDt;
	
	@Column(name="prdct_apprvd_by")
	private String prdctApprvdBy;
	
	@Column(name="prdct_features")
	private String prdctFeatures;
	
	@Column(name="prdct_notes")
	private String prdctNotes;
	
	@Column(name="prdct_in_stock")
	private String prdctInStock;
	
	@Column(name="created_dt")
	private Timestamp createdDt;
	
	@Column(name="created_by")
	private String createdBy;
	
	@Column(name="updated_by")
	private String updatedBy;
	
	@Column(name="updated_dt")
	private Timestamp updatedDt;
	
	@Column(name="isdeleted")
	private String isDeleted;

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public String getSkuId() {
		return skuId;
	}

	public void setSkuId(String skuId) {
		this.skuId = skuId;
	}

	public String getPrdctName() {
		return prdctName;
	}

	public void setPrdctName(String prdctName) {
		this.prdctName = prdctName;
	}

	public String getPrdctDesc() {
		return prdctDesc;
	}

	public void setPrdctDesc(String prdctDesc) {
		this.prdctDesc = prdctDesc;
	}

	public String getPrdctColor() {
		return prdctColor;
	}

	public void setPrdctColor(String prdctColor) {
		this.prdctColor = prdctColor;
	}

	public BigDecimal getPrdctPrice() {
		return prdctPrice;
	}

	public void setPrdctPrice(BigDecimal prdctPrice) {
		this.prdctPrice = prdctPrice;
	}

	public String getPrdctSts() {
		return prdctSts;
	}

	public void setPrdctSts(String prdctSts) {
		this.prdctSts = prdctSts;
	}

	public Date getPrdctPostDt() {
		return prdctPostDt;
	}

	public void setPrdctPostDt(Date prdctPostDt) {
		this.prdctPostDt = prdctPostDt;
	}

	public Date getPrdctApprvdDt() {
		return prdctApprvdDt;
	}

	public void setPrdctApprvdDt(Date prdctApprvdDt) {
		this.prdctApprvdDt = prdctApprvdDt;
	}

	public String getPrdctApprvdBy() {
		return prdctApprvdBy;
	}

	public void setPrdctApprvdBy(String prdctApprvdBy) {
		this.prdctApprvdBy = prdctApprvdBy;
	}

	public String getPrdctFeatures() {
		return prdctFeatures;
	}

	public void setPrdctFeatures(String prdctFeatures) {
		this.prdctFeatures = prdctFeatures;
	}

	public String getPrdctNotes() {
		return prdctNotes;
	}

	public void setPrdctNotes(String prdctNotes) {
		this.prdctNotes = prdctNotes;
	}

	public String getPrdctInStock() {
		return prdctInStock;
	}

	public void setPrdctInStock(String prdctInStock) {
		this.prdctInStock = prdctInStock;
	}

	public Timestamp getCreatedDt() {
		return createdDt;
	}

	public void setCreatedDt(Timestamp createdDt) {
		this.createdDt = createdDt;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	public Timestamp getUpdatedDt() {
		return updatedDt;
	}

	public void setUpdatedDt(Timestamp updatedDt) {
		this.updatedDt = updatedDt;
	}

	public String getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(String isDeleted) {
		this.isDeleted = isDeleted;
	}
	

}
