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
@Table(name="approval_request")
public class ApprovalRequest implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6115735550356500220L;
	
	@Column(name="apprvl_req_id")
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "apprvl_req_seq")
	@SequenceGenerator(name="apprvl_req_seq",  initialValue = 1, allocationSize = 1, sequenceName = "apprvl_req_seq")
	private Long approvalReqId;
	
	@Column(name="prdct_id")
	private Long productId;
	
	@Column(name="prdct_name")
	private String prdctName;
	
	@Column(name="prdct_price")
	private BigDecimal prdctPrice;
	
	@Column(name="prdct_sts")
	private String prdctSts;
	
	@Column(name="apprvl_req_type")
	private String apprvlReqType;
	
	@Column(name="apprvl_init_dt")
	private Date apprvlInitDt;
	
	@Column(name="apprvl_init_by")
	private String apprvlInitBy;
	
	@Column(name="apprvl_sts")
	private String apprvlSts;
	
	@Column(name="action_dt")
	private Date actionDt;
	
	@Column(name="action_by")
	private String actionBy;
	
	@Column(name="rjct_rsn")
	private String rejectRsn;
	
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

	public Long getApprovalReqId() {
		return approvalReqId;
	}

	public void setApprovalReqId(Long approvalReqId) {
		this.approvalReqId = approvalReqId;
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public String getPrdctName() {
		return prdctName;
	}

	public void setPrdctName(String prdctName) {
		this.prdctName = prdctName;
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

	public String getApprvlReqType() {
		return apprvlReqType;
	}

	public void setApprvlReqType(String apprvlReqType) {
		this.apprvlReqType = apprvlReqType;
	}

	public Date getApprvlInitDt() {
		return apprvlInitDt;
	}

	public void setApprvlInitDt(Date approvalInitDt) {
		this.apprvlInitDt = approvalInitDt;
	}

	public String getApprvlInitBy() {
		return apprvlInitBy;
	}

	public void setApprvlInitBy(String apprvlInitBy) {
		this.apprvlInitBy = apprvlInitBy;
	}

	public String getApprvlSts() {
		return apprvlSts;
	}

	public void setApprvlSts(String apprvlSts) {
		this.apprvlSts = apprvlSts;
	}

	public Date getActionDt() {
		return actionDt;
	}

	public void setActionDt(Date actionDt) {
		this.actionDt = actionDt;
	}

	public String getActionBy() {
		return actionBy;
	}

	public void setActionBy(String actionBy) {
		this.actionBy = actionBy;
	}

	public String getRejectRsn() {
		return rejectRsn;
	}

	public void setRejectRsn(String rejectRsn) {
		this.rejectRsn = rejectRsn;
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
